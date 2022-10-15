import model.TetrisCurrentBlock;
import model.TetrisNextBlock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.RandomUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class TetrisCurrentBlockTest {


    private static MockedStatic<RandomUtils> mock;

    @BeforeAll
    public static void init() {
        mock = mockStatic(RandomUtils.class);
    }

    @AfterAll
    public static void close() {
        mock.close();
    }

    @Test
    void move메소드는_블록의_위치를_이동() {

        //given
        TetrisCurrentBlock tetrisBlock = new TetrisCurrentBlock();

        //when
        List<Point> points = List.of(new Point(1, 2), new Point(2, 2), new Point(2, 3), new Point(3, 2));
        tetrisBlock.init();
        tetrisBlock.move(points);

        //then
        List<Point> savedPoints = tetrisBlock.getCurrentPoints();
        assertThat(savedPoints.size()).isEqualTo(4);
        assertThat(savedPoints.get(0).x).isEqualTo(1);
        assertThat(savedPoints.get(0).y).isEqualTo(2);
    }

    @Test
    void rotate메소드는_블록을회전시킴() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(2, 0, 0);

        //when
        TetrisCurrentBlock tetrisCurrentBlock = new TetrisCurrentBlock();
        tetrisCurrentBlock.init();

        //then
        tetrisCurrentBlock.rotate(tetrisCurrentBlock.getRotatablePoints());
        List<Point> savedPoints = tetrisCurrentBlock.getCurrentPoints();
        assertThat(savedPoints.get(0).x).isEqualTo(0);
        assertThat(savedPoints.get(0).y).isEqualTo(5);
        assertThat(savedPoints.get(1).x).isEqualTo(1);
        assertThat(savedPoints.get(1).y).isEqualTo(4);
        assertThat(savedPoints.get(2).x).isEqualTo(1);
        assertThat(savedPoints.get(2).y).isEqualTo(5);
        assertThat(savedPoints.get(3).x).isEqualTo(2);
        assertThat(savedPoints.get(3).y).isEqualTo(5);
    }


    @Test
    void getMovablePoints메소드는_오른쪽을입력값으로주면_블록의_이동위치를_반환() {

        //given
        TetrisCurrentBlock tetrisBlock = new TetrisCurrentBlock();

        //when
        List<Point> points = List.of(new Point(1, 2), new Point(2, 2), new Point(2, 3), new Point(3, 2));
        tetrisBlock.init();
        tetrisBlock.move(points);

        //then
        ArrayList<Point> savedPoints = tetrisBlock.getMovablePoints(TetrisCurrentBlock.Direction.RIGHT);
        assertThat(savedPoints.get(0).x).isEqualTo(1);
        assertThat(savedPoints.get(0).y).isEqualTo(3);
    }

    @Test
    void getMovablePoints메소드는_왼쪽을입력값으로주면_블록의_이동위치를_반환() {

        //given
        TetrisCurrentBlock tetrisBlock = new TetrisCurrentBlock();

        //when
        List<Point> points = List.of(new Point(1, 2), new Point(2, 2), new Point(2, 3), new Point(3, 2));
        tetrisBlock.init();
        tetrisBlock.move(points);

        //then
        ArrayList<Point> savedPoints = tetrisBlock.getMovablePoints(TetrisCurrentBlock.Direction.LEFT);
        assertThat(savedPoints.get(0).x).isEqualTo(1);
        assertThat(savedPoints.get(0).y).isEqualTo(1);
        assertThat(savedPoints.get(1).x).isEqualTo(2);
        assertThat(savedPoints.get(1).y).isEqualTo(1);

    }


    @Test
    void getMovablePoints메소드는_아래쪽을입력값으로주면_블록의_이동위치를_반환() {

        //given
        TetrisCurrentBlock tetrisBlock = new TetrisCurrentBlock();

        //when
        List<Point> points = List.of(new Point(1, 2), new Point(2, 2), new Point(2, 3), new Point(3, 2));
        tetrisBlock.init();
        tetrisBlock.move(points);

        ArrayList<Point> savedPoints = tetrisBlock.getMovablePoints(TetrisCurrentBlock.Direction.DOWN);
        assertThat(savedPoints.get(0).x).isEqualTo(2);
        assertThat(savedPoints.get(0).y).isEqualTo(2);

    }


    @Test
    void getRotatablePoints메소드는_블록을회전시켰을때_위치반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(2, 0, 0);

        //when
        TetrisCurrentBlock tetrisCurrentBlock = new TetrisCurrentBlock();
        tetrisCurrentBlock.init();

        //then
        List<Point> savedPoints = tetrisCurrentBlock.getRotatablePoints();
        assertThat(savedPoints.get(0).x).isEqualTo(0);
        assertThat(savedPoints.get(0).y).isEqualTo(5);
        assertThat(savedPoints.get(1).x).isEqualTo(1);
        assertThat(savedPoints.get(1).y).isEqualTo(4);
        assertThat(savedPoints.get(2).x).isEqualTo(1);
        assertThat(savedPoints.get(2).y).isEqualTo(5);
        assertThat(savedPoints.get(3).x).isEqualTo(2);
        assertThat(savedPoints.get(3).y).isEqualTo(5);
    }


    @Test
    void getCurrentPoints메소드는_블록현재위치반환() {

        //given
        TetrisCurrentBlock tetrisBlock = new TetrisCurrentBlock();

        //when
        List<Point> points = List.of(new Point(1, 2), new Point(2, 2), new Point(2, 3), new Point(3, 2));
        tetrisBlock.init();
        tetrisBlock.move(points);

        List<Point> savedPoints = tetrisBlock.getCurrentPoints();
        assertThat(savedPoints.get(0).x).isEqualTo(1);
        assertThat(savedPoints.get(0).y).isEqualTo(2);
    }

    @Test
    void getEdgePoints메소드는_블록가장자리위치반환() {

        //given
        TetrisCurrentBlock tetrisBlock = new TetrisCurrentBlock();

        //when
        Point point = tetrisBlock.getEdgePoint(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 0}
        });

        //then
        assertThat(point.x).isEqualTo(3);
        assertThat(point.y).isEqualTo(3);
    }

    @Test
    void change메소드는_현재블록을_다음블록으로교체() {
        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(2, 0, 0,0,0,0);
        TetrisCurrentBlock currentBlock = new TetrisCurrentBlock();
        currentBlock.init();
        TetrisNextBlock nextBlock = new TetrisNextBlock();
        nextBlock.init();

        //when
        currentBlock.change(nextBlock);

        //then
        List<Point> points = currentBlock.getCurrentPoints();
        assertThat(points.size()).isEqualTo(4);
        assertThat(points.get(0).x).isEqualTo(0);
        assertThat(points.get(0).y).isEqualTo(4);
        assertThat(points.get(1).x).isEqualTo(0);
        assertThat(points.get(1).y).isEqualTo(5);
        assertThat(points.get(2).x).isEqualTo(0);
        assertThat(points.get(2).y).isEqualTo(6);
        assertThat(points.get(3).x).isEqualTo(0);
        assertThat(points.get(3).y).isEqualTo(7);

    }

}
