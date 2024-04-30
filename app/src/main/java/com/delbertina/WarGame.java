package com.delbertina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;

public class WarGame extends Application{
    final int screenWidth = 960;
    final int screenHeight = 640;


    @Override
    public void start(Stage stage){


        Image greenwool = new Image("img/greenwool.png");
        BackgroundImage background = new BackgroundImage(greenwool, REPEAT, REPEAT, DEFAULT, BackgroundSize.DEFAULT);

        PlayPane panePlay = new PlayPane();

        //make buttons
        Button oneButton = new Button("P1 Play");
        Button twoButton = new Button("P2 Play");
        Button threeButton = new Button(panePlay.isComPlayer() ? "AI: ON" : "AI: OFF");
        Button fourButton = new Button("Reset");

        //position buttons
        oneButton.setTranslateX(-(screenWidth/2)+30);
        //standButton.setTranslateY((screenHeight/2)-10);
        twoButton.setTranslateX(-(screenWidth/2)+90);
        //hitButton.setTranslateY((screenHeight/2)-10);
        threeButton.setTranslateX(-(screenWidth/2)+210);
        // resetButton.setTranslateY((screenHeight/2)-10);
        fourButton.setTranslateX(-(screenWidth/2)+270);

        //set button actions
        oneButton.setOnAction(e ->{
            panePlay.p1Play();
        });

        twoButton.setOnAction(e ->{
            panePlay.p2Play();
        });
        threeButton.setOnAction(e ->{
            panePlay.toggleAI();
            threeButton.setText(panePlay.isComPlayer() ? "AI: ON" : "AI: OFF");
        });
        fourButton.setOnAction(e ->{
            panePlay.reset();
        });

        StackPane pane = new StackPane();
        pane.getChildren().addAll(panePlay,oneButton,twoButton,threeButton, fourButton);

        Scene scene = new Scene(pane, screenWidth,screenHeight);
        panePlay.setBackground(new Background(background));
        stage.setTitle("WarGame");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    class PlayPane extends Pane {

        Deck deck;
        Hand player1;
        Hand player2;
        Text points;
        Text winner;
        boolean comPlayer;

        public PlayPane() {
            //set initial values
            comPlayer = true;

            //make deck
            deck = new Deck();

            //shuffle deck
            deck.shuffle();
            deck.shuffle();
            deck.shuffle();
            //make player 1
            player1 = new Hand();
            player1.setCoords(50,screenHeight-200);
            //make player 2
            player2 = new Hand();
            player2.setCoords(50,50);
            //split to 2 players
            //subtract 1 to eliminate odd size problems
            for(int i=0; i<deck.getSize()-1;){
                player1.addCard(deck.deal(true));
                player2.addCard(deck.deal(true));
            }

            //setup points and winner
            points = new Text(screenWidth/2,screenHeight/2,"Player1: " + player1.getPoints() + " - " + "Player2: " + player2.getPoints());
            winner = new Text(screenWidth/2, (screenHeight/2) + 20, "First to 0 cards looses");
            points.setStroke(Color.WHITE);
            winner.setStroke(Color.WHITE);

            getChildren().addAll(points,winner);
        }

        public void p1Play(){
            //put card on board

            //wait for player 2 if no aI ?
        }

        public void p2Play(){

        }

        public void toggleAI(){
            comPlayer = !comPlayer;
        }

        public boolean isComPlayer() {
            return comPlayer;
        }

        public void reset(){

        }

        public void checkWin(){
            //if player 1 has no cards
            if(player1.getSize() < 1){
                winner.setText("Player 2 Wins!");
            }
            //if player 2 has no cards
            if(player2.getSize() < 1){
                winner.setText("Player 1 Wins!");
            }
        }
    }
}
