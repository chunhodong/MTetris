package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 게임배경데이터
 */
public class TetrisBackground {

    private int[][] positionMap;
    private Color[][] colorMap;

    public TetrisBackground(){

    }

    /**
     * 게임배경초기화
     */
    public void init(){
        this.positionMap = new int[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
        this.colorMap = new Color[TetrisOption.BOARD_HEIGHT][TetrisOption.BOARD_WIDTH];
        for (Color[] colors : this.colorMap) {
            Arrays.fill(colors, Color.BLACK);
        }
    }

    /**
     * 게임판에서 블록 좌우이동여부 확인
     * @param blockPoints 블록위치배열
     * @return 블록좌우이동 가능여부
     */
    public boolean isMovable(ArrayList<Point> blockPoints){
        return blockPoints.stream()
                .filter(point -> point.getY() < TetrisOption.BOARD_WIDTH)
                .filter(point -> point.getY() >= 0)
                .filter(point -> this.positionMap[(int)point.getX()][(int)point.getY()] == 0)
                .count() == 4;

    }

    /**
     * 게임판에서 블록 하단이동여부 확인
     * @param blockPoints 블록위치배열
     * @return 블록하단이동여부
     */
    public boolean isAddible(List<Point> blockPoints){
        return blockPoints.stream()

                .filter(point -> point.getX() < TetrisOption.BOARD_HEIGHT)
                .filter(point -> this.positionMap[(int)point.getX()][(int)point.getY()] == 0)
                .count() == 4;

    }


    /**
     * 게임종료여부 확인
     * @param currentPoints 블록포지션
     * @return 게임종료여부
     */
    public boolean isEnd( List<Point> currentPoints ) {
        return currentPoints.stream().anyMatch(point -> this.positionMap[(int) point.getX()][(int) point.getY()] == 1);
    }

    /**
     * 게임판에 블록을 배경으로 추가
     * @param blockPoints 블록위치배열
     * @param color 블록컬러
     */
    public void addBlock(List<Point> blockPoints, Color color) {
        blockPoints.forEach(point -> {
                    this.positionMap[(int)point.getX()][(int)point.getY()] = 1;
                    this.colorMap[(int)point.getX()][(int)point.getY()] = color;
                });

        clearLines();

    }


    /**
     * 게임배경컬러조회
     * @return 게임배경컬러맵
     */
    public Color[][] getColorMap(){
        return this.colorMap;
    }

    /**
     * 게임배경에서 제거할라인확인
     */
    private void clearLines() {

        for(int i = TetrisOption.BOARD_HEIGHT - 1; i > 0; i--){
            int checkCount = 0;

            for(int j = 0; j < TetrisOption.BOARD_WIDTH; j++){
                if(this.positionMap[i][j] == 1){
                    checkCount++;
                }

            }
            if(checkCount == TetrisOption.BOARD_WIDTH){
                removeLine(i);
                i++;
            }
        }


    }

    /**
     * 특정라인제거
     * @param lineIdx 라인인덱스
     */
    public void removeLine(Integer lineIdx){

        for(int i = lineIdx - 1; i >= 0; i--){
            for(int j = 0; j < TetrisOption.BOARD_WIDTH; j++){
                this.positionMap[i + 1][j] = this.positionMap[i][j];
                this.colorMap[i + 1][j] = this.colorMap[i][j];
            }
        }

        IntStream.range(1, TetrisOption.BOARD_WIDTH)
                .forEach(value -> {
                    this.positionMap[0][value - 1] = 0;
                    this.colorMap[0][value - 1] = Color.BLACK;
                });

    }


    /**
     * 현재블록위치에서 블록바닥위치값 조회
     * @param points 현재블록위치
     * @return 블록바닥위치
     */
    public List<Point> getBottomPoints(List<Point> points) {
        for(int i = 1; i < TetrisOption.BOARD_HEIGHT; i++){
            int addX = i;
            List<Point> movePoints = points.stream()
                    .map(point -> new Point((int) (point.getX() + addX), (int) point.getY()))
                    .collect(Collectors.toList());

            long bottonCount = movePoints
                    .stream()
                    .filter(point -> point.getX() == TetrisOption.BOARD_HEIGHT).count();

            if(bottonCount > 0){
                return points.stream()
                        .map(point -> new Point((int) (point.getX() + addX - 1), (int) point.getY()))
                        .collect(Collectors.toList());

            }

            long adjacentCount = movePoints.stream().filter(point -> this.positionMap[(int) point.getX()][(int) point.getY()] == 1).count();
            if(adjacentCount > 0){
                return points.stream()
                        .map(point -> new Point((int) (point.getX() + addX - 1), (int) point.getY())).collect(Collectors.toList());


            }
        }

        return new ArrayList<>();
    }

}
