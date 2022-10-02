
import model.TetrisBlock;
import model.TetrisOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TetrisBlockTest {



    @DisplayName("블록이동성공")
    @Test
    void 블록이동성공(){
        TetrisBlock tetrisBlock = new TetrisBlock();
        tetrisBlock.initCurrentBlock();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1,2));
        points.add(new Point(2,2));
        points.add(new Point(2,3));
        points.add(new Point(3,2));

        tetrisBlock.moveToBlock(points);

        try {
            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");

            field.setAccessible(true);

            int[][] value = (int[][])field.get(tetrisBlock);
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
            TetrisBlock tetrisBlock = new TetrisBlock();

            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][1] = 1;
            blocks[2][2] = 1;
            blocks[3][3] = 1;

            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(tetrisBlock,blocks);
            Assertions.assertThat(tetrisBlock.hasCurrentBlockElement(0,0)).isEqualTo(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블록요소확인실패(){

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
            Assertions.assertThat(tetrisBlock.hasCurrentBlockElement(0,2)).isEqualTo(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블록좌우이동가능포지션성공(){
        try {
            TetrisBlock tetrisBlock = new TetrisBlock();

            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][0] = 1;
            blocks[0][1] = 1;
            blocks[1][1] = 1;

            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(tetrisBlock,blocks);

            ArrayList<Point> points = tetrisBlock.getMovablePosition(TetrisBlock.Direction.RIGHT);
            Assertions.assertThat(points.size()).isEqualTo(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void 블록하단이동가능포지션성공(){
        try {
            TetrisBlock tetrisBlock = new TetrisBlock();

            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][0] = 1;
            blocks[0][1] = 1;
            blocks[1][1] = 1;

            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(tetrisBlock,blocks);

            ArrayList<Point> points = tetrisBlock.getMovablePosition(TetrisBlock.Direction.DOWN);
            Assertions.assertThat(points.size()).isEqualTo(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 현재블록위치조회성공(){
        try {
            TetrisBlock tetrisBlock = new TetrisBlock();

            int[][] blocks = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
            blocks[0][0] = 1;
            blocks[1][0] = 1;
            blocks[0][1] = 1;
            blocks[1][1] = 1;

            Field field = tetrisBlock.getClass().getDeclaredField("blockElementPosition");
            field.setAccessible(true);
            field.set(tetrisBlock,blocks);

            ArrayList<Point> points = tetrisBlock.getCurrentBlockPosition();
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


            TetrisBlock tetrisBlock = new TetrisBlock();

            Field field = tetrisBlock.getClass().getDeclaredField("blockColor");
            field.setAccessible(true);
            field.set(tetrisBlock,Color.BLUE);

            Color color = tetrisBlock.getCurrentBlockColor();
            Assertions.assertThat(color).isEqualTo(Color.BLUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void 게임블록방향판별여부성공(){
        try {
            TetrisBlock.Direction direction = TetrisBlock.Direction.LEFT;
            Assertions.assertThat(direction.isHorizontal()).isEqualTo(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void 블록회전좌표조회성공(){

        TetrisBlock tetrisBlock = new TetrisBlock();
        tetrisBlock.initCurrentBlock();

        ArrayList<Point> points = tetrisBlock.getRotatablePosition();
        assertThat(points.size()).isEqualTo(4);
    }


    @Test
    void 블록회전성공(){

        try {

            TetrisBlock tetrisBlock = new TetrisBlock();
            tetrisBlock.initCurrentBlock();
            Field field1 = tetrisBlock.getClass().getDeclaredField("blockShapeSet");
            field1.setAccessible(true);
            int [][][] blockShapeSet=new int[][][]{
                    {
                            {1,1,1,1},
                            {0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0}
                    },
                    {
                            {0,1,0,0},
                            {0,1,0,0},
                            {0,1,0,0},
                            {0,1,0,0},
                    }
            };
            field1.set(tetrisBlock,blockShapeSet);

            Field field2 = tetrisBlock.getClass().getDeclaredField("blockNumber");
            field2.setAccessible(true);
            field2.set(tetrisBlock,0);

            ArrayList<Point> list = new ArrayList<>();
            tetrisBlock.rotateBlock(list);

            Field field3 = tetrisBlock.getClass().getDeclaredField("initMaxX");
            field3.setAccessible(true);

            Field field4 = tetrisBlock.getClass().getDeclaredField("initMaxY");
            field4.setAccessible(true);

            int valueX = (int)field3.get(tetrisBlock);
            int valueY = (int)field4.get(tetrisBlock);
            assertThat(valueX).isEqualTo(3);
            assertThat(valueY).isEqualTo(5);



        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }




}
