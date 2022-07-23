import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 게임블록데이터
 */
public class GameBlock {

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


    /*블록모양세트*/
    private int [][][] currentBlockShapeSet;
    /*블록번호*/
    private int currentBlockNumber;


    /*블록모양세트*/
    private int [][][] nextBlockShapeSet;
    /*블록번호*/
    private int nextBlockNumber;


    /*블록모양타입*/
    private final int blockTypeSize = 7;
    /*블록위치배열*/
    private int [][] blockElementPosition;
    /*블록컬러배열*/
    private Color[][] blockColorPosition;
    /*현재블록컬러*/
    private Color blockColor;
    /*초기X위치*/
    private int initMaxX;
    /*초기Y위치*/
    private int initMaxY;

    /*처음블록배치시 게임화면에서 오른쪽으로 이동할 거리*/
    private int offsetY = 4;

    public GameBlock(){
        initCurrentBlock();
        initNextBlock();
        
    }

    /**
     * 게임블록초기화
     */
    public void initCurrentBlock(){
        this.currentBlockShapeSet = createBlockShape();
        this.currentBlockNumber = RandomUtils.nextInt(currentBlockShapeSet.length);

        initBlockColorPosition();
        initBlockElementPosition();

    }

    /**
     * 다음블록초기화
     */
    public void initNextBlock(){
        this.nextBlockShapeSet = createBlockShape();
        this.nextBlockNumber = RandomUtils.nextInt(nextBlockShapeSet.length);

    }

    /**
     * 게임블록모양초기화
     */
    private int[][][] createBlockShape(){
        int blockType = RandomUtils.nextInt(blockTypeSize);
        int[][][] blockShapeSet = null;
        switch(blockType){
            case 0:
                blockShapeSet=new int[][][]{
                        {
                                {1,1,1,1},
                                {0,0,0,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,1,0,0},
                                {0,1,0,0},
                                {0,1,0,0},
                                {0,1,0,0},
                        }
                };
                break;
            case 1:
                blockShapeSet=new int[][][]{
                        {
                                {0,1,1,0},
                                {0,1,1,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        }
                };
                break;
            case 2:
                blockShapeSet=new int[][][]{
                        {
                                {0,1,0,0},
                                {1,1,1,0},
                                {0,0,0,0},
                                {0,0,0,0},

                        },
                        {
                                {0,1,0,0},
                                {1,1,0,0},
                                {0,1,0,0},
                                {0,0,0,0}

                        },
                        {
                                {1,1,1,0},
                                {0,1,0,0},
                                {0,0,0,0},
                                {0,0,0,0}

                        },
                        {
                                {1,0,0,0},
                                {1,1,0,0},
                                {1,0,0,0},
                                {0,0,0,0}

                        }
                };
                break;
            case 3:
                blockShapeSet=new int[][][]{
                        {
                                {0,1,0,0},
                                {1,1,0,0},
                                {1,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {1,1,0,0},
                                {0,1,1,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        }
                };
                break;
            case 4:
                blockShapeSet=new int[][][]{
                        {
                                {1,0,0,0},
                                {1,1,0,0},
                                {0,1,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,1,1,0},
                                {1,1,0,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        }
                };
                break;
            case 5:
                blockShapeSet=new int[][][]{
                        {
                                {1,1,1,0},
                                {0,0,1,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,1,1,0},
                                {0,1,0,0},
                                {0,1,0,0},
                                {0,0,0,0}
                        },
                        {
                                {1,0,0,0},
                                {1,1,1,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,1,0,0},
                                {0,1,0,0},
                                {1,1,0,0},
                                {0,0,0,0}
                        }
                };
                break;
            case 6:
                blockShapeSet=new int[][][]{
                        {
                                {1,1,1,0},
                                {1,0,0,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,1,0,0},
                                {0,1,0,0},
                                {0,1,1,0},
                                {0,0,0,0}
                        },
                        {
                                {0,0,1,0},
                                {1,1,1,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {1,1,0,0},
                                {0,1,0,0},
                                {0,1,0,0},
                                {0,0,0,0}
                        }
                };
                break;
        }
        return blockShapeSet;
        //blockNumber = RandomUtils.nextInt(blockShapeSet.length);

    }

    /**
     * 게임블록색초기화
     */
    private void initBlockColorPosition(){
        this.blockColorPosition = new Color[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
        Color[] colorArray = new Color[]{Color.BLUE,Color.ORANGE,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW};
        this.blockColor = colorArray[new Random().nextInt(6)];
        int[][] blockShape = this.currentBlockShapeSet[this.currentBlockNumber];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockColorPosition[i][j + offsetY] = this.blockColor;
                }
            }
        }
    }

    /**
     * 블록배열초기화
     */
    public void initBlockElementPosition(){
        this.blockElementPosition = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
        int[][] blockShape = this.currentBlockShapeSet[this.currentBlockNumber];


        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockElementPosition[i][j + offsetY] = 1;
                    this.initMaxX = Math.max(this.initMaxX,i);
                    this.initMaxY = Math.max(this.initMaxY,j + offsetY);
                }
            }
        }




    }

    /**
     * 입력값에 해당하는 배열위치에 블록값이 존재하는지여부
     * @param i 블록X좌표
     * @param j 블록Y좌표
     * @return  블록요소존재여부
     */
    public boolean hasBlockElement(int i,int j){
        return this.blockElementPosition[i][j] == 1;
    }


    /**
     * 현재 블록색상
     * @return 블록생상
     */
    public Color getCurrentBlockColor(){
        return this.blockColor;
    }

    /**
     * 현재 블록위치값 조회
     * @return 블록위치배열
     */
    public ArrayList<Point> getCurrentBlockPosition(){
        ArrayList<Point> pointList = new ArrayList<>();
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

    /**
     * 입력값으로 블록위치이동
     * @param points 블록위치배열
     */
    public void moveToBlock(ArrayList<Point> points){

        for(int i = 0; i < blockElementPosition.length; i++){
            for(int j = 0; j < blockElementPosition[i].length; j++){
                this.blockElementPosition[i][j] = 0;
                this.blockColorPosition[i][j] = null;

            }
        }
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
        int rotateBlockNumber = ( this.currentBlockNumber + 1 ) % this.currentBlockShapeSet.length;
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

        int[][] blockShape = this.currentBlockShapeSet[rotateBlockNumber];
        ArrayList<Point> points  = new ArrayList<>();
        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    points.add(new Point(i + distanceX,j + offsetY + distanceY));
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
        this.currentBlockNumber = ( this.currentBlockNumber + 1 ) % this.currentBlockShapeSet.length;

        int[][] blockShape = this.currentBlockShapeSet[this.currentBlockNumber];
        this.initMaxX = 0;
        this.initMaxY = 0;

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.initMaxX = Math.max(this.initMaxX,i);
                    this.initMaxY = Math.max(this.initMaxY,j + offsetY);
                }
            }
        }


        moveToBlock(points);

    }





}
