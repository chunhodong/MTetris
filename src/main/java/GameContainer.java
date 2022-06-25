import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.KeyListener;

public class GameContainer {


    private GameFrame gameFrame(){
        return new GameFrame(gamePanel(),keyListener());
    }

    private GamePanel gamePanel(){
        return new GamePanel();
    }

    private KeyListener keyListener(){
        return new GameKeyHandler();
    }

    public void start(){
        gameFrame();

    }
}
