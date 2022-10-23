package model;

public class TetrisNextBlock extends TetrisBlock{

    private static final int OFFSET_Y = 0;

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected int getOffetY() {
        return OFFSET_Y;
    }

}
