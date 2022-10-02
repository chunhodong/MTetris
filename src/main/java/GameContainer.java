import controller.TetrisController;
import io.TetrisKeyboard;
import view.TetrisFrame;
import view.TetrisPanel;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {


    /*게임판*/
    private TetrisPanel tetrisPanel = new TetrisPanel();

    /*게임컨트롤러*/
    private TetrisController tetrisController = new TetrisController(tetrisPanel);

    /*게임키보드*/
    private TetrisKeyboard tetrisKeyboard = new TetrisKeyboard(tetrisController);

    public void start(){
        //테트리스 팩토리에서 객체만든다음에
        //조립
        //start
        new TetrisFrame(tetrisPanel, tetrisKeyboard);
    }
}
