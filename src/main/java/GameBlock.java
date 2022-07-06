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
    private int [][] blockElementPosition;
    
    private Color[][] blockColorPosition;



    public GameBlock(){
        initBlock();
    }

    /**
     * 게임블록초기화
     */
    public void initBlock(){
        initBlockShape();
        initBlockColorPosition();
        initBlockElementPosition();

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
    private void initBlockColorPosition(){
        this.blockColorPosition = new Color[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];

        Color[] colorArray = new Color[]{Color.BLUE,Color.ORANGE,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW};
        Color blockColor = colorArray[new Random().nextInt(6)];
        int[][] blockShape = this.blockShapeSet[this.blockNumber];


        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockColorPosition[i][j + 3] = blockColor;
                }
            }
        }





    }

    /**
     * 블록배열초기화
     */
    public void initBlockElementPosition(){
        this.blockElementPosition = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
        int[][] blockShape = this.blockShapeSet[this.blockNumber];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockElementPosition[i][j + 3] = 1;
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
     * 블록색상값 응답
     * @param i 블록X좌표
     * @param j 블록Y좌표
     * @return 해당위치에 블록 색상값
     */
    public Color getBlockColor(int i,int j){
        
        return this.blockColorPosition[i][j];
    }
    
    



}
