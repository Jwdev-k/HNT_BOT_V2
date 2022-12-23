package sp.gg.dev.api;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

@Component @Slf4j
public class DiscordAPI {

    private static JDA jda;
    private final BotObject bot = new BotObject();

    public void setActivity(Activity activity, String token) {
        bot.setActivity(activity);
        jda = JDABuilder.createLight(token, EnumSet.noneOf(GatewayIntent.class)) // slash commands don't need any intents
                .addEventListeners(new BotCommand())
                .setActivity(bot.getActivity())
                .setStatus(bot.getOnlineStatus())
                .build();
    }

    public void disableBot() {
        jda.shutdown();
    }

    public void Start(String token) {
        jda = JDABuilder.createLight(token, EnumSet.noneOf(GatewayIntent.class)) // slash commands don't need any intents
                .addEventListeners(new BotCommand())
                .setActivity(bot.getActivity())
                .setStatus(bot.getOnlineStatus())
                .setIdle(bot.isIdle())
                .build();

        // These commands might take a few minutes to be active after creation/update/delete
        CommandListUpdateAction commands = jda.updateCommands();

        //System Version Info
        commands.addCommands(Commands.slash("버전정보", "현재 봇의 버전 정보를 조회합니다.")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_SERVER))
        );

        // Moderation commands with required options
        commands.addCommands(
                Commands.slash("사용자추방", "현재 서버에 있는 사용자를 추방합니다. (사용자를 추방 할수있는 권한이 필요합니다.)")
                        .addOptions(new OptionData(USER, "user", "추방할 사용자 지정.") // USER type allows to include members of the server or other users by id
                                .setRequired(true)) // This command requires a parameter
                        .addOptions(new OptionData(INTEGER, "del_days", "최근 메세지 삭제") // This is optional
                                .setRequiredRange(0, 7)) // Only allow values between 0 and 7 (inclusive)
                        .addOptions(new OptionData(STRING, "reason", "The ban reason to use (default: Banned by <user>)")) // optional reason
                        .setGuildOnly(true) // This way the command can only be executed from a guild, and not the DMs
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)) // Only members with the BAN_MEMBERS permission are going to see this command
        );

        // Simple reply commands
        commands.addCommands(
                Commands.slash("말", "Makes the bot say what you tell it to")
                        .addOption(STRING, "content", "What the bot should say", true) // you can add required options like this too
        );

        // Commands without any inputs
        commands.addCommands(
                Commands.slash("서버나가기", "Make the bot leave the server")
                        .setGuildOnly(true) // this doesn't make sense in DMs
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED) // only admins should be able to use this command.
        );

        commands.addCommands(
                Commands.slash("채팅청소", "현재 채널의 메세지를 정리할거야!")
                        .addOption(INTEGER, "amount", "삭제할 메세지수를 정해 최대는 200개야(Default 100)") // simple optional argument
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MESSAGE_MANAGE))
        );

        commands.addCommands(
                Commands.slash("프로필", "유저의 프로필 사진을 가져올게!")
                        .addOption(USER, "user", "현재 서버에 있는 유저를 지정해주세요", true)
                        .setGuildOnly(true)
        );

        commands.addCommands(
                Commands.slash("가위바위보", "^~^ Zzz...")
        );
        commands.addCommands(
                Commands.slash("주사위", "최대 숫자를 입력해줘! (1 ~ XXX)")
                        .addOption(INTEGER, "count", "원하는 값으로 설정해줘!", true)
        );

        // Send the new set of commands to discord, this will override any existing global commands with the new set provided here
        commands.queue();
    }
}
