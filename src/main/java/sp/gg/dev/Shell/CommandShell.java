package sp.gg.dev.Shell;

import net.dv8tion.jda.api.entities.Activity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import sp.gg.dev.bot.DiscordAPI;

@ShellComponent
public class CommandShell {

    @ShellMethod(value = "hello world", key = "hi")
    public String hello(@ShellOption(defaultValue = "world!") String text) {
        return "hello " + text;
    }

    @ShellMethod(value = "sum numbers")
    public String sum(int a, int b){
        return "sum = " + (a + b);
    }

    @ShellMethod(value = "Enable Discord BOT Service", key = "-startbot")
    public void EnableBot() {
        DiscordAPI.Start();
    }

    @ShellMethod(value = "Enable Discord BOT Service", key = "-stopbot")
    public void DisableBot() {
        DiscordAPI.disableBot();
    }

    @ShellMethod(value = "Change BOT Status", key = "-changeStatus")
    public void ChangeStatus(String statusMessage) {
        DiscordAPI.setActivity(Activity.competing(statusMessage));
    }
}