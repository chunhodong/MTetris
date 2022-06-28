
import java.awt.event.KeyListener;

public class GameContainer {

    private GamePanelOption gamePanelOption;
    private GamePanel gamePanel;
    private GameFrame gameFrame;
    private GameKeyHandler gameKeyHandler;

    private GamePanelOption gamePanelOption(){
        return gamePanelOption == null ? new GamePanelOption() : gamePanelOption;
    }

    private GameFrame gameFrame(){
        return gameFrame == null ? new GameFrame(gamePanel(),keyListener()) : gameFrame;
    }

    private GamePanel gamePanel(){
        return gamePanel == null ? new GamePanel(gamePanelOption()) : gamePanel;

    }

    private KeyListener keyListener(){
        return gameKeyHandler == null ? new GameKeyHandler(gamePanel()) : null;
    }

    public void start(){
        gameFrame();

    }
}
