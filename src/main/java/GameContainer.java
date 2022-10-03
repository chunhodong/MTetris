import controller.TetrisController;
import io.TetrisKeyboard;
import model.*;
import view.TetrisFrame;
import view.TetrisView;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {



    public void start(){


        TetrisView view = new TetrisView();

        TetrisController controller = TetrisController
                .builder()
                .background(new TetrisBackground())
                .currentBlock(new TetrisCurrentBlock())
                .nextBlock(new TetrisNextBlock())
                .panel(view)
                .timer(new TetrisTimer())
                .build();

        new TetrisFrame(view,new TetrisKeyboard(controller));


    }
}
