
import model.TetrisBackground;
import model.TetrisOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@PrepareForTest(TetrisBackground.class)
public class TetrisBackgroundTest {

    @DisplayName("게임판널검사")
    @Test
    void 게임판널검사(){
        TetrisBackground tetrisBackground = new TetrisBackground();
        Assertions.assertThat(tetrisBackground).extracting("backgroundElement").isNotNull();

    }

    @Test
    void 게임판초기화필드값검사(){

        TetrisBackground tetrisBackground = new TetrisBackground();
        try {
            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);

            int[][] value = (int[][])field.get(tetrisBackground);

            for(int i = 0; i < value.length; i++){
                for(int j = 0; j < value[0].length; j++){

                    Assertions.assertThat(value[0][0]).isEqualTo(0);

                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블록이동여부가능(){

        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];

            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);
            field.set(tetrisBackground,blocks);

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(4,4));
            points.add(new Point(5,4));
            points.add(new Point(6,4));
            points.add(new Point(7,4));

            boolean result = tetrisBackground.isMovable(points);
            Assertions.assertThat(result).isEqualTo(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블록이동여부불가능_기존백그라운드에존재(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            int[][] wall = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            wall[4][4] = 1;
            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);
            field.set(tetrisBackground,wall);

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(4,4));
            points.add(new Point(5,4));
            points.add(new Point(6,4));
            points.add(new Point(7,4));

            boolean result = tetrisBackground.isMovable(points);
            Assertions.assertThat(result).isEqualTo(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 블록이동여부불가능_Y값범위이탈(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            int[][] wall = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            wall[4][4] = 1;
            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);
            field.set(tetrisBackground,wall);

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(4,-4));
            points.add(new Point(5,4));
            points.add(new Point(6,89));
            points.add(new Point(7,4));

            boolean result = tetrisBackground.isMovable(points);
            Assertions.assertThat(result).isEqualTo(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }



    @Test
    void 블록을배경에추가여부_성공(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            int[][] wall = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];

            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);
            field.set(tetrisBackground,wall);

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(4,4));
            points.add(new Point(5,4));
            points.add(new Point(6,4));
            points.add(new Point(7,4));

            boolean result = tetrisBackground.isAddible(points);
            Assertions.assertThat(result).isEqualTo(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 블록을배경에추가여부_실패_X값이탈(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            int[][] wall = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];

            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);
            field.set(tetrisBackground,wall);

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(84,4));
            points.add(new Point(5,4));
            points.add(new Point(6,4));
            points.add(new Point(7,4));

            boolean result = tetrisBackground.isAddible(points);
            Assertions.assertThat(result).isEqualTo(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 블록을배경에추가여부_실패_기존배경에존재(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            int[][] wall = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            wall[7][4] = 1;
            Field field = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);
            field.set(tetrisBackground,wall);

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(4,4));
            points.add(new Point(5,4));
            points.add(new Point(6,4));
            points.add(new Point(7,4));

            boolean result = tetrisBackground.isAddible(points);
            Assertions.assertThat(result).isEqualTo(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 블록을배경에추가성공(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();
            tetrisBackground.init();;

            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(4,4));
            points.add(new Point(5,4));
            points.add(new Point(6,4));
            points.add(new Point(7,4));

            tetrisBackground.addBlock(points,Color.BLUE);

            Field field1 = tetrisBackground.getClass().getDeclaredField("backgroundElement");

            field1.setAccessible(true);

            int[][] value = (int[][])field1.get(tetrisBackground);
            Assertions.assertThat(value[4][4]).isEqualTo(1);
            Assertions.assertThat(value[5][4]).isEqualTo(1);
            Assertions.assertThat(value[6][4]).isEqualTo(1);
            Assertions.assertThat(value[7][4]).isEqualTo(1);

            Field field2 = tetrisBackground.getClass().getDeclaredField("backgroundColor");

            field2.setAccessible(true);

            Color[][] value2 = (Color[][])field2.get(tetrisBackground);
            Assertions.assertThat(value2[4][4]).isEqualTo(Color.BLUE);
            Assertions.assertThat(value2[5][4]).isEqualTo(Color.BLUE);
            Assertions.assertThat(value2[6][4]).isEqualTo(Color.BLUE);
            Assertions.assertThat(value2[7][4]).isEqualTo(Color.BLUE);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 배경컬러값조회성공(){
        try {
            TetrisBackground tetrisBackground = new TetrisBackground();

            Color[][] backgroundColor = new Color[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            backgroundColor[0][0] = Color.YELLOW;
            backgroundColor[0][1] = Color.YELLOW;

            Field field = tetrisBackground.getClass().getDeclaredField("backgroundColor");
            field.setAccessible(true);
            field.set(tetrisBackground,backgroundColor);


            Color color = tetrisBackground.getColor(0,1);
            Assertions.assertThat(color).isEqualTo(Color.YELLOW);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 블록라인삭제성공(){

        try {
            TetrisBackground tetrisBackground = new TetrisBackground();
            tetrisBackground.init();;

            int[][] backgroundElement = new int[20][10];
            Color[][] backgroundColor = new Color[20][10];

            for(int i = 0; i < 10; i++){
                backgroundElement[17][i] = i % 2 == 0 ? 1 : 0;
                backgroundColor[17][i] = Color.RED;

                backgroundElement[18][i] = 1;
                backgroundColor[18][i] = Color.BLUE;

            }


            Field field1 = tetrisBackground.getClass().getDeclaredField("backgroundElement");
            field1.setAccessible(true);
            field1.set(tetrisBackground,backgroundElement);


            Field field2 = tetrisBackground.getClass().getDeclaredField("backgroundColor");
            field2.setAccessible(true);
            field2.set(tetrisBackground,backgroundColor);


            tetrisBackground.removeLine(18);

            for(int i = 0; i < 10; i++){
                Assertions.assertThat(backgroundElement[18][i]).isEqualTo(i % 2 == 0 ? 1 : 0);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
