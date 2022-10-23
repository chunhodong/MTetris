import controller.TetrisController;
import io.TetrisKeyboard;
import model.*;
import view.TetrisFrame;
import view.TetrisBlockView;

import java.util.List;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {



    public void start(){

        TetrisBlockView view = new TetrisBlockView();

        TetrisController controller = TetrisController
                .builder()
                .background(new TetrisBackground())
                .currentBlock(new TetrisCurrentBlock())
                .nextBlock(new TetrisNextBlock())
                .view(view)
                .timer(new TetrisTimer())
                .build();

        TetrisKeyboard keyboard = new TetrisKeyboard(controller);

        new TetrisFrame(List.of(view),keyboard);

    }
}
