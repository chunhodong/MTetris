package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.TetrisOption.BOARD_HEIGHT;
import static model.TetrisOption.BOARD_WIDTH;

public class TetrisCurrentBlock extends TetrisBlock{



    public enum Direction{
        LEFT(-1),RIGHT(1),DOWN(1);

        Integer direction;
        Direction(Integer direction){
            this.direction = direction;
        }

        public Integer getDirectionValue(){
            return this.direction;
        }

        public boolean isHorizontal(){
            return this == LEFT || this == RIGHT;
        }
    }
    /*블록컬러배열*/

    /*초기X위치*/
    private int initMaxX;

    /*초기Y위치*/
    private int initMaxY;

    /*처음블록배치시 게임화면에서 오른쪽으로 이동할 거리*/
    private static final int OFFSET_Y = 4;



    public TetrisCurrentBlock(){
    }


    @Override
    public void initBlock() {
        super.initBlock();
        initBlockColorPosition();
        initBlockElementPosition();
    }

    /**
     * 게임블록색초기화
     */
    private void initBlockColorPosition(){
        this.blockColorPosition = new Color[BOARD_HEIGHT][BOARD_WIDTH];
        int[][] blockShape = this.blockShapeSet[this.blockNumber];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockColorPosition[i][j + OFFSET_Y] = this.blockColor;
                }
            }
        }
    }

    /**
     * 블록배열초기화
     */
    public void initBlockElementPosition(){
        this.blockElementPosition = new int[BOARD_HEIGHT][BOARD_WIDTH];
        int[][] blockShape = this.blockShapeSet[this.blockNumber];


        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockElementPosition[i][j + OFFSET_Y] = 1;
                    this.initMaxX = Math.max(this.initMaxX,i);
                    this.initMaxY = Math.max(this.initMaxY,j + OFFSET_Y);
                }
            }
        }

    }


    /**
     * 현재 블록위치값 조회
     * @return 블록위치배열
     */
    public List<Point> getCurrentBlockPosition(){
        List<Point> pointList = new ArrayList<>();
        for(int i = 0; i < blockElementPosition.length; i++){
            for(int j = 0; j < blockElementPosition[i].length; j++){
                if(blockElementPosition[i][j] == 1){
                    pointList.add(new Point(i,j));
                }
            }
        }
        return pointList;
    }


    /**
     * 입력방향으로 이동한 블록위치 조회
     * @param direction 이동방향(좌,우,아래)
     * @return 블록위치배열
     */
    public ArrayList<Point> getMovablePosition(Direction direction){
        ArrayList<Point> pointList = new ArrayList<>();
        for(int i = 0; i < blockElementPosition.length; i++){
            for(int j = 0; j < blockElementPosition[i].length; j++){
                if(blockElementPosition[i][j] == 1){
                    System.out.println();

                    Point point = direction.isHorizontal()
                            ? new Point(i,j+direction.getDirectionValue())
                            : new Point(i + direction.getDirectionValue(),j);
                    pointList.add(point);
                }
            }
        }
        return pointList;
    }

    private void clearCurrentBlocks(){

        for(int i = 0; i < blockElementPosition.length; i++){
            for(int j = 0; j < blockElementPosition[i].length; j++){
                this.blockElementPosition[i][j] = 0;
                this.blockColorPosition[i][j] = null;

            }
        }
    }



    /**
     * 입력값으로 블록위치이동
     * @param points 블록위치배열
     */
    public void moveBlock(List<Point> points){
        clearCurrentBlocks();
        points.stream().forEach(point -> {
            this.blockElementPosition[(int)point.getX()][(int)point.getY()] = 1;
            this.blockColorPosition[(int)point.getX()][(int)point.getY()] = this.blockColor;

        });

    }

    /**
     * 회전시 이동좌표 구하기
     * @return 회전시 이동좌표
     */
    public ArrayList<Point> getRotatablePosition() {
        int rotateBlockNumber = ( this.blockNumber + 1 ) % this.blockShapeSet.length;
        int currentMaxX = 0;
        int currentMaxY = 0;
        for(int i = 0; i < blockElementPosition.length; i++){
            for(int j = 0; j < blockElementPosition[i].length; j++){
                if(this.blockElementPosition[i][j] == 1){
                    currentMaxX = Math.max(currentMaxX,i);
                    currentMaxY = Math.max(currentMaxY,j);

                }
            }
        }

        int distanceX = currentMaxX - initMaxX;
        int distanceY = currentMaxY - initMaxY;

        int[][] blockShape = this.blockShapeSet[rotateBlockNumber];
        ArrayList<Point> points  = new ArrayList<>();
        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    points.add(new Point(i + distanceX,j + OFFSET_Y + distanceY));
                }
            }
        }
        return points;
    }

    /**
     * 기존블록 회전시키키
     * @param points 회전된 블록위치 배열
     */
    public void rotateBlock(ArrayList<Point> points) {
        this.blockNumber = ( this.blockNumber + 1 ) % this.blockShapeSet.length;

        int[][] blockShape = this.blockShapeSet[this.blockNumber];
        this.initMaxX = 0;
        this.initMaxY = 0;

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.initMaxX = Math.max(this.initMaxX,i);
                    this.initMaxY = Math.max(this.initMaxY,j + OFFSET_Y);
                }
            }
        }


        moveBlock(points);

    }

    /**
     * 다음블록을 현재블록으로 교체
     */
    public void requestNewBlock(TetrisNextBlock nextBlock){
        clearCurrentBlocks();
        this.initMaxY = 0;
        this.initMaxX = 0;
        this.blockShapeSet = nextBlock.blockShapeSet;
        this.blockNumber = nextBlock.blockNumber;
        this.blockColor = nextBlock.blockColor;
        initBlockColorPosition();
        initBlockElementPosition();
        //initNextBlock();


    }


    /**
     * 현재 블록색상
     * @return 블록생상
     */
    public Color getCurrentBlockColor(){
        return this.blockColor;
    }

}
