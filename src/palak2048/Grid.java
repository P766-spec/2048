package palak2048;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public final  class Grid extends JPanel {
    Grid(){
        update();
    }

    public void update() {
        this.repaint();
    }

    public void paintCompnent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 160, 160);
        g.drawLine(40, 0, 40, 160);
        g.drawLine(80, 0, 80, 160);
        g.drawLine(120, 0, 120, 160);
        g.drawLine(0, 40, 160, 40);
        g.drawLine(0, 80, 160, 80);
        g.drawLine(0, 120, 160, 120);

    }
}
