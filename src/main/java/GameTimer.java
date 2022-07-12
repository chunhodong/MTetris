import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {


    @Override
    public void schedule(TimerTask task, Date time) {
        super.schedule(task, time);
    }

    public void run(GameController gameController){

        schedule(new TimerTask() {
            @Override
            public void run() {
                gameController.repaintGame();
            }
        },0, 2000);

    }
}
