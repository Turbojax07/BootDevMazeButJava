package org.turbojax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Maze extends Pane {
    private List<List<Cell>> cells = new ArrayList<>();
    private Random rand = new Random();

    public Maze(int x, int y, int numRows, int numCols, int cellWidth, int cellHeight) {
        // Creating the cells
        for (int i = 0; i < numRows; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < numCols; j++) {
                cells.get(i).add(new Cell(this, x + (j * cellWidth), y + (i * cellHeight), cellWidth, cellHeight));
            }
        }

        // Loading the cells
        loadAnimated();

        // Opening the entrance and exit
        cells.getFirst().getFirst().leftVisibility = false;
        cells.getFirst().getFirst().draw();

        cells.getLast().getLast().rightVisibility = false;
        cells.getLast().getLast().draw();

        // Generating the maze
        breakWalls();
        
        // Resetting visited status of each cell
        cells.forEach(row -> row.forEach(cell -> cell.visited = false));

        // Solving the maze
    }
    
    /** Sets the seed for the random number generator. */
    public void setSeed(long seed) {
        rand.setSeed(seed);
    }

    /** Loads all cells. */
    public void load() {
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                cells.get(i).get(j).draw();
            }
        }
    }

    /** Loads all cells with a delay between each cell. */
    public void loadAnimated() {
        loadAnimated(0, 0);
    }

    /**
     * Recursive load function.
     * Starts at cells[i][j], so to load the whole thing, use "load_animated(0, 0)"
     * 
     * @param i The row of the cell to load.
     * @param j The column of the cell to load.
     */
    public void loadAnimated(int i, int j) {
        PauseTransition delay = new PauseTransition(Duration.millis(50));
        delay.setOnFinished((event) -> {
            // Drawing the current cell
            cells.get(i).get(j).draw();

            // Calculating the position of the next cell
            if ((j + 1) >= cells.get(i).size()) {
                if ((i + 1) >= cells.size()) return;
                loadAnimated(i + 1, 0);
            } else {
                loadAnimated(i, j + 1);
            }
        });

        // Starting the delay
        delay.play();
    }

    /** Breaks walls from start to finish. */
    public void breakWalls() {
        breakWalls(0, 0);
    }

    /**
     * Recursive break function.
     * It breaks walls randomly.
     * 
     * @param i The row of the starting cell.
     * @param j The column of the starting cell.
     */
    public void breakWalls(int i, int j) {
        cells.get(i).get(j).visited = true;

        while (true) {
            List<String> positions = new ArrayList<>();

            if (i + 1 < cells.size() && !cells.get(i + 1).get(j).visited) positions.add("up");
            if (i - 1 > -1 && !cells.get(i - 1).get(j).visited) positions.add("down");
            if (j - 1 > -1 && !cells.get(i).get(j - 1).visited) positions.add("left");
            if (j + 1 < cells.get(i).size() && !cells.get(i).get(j + 1).visited) positions.add("right");

            if (positions.size() == 0) {
                cells.get(i).get(j).draw();
                return;
            }

            String nextPos = positions.get(rand.nextInt(positions.size()));

            switch (nextPos) {
                case "up":
                    cells.get(i).get(j).topVisibility = false;
                    cells.get(i).get(j).draw();

                    cells.get(i + 1).get(j).bottomVisibility = false;
                    cells.get(i + 1).get(j).draw();
                    
                    breakWalls(i + 1, j);

                case "down":
                    cells.get(i).get(j).bottomVisibility = false;
                    cells.get(i).get(j).draw();

                    cells.get(i - 1).get(j).topVisibility = false;
                    cells.get(i - 1).get(j).draw();
                    
                    breakWalls(i - 1, j);

                case "left":
                    cells.get(i).get(j).leftVisibility = false;
                    cells.get(i).get(j).draw();

                    cells.get(i).get(j - 1).rightVisibility = false;
                    cells.get(i).get(j - 1).draw();
                    
                    breakWalls(i, j - 1);

                case "right":
                    cells.get(i).get(j).rightVisibility = false;
                    cells.get(i).get(j).draw();

                    cells.get(i).get(j + 1).leftVisibility = false;
                    cells.get(i).get(j + 1).draw();
                    
                    breakWalls(i, j + 1);
            }
        }
    }
}