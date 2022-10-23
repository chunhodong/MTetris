package view;

import model.TetrisOption;

import javax.swing.*;
import java.awt.*;

/**
 * 게임배경,게임블록등을 그래픽객체로 그려서 화면에출력
 */
public class TetrisNextBlockView extends JPanel {


    private Color[][] block;

    private static final int BLOCK_COLUMN_SIZE = 4;

    private static final int BLOCK_ROW_SIZE = 4;

    private static final int DISPLAY_WIDTH = 100;

    private static final int DISPLAY_HEIGHT = 100;

    private static final int X_POSITION = 285;

    private static final int Y_POSITION = 10;

    /**
     * 게임화면 위치/크기 초기화
     */
    public TetrisNextBlockView() {
        setBounds(X_POSITION,
                Y_POSITION,
                DISPLAY_WIDTH,
                DISPLAY_HEIGHT);
    }



    /**
     * 게임컴포넌트를 화면에 그림
     * @param g 그래픽객체
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintBlock(g);

    }

    /**
     * 그래픽객체가 게임화면을 그림
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g) {
        for (int i = 0; i < BLOCK_ROW_SIZE; i++) {
            for (int j = 0; j < BLOCK_COLUMN_SIZE; j++) {

                g.setColor(Color.BLACK);
                g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                g.setColor(Color.white);
                g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);

            }
        }
    }



    /**
     * 그래픽객체가 블록을 그림
     * @param g 그래픽객체
     */
    private void paintBlock(Graphics g) {

        if (this.block == null) return;

        for (int i = 0; i <  BLOCK_ROW_SIZE; i++) {
            for (int j = 0; j < BLOCK_COLUMN_SIZE; j++) {
                if(this.block[i][j] != null){
                    g.setColor(this.block[i][j]);
                    g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                    g.setColor(Color.white);
                    g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                }
            }
        }

    }

    public void updateNextBlock(Color[][] block) {
        this.block = block;
    }
}
