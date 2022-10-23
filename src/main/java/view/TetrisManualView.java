package view;

import javax.swing.*;
import java.awt.*;

/**
 * 게임배경,게임블록등을 그래픽객체로 그려서 화면에출력
 */
public class TetrisManualView extends JPanel {


    /*게임화면 시작넓이*/
    private static final int DISPLAY_WIDTH = 200;

    /*게임화면 높이*/
    private static final int DISPLAY_HEIGHT = 100;


    /*게임화면 시작X좌표*/
    private static final int X_POSITION = 285;

    /*게임화면 시작Y좌표*/
    private static final int Y_POSITION = 150;

    private static final String INTRO_GAME = "-----------메뉴--------------";
    private static final String NEW_GAME = "F1 => 새게임";
    private static final String RESUME_GAME = "F2 => 정지/재시작";

    private static final int MANU_X_POSITION = 5;
    private static final int MANU_Y_POSITION = 10;


    /**
     * 게임화면 위치/크기 초기화
     */
    public TetrisManualView() {
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

    }

    /**
     * 그래픽객체가 게임화면을 그림
     *
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawString(INTRO_GAME, MANU_X_POSITION, MANU_Y_POSITION);
        g.drawString(NEW_GAME, MANU_X_POSITION, MANU_Y_POSITION + ( MANU_Y_POSITION * 2 ));
        g.drawString(RESUME_GAME, MANU_X_POSITION, MANU_Y_POSITION + ( MANU_Y_POSITION * 4 ));

    }

}
