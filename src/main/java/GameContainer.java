import controller.GameController;
import io.GameKeyHandler;
import view.GameFrame;
import view.TetrisPanel;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {


    /*게임판*/
    private TetrisPanel tetrisPanel = new TetrisPanel();

    /*게임컨트롤러*/
    private GameController gameController = new GameController(tetrisPanel);

    /*게임키보드*/
    private GameKeyHandler gameKeyHandler = new GameKeyHandler(gameController);

    public void start(){
        //테트리스 팩토리에서 객체만든다음에
        //조립
        //start
        new GameFrame(tetrisPanel,gameKeyHandler);
    }
}
