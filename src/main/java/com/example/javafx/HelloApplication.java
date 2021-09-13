package com.example.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            gc.clearRect(0, 0, 600, 600);
            try {
                drawPrimitiveFace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(360);
        timeline.play();

    }

    public static int getRandomInt(){
        int x = new Random().nextInt(255);
        return x;
    }

    public static void drawPrimitiveFace() {
        //Random boolean som afgør om vores mand er sur eller glad.
        boolean happy = randomboolean();
        boolean hasGlasses = randomboolean();
        drawEars();
        drawShape();
        drawMouth(50, happy);
        drawEyes(happy);
        if (hasGlasses == true) {
            drawGoogles();
        }
        drawNose();
        drawBody();
        drawHair();
    }

    public static void drawEars(){
        gc.setFill(Color.rgb(255, 220, 177));
        gc.fillOval(130, 270, 40, 40);

        gc.fillOval(430, 270, 40, 40);

    }
    public static void drawShape() {
        //Ansigtet formes med en kaukasisk hudfarve :)
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

    public static void drawGoogles(){
        gc.strokeOval(210, 215, 40, 30); //Venstre brillestel
        gc.strokeOval(350, 215, 40, 30); //Højre brillestel
        gc.strokeLine(250, 230, 350, 230); //Stel mellem øjnene
        gc.strokeLine(210, 230, 155, 270); //Stel fra venstre øje til venstre øre
        gc.strokeLine(390, 230, 445, 270); //Stel fra højre øje til højre øre

    }

    public static void drawHair (){
        int whichHaircut = new Random().nextInt(3);
        if (whichHaircut == 0){
            drawMonkHair();
        }
        else if (whichHaircut == 1 ){
            drawBadMohawk();
        }
        else if (whichHaircut == 2){
            drawBabyCurl();

        }
    }

    public static void drawBadMohawk() {
        double x = 0;
        for (int i = 0; i < 20; i++) {
            gc.strokeLine(250 + x, 160, 240 + x, 140);
            x = x + 5;
        }
    }

    public static void drawBabyCurl(){
        gc.strokeOval(300, 125, 20,20);
        gc.strokeArc(300, 125, 20, 30, 90, 180, ArcType.OPEN);
        gc.strokeArc(300, 115, 20, 30, 45, 225, ArcType.OPEN);
    }

    public static void drawMonkHair(){
        double x = 0;
        double y = 0;
        for (int i = 0; i < 14; i++){
            gc.strokeLine(160 + x, 200, 160 + x, 240 - y);
            x = x + 2;
            y = y + 3;
        }
        x = 0;
        y = 0;
        for (int i = 0; i < 14; i++) {
            gc.strokeLine(440 - x, 200, 440 - x, 240 - y);
            x = x + 2;
            y = y + 3;
        }

    }

    public static boolean randomboolean(){
        boolean randomBoolean = new Random().nextBoolean();
        return randomBoolean;
    }

    public static void main(String[] args) {
        launch();
    }
}