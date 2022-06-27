import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {



    private GamePanelOption option;

    /**
     * 게임화면 위치/크기 초기화
     */
    public GamePanel(GamePanelOption option){
        this.option = option;
        setLayout(null);
        setBounds(this.option.xPosition,this.option.yPosition,this.option.width,this.option.height);
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
                g.fillRect(j*this.option.boxSize, i*this.option.boxSize,this.option.boxSize,this.option.boxSize);
                g.setColor(Color.white);
                g.drawRect(j*this.option.boxSize, i*this.option.boxSize,this.option.boxSize,this.option.boxSize);
            }
        }
    }
}
