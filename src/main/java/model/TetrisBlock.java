package model;

import utils.RandomUtils;

import java.awt.*;

import static model.TetrisOption.BOARD_COLUMN_SIZE;
import static model.TetrisOption.BOARD_ROW_SIZE;

/**
 * 게임블록데이터
 */
public abstract class TetrisBlock {
    protected int [][][] shapeSet;

    protected Color[][] colorMap;

    protected int [][] positionMap;

    protected int number;

    protected Color color;

    private static final int BLOCK_TYPE_SIZE = 7;
    private static final Color[] BLOCK_COLOR_TYPES = new Color[]{Color.BLUE,Color.ORANGE,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW};

    public void init(){
        this.shapeSet = createShape();
        this.number = RandomUtils.nextInt(shapeSet.length);
        this.color = BLOCK_COLOR_TYPES[ RandomUtils.nextInt(BLOCK_COLOR_TYPES.length)];
        this.colorMap = new Color[BOARD_ROW_SIZE][BOARD_COLUMN_SIZE];
        initColor();
    }

    /**
     * 블록생성하기
     * @return 블록좌표
     */
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


    /**
     * 블록컬러좌표조회
     * @return 블록컬러좌표
     */
    public Color[][] getColorMap() {
        return colorMap;
    }

    /**
     * 블록컬러값초기화
     */
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



    /**
     * 현재 블록색상
     * @return 블록생상
     */
    public Color getColor(){
        return this.color;
    }

    abstract protected int getOffetY();


}
