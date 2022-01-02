package palak2048;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Matrix {



    private final int target_score = 2048;
    private final int[][] matrix = new int[4][4];
    private int score = 0;
    private int row = 0;
    private int column = 0;
    public boolean game_Over = false;
    public boolean game_Won = false;
    private List<aTile> tiles = new ArrayList<>();

    Matrix(List<aTile> Tiles) {

        tiles = Tiles;
        init_matrix();
    }

    public void init_matrix() {
        for (column = 0; column < 4; column++) {
            for (row = 0; row < 4; row++) {
                matrix[row][column] = 0;
            }
        }
    }

    public void reset()
    {
        init_matrix();
        score =0;
        for (int i =0; i<16; i++)
        {
            tiles.get(i).setTileValue(0);
            tiles.get(i).updateTile();
        }

    }
    public String getScore() {
        return String.valueOf(score);
    }

    public void setMatrixValue(int row, int column, int value) {
        matrix[row][column] = value;
    }

    private void createNewTile() throws InterruptedException  {
        int number = (int) (Math.random() * 16);
        int get_row = number / 4;
        int get_column = number - (get_row * 4);
        while (this.matrix[get_row][get_column] != 0) {

            number = (int) (Math.random() * 16);
            get_row = number / 4;
            get_column = number - (get_row * 4);
        }

        tiles.get(number).initTile();
        this.setMatrixValue(get_row, get_column, tiles.get(number).getTileValue());
    }

    private boolean checkNewTile() {
        int number = 0;
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if (this.matrix[i][j] != 0) {

                    number++;
                }
            }
        }
        if (number >= 16) {
            return false;
        }

        return true;
    }

    public void slide_Left() throws InterruptedException {
        //Check each row and column

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                check_move_left(i, 3, 2);
                check_move_left(i, 2, 1);
                check_move_left(i, 1, 0);

            }
        }
        if (checkNewTile()) {
            createNewTile();
        } else {
            game_Over = true;
        }

    }

    public void slide_Right() {
        //Check each row and column

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                check_move_right(i, 0, 1);
                check_move_right(i, 1, 2);
                check_move_right(i, 2, 3);

            }
        }

        if (checkNewTile()) {
            try {
                createNewTile();
            } catch (InterruptedException ex) {
                Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            game_Over = true;
        }

    }

    public void slide_Up() {
        //Check each row and column

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                check_move_up(i, 3, 2);
                check_move_up(i, 2, 1);
                check_move_up(i, 1, 0);

            }
        }

        if (checkNewTile()) {
            try {
                createNewTile();
            } catch (InterruptedException ex) {
                Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            game_Over = true;
        }

    }

    public void slide_Down() {
        //Check each row and column

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                check_move_down(i, 0, 1);
                check_move_down(i, 1, 2);
                check_move_down(i, 2, 3);

            }
        }

        if (checkNewTile()) {
            try {
                createNewTile();
            } catch (InterruptedException ex) {
                Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            game_Over = true;
        }

    }

    private void check_move_left(int row, int column1, int column2) {
        if (matrix[row][column1] != 0 && matrix[row][column2] != 0) {
            if (matrix[row][column1] == matrix[row][column2]) {

                score = score + matrix[row][column2] + matrix[row][column1];
                if((matrix[row][column2]+ matrix[row][column1])==target_score)
                {
                    game_Won = true;
                }
                matrix[row][column2] = matrix[row][column2] + matrix[row][column1];

                matrix[row][column1] = 0;
                tiles.get((row * 4) + column2).setTileValue(matrix[row][column2]);
                tiles.get((row * 4) + column1).setTileValue(matrix[row][column1]);

                tiles.get((row * 4) + column2).updateTile();
                tiles.get((row * 4) + column1).updateTile();
            }

        } else if (matrix[row][column1] != 0 && matrix[row][column2] == 0) {

            /*tiles.get((row * 4) + column1).setDesiredx((column2 * 40));
            tiles.get((row * 4) + column1).slide_Tile_Left();*/
            matrix[row][column2] = matrix[row][column1];

            matrix[row][column1] = 0;
            tiles.get((row * 4) + column1).setTileValue(matrix[row][column1]);
            tiles.get((row * 4) + column2).setTileValue(matrix[row][column2]);
            tiles.get((row * 4) + column1).updateTile();
            tiles.get((row * 4) + column2).updateTile();

        }
    }

    private void check_move_right(int row, int column1, int column2)  {
        if (matrix[row][column1] != 0 && matrix[row][column2] != 0) {
            if (matrix[row][column1] == matrix[row][column2]) {

                score = score + matrix[row][column2] + matrix[row][column1];
                if((matrix[row][column2]+ matrix[row][column1])==target_score)
                {
                    game_Won = true;
                }
                matrix[row][column2] = matrix[row][column2] + matrix[row][column1];

                matrix[row][column1] = 0;
                tiles.get((row * 4) + column2).setTileValue(matrix[row][column2]);
                tiles.get((row * 4) + column1).setTileValue(matrix[row][column1]);

                tiles.get((row * 4) + column2).updateTile();
                tiles.get((row * 4) + column1).updateTile();
            }

        } else if (matrix[row][column1] != 0 && matrix[row][column2] == 0) {


            matrix[row][column2] = matrix[row][column1];

            matrix[row][column1] = 0;
            tiles.get((row * 4) + column1).setTileValue(matrix[row][column1]);
            tiles.get((row * 4) + column2).setTileValue(matrix[row][column2]);
            tiles.get((row * 4) + column1).updateTile();
            tiles.get((row * 4) + column2).updateTile();

        }
    }

    private void check_move_up(int column, int row1, int row2)  {
        if (matrix[row1][column] != 0 && matrix[row2][column] != 0) {
            if (matrix[row1][column] == matrix[row2][column]) {

                score = score + matrix[row1][column] + matrix[row2][column];
                if((matrix[row2][column]+ matrix[row1][column])==target_score)
                {
                    game_Won = true;
                }
                matrix[row2][column] = matrix[row1][column] + matrix[row2][column];

                matrix[row1][column] = 0;
                tiles.get((row2 * 4) + column).setTileValue(matrix[row2][column]);
                tiles.get((row1 * 4) + column).setTileValue(matrix[row1][column]);

                tiles.get((row2 * 4) + column).updateTile();
                tiles.get((row1 * 4) + column).updateTile();
            }

        } else if (matrix[row1][column] != 0 && matrix[row2][column] == 0) {


            matrix[row2][column] = matrix[row1][column];

            matrix[row1][column] = 0;
            tiles.get((row1 * 4) + column).setTileValue(matrix[row1][column]);
            tiles.get((row2 * 4) + column).setTileValue(matrix[row2][column]);
            tiles.get((row1 * 4) + column).updateTile();
            tiles.get((row2 * 4) + column).updateTile();

        }
    }

    private void check_move_down(int column, int row1, int row2) {
        if (matrix[row1][column] != 0 && matrix[row2][column] != 0) {
            if (matrix[row1][column] == matrix[row2][column]) {

                score = score + matrix[row1][column] + matrix[row2][column];
                if((matrix[row2][column]+ matrix[row1][column])==target_score)
                {
                    game_Won = true;
                }
                matrix[row2][column] = matrix[row2][column] + matrix[row1][column];

                matrix[row1][column] = 0;
                tiles.get((row2 * 4) + column).setTileValue(matrix[row2][column]);
                tiles.get((row1 * 4) + column).setTileValue(matrix[row1][column]);

                tiles.get((row1 * 4) + column).updateTile();
                tiles.get((row2 * 4) + column).updateTile();
            }

        } else if (matrix[row1][column] != 0 && matrix[row2][column] == 0) {


            matrix[row2][column] = matrix[row1][column];

            matrix[row1][column] = 0;
            tiles.get((row2 * 4) + column).setTileValue(matrix[row2][column]);
            tiles.get((row1 * 4) + column).setTileValue(matrix[row1][column]);
            tiles.get((row2 * 4) + column).updateTile();
            tiles.get((row1 * 4) + column).updateTile();

        }
    }
}

