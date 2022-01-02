package palak2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class aTile extends JPanel implements ActionListener {

    private int _x = 0;
    private int _y = 0;
    private int tile_value = 0;
    private boolean tile = false;
    private int font_offset_x = 14;
    private boolean tile_glow = false;
    private int counter = 0;
    private final Timer timer = new Timer(250, this);

    aTile(int x, int y) throws InterruptedException {

        _x = x;
        _y = y;

    }

    public void initTile() throws InterruptedException {

        int rand = (int) (Math.random() * 2);
        if (rand == 0) {
            drawTile2();
        } else {
            drawTile4();
        }
        timer.setRepeats(true);
        timer.start();
    }

    private void drawTile2() throws InterruptedException {

        tile = true;
        tile_value = 2;
        repaint();
    }

    private void drawTile4() throws InterruptedException {

        tile = true;
        tile_value = 4;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        if (tile && !tile_glow && tile_value != 0) {

            g.setColor(Color.BLACK);
            g.drawRect(_x, _y, 40, 40);

            if (tile_value != 0) {
                g.setColor(getTileColor(tile_value));
            } else {
                g.setColor(new Color(238, 238, 238));
            }
            g.fillRect(_x + 2, _y + 2, 37, 37);
            g.setColor(Color.WHITE);
            g.setFont(setFontOnValue(tile_value));
            if (tile_value != 0) {
                g.drawString(String.valueOf(tile_value), _x + font_offset_x, _y + 27);
            }

        }

        if (tile_glow) {
            /*g.setColor(Color.BLACK);
                g.drawRect(_x, _y, 40, 40);*/
            g.setColor(Color.getHSBColor(40, 240, 128));

            g.fillRect(_x + 2, _y + 2, 37, 37);
            if (tile) {
                g.setColor(Color.WHITE);
                g.setFont(setFontOnValue(tile_value));
                if (tile_value != 0) {
                    g.drawString(String.valueOf(tile_value), _x + 14, _y + 27);
                }
            }
        }

    }

    void updateTile() {
        tile_glow = false;
        tile = true;
        this.repaint();
    }

    void setGlow(boolean value) {
        tile_glow = value;
        tile = true;
        timer.setRepeats(true);
        timer.start();

    }

    int getTileValue() {
        return tile_value;
    }

    void setTileValue(int val) {
        tile_value = val;
    }

    int getCurrentx() {
        return _x;
    }

    Color getTileColor(int value) {

        if (value == 8) {
            return new Color(255, 127, 39);
        }
        if (value == 16) {
            return new Color(128, 255, 0);
        }
        if (value == 32) {
            return new Color(255, 0, 0);
        }
        if (value == 64) {
            return new Color(0, 0, 255);
        }
        if (value == 128) {
            return new Color(255, 128, 128);
        }
        if (value == 256) {
            return new Color(128, 0, 255);
        }
        if (value == 512) {
            return new Color(128, 128, 0);
        }
        if (value == 1024) {
            return new Color(0, 255, 255);
        }
        if (value == 2048) {
            return new Color(255, 128, 0);
        }
        return Color.GRAY;
    }

    Font setFontOnValue(int value) {
        if (value > 8 && value < 128) {
            font_offset_x = 12;
            return new Font("Arial", Font.PLAIN, 18);

        }
        if (value >= 128 && value < 1024) {
            font_offset_x = 8;
            return new Font("Arial", Font.BOLD, 14);
        }
        if (value >= 1024) {
            font_offset_x = 2;
            return new Font("Arial", Font.BOLD, 14);
        }
        font_offset_x = 14;
        return new Font("Arial", Font.PLAIN, 20);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer && tile_glow == false) {
            tile_glow = true;
            this.repaint();
        } else if (event.getSource() == timer && tile_glow == true) {
            tile_glow = false;
            this.repaint();
        }
        if (event.getSource() == timer) {
            counter++;
        }

        if (counter > 5) {
            timer.stop();
            counter = 0;
            tile_glow = false;
        }

    }
}

