
import java.awt.event.KeyListener;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {

    /*게임판*/
    private GamePanel gamePanel;
    /*게임프레임*/
    private GameFrame gameFrame;
    /*게임키보드*/
    private GameKeyHandler gameKeyHandler;
    /*게임컨트롤러*/
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
