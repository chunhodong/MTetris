package io;

import controller.TetrisController;
import model.TetrisCurrentBlock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyboard implements KeyListener {

    private TetrisController controller;

    public TetrisKeyboard(TetrisController controller){
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F1){
            this.controller.switchStart();
        }
        if(e.getKeyCode() == KeyEvent.VK_F2){
            this.controller.switchPause();
        }

        if(this.controller.getStatus() == TetrisController.Status.STOP)return;

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.controller.requestMoveBlockHorizontal(TetrisCurrentBlock.Direction.RIGHT);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            this.controller.requestMoveBlockHorizontal(TetrisCurrentBlock.Direction.LEFT);
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            this.controller.requestMoveBlockUp();
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            this.controller.requestMoveBlockDown(TetrisCurrentBlock.Direction.DOWN);
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            this.controller.requestMoveBlockBottom();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
