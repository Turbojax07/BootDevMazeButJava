package org.turbojax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class JavaMaze extends Application {
    public static final Logger logger = LoggerFactory.getLogger("JavaMaze");
    private static JavaMaze instance;

    public int i = 0;

    public static JavaMaze getInstance() {
        if (instance == null) new JavaMaze();

        return instance;
    }

    public JavaMaze() {
        instance = this;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaMaze");

        Line line = new Line(0, 0, 800, 800);
        line.setStrokeWidth(5);
        line.setStroke(Color.GREEN);

        Maze maze = new Maze(10, 10, 10, 10, 50, 50);
        Scene scene = new Scene(maze, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}