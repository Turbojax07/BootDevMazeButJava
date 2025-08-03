package org.turbojax;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Cell {
    private int x;
    private int y;
    private int width;
    private int height;

    private Line leftWall = new Line();
    private Line rightWall = new Line();
    private Line topWall = new Line();
    private Line bottomWall = new Line();

    public boolean leftVisibility = true;
    public boolean rightVisibility = true;
    public boolean topVisibility = true;
    public boolean bottomVisibility = true;

    public boolean visited = false;

    private Maze maze;

    public Cell(Maze maze, int x, int y, int width, int height) {
        this.maze = maze;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        leftWall.setStartX(x);
        leftWall.setStartY(y);
        leftWall.setEndX(x);
        leftWall.setEndY(y + height);
        leftWall.setVisible(false);

        rightWall.setStartX(x + width);
        rightWall.setStartY(y);
        rightWall.setEndX(x + width);
        rightWall.setEndY(y + height);
        rightWall.setVisible(false);

        topWall.setStartX(x);
        topWall.setStartY(y);
        topWall.setEndX(x + width);
        topWall.setEndY(y);
        topWall.setVisible(false);

        bottomWall.setStartX(x);
        bottomWall.setStartY(y + height);
        bottomWall.setEndX(x + width);
        bottomWall.setEndY(y + height);
        bottomWall.setVisible(false);

        maze.getChildren().addAll(leftWall, rightWall, topWall, bottomWall);
    }

    public void draw() {
        leftWall.setVisible(leftVisibility);
        rightWall.setVisible(rightVisibility);
        topWall.setVisible(topVisibility);
        bottomWall.setVisible(bottomVisibility);
    }

    public void drawMove(Cell toCell, boolean undo) {
        Line path = new Line(x + (width / 2), y + (height / 2), toCell.x + (toCell.width / 2), toCell.y + (toCell.height / 2));
        path.setStroke(undo ? Color.RED : Color.GRAY);
        maze.getChildren().add(path);
    }

    public void drawMove(Cell toCell) {
        drawMove(toCell, false);
    }
}