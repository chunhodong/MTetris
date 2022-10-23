package view;

import model.TetrisBackground;
import model.TetrisOption;

import javax.swing.*;
import java.awt.*;

/**
 * 게임배경,게임블록등을 그래픽객체로 그려서 화면에출력
 */
public class TetrisView extends JPanel {


    private Color[][] background;

    private Color[][] currentBlock;

    private Color[][] nextBlock;


    /**
     * 게임화면 위치/크기 초기화
     */
    public TetrisView() {
        setLayout(null);
        setBounds(TetrisOption.X_POSITION,
                TetrisOption.Y_POSITION,
                TetrisOption.DISPLAY_WIDTH,
                TetrisOption.DISPLAY_HEIGHT);
    }


    /**
     * 게임컴포넌트를 화면에 그림
     *
     * @param g 그래픽객체
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintCurrentBlock(g);
        paintNextBlock(g);

    }

    /**
     * 그래픽객체가 게임화면을 그림
     *
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g) {

        for (int i = 0; i < TetrisOption.BOARD_HEIGHT; i++) {
            for (int j = 0; j < TetrisOption.BOARD_WIDTH; j++) {

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
    private void paintCurrentBlock(Graphics g) {


        if (this.currentBlock == null) return;

        for (int i = 0; i < TetrisOption.BOARD_HEIGHT; i++) {
            for (int j = 0; j < TetrisOption.BOARD_WIDTH; j++) {
                if (currentBlock[i][j] != null) {
                    g.setColor(this.currentBlock[i][j]);
                    g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                    g.setColor(Color.white);
                    g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                }
            }
        }
    }


    /**
     * 그래픽객체가 다음게임블록을 그림
     *
     * @param g 그래픽객체
     */
    private void paintNextBlock(Graphics g) {


        for (int i = 0; i < (TetrisOption.BOARD_HEIGHT / 5); i++) {
            for (int j = 0; j < (TetrisOption.BOARD_HEIGHT / 5); j++) {
                g.setColor(this.nextBlock == null || this.nextBlock[i][j] == null ? Color.BLACK : this.nextBlock[i][j]);
                g.fillRect(j * TetrisOption.BOX_SIZE + 265, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                g.setColor(Color.white);
                g.drawRect(j * TetrisOption.BOX_SIZE + 265, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);

            }
        }

    }


    public void updateBackground(Color[][] background) {
        this.background = background;

    }

    public void updateCurrentBlock(Color[][] currentBlock) {
        this.currentBlock = currentBlock;
    }

    public void updateNextBlock(Color[][] nextBlock) {
        this.nextBlock = nextBlock;
    }
}
