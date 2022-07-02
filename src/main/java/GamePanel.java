import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {



    private GameBackground gameBackground;
    private GameBlock gameBlock;

    /**
     * 게임화면 위치/크기 초기화
     */
    public GamePanel(){
        setLayout(null);
        setBounds(GameOption.X_POSITION,
                GameOption.Y_POSITION,
                GameOption.DISPLAY_WIDTH,
                GameOption.DISPLAY_HEIGHT);
    }

    public void setGameBackground(GameBackground gameBackground){
        this.gameBackground = gameBackground;

    }
    public void setGameBlock(GameBlock gameBlock){
        this.gameBlock = gameBlock;

    }


    /**
     * 게임컴포넌트를 화면에 그림
     * @param g 그래픽객체
     */
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
                g.fillRect(j* GameOption.BOX_SIZE, i* GameOption.BOX_SIZE, GameOption.BOX_SIZE, GameOption.BOX_SIZE);
                g.setColor(Color.white);
                g.drawRect(j* GameOption.BOX_SIZE, i* GameOption.BOX_SIZE, GameOption.BOX_SIZE, GameOption.BOX_SIZE);
            }
        }
    }

    /**
     * 그래픽객체가 게임블록을 그림
     * @param g 그래픽객체
     */
    private void paintBlock(Graphics g){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                g.setColor(Color.red);
                g.fillRect(j* GameOption.BOX_SIZE,
                        i* GameOption.BOX_SIZE,
                        GameOption.BOX_SIZE, GameOption.BOX_SIZE);
                g.setColor(Color.white);
                g.drawRect(j* GameOption.BOX_SIZE, i* GameOption.BOX_SIZE, GameOption.BOX_SIZE, GameOption.BOX_SIZE);
            }
        }
    }

}
