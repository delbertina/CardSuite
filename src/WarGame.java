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


        Image greenwool = new Image("/greenwool.png");
        BackgroundImage background = new BackgroundImage(greenwool, REPEAT, REPEAT, DEFAULT, BackgroundSize.DEFAULT);

        PlayPane panePlay = new PlayPane();

        //make buttons
        Button oneButton = new Button("P1 Play");
        Button twoButton = new Button("P2 Play");
        Button threeButton = new Button("AI Toggle");
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
            panePlay.onePressed();
        });

        twoButton.setOnAction(e ->{
            panePlay.twoPressed();
        });
        threeButton.setOnAction(e ->{
            panePlay.threePressed();
        });
        fourButton.setOnAction(e ->{
            panePlay.fourPressed();
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

            //setup points and winner
            points = new Text(screenWidth/2,screenHeight/2,"Player1: " + player1.getPoints() + " - " + "Player2: " + player2.getPoints());
            winner = new Text(screenWidth/2, (screenHeight/2) + 20, "First to 0 cards looses");
            points.setStroke(Color.WHITE);
            winner.setStroke(Color.WHITE);

            getChildren().addAll(points,winner);
        }

        public void onePressed(){

        }

        public void twoPressed(){

        }

        public void threePressed(){

        }

        public void fourPressed(){

        }
    }
}
