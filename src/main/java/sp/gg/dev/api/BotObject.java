package sp.gg.dev.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

@RequiredArgsConstructor
@Getter @Setter
public class BotObject {
    private Activity activity = Activity.competing("현재 개발 작업중");
    private OnlineStatus onlineStatus = OnlineStatus.DO_NOT_DISTURB;
    private boolean idle = false; // AFK 표시여부
}
