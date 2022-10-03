package model;

import utils.RandomUtils;

import java.awt.*;
import java.util.ArrayList;

/**
 * 게임블록데이터
 */
public class TetrisBlock {
    protected int [][][] blockShapeSet;

    protected Color[][] blockColorPosition;


    /*블록위치배열*/
    protected int [][] blockElementPosition;

    /*블록번호*/
    protected int blockNumber;

    protected Color blockColor;


    private static final int BLOCK_TYPE_SIZE = 7;
    private static final Color[] BLOCK_COLOR_TYPES = new Color[]{Color.BLUE,Color.ORANGE,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW};

    public void initBlock(){
        this.blockShapeSet = createBlockShape();
        this.blockNumber = RandomUtils.nextInt(blockShapeSet.length);
        this.blockColor = BLOCK_COLOR_TYPES[ RandomUtils.nextInt(BLOCK_COLOR_TYPES.length)];

    }

    private int[][][] createBlockShape(){
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


    public Color[][] getBlockColorMap() {
        return blockColorPosition;
    }


}
