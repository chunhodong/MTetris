
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;



public class GameBackgroundTest {

    @DisplayName("게임판널검사")
    @Test
    void 게임판널검사(){
        GameBackground gameBackground = new GameBackground();
        Assertions.assertThat(gameBackground).extracting("backgroundElement").isNotNull();

    }

    @Test
    void 게임판초기화필드값검사(){

        GameBackground gameBackground = new GameBackground();
        try {
            Field field = gameBackground.getClass().getDeclaredField("backgroundElement");
            field.setAccessible(true);

            int[][] value = (int[][])field.get(gameBackground);

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

}
