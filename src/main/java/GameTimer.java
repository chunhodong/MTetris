import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {


    private Timer timer;

    public void run(GameController gameController,Timer timer){
        this.timer = timer;

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameController.requestMoveBlockDown(GameBlock.Direction.DOWN);
            }
        },1000, 1000);

    }

    public void stop() {
        this.timer.cancel();;

    }

    public void resume(GameController gameController,Timer timer) {
        this.timer = timer;

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameController.requestMoveBlockDown(GameBlock.Direction.DOWN);
            }
        }, 0,1000);
    }
}
