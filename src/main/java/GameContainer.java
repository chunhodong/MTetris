
import java.awt.event.KeyListener;

public class GameContainer {

    private GamePanel gamePanel;
    private GameFrame gameFrame;
    private GameKeyHandler gameKeyHandler;
    private GameController gameController;



    private GameFrame gameFrame(){
        return gameFrame == null ? new GameFrame(gamePanel(),keyListener()) : gameFrame;
    }

    private GamePanel gamePanel(){
        return gamePanel == null ? new GamePanel() : gamePanel;

    }

    private GameController gameController(){
        return gameController == null ? new GameController(gamePanel()) : gameController;
    }

    private KeyListener keyListener(){
        return gameKeyHandler == null ? new GameKeyHandler(gameController()) : null;
    }

    public void start(){
        gameFrame();

    }
}
