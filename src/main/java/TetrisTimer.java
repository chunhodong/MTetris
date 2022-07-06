import java.util.TimerTask;

public class TetrisTimer extends TimerTask{
	private GamePanel background;
	public TetrisTimer(GamePanel background){
		this.background=background;
	}
	public void run(){
		background.repaint();
	}

}
