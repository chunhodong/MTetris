import java.awt.*;
import java.util.ArrayList;

/**
 * 게임배경데이터
 */
public class GameBackground {

    private int[][] backgroundElement;
    private Color[][] backgroundColor;

    public GameBackground(){
        init();
    }

    /**
     * 게임배경초기화
     */
    public void init(){
        this.backgroundElement = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
        this.backgroundColor = new Color[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
        for(int i = 0; i < this.backgroundColor.length; i++){
            for(int j = 0; j < this.backgroundColor[i].length; j++){
                this.backgroundColor[i][j] = Color.BLACK;
            }
        }
    }

    /**
     * 게임판에서 블록 좌우이동여부 확인
     * @param blockPoints 블록위치배열
     * @return 블록좌우이동 가능여부
     */
    public boolean isMovable(ArrayList<Point> blockPoints){
        return blockPoints.stream()
                .filter(point -> point.getY() < GameOption.BOARD_WIDTH)
                .filter(point -> point.getY() >= 0)
                .filter(point -> this.backgroundElement[(int)point.getX()][(int)point.getY()] == 0)
                .count() == 4;

    }

    /**
     * 게임판에서 블록 하단이동여부 확인
     * @param blockPoints 블록위치배열
     * @return 블록하단이동여부
     */
    public boolean isAddible(ArrayList<Point> blockPoints){
        return blockPoints.stream()

                .filter(point -> point.getX() < GameOption.BOARD_HEIGHT)
                .filter(point -> this.backgroundElement[(int)point.getX()][(int)point.getY()] == 0)
                .count() == 4;

    }

    /**
     * 게임판에 블록을 배경으로 추가
     * @param blockPoints 블록위치배열
     * @param color 블록컬러
     */
    public void addBlock(ArrayList<Point> blockPoints, Color color) {
        blockPoints.stream()
                .forEach(point -> {
                    this.backgroundElement[(int)point.getX()][(int)point.getY()] = 1;
                    this.backgroundColor[(int)point.getX()][(int)point.getY()] = color;
                });

    }

    /**
     * 입력위치에 해당하는 배경컬러조회
     * @param i X좌표
     * @param j Y좌표
     * @return 배경컬러
     */
    public Color getColor(int i,int j){
        return this.backgroundColor[i][j];
    }
}
