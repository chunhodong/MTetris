import java.awt.*;
import java.util.ArrayList;
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
        repaintGame();


    }

    public void repaintGame(){
        this.gamePanel.repaint();
    }

    public void requestMoveBlockHorizontal(GameBlock.Direction direction){
        ArrayList<Point> points = this.gameBlock.getMovablePosition(direction);
        boolean isEnable = this.gameBackground.isEnableAdd(points);
        if(isEnable) {
            this.gameBlock.moveToBlock(points);
            repaintGame();
        }
    }




    
    
}
