package model;

import controller.TetrisController;

import java.util.Timer;
import java.util.TimerTask;

public class TetrisTimer {


    private Timer timer;
    private static final int TIME_PERIOD = 1000;
    private static final int RUN_DELAY = 1000;
    private static final int RESUME_DEPLAY = 0;

    public void run(TetrisController tetrisController, Timer timer){
        stop();
        this.timer = timer;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tetrisController.requestMoveBlockDown(TetrisCurrentBlock.Direction.DOWN);
            }
        },RUN_DELAY, TIME_PERIOD);

    }

    public void stop() {
        if(this.timer != null)
            this.timer.cancel();

    }

    public void resume(TetrisController tetrisController, Timer timer) {
        this.timer = timer;

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tetrisController.requestMoveBlockDown(TetrisCurrentBlock.Direction.DOWN);
            }
        }, RESUME_DEPLAY,TIME_PERIOD);
    }
}
