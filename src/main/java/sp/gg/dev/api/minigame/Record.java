package sp.gg.dev.api.minigame;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Record {
    private String name;
    private int win;
    private int lose;
    private int playCount;

    public void setInfo(int win, int lose, int playCount) {
        this.win += win;
        this.lose += lose;
        this.playCount += playCount;
    }
}
