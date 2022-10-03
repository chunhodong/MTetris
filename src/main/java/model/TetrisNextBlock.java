package model;

import java.awt.*;

public class TetrisNextBlock extends TetrisBlock{


    @Override
    public void init() {
        super.init();
        initColor();
    }


    /**
     * 게임블록색초기화
     */
    private void initColor(){
        this.colorMap = new Color[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
        int[][] blockShape = this.shapeSet[this.number];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.colorMap[i][j] = this.color;
                }
            }
        }
    }

}
