package palak2048;

import javax.swing.*;
import java.awt.*;

public class scoreBoard extends JPanel {

    Matrix matrix;

    public boolean showGameOver = false;
    public boolean showGameWon = false;

    scoreBoard(Matrix mat){
        matrix = mat;
    }
    public void paintComponent(Graphics g) {

        g.setColor(Color.YELLOW);



        g.fillRoundRect(280, 100, 120, 120, 15, 15);

        g.setFont(new Font("Arial", Font.PLAIN, 48));

        g.setColor(Color.WHITE);

        g.drawString("2048", 284, 180);



        g.setColor(Color.DARK_GRAY);



        g.fillRoundRect(280, 25, 120, 40, 15, 15);

        g.setFont(new Font("Arial", Font.BOLD, 14));

        g.setColor(Color.WHITE);

        g.drawString("SCORE: ", 284, 38);

        g.drawString(matrix.getScore(), 284, 54);



        if (showGameOver) {

            g.setColor(Color.BLACK);

            g.setFont(new Font("Arial", Font.BOLD, 64));

            g.drawString("GAME OVER", 30, 240);



        }
        if (showGameWon) {

            g.setColor(Color.BLACK);

            g.setFont(new Font("Arial", Font.BOLD, 64));

            g.drawString("YOU WON!!", 30, 240);


        }

    }


    public void Update() {
        repaint();
    }
}
