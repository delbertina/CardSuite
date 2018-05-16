public class Play {
    public static void main(String[] args) {
//setup the game
        //make a deck
        Deck deck = new Deck();

        //shuffle 3 times
        deck.shuffle();
        deck.shuffle();
        deck.shuffle();
        //make player1
        Hand player1 = new Hand();
        //make dealer
        Hand dealer = new Hand();

//give both players 2 cards face up
        //give the dealer a card flipped up
        Card tempFlip = deck.deal(true);
        tempFlip.flip();
        dealer.addCard(tempFlip);
        //if the card is a 1 then make it an 11
        if(tempFlip.getRank() == '1'){dealer.addPoints(11);}
        //give player card flipped up
        tempFlip = deck.deal(true);
        tempFlip.flip();
        player1.addCard(tempFlip);
        //if card was a 1 then pick 1 or 11
        if(tempFlip.getRank() == '1'){
            //prompt player to pick

        }

        //give dealer
        tempFlip.flip();
        dealer.addCard(tempFlip);
        //if the card is a 1 then make it an 11
        if(tempFlip.getRank() == '1' && dealer.getPoints() != 11){dealer.addPoints(11);}
        //give player
        tempFlip = deck.deal(true);
        tempFlip.flip();
        player1.addCard(tempFlip);
        //if card was a 1 then pick 1 or 11
        if(tempFlip.getRank() == '1'){
            //prompt player to pick

        }

//loop if dealer should hit then hit
        //loop if dealer has less than 17 points
        while(dealer.getPoints() < 17) {

            //hit
            tempFlip.flip();
            dealer.addCard(tempFlip);
            //if the card is a 1 then make it an 11
            if(tempFlip.getRank() == '1' && dealer.getPoints() != 11){dealer.addPoints(11);}

        }
//loop until break on stand
        //loop if player is still playing
        while(player1.isPlaying() && !player1.isBust() && !player1.isBlackjack()) {

//if the player has bust or gotten blackjack break the loop
            //display buttons


//if the player wants to hit then hit
            //if they click the hit button

            //hit

            //get rid of buttons


//else if the player stands break the loop
            //if they click the stand button

            //stand

            //get rid of buttons

            //break the loop

//loop ends
        }

//see who won
        //if the player has blackjack and dealer then tie

        //else if player has same points as dealer and not bust then tie

        //else if player has blackjack player wins

        //else if dealer has blackjack dealer wins

        //else if player busts player looses

        //else if dealer is bust and player has less than 21 player wins

        //else if player has more points than dealer player wins


//display results
        //display text of win message

        //display text to press enter


//if they press enter reset game
        //delete player hands

    }
}
