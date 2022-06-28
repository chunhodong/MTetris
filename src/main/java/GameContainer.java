import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.KeyListener;

public class GameContainer {


    private GamePanelOption gamePanelOption(){
        return new GamePanelOption();
    }

    private GameFrame gameFrame(){
        return new GameFrame(gamePanel(),keyListener());
    }

    private GamePanel gamePanel(){
        return new GamePanel(gamePanelOption());
    }

    private KeyListener keyListener(){
        return new GameKeyHandler(gamePanel());
    }

    public void start(){
        gameFrame();

    }
}
