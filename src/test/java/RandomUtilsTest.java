import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomUtilsTest {

    @Test
    void 랜덤값반환테스트(){
        RandomUtils randomUtils = new RandomUtils();
        int result = RandomUtils.nextInt(5);
        assertThat(result).isLessThan(5);
    }
}
