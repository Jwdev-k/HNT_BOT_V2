package sp.gg.dev.api.minigame;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RPS {

    private static final ConcurrentHashMap<String,Record> record = new ConcurrentHashMap<>();

    public static void init(ButtonInteractionEvent event) {
        String[] rps = new String[]{"가위", "바위", "보"};
        int num = (int) Math.round(Math.random() * (rps.length - 1));
        String result = rps[num];
        String userResult = event.getButton().getLabel();
        String userName = event.getUser().getName();
        String userId = event.getUser().getId();

        Record userRecord;
        if(record.get(event.getUser().getId()) == null) { // 가위바위보 전적 없으면 추가.
            record.put(userId, new Record(userName, 0, 0, 0, 0));
        }
        userRecord = record.get(event.getUser().getId());

        // 가위바위보 승패 로직
        if (result.equals(event.getButton().getLabel())) {
            event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                    + "Hinata : " + result + "\n"
                    + "뭐야 비겼잖아 다시해!!").queue();
            userRecord.setInfo(0, 0, 1, 1);
        } else {
            if (userResult.equals("가위")) {
                if (result.equals("바위")) {
                    event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                            + "Hinata : " + result + "\n"
                            + "내가 이겼어 ㅎㅎ").queue();
                    userRecord.setInfo(1, 0, 0, 1);
                } else {
                    event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                            + "Hinata : " + result + "\n"
                            + "내가 졌어 ㅠㅠ").queue();
                    userRecord.setInfo(0, 1, 0, 1);
                }
            }
            if (userResult.equals("바위")) {
                if (result.equals("보")) {
                    event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                            + "Hinata : " + result + "\n"
                            + "내가 이겼어 ㅎㅎ").queue();
                    userRecord.setInfo(1, 0, 0, 1);
                } else {
                    event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                            + "Hinata : " + result + "\n"
                            + "내가 졌어 ㅠㅠ").queue();
                    userRecord.setInfo(0, 1, 0, 1);
                }
            }
            if (userResult.equals("보")) {
                if (result.equals("가위")) {
                    event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                            + "Hinata : " + result + "\n"
                            + "내가 이겼어 ㅎㅎ").queue();
                    userRecord.setInfo(1, 0, 0, 1);
                } else {
                    event.reply(userName + ": " + event.getButton().getLabel() + "\n"
                            + "Hinata : " + result + "\n"
                            + "내가 졌어 ㅠㅠ").queue();
                    userRecord.setInfo(0, 1, 0, 1);
                }
            }
        }
        log.info(record.get(event.getUser().getId()).toString());
    }

    public static Record getUserRpsRecord(String userID) {
        return record.get(userID);
    }
}
