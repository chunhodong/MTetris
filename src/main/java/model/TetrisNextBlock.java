package model;

import java.awt.*;

public class TetrisNextBlock extends TetrisBlock{


    @Override
    public void initBlock() {
        super.initBlock();
        initBlockColorPosition();
    }


    /**
     * 게임블록색초기화
     */
    private void initBlockColorPosition(){
        this.blockColorPosition = new Color[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
        int[][] blockShape = this.blockShapeSet[this.blockNumber];

        for(int i = 0; i < blockShape.length; i++){
            for(int j = 0; j < blockShape.length; j++){
                if(blockShape[i][j] == 1){
                    this.blockColorPosition[i][j] = this.blockColor;
                }
            }
        }
    }

}
