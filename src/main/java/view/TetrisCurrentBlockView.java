package view;

import model.TetrisOption;

import javax.swing.*;
import java.awt.*;

/**
 * 게임배경,게임블록등을 그래픽객체로 그려서 화면에출력
 */
public class TetrisCurrentBlockView extends JPanel {


    private Color[][] background;

    private Color[][] block;

    /*게임판 넓이*/
    public static final int BLOCK_COLUMN_SIZE = 10;

    /*게임판 높이*/
    public static final int BLOCK_ROW_SIZE = 20;

    /*게임화면 넓이*/
    public static final int DISPLAY_WIDTH = 275;

    /*게임화면 높이*/
    public static final int DISPLAY_HEIGHT = 500;

    public static final int X_POSITION = 10;

    /*게임화면 시작Y좌표*/
    public static final int Y_POSITION = 10;

    /**
     * 게임화면 위치/크기 초기화
     */
    public TetrisCurrentBlockView() {
        setLayout(null);
        setBorder(null);

        setBounds(X_POSITION,
                Y_POSITION,
                DISPLAY_WIDTH,
                DISPLAY_HEIGHT);
    }


    /**
     * 게임컴포넌트를 화면에 그림
     *
     * @param g 그래픽객체
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintBlock(g);

    }

    /**
     * 그래픽객체가 게임화면을 그림
     *
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g) {

        for (int i = 0; i < BLOCK_ROW_SIZE; i++) {
            for (int j = 0; j < BLOCK_COLUMN_SIZE; j++) {

                g.setColor(this.background == null ? Color.BLACK : this.background[i][j]);
                g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                g.setColor(Color.white);
                g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);

            }
        }
    }

    /**
     * 그래픽객체가 현재게임블록을 그림
     *
     * @param g 그래픽객체
     */
    private void paintBlock(Graphics g) {


        if (this.block == null) return;

        for (int i = 0; i < BLOCK_ROW_SIZE; i++) {
            for (int j = 0; j < BLOCK_COLUMN_SIZE; j++) {
                if (block[i][j] != null) {
                    g.setColor(this.block[i][j]);
                    g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                    g.setColor(Color.white);
                    g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                }
            }
        }
    }

    public void updateBackground(Color[][] background) {
        this.background = background;

    }

    public void updateCurrentBlock(Color[][] block) {
        this.block = block;
    }

}
