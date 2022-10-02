import model.TetrisBackground;
import model.TetrisBlock;
import model.TetrisOption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import view.TetrisView;

import static org.assertj.core.api.Assertions.assertThat;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TetrisViewTest {

    @InjectMocks
    private TetrisView tetrisView;


    @Test
    void 게임백그라운드세팅(){


        try {


            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(1,2));
            points.add(new Point(2,2));
            points.add(new Point(3,2));
            points.add(new Point(4,2));
            TetrisBackground gb = new TetrisBackground();
            gb.init();
            gb.addBlock(points,Color.BLUE);
            tetrisView.setGameBackground(gb);
            Field field = tetrisView.getClass().getDeclaredField("gameBackground");
            field.setAccessible(true);

            TetrisBackground value = (TetrisBackground)field.get(tetrisView);
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


            TetrisBlock tetrisBlock = new TetrisBlock();
            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][1] = 1;
            blocks[2][2] = 1;
            blocks[3][3] = 1;

            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(tetrisBlock,blocks);


            tetrisView.setGameBlock(tetrisBlock);


            Field field1 = tetrisView.getClass().getDeclaredField("gameBlock");
            field1.setAccessible(true);

            TetrisBlock value = (TetrisBlock)field1.get(tetrisView);
            assertThat(value.hasCurrentBlockElement(1,1)).isEqualTo(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }


    @Test
    void 게임백그라운드그리기(){

        try {

            TetrisBlock tetrisBlock = new TetrisBlock();
            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][1] = 1;
            blocks[2][2] = 1;
            blocks[3][3] = 1;

            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(tetrisBlock,blocks);


            tetrisView.setGameBlock(tetrisBlock);


            Field field1 = tetrisView.getClass().getDeclaredField("gameBlock");
            field1.setAccessible(true);

            TetrisBlock value = (TetrisBlock)field1.get(tetrisView);
            assertThat(value.hasCurrentBlockElement(1,1)).isEqualTo(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }


}
