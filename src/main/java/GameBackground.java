import java.awt.*;
import java.util.ArrayList;

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
        this.background = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];

    }

    /**
     * 게임판에서 블록이 추가될수있는지 체크
     * @param blockPoints 블록위치배열
     * @return 게임판추가여부
     */
    public boolean isEnableAdd(ArrayList<Point> blockPoints){
        return blockPoints.stream()
                .filter(point -> point.getY() < GameOption.BOARD_WIDTH)
                .filter(point -> point.getY() >= 0)
                .filter(point -> this.background[(int)point.getX()][(int)point.getY()] == 0)
                .count() == 4;

    }
}
