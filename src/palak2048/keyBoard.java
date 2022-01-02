package palak2048;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class keyBoard implements KeyListener {

    Matrix mat;
    scoreBoard board;

    keyBoard(Matrix myMatrix, scoreBoard score) {
        mat = myMatrix;
        board = score;
    }

    private void doGameOver() {
        if (mat.game_Over) {
            board.showGameOver = true;
        } else if(mat.game_Won) {
            board.showGameWon = true;
        }

        board.Update();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        switch (c) {
            case KeyEvent.VK_UP:
                if (!mat.game_Over) {
                    board.Update();
                    mat.slide_Up();
                    if(mat.game_Won){
                        doGameOver();
                    }
                } else {
                    doGameOver();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!mat.game_Over) {
                    board.Update();
                    mat.slide_Down();
                    if(mat.game_Won){
                        doGameOver();
                    }
                } else {
                    doGameOver();
                }
                break;
            case KeyEvent.VK_LEFT: {
                if (!mat.game_Over) {
                    board.Update();
                    try {
                        mat.slide_Left();
                        if(mat.game_Won){
                            doGameOver();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(keyBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    doGameOver();
                }
            }

            break;
            case KeyEvent.VK_RIGHT:
                if (!mat.game_Over) {
                    board.Update();
                    mat.slide_Right();
                    if(mat.game_Won){
                        doGameOver();
                    }
                } else {
                    doGameOver();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

