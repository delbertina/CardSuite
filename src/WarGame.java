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
        Button oneButton = new Button("Stand");
        Button twoButton = new Button("Hit");
        Button threeButton = new Button("Reset");

        //position buttons
        oneButton.setTranslateX(-(screenWidth/2)+30);
        //standButton.setTranslateY((screenHeight/2)-10);
        twoButton.setTranslateX(-(screenWidth/2)+90);
        //hitButton.setTranslateY((screenHeight/2)-10);
        threeButton.setTranslateX(-(screenWidth/2)+150);
        // resetButton.setTranslateY((screenHeight/2)-10);

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

        StackPane pane = new StackPane();
        pane.getChildren().addAll(panePlay,oneButton,twoButton,threeButton);

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

        }

        public void onePressed(){

        }

        public void twoPressed(){

        }
        
        public void threePressed(){

        }
    }
}
