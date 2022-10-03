package view;

import model.TetrisBackground;
import model.TetrisBlock;
import model.TetrisCurrentBlock;
import model.TetrisOption;

import javax.swing.*;
import java.awt.*;

/**
 * 게임배경,게임블록등을 그래픽객체로 그려서 화면에출력
 */
public class TetrisView extends JPanel {



    private TetrisBackground tetrisBackground;
    private TetrisCurrentBlock tetrisBlock;

    private Color[][] currentBlockColors;

    private Color[][] nextBlockColors;


    /**
     * 게임화면 위치/크기 초기화
     */
    public TetrisView(){
        setLayout(null);
        setBounds(TetrisOption.X_POSITION,
                TetrisOption.Y_POSITION,
                TetrisOption.DISPLAY_WIDTH,
                TetrisOption.DISPLAY_HEIGHT);
    }

    /**
     * 게임배경데이터초기화
     * @param tetrisBackground 게임배경
     */
    public void setGameBackground(TetrisBackground tetrisBackground){
        this.tetrisBackground = tetrisBackground;

    }

    /**
     * 게임블록데이터초기화
     * @param tetrisBlock 게임블록
     */
    public void setGameBlock(TetrisCurrentBlock tetrisBlock){
        this.tetrisBlock = tetrisBlock;

    }


    /**
     * 게임컴포넌트를 화면에 그림
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
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g){

        for(int i = 0; i< TetrisOption.BOARD_HEIGHT; i++){
            for(int j = 0; j< TetrisOption.BOARD_WIDTH; j++){
                g.setColor(this.tetrisBackground == null ? Color.BLACK : this.tetrisBackground.getColor(i,j));
                g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                g.setColor(Color.white);
                g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);

            }
        }
    }

    /**
     * 그래픽객체가 현재게임블록을 그림
     * @param g 그래픽객체
     */
    private void paintCurrentBlock(Graphics g){


        if(this.tetrisBlock == null)return;

        for(int i = 0; i< TetrisOption.BOARD_HEIGHT; i++){
            for(int j = 0; j< TetrisOption.BOARD_WIDTH; j++){
                if(this.tetrisBlock.hasCurrentBlockElement(i,j)) {

                    g.setColor(this.tetrisBlock.getCurrentBlockColor());
                    g.fillRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                    g.setColor(Color.white);
                    g.drawRect(j * TetrisOption.BOX_SIZE, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                }
            }
        }
    }


    /**
     * 그래픽객체가 다음게임블록을 그림
     * @param g 그래픽객체
     */
    private void paintNextBlock(Graphics g){



        for(int i = 0; i< ( TetrisOption.BOARD_HEIGHT / 5 ); i++){
            for(int j = 0; j< ( TetrisOption.BOARD_HEIGHT / 5 ); j++){
                if(this.tetrisBlock == null || !this.tetrisBlock.hasNextBlockElement(i,j)){
                    g.setColor(Color.BLACK);
                }
                else{
                    g.setColor(this.tetrisBlock.getNextBlockColor());
                }
                g.fillRect(j * TetrisOption.BOX_SIZE + 265, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
                //g.setColor(Color.white);
                g.drawRect(j * TetrisOption.BOX_SIZE + 265, i * TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE, TetrisOption.BOX_SIZE);
            }
        }

    }
}
