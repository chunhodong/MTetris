import controller.TetrisController;
import io.TetrisKeyboard;
import model.TetrisBlock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.event.KeyEvent;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TetrisKeyboardTest {

    @InjectMocks
    private TetrisKeyboard tetrisKeyboard;

    @Mock
    private TetrisController tetrisController;

    @Test
    void 방향오른쪽이동성공(){
        KeyEvent event = new KeyEvent(
         new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_RIGHT);
        tetrisKeyboard.keyPressed(event);
        verify(tetrisController,times(1)).requestMoveBlockHorizontal(TetrisBlock.Direction.RIGHT);

    }

    @Test
    void 방향왼쪽이동성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_LEFT);
        tetrisKeyboard.keyPressed(event);
        tetrisKeyboard.keyReleased(event);
        tetrisKeyboard.keyTyped(event);
        verify(tetrisController,times(1)).requestMoveBlockHorizontal(TetrisBlock.Direction.LEFT);

    }

    @Test
    void 방향아래이동성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_DOWN);
        tetrisKeyboard.keyPressed(event);
        verify(tetrisController,times(1)).requestMoveBlockDown(TetrisBlock.Direction.DOWN);

    }

    @Test
    void 게임시작키동작성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_F1);
        tetrisKeyboard.keyPressed(event);
        verify(tetrisController,times(1)).startGame();


    }

    @Test
    void 게임정지동작성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_F2);
        tetrisKeyboard.keyPressed(event);
        verify(tetrisController,times(1)).turnPause();

    }

    @Test
    void 게임블록회전성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_UP);
        tetrisKeyboard.keyPressed(event);
        verify(tetrisController,times(1)).requestMoveBlockUp();

    }

    @Test
    void 게임블록바닥이동성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_SPACE);
        tetrisKeyboard.keyPressed(event);
        verify(tetrisController,times(1)).requestMoveBlockBottom();

    }



    class TestComponent extends Component{

    }


}
