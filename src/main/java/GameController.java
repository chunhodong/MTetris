import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;


public class GameController {
    
    private GameBackground gameBackground;
    private GameBlock gameBlock;
    private GamePanel gamePanel;
    private GameTimer gameTimer;

    private Status status;



    public enum Status{
        RUNNING,STOP,END
    }


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
        this.gameTimer = new GameTimer();

    }


    /**
     * 게임시작
     * 게임오브젝트초기화,게임화면그리기,타이머작동
     */
    public void startGame(){
        initGame();
        repaintGame();
        this.status = Status.RUNNING;
        this.gameTimer.run(this,new Timer());

    }


    /**
     * 게임정지여부수정
     */
    public void turnPause() {

        if(this.status == Status.RUNNING){
            this.gameTimer.stop();
            this.status = Status.STOP;
        }

        if(this.status == Status.STOP){
            this.gameTimer.resume(this,new Timer());
            this.status = Status.RUNNING;
        }


    }

    /**
     * 게임판화면 그리기
     */
    public void repaintGame(){
        this.gamePanel.repaint();
    }

    /**
     * 게임판 블록좌우이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockHorizontal(GameBlock.Direction direction){
        ArrayList<Point> points = this.gameBlock.getMovablePosition(direction);
        boolean isEnable = this.gameBackground.isMovable(points);
        if(isEnable) {
            this.gameBlock.moveToBlock(points);
            repaintGame();
        }
    }


    /**
     * 게임판 블록회전 요청
     */
    public void requestMoveBlockUp() {
        ArrayList<Point> points = this.gameBlock.getRotatablePosition();
        boolean isEnable = this.gameBackground.isMovable(points);
        if(isEnable) {
            this.gameBlock.rotateBlock(points);
            repaintGame();
        }
    }

    /**
     * 게임판 블록하단이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockDown(GameBlock.Direction direction){
        ArrayList<Point> movablePoints = this.gameBlock.getMovablePosition(direction);
        if(this.gameBackground.isAddible(movablePoints)){
            this.gameBlock.moveToBlock(movablePoints);
        }
        else {
            ArrayList<Point> currentPoints = this.gameBlock.getCurrentBlockPosition();
            Color color = this.gameBlock.getCurrentBlockColor();
            this.gameBackground.addBlock(currentPoints, color);
            this.gameBackground.clearLines();
            this.gameBlock.requestNewBlock();
            checkGameStatus(currentPoints);
        }
        repaintGame();



    }

    public void checkGameStatus(ArrayList<Point> currentPoints){
        if(this.gameBackground.isEnd(currentPoints)){
            this.status = Status.STOP;
            this.gameTimer.stop();
        }

    }


    /**
     * 게임판 블록바닥이동 요청
     */
    public void requestMoveBlockBottom() {
        ArrayList<Point> currentBlockPosition = this.gameBlock.getCurrentBlockPosition();
        Color color = this.gameBlock.getCurrentBlockColor();

        ArrayList<Point> bottomPoints = this.gameBackground.getBottomPoints(currentBlockPosition);
        this.gameBackground.addBlock(bottomPoints,color);
        this.gameBackground.clearLines();
        this.gameBlock.requestNewBlock();
        checkGameStatus(currentBlockPosition);

        repaintGame();



    }

    /**
     * 현재 게임상태 조회
     * @return 게임상태값
     */
    public Status getStatus(){
        return this.status;
    }

}
