package sp.gg.dev.Shell;

import net.dv8tion.jda.api.entities.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import sp.gg.dev.api.DiscordAPI;

@ShellComponent
public class CommandShell {
    private final DiscordAPI discordAPI = new DiscordAPI();
    @Value("${discord.token.key}")
    private String token;

    @ShellMethod(value = "hello world", key = "-hello")
    public String hello(@ShellOption(defaultValue = "world!") String text) {
        return "hello " + text;
    }

    @ShellMethod(value = "sum numbers", key = "-sum")
    public String sum(int a, int b){
        return "sum = " + (a + b);
    }

    @ShellMethod(value = "Enable Discord BOT Service", key = "-startbot")
    public void EnableBot() {
        discordAPI.Start(token);
    }

    @ShellMethod(value = "Disable Discord BOT Service", key = "-stopbot")
    public void DisableBot() {
        discordAPI.disableBot();
    }

    @ShellMethod(value = "Change BOT Status Message", key = "-changeStatus")
    public void ChangeStatus(String statusMessage) {
        discordAPI.setActivity(Activity.competing(statusMessage), token);
    }
}