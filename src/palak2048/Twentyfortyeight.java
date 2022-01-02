package palak2048;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Twentyfortyeight {
    public static void main(String[] args) throws InterruptedException {
        JFrame window = new JFrame();
        JPanel panel = new JPanel();

        panel.setSize(new Dimension(161, 161));

        List<aTile> Tiles = new ArrayList<>();
        int marker = 0;
        for (int j = 0; j <= 120; j = j + 40) {
            for (int i = 0; i <= 120; i = i + 40) {

                Tiles.add(new aTile(i, j));
                Tiles.get(marker).setSize(new Dimension(161, 161));
                Tiles.get(marker).setOpaque(false);
                marker++;
            }
        }
        Matrix mat = new Matrix(Tiles);
        mat.init_matrix();
        int number = (int) (Math.random() * 16);
        int get_row = number / 4;
        int get_column = number - (get_row * 4);

        Tiles.get(number).initTile();
        mat.setMatrixValue(get_row, get_column, Tiles.get(number).getTileValue());

        int number2 = (int) ((Math.random() * 16));
        while (number2 == number) {
            number2 = (int) ((Math.random() * 16));
        }
        get_row = number2 / 4;
        get_column = number2 - (get_row * 4);

        Tiles.get(number2).initTile();
        mat.setMatrixValue(get_row, get_column, Tiles.get(number2).getTileValue());

        Grid grid = new Grid();
        grid.setSize(new Dimension(161, 161));
        scoreBoard score = new scoreBoard(mat);
        score.setSize(new Dimension(480, 380));
        panel.setLayout(null);
        for (int i = 0; i < Tiles.size(); i++) {
            panel.add(Tiles.get(i));
        }
        score.setLayout(null);
        score.setOpaque(false);
        panel.add(score);
        panel.add(grid);
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(480, 380));
        window.setLocationRelativeTo(null);

        window.setVisible(true);
        keyBoard keyb = new keyBoard(mat, score);
        window.addKeyListener(keyb);
        window.setTitle("2048 Game.");

    }

}



