package controller;

import model.TetrisBackground;
import model.TetrisBlock;
import model.TetrisTimer;
import view.TetrisPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;


public class TetrisController {
    
    private TetrisBackground tetrisBackground;
    private TetrisBlock tetrisBlock;
    private TetrisPanel tetrisPanel;
    private TetrisTimer tetrisTimer;

    private Status status;



    public enum Status{
        RUNNING,STOP,END
    }


    public TetrisController(TetrisPanel tetrisPanel){
        this.tetrisPanel = tetrisPanel;
    }

    /**
     * 게임화면,게임블록 초기화
     */
    public void initGame(){
        tetrisBackground = new TetrisBackground();
        tetrisBlock = new TetrisBlock();
        this.tetrisPanel.setGameBackground(tetrisBackground);
        this.tetrisPanel.setGameBlock(tetrisBlock);
        this.tetrisTimer = new TetrisTimer();

    }


    /**
     * 게임시작
     * 게임오브젝트초기화,게임화면그리기,타이머작동
     */
    public void startGame(){
        initGame();
        repaintGame();
        this.status = Status.RUNNING;
        this.tetrisTimer.run(this,new Timer());

    }


    /**
     * 게임정지여부수정
     */
    public void turnPause() {

        if(this.status == Status.RUNNING){
            this.tetrisTimer.stop();
            this.status = Status.STOP;
        }

        if(this.status == Status.STOP){
            this.tetrisTimer.resume(this,new Timer());
            this.status = Status.RUNNING;
        }


    }

    /**
     * 게임판화면 그리기
     */
    public void repaintGame(){
        this.tetrisPanel.repaint();
    }

    /**
     * 게임판 블록좌우이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockHorizontal(TetrisBlock.Direction direction){
        ArrayList<Point> points = this.tetrisBlock.getMovablePosition(direction);
        boolean isEnable = this.tetrisBackground.isMovable(points);
        if(isEnable) {
            this.tetrisBlock.moveToBlock(points);
            repaintGame();
        }
    }


    /**
     * 게임판 블록회전 요청
     */
    public void requestMoveBlockUp() {
        ArrayList<Point> points = this.tetrisBlock.getRotatablePosition();
        boolean isEnable = this.tetrisBackground.isMovable(points);
        if(isEnable) {
            this.tetrisBlock.rotateBlock(points);
            repaintGame();
        }
    }

    /**
     * 게임판 블록하단이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockDown(TetrisBlock.Direction direction){
        ArrayList<Point> movablePoints = this.tetrisBlock.getMovablePosition(direction);
        if(this.tetrisBackground.isAddible(movablePoints)){
            this.tetrisBlock.moveToBlock(movablePoints);
        }
        else {
            ArrayList<Point> currentPoints = this.tetrisBlock.getCurrentBlockPosition();
            Color color = this.tetrisBlock.getCurrentBlockColor();
            this.tetrisBackground.addBlock(currentPoints, color);
            this.tetrisBackground.clearLines();
            this.tetrisBlock.requestNewBlock();
            checkGameStatus(currentPoints);
        }
        repaintGame();



    }

    public void checkGameStatus(ArrayList<Point> currentPoints){
        if(this.tetrisBackground.isEnd(currentPoints)){
            this.status = Status.STOP;
            this.tetrisTimer.stop();
        }

    }


    /**
     * 게임판 블록바닥이동 요청
     */
    public void requestMoveBlockBottom() {
        ArrayList<Point> currentBlockPosition = this.tetrisBlock.getCurrentBlockPosition();
        Color color = this.tetrisBlock.getCurrentBlockColor();

        ArrayList<Point> bottomPoints = this.tetrisBackground.getBottomPoints(currentBlockPosition);
        this.tetrisBackground.addBlock(bottomPoints,color);
        this.tetrisBackground.clearLines();
        this.tetrisBlock.requestNewBlock();
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
