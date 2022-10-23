package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.TetrisOption.BOARD_COLUMN_SIZE;
import static model.TetrisOption.BOARD_ROW_SIZE;


public class TetrisCurrentBlock extends TetrisBlock{



    public enum Direction{
        LEFT(-1),RIGHT(1),DOWN(1);

        final Integer direction;
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

    private Point startEdgePoint;

    private static final int OFFSET_Y = 4;

    public TetrisCurrentBlock(){
    }


    @Override
    public void init() {
        super.init();
        initPosition();
    }

    @Override
    protected int getOffetY() {
        return OFFSET_Y;
    }

    /**
     * 블록배열초기화
     */
    private void initPosition(){
        this.positionMap = new int[BOARD_ROW_SIZE][BOARD_COLUMN_SIZE];
        int[][] blockShape = this.shapeSet[this.number];


        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.positionMap[i][j + OFFSET_Y] = 1;
                }
            }
        }

        startEdgePoint = getEdgePoint(this.positionMap);

    }

    /**
     * 현재 블록위치값 조회
     * @return 블록위치배열
     */
    public List<Point> getCurrentPoints(){
        List<Point> pointList = new ArrayList<>();
        for(int i = 0; i < positionMap.length; i++){
            for(int j = 0; j < positionMap[i].length; j++){
                if(positionMap[i][j] == 1){
                    pointList.add(new Point(i,j));
                }
            }
        }
        return pointList;
    }


    /**
     * 블록가장자리 좌표조회
     * @param blockPositionMap 블록 위치값
     * @return
     */
    public Point getEdgePoint(int[][] blockPositionMap){

        Point point = new Point();
        for(int i = 0; i < blockPositionMap.length; i++){
            for(int j = 0; j < blockPositionMap[i].length; j++){
                if(blockPositionMap[i][j] == 1){
                    point.x = Math.max(point.x,i);
                    point.y = Math.max(point.y,j);

                }
            }
        }

        return point;


    }
    /**
     * 입력방향으로 이동한 블록위치 조회
     * @param direction 이동방향(좌,우,아래)
     * @return 블록위치배열
     */
    public ArrayList<Point> getMovablePoints(Direction direction){
        ArrayList<Point> pointList = new ArrayList<>();
        for(int i = 0; i < positionMap.length; i++){
            for(int j = 0; j < positionMap[i].length; j++){
                if(positionMap[i][j] == 1){
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

    /**
     * 회전시 이동좌표 구하기
     * @return 회전시 이동좌표
     */
    public ArrayList<Point> getRotatablePoints() {
        int rotateBlockNumber = ( this.number + 1 ) % this.shapeSet.length;
        Point currentEdgePoint = getEdgePoint(this.positionMap);
        int distanceX = currentEdgePoint.x - startEdgePoint.x;
        int distanceY = currentEdgePoint.y - startEdgePoint.y;

        int[][] blockShape = this.shapeSet[rotateBlockNumber];
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
     * 블록위치,컬러위치 삭제
     */
    private void clear(){

        for(int i = 0; i < positionMap.length; i++){
            for(int j = 0; j < positionMap[i].length; j++){
                this.positionMap[i][j] = 0;
                this.colorMap[i][j] = null;
            }
        }

    }

    /**
     * 입력값으로 블록위치이동
     * @param points 블록위치배열
     */
    public void move(List<Point> points){
        clear();
        points.forEach(point -> {
            this.positionMap[(int)point.getX()][(int)point.getY()] = 1;
            this.colorMap[(int)point.getX()][(int)point.getY()] = this.color;

        });

    }

    /**
     * 기존블록 회전시키키
     * @param points 회전된 블록위치 배열
     */
    public void rotate(ArrayList<Point> points) {
        this.number = ( this.number + 1 ) % this.shapeSet.length;

        int[][] blockShape = this.shapeSet[this.number];
        this.startEdgePoint = getEdgePoint(blockShape);
        this.startEdgePoint.y += OFFSET_Y;
        move(points);

    }

    /**
     * 다음블록을 현재블록으로 교체
     */
    public void change(TetrisNextBlock nextBlock){
        this.shapeSet = nextBlock.shapeSet;
        this.number = nextBlock.number;
        this.color = nextBlock.color;
        clear();
        initColor();
        initPosition();
    }


}
