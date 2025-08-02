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

    private boolean leftVisibility = true;
    private boolean rightVisibility = true;
    private boolean topVisibility = true;
    private boolean bottomVisibility = true;

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

        rightWall.setStartX(x + width);
        rightWall.setStartY(y);
        rightWall.setEndX(x + width);
        rightWall.setEndY(y + height);

        topWall.setStartX(x);
        topWall.setStartY(y);
        topWall.setEndX(x + width);
        topWall.setEndY(y);

        bottomWall.setStartX(x);
        bottomWall.setStartY(y + height);
        bottomWall.setEndX(x + width);
        bottomWall.setEndY(y + height);

        maze.getChildren().addAll(leftWall, rightWall, topWall, bottomWall);
    }

    public void setVisible(boolean visible) {
        if (leftVisibility) leftWall.setVisible(visible);
        if (rightVisibility) rightWall.setVisible(visible);
        if (topVisibility) topWall.setVisible(visible);
        if (bottomVisibility) bottomWall.setVisible(visible);
    }

    public void setLeftVisibility(boolean visible) {
        leftWall.setVisible(visible);
        leftVisibility = visible;
    }

    public void setRightVisibility(boolean visible) {
        rightWall.setVisible(visible);
        rightVisibility = visible;
    }

    public void setTopVisibility(boolean visible) {
        topWall.setVisible(visible);
        topVisibility = visible;
    }

    public void setBottomVisibility(boolean visible) {
        bottomWall.setVisible(visible);
        bottomVisibility = visible;
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