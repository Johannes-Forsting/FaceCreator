package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Canvas canvas = new Canvas(650, 700);
    static GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Face");
        stage.setScene(scene);

        gc.clearRect(0,0,600,600);
        root.getChildren().add(canvas);

        drawPrimitiveFace();

        stage.show();
    }

    public static void drawPrimitiveFace() {
        drawShape();
        drawMouth(50);
        drawEyes();
    }

    public static void drawShape() {
        gc.strokeOval(150, 150, 300, 300);
    }

    public static void drawMouth(int mouthSize) {
        gc.setFill(Color.BLACK);
        gc.fillOval(280,370,mouthSize,mouthSize + mouthSize / 2);
    }

    public static void drawEyes() {
        gc.setFill(Color.BLACK);
        gc.fillOval(220,220,20,40);
        gc.fillOval(360,220,20,40);
    }

    public static void main(String[] args) {
        launch();
    }
}