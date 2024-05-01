package com.delbertina;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;

public class Launcher extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        //setup dimensions
        final int screenWidth = 480;
        final int screenHeight = 640;

        VBox options = new VBox(10);

        Text logoTx = new Text("Card Game Suite");
        logoTx.setFont(Font.font(64));
        logoTx.setFill(Color.LIGHTPINK);

        //buttons

        //
        // War game is incomplete
        //
        Button blackjackBt = new Button("Blackjack");
        Button warGameBt = new Button("War");
        Button emptyBt = new Button("Coming Soon");

        //make buttons do things
        blackjackBt.setOnAction(e -> {
            BlackJack game = new BlackJack();
            game.start(new Stage());
        });

        emptyBt.setOnAction(e -> {

        });

        warGameBt.setOnAction(e -> {
            WarGame game = new WarGame();
            game.start(new Stage());
        });

        blackjackBt.setOnMouseEntered(e -> blackjackBt.setStyle(""));
        blackjackBt.setOnMouseExited(e -> blackjackBt.setStyle(""));
        warGameBt.setOnMouseEntered(e -> blackjackBt.setStyle(""));
        warGameBt.setOnMouseExited(e -> blackjackBt.setStyle(""));

        //make button pretty
        blackjackBt.setMinWidth(screenWidth*0.8);
        warGameBt.setMinWidth(screenWidth*0.8);
        emptyBt.setMinWidth(screenWidth*0.8);

        blackjackBt.setMinHeight(64);
        warGameBt.setMinHeight(64);
        emptyBt.setMinHeight(64);

        blackjackBt.setFont(Font.font(32));
        warGameBt.setFont(Font.font(32));
        emptyBt.setFont(Font.font(32));

        blackjackBt.setTextFill(Color.BEIGE);
        warGameBt.setTextFill(Color.BEIGE);
        emptyBt.setTextFill(Color.BEIGE);

        options.getChildren().addAll(logoTx,blackjackBt, warGameBt, emptyBt);
        options.setAlignment(Pos.CENTER);

        BackgroundFill btbackground = new BackgroundFill(Paint.valueOf("brown"),CornerRadii.EMPTY, Insets.EMPTY);

        Image greenwool = new Image("img/greenwool.png");
        BackgroundImage background = new BackgroundImage(greenwool, REPEAT, REPEAT, DEFAULT, BackgroundSize.DEFAULT);


        Scene scene = new Scene(options, screenWidth, screenHeight);

        emptyBt.setBackground(new Background(btbackground));
        warGameBt.setBackground(new Background(btbackground));
        blackjackBt.setBackground(new Background(btbackground));
        options.setBackground(new Background(background));

        stage.setTitle("Lanucher");
        stage.setScene(scene);
        stage.show();
    }
}
