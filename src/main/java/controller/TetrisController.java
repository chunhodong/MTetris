package controller;

import model.*;
import view.TetrisView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;


public class TetrisController {
    
    private TetrisBackground background;
    private TetrisCurrentBlock tetrisCurrentBlock;
    private TetrisView tetrisView;
    private TetrisTimer tetrisTimer;
    private Status status;



    public enum Status{
        RUNNING,STOP,END
    }


    private TetrisController(TetrisControllerBuilder builder){
        this.background = builder.background;
        this.tetrisCurrentBlock = builder.tetrisCurrentBlock;
        this.tetrisView = builder.panel;
        this.tetrisTimer = builder.timer;
    }


    public static TetrisControllerBuilder builder(){

        return new TetrisControllerBuilder();
    }

    public static class TetrisControllerBuilder{
        private TetrisBackground background;
        private TetrisCurrentBlock tetrisCurrentBlock;
        private TetrisNextBlock tetrisNextBlock;
        private TetrisView panel;
        private TetrisTimer timer;


        public TetrisControllerBuilder background(TetrisBackground background){
            this.background = background;
            return this;
        }
        public TetrisControllerBuilder currentBlock(TetrisCurrentBlock tetrisCurrentBlock){
            this.tetrisCurrentBlock = tetrisCurrentBlock;
            return this;
        }

        public TetrisControllerBuilder nextBlock(TetrisNextBlock tetrisNextBlock){
            this.tetrisNextBlock = tetrisNextBlock;
            return this;
        }


        public TetrisControllerBuilder panel(TetrisView panel){
            this.panel = panel;
            return this;
        }
        public TetrisControllerBuilder timer(TetrisTimer timer){
            this.timer = timer;
            return this;
        }
        public TetrisController build(){

            return new TetrisController(this);
        }
    }

    /**
     * 게임화면,게임블록 초기화
     */
    public void initGame(){
        background = new TetrisBackground();
        tetrisCurrentBlock = new TetrisCurrentBlock();
        this.tetrisView.setGameBackground(background);
        this.tetrisView.setGameBlock(tetrisCurrentBlock);
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
        this.tetrisView.repaint();
    }

    /**
     * 게임판 블록좌우이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockHorizontal(TetrisCurrentBlock.Direction direction){
        ArrayList<Point> points = this.tetrisCurrentBlock.getMovablePosition(direction);
        boolean isEnable = this.background.isMovable(points);
        if(isEnable) {
            this.tetrisCurrentBlock.moveToBlock(points);
            repaintGame();
        }
    }


    /**
     * 게임판 블록회전 요청
     */
    public void requestMoveBlockUp() {
        ArrayList<Point> points = this.tetrisCurrentBlock.getRotatablePosition();
        boolean isEnable = this.background.isMovable(points);
        if(isEnable) {
            this.tetrisCurrentBlock.rotateBlock(points);
            repaintGame();
        }
    }

    /**
     * 게임판 블록하단이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockDown(TetrisCurrentBlock.Direction direction){
        ArrayList<Point> movablePoints = this.tetrisCurrentBlock.getMovablePosition(direction);
        if(this.background.isAddible(movablePoints)){
            this.tetrisCurrentBlock.moveToBlock(movablePoints);
        }
        else {
            ArrayList<Point> currentPoints = this.tetrisCurrentBlock.getCurrentBlockPosition();
            Color color = this.tetrisCurrentBlock.getCurrentBlockColor();
            this.background.addBlock(currentPoints, color);
            this.background.clearLines();
            this.tetrisCurrentBlock.requestNewBlock();
            checkGameStatus(currentPoints);
        }
        repaintGame();



    }

    public void checkGameStatus(ArrayList<Point> currentPoints){
        if(this.background.isEnd(currentPoints)){
            this.status = Status.STOP;
            this.tetrisTimer.stop();
        }

    }


    /**
     * 게임판 블록바닥이동 요청
     */
    public void requestMoveBlockBottom() {
        ArrayList<Point> currentBlockPosition = this.tetrisCurrentBlock.getCurrentBlockPosition();
        Color color = this.tetrisCurrentBlock.getCurrentBlockColor();

        ArrayList<Point> bottomPoints = this.background.getBottomPoints(currentBlockPosition);
        this.background.addBlock(bottomPoints,color);
        this.background.clearLines();
        this.tetrisCurrentBlock.requestNewBlock();
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
