package controller;

import model.*;
import view.TetrisView;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class TetrisController {

    private TetrisBackground background;
    private TetrisNextBlock nextBlock;
    private TetrisCurrentBlock currentBlock;
    private TetrisView tetrisView;
    private TetrisTimer tetrisTimer;
    private Status status;

    public enum Status {
        RUNNING, STOP
    }

    private TetrisController(TetrisControllerBuilder builder) {
        this.background = builder.background;
        this.currentBlock = builder.currentBlock;
        this.tetrisView = builder.panel;
        this.tetrisTimer = builder.timer;
    }

    public static TetrisControllerBuilder builder() {

        return new TetrisControllerBuilder();
    }

    public static class TetrisControllerBuilder {
        private TetrisBackground background;
        private TetrisCurrentBlock currentBlock;
        private TetrisNextBlock nextBlock;
        private TetrisView panel;
        private TetrisTimer timer;


        public TetrisControllerBuilder background(TetrisBackground background) {
            this.background = background;
            return this;
        }

        public TetrisControllerBuilder currentBlock(TetrisCurrentBlock currentBlock) {
            this.currentBlock = currentBlock;
            return this;
        }

        public TetrisControllerBuilder nextBlock(TetrisNextBlock nextBlock) {
            this.nextBlock = nextBlock;
            return this;
        }


        public TetrisControllerBuilder panel(TetrisView panel) {
            this.panel = panel;
            return this;
        }

        public TetrisControllerBuilder timer(TetrisTimer timer) {
            this.timer = timer;
            return this;
        }

        public TetrisController build() {

            return new TetrisController(this);
        }
    }


    /**
     * 게임판 블록좌우이동 요청
     *
     * @param direction 이동방향
     */
    public void requestMoveBlockHorizontal(TetrisCurrentBlock.Direction direction) {
        ArrayList<Point> points = this.currentBlock.getMovablePoints(direction);

        if (this.background.isMovable(points)) {
            this.currentBlock.move(points);
            this.tetrisView.updateCurrentBlock(this.currentBlock.getColorMap());
            this.tetrisView.repaint();
        }
    }

    /**
     * 게임판 블록회전 요청
     */
    public void requestMoveBlockUp() {
        ArrayList<Point> points = this.currentBlock.getRotatablePoints();

        if (this.background.isMovable(points)) {
            this.currentBlock.rotate(points);
            this.tetrisView.updateCurrentBlock(this.currentBlock.getColorMap());
            this.tetrisView.repaint();

        }
    }

    /**
     * 게임판 블록하단이동 요청
     *
     * @param direction 이동방향
     */
    public void requestMoveBlockDown(TetrisCurrentBlock.Direction direction) {
        List<Point> movablePoints = this.currentBlock.getMovablePoints(direction);

        if (this.background.isAddible(movablePoints)) {
            doMoveDown(movablePoints);
            return;
        }
        List<Point> bottonPoints = this.currentBlock.getCurrentPoints();
        Color color = this.currentBlock.getColor();
        doMoveBotton(bottonPoints,color);
    }

    /**
     * 게임판 블록바닥이동 요청
     */
    public void requestMoveBlockBottom() {
        List<Point> currentBlockPosition = this.currentBlock.getCurrentPoints();
        List<Point> bottomPoints = this.background.getBottomPoints(currentBlockPosition);
        Color color = this.currentBlock.getColor();
        doMoveBotton(bottomPoints,color);



    }



    private void doMoveDown(List<Point> movablePoints) {
        this.currentBlock.move(movablePoints);
        this.tetrisView.updateCurrentBlock(this.currentBlock.getColorMap());
        this.tetrisView.repaint();


    }

    private void doMoveBotton(List<Point> currentPoints,Color color) {
        this.background.addBlock(currentPoints, color);
        this.currentBlock.change(this.nextBlock);
        this.nextBlock.init();
        switchStop(currentPoints);

        this.tetrisView.updateBackground(this.background.getBackgroundColor());
        this.tetrisView.updateCurrentBlock(this.currentBlock.getColorMap());
        this.tetrisView.updateNextBlock(this.nextBlock.getColorMap());
        this.tetrisView.repaint();


    }

    private void requestCreateBlocks(){
        this.currentBlock.init();
        this.nextBlock.init();
        this.background.init();

        this.tetrisView.updateBackground(this.background.getBackgroundColor());
        this.tetrisView.updateCurrentBlock(this.currentBlock.getColorMap());
        this.tetrisView.updateNextBlock(this.nextBlock.getColorMap());
        this.tetrisView.repaint();
    }




    /**
     * 게임시작
     * 게임오브젝트초기화,게임화면그리기,타이머작동
     */
    public void switchStart() {

        this.tetrisView.repaint();
        this.status = Status.RUNNING;
        this.tetrisTimer = new TetrisTimer();
        this.tetrisTimer.run(this, new Timer());

    }

    public void switchStop(List<Point> currentPoints) {
        if (this.background.isEnd(currentPoints)) {
            this.status = Status.STOP;
            this.tetrisTimer.stop();
        }

    }

    /**
     * 게임정지여부수정
     */
    public void switchPause() {

        if (this.status == Status.RUNNING) {
            this.tetrisTimer.stop();
            this.status = Status.STOP;
        }

        if (this.status == Status.STOP) {
            this.tetrisTimer.resume(this, new Timer());
            this.status = Status.RUNNING;
        }


    }

    /**
     * 현재 게임상태 조회
     *
     * @return 게임상태값
     */
    public Status getStatus() {
        return this.status;
    }

}
