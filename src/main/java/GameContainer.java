
import java.awt.event.KeyListener;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {

    /*게임판*/
    private GamePanel gamePanel = new GamePanel();

    /*게임컨트롤러*/
    private GameController gameController = new GameController(gamePanel);

    /*게임키보드*/
    private GameKeyHandler gameKeyHandler = new GameKeyHandler(gameController);

    public void start(){
        new GameFrame(gamePanel,gameKeyHandler);

    }
}
