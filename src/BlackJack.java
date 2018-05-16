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

public class BlackJack extends Application{
    final int screenWidth = 960;
    final int screenHeight = 640;


    @Override
    public void start(Stage stage){


        Image greenwool = new Image("/greenwool.png");
        BackgroundImage background = new BackgroundImage(greenwool, REPEAT, REPEAT, DEFAULT, BackgroundSize.DEFAULT);

        PlayPane panePlay = new PlayPane();

        //make buttons
        Button standButton = new Button("Stand");
        Button hitButton = new Button("Hit");
        Button resetButton = new Button("Reset");

        //position buttons
        standButton.setTranslateX(-(screenWidth/2)+30);
        //standButton.setTranslateY((screenHeight/2)-10);
        hitButton.setTranslateX(-(screenWidth/2)+90);
        //hitButton.setTranslateY((screenHeight/2)-10);
        resetButton.setTranslateX(-(screenWidth/2)+150);
       // resetButton.setTranslateY((screenHeight/2)-10);

        //set button actions
        standButton.setOnAction(e ->{
            panePlay.standPressed();
        });

        hitButton.setOnAction(e ->{
            panePlay.hitPressed();
        });
        resetButton.setOnAction(e ->{
            panePlay.startOver();
        });

        StackPane pane = new StackPane();
        pane.getChildren().addAll(panePlay,standButton,hitButton,resetButton);


        Scene scene = new Scene(pane, screenWidth,screenHeight);
        panePlay.setBackground(new Background(background));
        stage.setTitle("BlackJack");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    class PlayPane extends Pane {

        Deck deck;
        Hand player1;
        Hand dealer;
        Text points;
        Text winner;
        boolean deciding;

        public PlayPane(){
            //setup the game
            deciding = false;
            //make a deck
            deck = new Deck();

            getChildren().add(deck);

            deck.setCoords(screenWidth-150,(screenHeight-100)/2);

            //shuffle 3 times
            deck.shuffle();
            deck.shuffle();
            deck.shuffle();

            //make player1
            player1 = new Hand();
            player1.setCoords(50,screenHeight-200);
            //make dealer
            dealer = new Hand();
            dealer.setCoords(50,50);

            //setup points and winner
            points = new Text(screenWidth/2,screenHeight/2,"Player: " + player1.getPoints() + " - " + "Dealer: " + dealer.getPoints());
            winner = new Text(screenWidth/2, (screenHeight/2) + 20, "Closest to 21 without going over.");
            points.setStroke(Color.WHITE);
            winner.setStroke(Color.WHITE);

            getChildren().addAll(points,winner);

            updateScore();
        }

        public void dealerPlay(){
            //loop if dealer should hit then hit
            //loop if dealer has less than 17 points
            if(dealer.getPoints() < 17 ) {

                //hit
                Card tempFlip = deck.deal(true);
                tempFlip.flip();
                dealer.addCard(tempFlip);

                //Text tempFlipRank = new Text(String.valueOf(tempFlip.getRank()));
                //tempFlipRank.setX(tempFlip.getX()+10);
                //tempFlipRank.setY(tempFlip.getY()+10);

                getChildren().addAll(tempFlip);
                //if the card is a 1 then make it an 11
                if(tempFlip.getRank() == '1' && dealer.getPoints() < 11){dealer.addPoints(11);}
                else {dealer.addPoints(tempFlip, true);}
            }
            else{dealer.stand();}

            if(!player1.isPlaying() && dealer.getPoints() < 17){
                dealerPlay();
            }
        }

        public void playerPlay(){

            Card tempFlip = deck.deal(true);
            tempFlip.flip();
            player1.addCard(tempFlip);
            //Text tempFlipRank = new Text(String.valueOf(tempFlip.getRank()));
            //tempFlipRank.setX(tempFlip.getX() + 10);
            //tempFlipRank.setY(tempFlip.getY() + 10);

            getChildren().addAll(tempFlip);
            //if the card is a 1 then make it an 11
            if (tempFlip.getRank() == '1' && player1.getPoints() < 11) {
                player1.addPoints(11);
            } else {
                player1.addPoints(tempFlip, true);
            }

            if(!player1.isPlaying()){
                dealerPlay();
                updateScore();
                checkWin();
            }
        }

        public void standPressed(){
            player1.stand();
            if(!dealer.isPlaying()){
                updateScore();
                checkWin();
            }
            else{
                dealerPlay();
                updateScore();
                checkWin();
            }
        }

        public void hitPressed(){
            if(player1.isPlaying()){
                playerPlay();
            }
            dealerPlay();
            updateScore();
        }

        public void updateScore(){
            points.setText("Player: " + player1.getPoints() + " - " + "Dealer: " + dealer.getPoints());
        }

        public void checkWin(){
            //see who won
            //if the player and dealer have same points then tie
            if(player1.getPoints() == dealer.getPoints()){
                //tie
                winner.setText("It's a Tie!");
            }
            //else if both players bust no winner
            else if(player1.isBust() && dealer.isBust()){
                //no winner
                winner.setText("No Winner!");
            }
            //else if player is bust and dealer is not OR dealer has more points than player dealer wins
            else if((player1.isBust() && !dealer.isBust()) || ((dealer.getPoints() > player1.getPoints())&& !dealer.isBust())){
                //dealer wins
                winner.setText("Dealer Wins!");
            }
            //else if dealer is bust and player is not OR player has more points than dealer player wins
            else if((dealer.isBust() && !player1.isBust()) || ((player1.getPoints() > dealer.getPoints()) && !player1.isBust())){
                //player wins
                winner.setText("Player Wins!");
            }

        }
/*
        public void decideAce(){
            deciding = true;

            //add new buttons
            Button oneButton = new Button("One");
            Button elevenButton = new Button("Eleven");

            //position them
            oneButton.setTranslateX(190);
            oneButton.setTranslateY((screenHeight/2)-10);
            elevenButton.setTranslateX(250);
            elevenButton.setTranslateY((screenHeight/2)-10);

            //make em do things
            oneButton.setOnAction(e -> {
                player1.addPoints(1);
                getChildren().removeAll(oneButton,elevenButton);
                deciding = false;
            });
            elevenButton.setOnAction(e -> {
                player1.addPoints(11);
                getChildren().removeAll(oneButton,elevenButton);
                deciding = false;
            });

            //add to pane
            getChildren().addAll(oneButton,elevenButton);
            while(deciding){}
        }
*/
        public void displayResults(){
            //display results
            //display text of win message

            //display text to press enter


        }

        public void startOver(){
            //if they press enter reset game
            //delete player hands
            getChildren().clear();

            deck = new Deck();

            getChildren().add(deck);

            deck.setCoords(screenWidth-150,(screenHeight-100)/2);

            //shuffle 3 times
            deck.shuffle();
            deck.shuffle();
            deck.shuffle();

            //make player1
            player1 = new Hand();
            player1.setCoords(50,screenHeight-200);
            //make dealer
            dealer = new Hand();
            dealer.setCoords(50,50);

            //setup points and winner
            points = new Text(screenWidth/2,screenHeight/2,"P: " + player1.getPoints() + " - " + "D: " + dealer.getPoints());
            winner = new Text(screenWidth/2, (screenHeight/2) + 20, "Closest to 21 without going over.");
            points.setStroke(Color.WHITE);
            winner.setStroke(Color.WHITE);

            getChildren().addAll(points,winner);

            updateScore();

        }

    }
}
