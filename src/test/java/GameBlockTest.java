
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class GameBlockTest {



    @DisplayName("블록이동성공")
    @Test
    void 블록이동성공(){
        GameBlock gameBlock = new GameBlock();
        gameBlock.initBlock();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1,2));
        points.add(new Point(2,2));
        points.add(new Point(2,3));
        points.add(new Point(3,2));

        gameBlock.moveToBlock(points);

        try {
            Field field = gameBlock.getClass().getDeclaredField("blockElementPosition");

            field.setAccessible(true);

            int[][] value = (int[][])field.get(gameBlock);
            Assertions.assertThat(value[1][2]).isEqualTo(1);
            Assertions.assertThat(value[2][2]).isEqualTo(1);
            Assertions.assertThat(value[2][3]).isEqualTo(1);
            Assertions.assertThat(value[3][2]).isEqualTo(1);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
    @Test
    void 블록요소확인성공(){

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
            Assertions.assertThat(gameBlock.hasBlockElement(0,0)).isEqualTo(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블록요소확인실패(){

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
            Assertions.assertThat(gameBlock.hasBlockElement(0,2)).isEqualTo(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블록좌우이동가능포지션성공(){
        try {
            GameBlock gameBlock = new GameBlock();

            int[][] blocks = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][0] = 1;
            blocks[0][1] = 1;
            blocks[1][1] = 1;

            Field field = gameBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(gameBlock,blocks);

            ArrayList<Point> points = gameBlock.getMovablePosition(GameBlock.Direction.RIGHT);
            Assertions.assertThat(points.size()).isEqualTo(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void 블록하단이동가능포지션성공(){
        try {
            GameBlock gameBlock = new GameBlock();

            int[][] blocks = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][0] = 1;
            blocks[0][1] = 1;
            blocks[1][1] = 1;

            Field field = gameBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(gameBlock,blocks);

            ArrayList<Point> points = gameBlock.getMovablePosition(GameBlock.Direction.DOWN);
            Assertions.assertThat(points.size()).isEqualTo(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 현재블록위치조회성공(){
        try {
            GameBlock gameBlock = new GameBlock();

            int[][] blocks = new int[GameOption.BOARD_HEIGHT][GameOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][0] = 1;
            blocks[0][1] = 1;
            blocks[1][1] = 1;

            Field field = gameBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(gameBlock,blocks);

            ArrayList<Point> points = gameBlock.getCurrentBlockPosition();
            Assertions.assertThat(points.size()).isEqualTo(4);
            Assertions.assertThat(points.get(0).x).isEqualTo(0);
            Assertions.assertThat(points.get(0).y).isEqualTo(0);

            Assertions.assertThat(points.get(1).x).isEqualTo(0);
            Assertions.assertThat(points.get(1).y).isEqualTo(1);

            Assertions.assertThat(points.get(2).x).isEqualTo(1);
            Assertions.assertThat(points.get(2).y).isEqualTo(0);

            Assertions.assertThat(points.get(3).x).isEqualTo(1);
            Assertions.assertThat(points.get(3).y).isEqualTo(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void 현재블록컬러조회성공(){
        try {


            GameBlock gameBlock = new GameBlock();

            Field field = gameBlock.getClass().getDeclaredField("blockColor");
            field.setAccessible(true);
            field.set(gameBlock,Color.BLUE);

            Color color = gameBlock.getCurrentBlockColor();
            Assertions.assertThat(color).isEqualTo(Color.BLUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void 게임블록방향판별여부성공(){
        try {
            GameBlock.Direction direction = GameBlock.Direction.LEFT;
            Assertions.assertThat(direction.isHorizontal()).isEqualTo(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
