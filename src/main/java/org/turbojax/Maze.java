package org.turbojax;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Maze extends Pane {
    private Cell[][] cells;

    public Maze(int x, int y, int numRows, int numCols, int cellWidth, int cellHeight) {
        cells = new Cell[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cells[i][j] = new Cell(this, x + (j * cellWidth), y + (i * cellHeight), cellWidth, cellHeight);
                cells[i][j].setVisible(false);
            }
        }

        load_animated(0, 0);
    }

    /**
     * Recursive load function.  Initial i and j values should be 0 and 0.
     * @param i
     * @param j
     */
    public void load_animated(int i, int j) {
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished((event) -> {
            System.out.printf("Loading Cell (%d,%d)\n", i, j);
            cells[i][j].setVisible(true);

            if ((j + 1) >= cells[i].length) {
                if ((i + 1) >= cells.length) return;
                load_animated(i + 1, 0);
            } else {
                load_animated(i, j + 1);
            }
        });
        delay.play();
    }
}