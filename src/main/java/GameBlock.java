import java.awt.*;
import java.util.Random;

/**
 * 게임블록데이터
 */
public class GameBlock {


    /*블록모양세트*/
    private int [][][] blockShapeSet;
    /*블록타입*/
    private int blockType;
    /*블록번호*/
    private int blockNumber;
    /*블록모양타입*/
    private final int blockTypeSize = 7;
    /*블록배열*/
    private int [][] blockArray;
    
    private Color[][] blockColorArray;

    private Color blockColor;


    public void GameBlock(){
        initBlock();
        initBlockArray();

    }

    /**
     * 게임블록초기화
     */
    public void initBlock(){
        initBlockShape();
        initBlockColor();

    }

    /**
     * 게임블록모양초기화
     */
    private void initBlockShape(){
        blockType = RandomUtils.nextInt(blockTypeSize);
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
                                {0,1,0,0},
                                {0,1,1,0},
                                {0,1,0,0},
                                {0,0,0,0}

                        }
                };
                break;
            case 3:
                blockShapeSet=new int[][][]{
                        {
                                {0,0,1,0},
                                {0,1,1,0},
                                {0,1,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,1,1,0},
                                {0,0,1,1},
                                {0,0,0,0},
                                {0,0,0,0}
                        }
                };
                break;
            case 4:
                blockShapeSet=new int[][][]{
                        {
                                {0,1,0,0},
                                {0,1,1,0},
                                {0,0,1,0},
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
                                {0,1,0,0},
                                {0,1,1,1},
                                {0,0,0,0},
                                {0,0,0,0}
                        },
                        {
                                {0,0,1,0},
                                {0,0,1,0},
                                {0,1,1,0},
                                {0,0,0,0}
                        }
                };
                break;
            case 6:
                blockShapeSet=new int[][][]{
                        {
                                {0,1,1,1},
                                {0,1,0,0},
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
                                {0,1,1,0},
                                {0,0,1,0},
                                {0,0,1,0},
                                {0,0,0,0}
                        }
                };
                break;
        }
        blockNumber = RandomUtils.nextInt(blockShapeSet.length);
    }

    /**
     * 게임블록색초기화
     */
    private void initBlockColor(){
        Color[] colorArray = new Color[]{Color.BLUE,Color.ORANGE,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW};
        blockColor = colorArray[new Random().nextInt(6)];
    }

    /**
     * 블록배열초기화
     */
    public void initBlockArray(){
        blockArray = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
        int[][] blockShape = blockShapeSet[blockNumber];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    blockArray[i][j + 4] = 1;
                    blockColorArray[i][j + 4] = blockColor;
                }
            }
        }
    }

    public void rotateBlock(){

    }

}
