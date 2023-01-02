package sp.gg.dev.api.minigame;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

@Slf4j
public class RPS {

    private static final Record record = new Record("가위바위보 INFO", 0 ,0, 0);

    public static void init(ButtonInteractionEvent event) {
        String[] rps = new String[]{"가위", "바위", "보"};
        int num = (int) Math.round(Math.random() * (rps.length - 1));
        String result = rps[num];
        String userResult = event.getButton().getLabel();
        String userID = event.getUser().getName();
        if (result.equals(event.getButton().getLabel())) {
            event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                    + "Hinata : " + result + "\n"
                    + "뭐야 비겼잖아 다시해!!").queue();
        }
        if (userResult.equals("가위")) {
            if (result.equals("바위")) {
                event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                        + "Hinata : " + result + "\n"
                        + "내가 이겼어 ㅎㅎ").queue();
                record.setInfo(1,0, 1);
            } else {
                event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                        + "Hinata : " + result + "\n"
                        + "내가 졌어 ㅠㅠ").queue();
                record.setInfo(0,1, 1);
            }
        }
        if (userResult.equals("바위")) {
            if (result.equals("보")) {
                event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                        + "Hinata : " + result + "\n"
                        + "내가 이겼어 ㅎㅎ").queue();
                record.setInfo(1,0, 1);
            } else {
                event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                        + "Hinata : " + result + "\n"
                        + "내가 졌어 ㅠㅠ").queue();
                record.setInfo(0,1, 1);
            }
        }
        if (userResult.equals("보")) {
            if (result.equals("가위")) {
                event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                        + "Hinata : " + result + "\n"
                        + "내가 이겼어 ㅎㅎ").queue();
                record.setInfo(1,0, 1);
            } else {
                event.editMessage(userID + ": " + event.getButton().getLabel() + "\n"
                        + "Hinata : " + result + "\n"
                        + "내가 졌어 ㅠㅠ").queue();
                record.setInfo(0,1, 1);
            }
        }
        log.info(record.toString());
    }
}
