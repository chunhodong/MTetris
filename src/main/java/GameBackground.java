/**
 * 게임배경데이터
 */
public class GameBackground {

    private int[][] background;

    public GameBackground(){
        init();
    }

    /**
     * 게임배경초기화
     */
    public void init(){
        this.background = new int[GameOption.BOARD_WIDTH][GameOption.BOARD_HEIGHT];

    }
}
