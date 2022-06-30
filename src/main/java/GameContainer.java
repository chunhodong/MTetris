
import java.awt.event.KeyListener;

public class GameContainer {

    private GameOption gameOption;
    private GamePanel gamePanel;
    private GameFrame gameFrame;
    private GameKeyHandler gameKeyHandler;

    private GameOption gamePanelOption(){
        return gameOption == null ? new GameOption() : gameOption;
    }

    private GameFrame gameFrame(){
        return gameFrame == null ? new GameFrame(gamePanel(),keyListener()) : gameFrame;
    }

    private GamePanel gamePanel(){
        return gamePanel == null ? new GamePanel() : gamePanel;

    }

    private KeyListener keyListener(){
        return gameKeyHandler == null ? new GameKeyHandler(gamePanel()) : null;
    }

    public void start(){
        gameFrame();

    }
}
