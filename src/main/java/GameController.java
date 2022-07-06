import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    
    private GameBackground gameBackground;
    private GameBlock gameBlock;
    private GamePanel gamePanel;

    public GameController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * 게임화면,게임블록 초기화
     */
    public void initGame(){
        gameBackground = new GameBackground();
        gameBlock = new GameBlock();
        this.gamePanel.setGameBackground(gameBackground);
        this.gamePanel.setGameBlock(gameBlock);

    }


    public void startGame(){


        initGame();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaintGame();
            }
        }, 0, 2000);

    }

    public void repaintGame(){
        this.gamePanel.repaint();
    }




    
    
}
