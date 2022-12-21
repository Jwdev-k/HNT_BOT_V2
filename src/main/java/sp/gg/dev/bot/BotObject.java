package sp.gg.dev.bot;

import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

@NoArgsConstructor
@Getter @Setter
public class BotObject {
    public String token = "NDg3ODk3NDIxNDAyMDc5MjMz.GQLpfI.N96Rq5Zfp5ekLVjYkquzjYxIqwUFITDkaxyYDE";
    private Activity activity = Activity.competing("현재 개발 작업중");
    private OnlineStatus onlineStatus = OnlineStatus.DO_NOT_DISTURB;
}
