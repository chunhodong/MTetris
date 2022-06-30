import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {



    /**
     * 게임화면 위치/크기 초기화
     */
    public GamePanel(){
        setLayout(null);
        setBounds(GameOption.xPosition,
                GameOption.yPosition,
                GameOption.displayWidth,
                GameOption.displayHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintBlock(g);
    }

    /**
     * 그래픽객체가 게임화면을 그림
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g){
        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++){
                g.setColor(Color.black);
                g.fillRect(j* GameOption.boxSize, i* GameOption.boxSize, GameOption.boxSize, GameOption.boxSize);
                g.setColor(Color.white);
                g.drawRect(j* GameOption.boxSize, i* GameOption.boxSize, GameOption.boxSize, GameOption.boxSize);
            }
        }
    }

    /**
     * 그래픽객체가 게임화면을 그림
     * @param g 그래픽객체
     */
    private void paintBlock(Graphics g){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                g.setColor(Color.red);
                g.fillRect(j* GameOption.boxSize,
                        i* GameOption.boxSize,
                        GameOption.boxSize, GameOption.boxSize);
                g.setColor(Color.white);
                g.drawRect(j* GameOption.boxSize, i* GameOption.boxSize, GameOption.boxSize, GameOption.boxSize);
            }
        }
    }

}
