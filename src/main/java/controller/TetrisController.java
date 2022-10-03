package controller;

import model.*;
import view.TetrisView;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class TetrisController {

    private TetrisBackground background;

    private TetrisNextBlock tetrisNextBlock;
    private TetrisCurrentBlock tetrisCurrentBlock;
    private TetrisView tetrisView;
    private TetrisTimer tetrisTimer;
    private Status status;


    public enum Status {
        RUNNING, STOP, END
    }


    private TetrisController(TetrisControllerBuilder builder) {
        this.background = builder.background;
        this.tetrisCurrentBlock = builder.tetrisCurrentBlock;
        this.tetrisView = builder.panel;
        this.tetrisTimer = builder.timer;
    }


    public static TetrisControllerBuilder builder() {

        return new TetrisControllerBuilder();
    }

    public static class TetrisControllerBuilder {
        private TetrisBackground background;
        private TetrisCurrentBlock tetrisCurrentBlock;
        private TetrisNextBlock tetrisNextBlock;
        private TetrisView panel;
        private TetrisTimer timer;


        public TetrisControllerBuilder background(TetrisBackground background) {
            this.background = background;
            return this;
        }

        public TetrisControllerBuilder currentBlock(TetrisCurrentBlock tetrisCurrentBlock) {
            this.tetrisCurrentBlock = tetrisCurrentBlock;
            return this;
        }

        public TetrisControllerBuilder nextBlock(TetrisNextBlock tetrisNextBlock) {
            this.tetrisNextBlock = tetrisNextBlock;
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
     * 게임시작
     * 게임오브젝트초기화,게임화면그리기,타이머작동
     */
    public void startGame() {

        this.tetrisView.repaint();
        this.status = Status.RUNNING;
        this.tetrisTimer = new TetrisTimer();
        this.tetrisTimer.run(this, new Timer());

    }


    /**
     * 게임정지여부수정
     */
    public void turnPause() {

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
     * 게임판 블록좌우이동 요청
     *
     * @param direction 이동방향
     */
    public void requestMoveBlockHorizontal(TetrisCurrentBlock.Direction direction) {
        ArrayList<Point> points = this.tetrisCurrentBlock.getMovablePosition(direction);
        boolean isEnable = this.background.isMovable(points);
        if (isEnable) {
            this.tetrisCurrentBlock.moveBlock(points);
            Color[][] blockColorMap = this.tetrisCurrentBlock.getBlockColorMap();
            this.tetrisView.updateCurrentBlock(blockColorMap);
            this.tetrisView.repaint();

        }
    }

    /**
     * 게임판 블록회전 요청
     */
    public void requestMoveBlockUp() {
        ArrayList<Point> points = this.tetrisCurrentBlock.getRotatablePosition();
        boolean isEnable = this.background.isMovable(points);
        if (isEnable) {
            this.tetrisCurrentBlock.rotateBlock(points);
            Color[][] blockColorMap = this.tetrisCurrentBlock.getBlockColorMap();
            this.tetrisView.updateCurrentBlock(blockColorMap);
            this.tetrisView.repaint();

        }
    }

    /**
     * 게임판 블록하단이동 요청
     *
     * @param direction 이동방향
     */
    public void requestMoveBlockDown(TetrisCurrentBlock.Direction direction) {
        List<Point> movablePoints = this.tetrisCurrentBlock.getMovablePosition(direction);

        if (this.background.isAddible(movablePoints)) {
            doMoveDown(movablePoints);
            return;
        }
        List<Point> bottonPoints = this.tetrisCurrentBlock.getCurrentBlockPosition();
        Color color = this.tetrisCurrentBlock.getCurrentBlockColor();
        doMoveBotton(bottonPoints,color);
    }

    /**
     * 게임판 블록바닥이동 요청
     */
    public void requestMoveBlockBottom() {
        List<Point> currentBlockPosition = this.tetrisCurrentBlock.getCurrentBlockPosition();
        List<Point> bottomPoints = this.background.getBottomPoints(currentBlockPosition);
        Color color = this.tetrisCurrentBlock.getCurrentBlockColor();
        doMoveBotton(bottomPoints,color);



    }



    private void doMoveDown(List<Point> movablePoints) {
        this.tetrisCurrentBlock.moveBlock(movablePoints);
        Color[][] blockColorMap = this.tetrisCurrentBlock.getBlockColorMap();
        this.tetrisView.updateCurrentBlock(blockColorMap);
        this.tetrisView.repaint();


    }

    private void doMoveBotton(List<Point> currentPoints,Color color) {
        this.background.addBlock(currentPoints, color);
        this.tetrisCurrentBlock.requestNewBlock(this.tetrisNextBlock);
        this.tetrisNextBlock.initBlock();
        checkGameStatus(currentPoints);

        Color[][] backgroundColorMap =  this.background.getBackgroundColor();
        this.tetrisView.updateBackground(backgroundColorMap);

        Color[][] blockColorMap = this.tetrisCurrentBlock.getBlockColorMap();
        this.tetrisView.updateCurrentBlock(blockColorMap);

        Color[][] nextBlockColorMap = this.tetrisNextBlock.getBlockColorMap();
        this.tetrisView.updateNextBlock(nextBlockColorMap);

        this.tetrisView.repaint();


    }


    /**
     * 게임판화면 그리기
     */
/*
    public void repaintGame() {
    }
*/


    public void checkGameStatus(List<Point> currentPoints) {
        if (this.background.isEnd(currentPoints)) {
            this.status = Status.STOP;
            this.tetrisTimer.stop();
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
