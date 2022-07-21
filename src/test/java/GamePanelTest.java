import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class GamePanelTest {

    @InjectMocks
    private GamePanel gamePanel;


    @Test
    void 게임백그라운드세팅(){


        try {


            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(1,2));
            points.add(new Point(2,2));
            points.add(new Point(3,2));
            points.add(new Point(4,2));
            GameBackground gb = new GameBackground();
            gb.init();
            gb.addBlock(points,Color.BLUE);
            gamePanel.setGameBackground(gb);
            Field field = gamePanel.getClass().getDeclaredField("gameBackground");
            field.setAccessible(true);

            GameBackground value = (GameBackground)field.get(gamePanel);
            assertThat(value.getColor(4,2)).isEqualTo(Color.BLUE);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 게임블록세팅(){

        try {


            GameBlock gameBlock = new GameBlock();
            int[][] blocks = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][1] = 1;
            blocks[2][2] = 1;
            blocks[3][3] = 1;

            Field field = gameBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(gameBlock,blocks);


            gamePanel.setGameBlock(gameBlock);


            Field field1 = gamePanel.getClass().getDeclaredField("gameBlock");
            field1.setAccessible(true);

            GameBlock value = (GameBlock)field1.get(gamePanel);
            assertThat(value.hasBlockElement(1,1)).isEqualTo(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }


    @Test
    void 게임백그라운드그리기(){

        try {

            GameBlock gameBlock = new GameBlock();
            int[][] blocks = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][1] = 1;
            blocks[2][2] = 1;
            blocks[3][3] = 1;

            Field field = gameBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(gameBlock,blocks);


            gamePanel.setGameBlock(gameBlock);


            Field field1 = gamePanel.getClass().getDeclaredField("gameBlock");
            field1.setAccessible(true);

            GameBlock value = (GameBlock)field1.get(gamePanel);
            assertThat(value.hasBlockElement(1,1)).isEqualTo(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }


}
