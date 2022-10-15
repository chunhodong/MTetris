package model;

import utils.RandomUtils;

import java.awt.*;

/**
 * 게임블록데이터
 */
abstract class TetrisBlock {
    protected int [][][] shapeSet;

    /*블록컬러배열*/
    protected Color[][] colorMap;

    /*블록위치배열*/
    protected int [][] positionMap;

    /*블록번호*/
    protected int number;

    /*블록컬러*/
    protected Color color;


    /*블록타입*/
    private static final int BLOCK_TYPE_SIZE = 7;
    private static final Color[] BLOCK_COLOR_TYPES = new Color[]{Color.BLUE,Color.ORANGE,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW};

    public void init(){
        this.shapeSet = createShape();
        this.number = RandomUtils.nextInt(shapeSet.length);
        this.color = BLOCK_COLOR_TYPES[ RandomUtils.nextInt(BLOCK_COLOR_TYPES.length)];
        this.colorMap = new Color[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
        initColor();
    }

    private int[][][] createShape(){
        switch(RandomUtils.nextInt(BLOCK_TYPE_SIZE)){
            case 0:
                return new int[][][]{
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
            case 1:
                return new int[][][]{
                        {
                                {0,1,1,0},
                                {0,1,1,0},
                                {0,0,0,0},
                                {0,0,0,0}
                        }
                };
            case 2:
                return new int[][][]{
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
            case 3:
                return new int[][][]{
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
            case 4:
                return new int[][][]{
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
            case 5:
                return new int[][][]{
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
            case 6:
                return new int[][][]{
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
        }
        throw new IllegalArgumentException();

    }


    public Color[][] getColorMap() {
        return colorMap;
    }

    protected void initColor(){

        int[][] blockShape = this.shapeSet[this.number];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.colorMap[i][j + getOffetY()] = this.color;
                }
            }
        }

    }

    abstract protected int getOffetY();


}
