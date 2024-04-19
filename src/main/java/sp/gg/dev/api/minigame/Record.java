package sp.gg.dev.api.minigame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter @ToString
public class Record {
    private String name;
    private int win;
    private int lose;
    private int draw;
    private int playCount;

    public void setInfo(int win, int lose, int draw, int playCount) {
        this.win += win;
        this.lose += lose;
        this.draw += draw;
        this.playCount += playCount;
    }
}
