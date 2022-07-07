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
            System.out.println("rr");
            this.controller.callToMovingBlock();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
