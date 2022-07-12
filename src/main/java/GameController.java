import java.awt.*;
import java.net.PortUnreachableException;
import java.util.ArrayList;


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
        new GameTimer().run(this);
/*

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                repaintGame();
            }
        }, 0, 2000);
*/

    }

    public void repaintGame(){
        this.gamePanel.repaint();
    }

    public void requestMoveBlockHorizontal(GameBlock.Direction direction){
        ArrayList<Point> points = this.gameBlock.getMovablePosition(direction);
        boolean isEnable = this.gameBackground.isMovable(points);
        if(isEnable) {
            this.gameBlock.moveToBlock(points);
            repaintGame();
        }
    }

    public void requestMoveBlockDown(GameBlock.Direction direction){
        ArrayList<Point> points = this.gameBlock.getMovablePosition(direction);
        boolean isEnable = this.gameBackground.isAddible(points);
        if(isEnable){

        }
        else{

        }
    }




    
    
}
