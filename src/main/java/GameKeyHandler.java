import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyHandler implements KeyListener {

    private GameController controller;

    public GameKeyHandler(GameController controller){
        this.controller = controller;

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {



        if(e.getKeyCode() == KeyEvent.VK_F1){
            this.controller.startGame();
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.controller.requestMoveBlockHorizontal(GameBlock.Direction.RIGHT);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            this.controller.requestMoveBlockHorizontal(GameBlock.Direction.LEFT);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            this.controller.requestMoveBlockDown(GameBlock.Direction.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
