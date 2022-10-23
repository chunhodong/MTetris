
import model.TetrisBlock;
import model.TetrisCurrentBlock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.RandomUtils;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class TetrisBlockTest {


    private static MockedStatic<RandomUtils> mock;

    private TetrisBlock tetrisBlock;

    @BeforeAll
    public static void init() {
        mock = mockStatic(RandomUtils.class);
    }

    @AfterAll
    public static void close() {
        mock.close();
    }


    @BeforeEach
    public void initBlock() {
        tetrisBlock = new TetrisBlock() {
            @Override
            protected int getOffetY() {
                return 0;
            }
        };

    }

    @Test
    void 블록번호가1번일때_1번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(0, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[0][0];
        assertThat(color).isEqualTo(Color.BLUE);
        assertThat(tetrisBlock.getColor()).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가2번일때_2번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(1, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[0][1];
        assertThat(color).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가3번일때_3번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(2, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[1][2];
        assertThat(color).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가4번일때_4번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(3, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[2][0];
        assertThat(color).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가5번일때_5번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(4, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[2][1];
        assertThat(color).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가6번일때_6번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(5, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[1][2];
        assertThat(color).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가7번일때_7번위치에_지정된컬러값_반환() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(6, 0, 0);

        //when
        tetrisBlock.init();

        //then
        Color color = tetrisBlock.getColorMap()[1][0];
        assertThat(color).isEqualTo(Color.BLUE);

    }

    @Test
    void 블록번호가1에서7사이가이아닐때_예외발생() {

        //given
        mock.when(() -> RandomUtils.nextInt(anyInt()))
                .thenReturn(23, 0, 0);

        //when/then
        assertThatThrownBy(() -> tetrisBlock.init())
                .isInstanceOf(IllegalArgumentException.class);

    }


}
