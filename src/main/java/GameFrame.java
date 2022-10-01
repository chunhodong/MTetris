import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {

    /*x좌표*/
    public final int xPos = 400;
    /*y좌표*/
    public final int yPos = 100;
    /*화면크기*/
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    /*화면가로길이*/
    private int width = (int) (screen.width*0.25);
    /*화면세로길이*/
    private int height = (int) (screen.height*0.57);

    public GameFrame(JPanel gamePanel,KeyListener keyListener){
        setLayout(null);
        setBounds(xPos,yPos,width,height);
        setResizable(false);
        setVisible(true);
        add(gamePanel);
        addKeyListener(keyListener);

    }
}
