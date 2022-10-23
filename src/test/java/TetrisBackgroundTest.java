import model.TetrisBackground;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class TetrisBackgroundTest {


    @Test
    void isMovable메소드는_겹치는블록이없으면_true반환() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 4));
        points.add(new Point(5, 4));
        points.add(new Point(6, 4));
        points.add(new Point(7, 4));

        //when
        boolean result = tetrisBackground.isMovable(points);

        //then
        assertThat(result).isEqualTo(true);

    }

    @Test
    void isMovable메소드는_겹치는블록이있으면_false반환() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 4));
        points.add(new Point(5, 4));
        points.add(new Point(6, 4));
        points.add(new Point(7, 4));
        tetrisBackground.addBlock(points, Color.BLUE);

        //when
        boolean result = tetrisBackground.isMovable(points);

        //then
        assertThat(result).isEqualTo(false);

    }

    @Test
    void isMovable메소드는_블록이배경범위를이탈했으면_false반환() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 24));
        points.add(new Point(5, 4));
        points.add(new Point(6, 4));
        points.add(new Point(7, 4));


        //when
        boolean result = tetrisBackground.isMovable(points);

        //then
        assertThat(result).isEqualTo(false);

    }

    @Test
    void addBlock메소드는_배경에서_가로를다채운라인제거() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();

        //when
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if(i % 2 == 0)
                points.add(new Point(18, i));

        }
        tetrisBackground.addBlock(points,Color.YELLOW);

        points = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            points.add(new Point(19, i));
        }
        tetrisBackground.addBlock(points,Color.BLUE);

        //then
        Color color = tetrisBackground.getColorMap()[19][0];
        assertThat(color).isEqualTo(Color.YELLOW);
    }


    @Test
    void addBlock메소드는_추가한위치의_컬러값이바뀐다() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 4));
        points.add(new Point(5, 4));
        points.add(new Point(6, 4));
        points.add(new Point(7, 4));

        //when
        tetrisBackground.addBlock(points, Color.BLUE);
        Color addColor = tetrisBackground.getColorMap()[4][4];
        Color noneAddColor = tetrisBackground.getColorMap()[0][0];

        //then
        assertThat(addColor).isEqualTo(Color.BLUE);
        assertThat(noneAddColor).isEqualTo(Color.BLACK);

    }

    @Test
    void isOverlap메소드는_배경위치와_겹치는블록을추가할경우_true반환() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 4));
        points.add(new Point(5, 4));
        points.add(new Point(6, 4));
        points.add(new Point(7, 4));
        tetrisBackground.addBlock(points,Color.BLUE);

        //when
        ArrayList<Point> overlatPoints = new ArrayList<>();
        overlatPoints.add(new Point(4, 4));
        overlatPoints.add(new Point(4, 5));
        overlatPoints.add(new Point(4, 6));
        overlatPoints.add(new Point(4, 7));
        boolean isOverlap = tetrisBackground.isOverlap(overlatPoints);

        //then
        assertThat(isOverlap).isTrue();


    }

    @Test
    void getBottomPoints메소드는_현재블록이_스페이스를눌렀을떄_위치반환() {

        //given
        TetrisBackground tetrisBackground = new TetrisBackground();
        tetrisBackground.init();
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(19, 4));
        points.add(new Point(19, 5));
        points.add(new Point(19, 6));
        points.add(new Point(19, 7));
        tetrisBackground.addBlock(points,Color.BLUE);

        //when
        ArrayList<Point> addPoints = new ArrayList<>();
        addPoints.add(new Point(0, 1));
        addPoints.add(new Point(0, 2));
        addPoints.add(new Point(0, 3));
        addPoints.add(new Point(0, 4));
        List<Point> bottomPoints = tetrisBackground.getBottomPoints(addPoints);

        //then
        assertThat(bottomPoints.get(0).x).isEqualTo(18);
        assertThat(bottomPoints.get(1).x).isEqualTo(18);
        assertThat(bottomPoints.get(2).x).isEqualTo(18);
        assertThat(bottomPoints.get(3).x).isEqualTo(18);
        assertThat(bottomPoints.get(0).y).isEqualTo(1);
        assertThat(bottomPoints.get(1).y).isEqualTo(2);
        assertThat(bottomPoints.get(2).y).isEqualTo(3);
        assertThat(bottomPoints.get(3).y).isEqualTo(4);
    }

}
