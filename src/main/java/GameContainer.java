import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.KeyListener;

public class GameContainer {

    public GameFrame gameFrame(){
        return new GameFrame(gamePanel(),keyListener());
    }

    public GamePanel gamePanel(){
        return new GamePanel();
    }

    public KeyListener keyListener(){
        return new GameKeyHandler();
    }
}
