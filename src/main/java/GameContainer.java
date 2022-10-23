import controller.TetrisController;
import io.TetrisKeyboard;
import model.*;
import view.TetrisFrame;
import view.TetrisCurrentBlockView;
import view.TetrisManualView;
import view.TetrisNextBlockView;

import java.util.List;

/**
 * 게임컴포넌트들을 조립
 */
public class GameContainer {



    public void start(){

        TetrisCurrentBlockView blockView = new TetrisCurrentBlockView();
        TetrisNextBlockView nextBlockView = new TetrisNextBlockView();
        TetrisManualView tetrisManualView = new TetrisManualView();
        TetrisController controller = TetrisController
                .builder()
                .background(new TetrisBackground())
                .currentBlock(new TetrisCurrentBlock())
                .nextBlock(new TetrisNextBlock())
                .currentBlockView(blockView)
                .nextBlockView(nextBlockView)
                .manualView(tetrisManualView)
                .timer(new TetrisTimer())
                .build();

        TetrisKeyboard keyboard = new TetrisKeyboard(controller);

        new TetrisFrame(List.of(blockView,nextBlockView,tetrisManualView),keyboard);


    }
}
