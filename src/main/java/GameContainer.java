import controller.TetrisController;
import io.TetrisKeyboard;
import model.TetrisBackground;
import model.TetrisBlock;
import model.TetrisTimer;
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
                .block(new TetrisBlock())
                .panel(view)
                .timer(new TetrisTimer())
                .build();

        new TetrisFrame(view,new TetrisKeyboard(controller));


    }
}
