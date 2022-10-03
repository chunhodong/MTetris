package model;

import controller.TetrisController;

import java.util.Timer;
import java.util.TimerTask;

public class TetrisTimer {


    private Timer timer;

    public void run(TetrisController tetrisController, Timer timer){
        stop();
        this.timer = timer;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tetrisController.requestMoveBlockDown(TetrisCurrentBlock.Direction.DOWN);
            }
        },1000, 1000);

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
        }, 0,1000);
    }
}
