import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    /*게임화면 시작X좌표*/
    public final int xPosition = 10;

    /*게임화면 시작Y좌표*/
    public final int yPosition = 10;
    
    /*게임화면 시작넓이*/
    public final int width = 250;
    
    /*게임화면 높이*/
    public final int height = 500;

    /**
     * 게임화면 위치/크기 초기화
     */
    public GamePanel(){
        setLayout(null);
        setBounds(xPosition,yPosition,width,height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
    }

    /**
     * 그래픽객체가 게임화면을 그림
     * @param g 그래픽객체
     */
    private void paintBackground(Graphics g){
        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++){
                g.setColor(Color.black);
                g.fillRect(j*25, i*25,25, 25);
                g.setColor(Color.white);
                g.drawRect(j*25, i*25,25,25);
            }
        }
    }
}
