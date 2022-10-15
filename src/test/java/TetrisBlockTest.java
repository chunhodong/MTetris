import model.TetrisCurrentBlock;
import model.TetrisOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TetrisBlockTest {


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


    }


}
