package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

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

    public static int getRandomInt(){
        int x = new Random().nextInt(255);
        return x;
    }

    public static void drawPrimitiveFace() {
        //Random boolean som afgør om vores mand er sur eller glad.
        boolean happy = new Random().nextBoolean();
        drawShape();
        drawMouth(50, happy);
        drawEyes(happy);
        drawNose();
        drawBody();
        drawHair();
    }

    public static void drawShape() {
        //Ansigtet formes med en kaukasisk hudfarve :)
        gc.setFill(Color.rgb(255, 220, 177));
        gc.fillOval(150, 150, 300, 300);
    }

    public static void drawBody(){
        //En stickman krop tegnes under hovedet
        gc.strokeLine(300, 450, 300, 550); //Body
        gc.strokeLine(300,500, 350, 470 ); //Right arm
        gc.strokeLine(300,500, 250, 470 ); //Left arm
        gc.strokeLine(300,550, 350, 590 ); //Right leg
        gc.strokeLine(300,550, 250, 590 ); //Left leg

    }

    public static void drawNose(){
        //Næsen tegnes med 2 næsebor
        gc.setFill(Color.BLACK);
        gc.fillOval(280, 300, 10, 10);
        gc.fillOval(310, 300, 10, 10);
        gc.strokeOval(275, 295, 50, 20);
    }

    public static void drawMouth(int mouthSize, boolean happy) {
        gc.setFill(Color.rgb(getRandomInt(), getRandomInt(), getRandomInt()));
        //Munden tegnes enten som sur eller glad
        if (happy == true){
            gc.fillArc(250, 300, 100, 100, 180, 180, ArcType.ROUND);
        }
        else{

            gc.fillArc(250, 350, 100, 100, 360, 180, ArcType.ROUND);
        }
    }

    public static void drawEyes(boolean happy) {
        gc.setFill(Color.WHITE);
        gc.fillOval(215, 220, 30, 20); //Øje omkreds venstre
        gc.fillOval(355, 220, 30, 20); //Øje omkreds højre
        gc.setFill(Color.rgb(getRandomInt(), getRandomInt(), getRandomInt()));
        gc.fillOval(220,220,20,20); //Øjenfarve venstre
        gc.fillOval(360,220,20,20); //Øjenfarve højre
        gc.setFill((Color.BLACK));
        gc.fillOval(225, 225, 10, 10); //Pupil venstre
        gc.fillOval(365, 225, 10, 10); //Pupil højre

        //Øjenbryn tegnes enten som sur eller glad
        if (happy == false) {
            gc.strokeLine(220, 200, 250, 220);
            gc.strokeLine(380, 200, 350, 220);
        }
        else{
            gc.strokeLine(210, 220, 250, 210);
            gc.strokeLine(390, 220, 350, 210);
        }
    }

    public static void drawHair (){
        int x = 1;
        int y = 1;
        for (int i = 0; i < 20; i++){
            gc.strokeLine(170 + x, 225 - x, 160 + x, 215 - x);
            x = x + 5;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}