import org.junit.jupiter.api.Test;

public class GameControllerTest {



    @Test
    void 블록바닥이동요청성공(){
        GameController gameController = new GameController(new GamePanel());
        gameController.initGame();
        gameController.requestMoveBlockBottom();
        
        //현재 블록위치 구하기
        //현재 게임판에서 바닥위치구하기
        
        //바닥에 내리꽃기



    }


}
