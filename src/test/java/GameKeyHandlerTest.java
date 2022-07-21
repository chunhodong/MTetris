import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameKeyHandlerTest {

    @InjectMocks
    private GameKeyHandler gameKeyHandler;

    @Mock
    private GameController gameController;

    @Test
    void 방향오른쪽이동성공(){
        KeyEvent event = new KeyEvent(
         new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_RIGHT);
        gameKeyHandler.keyPressed(event);
        verify(gameController,times(1)).requestMoveBlockHorizontal(GameBlock.Direction.RIGHT);

    }

    @Test
    void 방향왼쪽이동성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_LEFT);
        gameKeyHandler.keyPressed(event);
        gameKeyHandler.keyReleased(event);
        gameKeyHandler.keyTyped(event);
        verify(gameController,times(1)).requestMoveBlockHorizontal(GameBlock.Direction.LEFT);

    }

    @Test
    void 방향아래이동성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_DOWN);
        gameKeyHandler.keyPressed(event);
        verify(gameController,times(1)).requestMoveBlockDown(GameBlock.Direction.DOWN);

    }

    @Test
    void 게임시작키동작성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_F1);
        gameKeyHandler.keyPressed(event);
        verify(gameController,times(1)).startGame();


    }

    @Test
    void 게임정지동작성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_F2);
        gameKeyHandler.keyPressed(event);
        verify(gameController,times(1)).turnPause();

    }

    @Test
    void 게임블록회전성공(){
        KeyEvent event = new KeyEvent(
                new TestComponent(),12,1,1,KeyEvent.VK_DOWN,'k'
        );
        event.setKeyCode(KeyEvent.VK_UP);
        gameKeyHandler.keyPressed(event);
        verify(gameController,times(1)).requestMoveBlockUp();

    }



    class TestComponent extends Component{

    }


}
