

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Deck extends Rectangle{

    private char[] alpha = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
    private String[] suits = {"hearts", "clubs", "diamonds", "spades"};
    private String[] color = {"red", "black"};

    private static int deckHeight = 133;
    private static int deckWidth = 100;
    private static int deckArcHeight = 10;
    private static int deckArcWidth = 10;
    
    private ArrayList<Card> cards;
    private Rectangle location;
    
    public Deck(){

        super(deckWidth, deckHeight);
        super.setArcHeight(deckArcHeight);
        super.setArcWidth(deckArcWidth);
        super.setFill(Color.SALMON);
        super.setStroke(Color.SILVER);
        this.cards = new ArrayList<Card>();

        for (int i = 0;i < 4;i++){
            for(int j = 0;j < 13;j++){
                Card tempCard = new Card(alpha[j],suits[i],color[i%2]);
                cards.add(tempCard);
            }
        }
    }
    
    public Deck(int decks){
        super(deckWidth, deckHeight);

        this.cards = new ArrayList<Card>();

        for(int h = 0;h < decks;h++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    Card tempCard = new Card(alpha[j], suits[i], color[i % 2]);
                    cards.add(tempCard);
                }
            }
        }
    }

    public void flipAll(){
        for (int i = 0; i < cards.size();i++){
            cards.get(i).flip();
        }
    }

    public void flipTop(){
        cards.get(0).flip();
    }

    public int getSize(){
        return cards.size();
    }

    public Card deal(boolean t){
        if(t){
            return cards.remove(0);
        }
        else{
            return cards.remove(cards.size()-1);
        }
    }

    public ArrayList<Card> deal(boolean t, int n){
        //make temp array
        ArrayList<Card> tempReturn = new ArrayList<Card>();

        if(t){
            for(int i = 0; i < n; i++) {
                tempReturn.add(cards.remove(0));
            }
        }
        else{
            for(int i = 0; i < n; i++) {
                tempReturn.add(cards.remove(cards.size()-1));
            }
        }
        return tempReturn;
    }

    public void addCard(boolean t, Card c){
        if(t){
            cards.add(c);
        }
        else{
            cards.add(cards.size()-1,c);
        }
    }

    public void addCard(boolean t, ArrayList<Card> c){
        if(t){
            cards.addAll(c);
        }
        else{
            cards.addAll(cards.size()-1,c);
        }
    }

    public void addDeck(Deck d){
        cards.addAll(d.deal(true, d.getSize()));
    }

    public void shuffle(){
        //////create temp array lists
        //temp shuffled array list
        ArrayList<Card> shuffled = new ArrayList<>();
        //temp top half array list
        ArrayList<Card> topHalf = new ArrayList<>((cards.size()/2));
        //temp bottom half array list
        ArrayList<Card> bottomHalf = new ArrayList<>((cards.size()/2));

        //split input array into the top and bottom array lists
        //bottomHalf.addAll((cards.size()/2),cards);
        for(int i = (cards.size()/2);i<(cards.size());i++){
            bottomHalf.add(cards.get(i));
        }
        for(int i = 0;i<(cards.size()/2);i++){
            topHalf.add(cards.get(i));
        }

        //while one of the lists isn't empty ...
        while(!topHalf.isEmpty() && !bottomHalf.isEmpty()) {
            //if 50/50 chance is true ...
            if(ThreadLocalRandom.current().nextInt(0, 2)==1) {
                //add bottom card from top half to shuffled
                shuffled.add(0,topHalf.remove(topHalf.size()-1));
            }
            //else ...
            else {
                //add bottom card from bottom half to shuffled
                shuffled.add(0,bottomHalf.remove(bottomHalf.size()-1));
            }
        }
        //if top half is empty and bottom half is not ...
        if(topHalf.isEmpty() && !bottomHalf.isEmpty()) {
            //for each element left in bottom half ...
            for(int i = 0;i < bottomHalf.size();) {
                //add last to top of shuffled
                shuffled.add(0,bottomHalf.remove(bottomHalf.size()-1));
            }
        }
        //else if bottom half is empty and top half is not ...
        else if(!topHalf.isEmpty() && bottomHalf.isEmpty()) {
            //for each element left in top half ..
            for(int i = 0;i < topHalf.size();) {
                //add last to top of shuffled
                shuffled.add(0, topHalf.remove(topHalf.size() - 1));
            }
        }
        //return shuffled array list
        cards = shuffled;
    }

    public void sort(){
        //make temp sorted array
        ArrayList<Card> sorted = cards;

        //for all the cards
        for(int i = 0; i < sorted.size();i++) {
            //sort list with compareTo
            Collections.sort(sorted);
        }

        cards = sorted;

    }

    public int countRank(char c){
        int rankCount = 0;
        for(int i = 0; i < cards.size();i++) {
            if(cards.get(i).getRank() == c){
                rankCount++;
            }
        }
        return rankCount;
    }

    public int countSuit(String s){
        int suitCount = 0;
        for(int i = 0; i < cards.size();i++) {
            if(cards.get(i).getSuit().equals(s)){
                suitCount++;
            }
        }
        return suitCount;
    }

    public int countColor(String s){
        int colorCount = 0;
        for(int i = 0; i < cards.size();i++) {
            if(cards.get(i).getColor().equals(s)){
                colorCount++;
            }
        }
        return colorCount;
    }

    public double getxCoord(){
        return super.getX();
    }

    public double getyCoord(){
        return super.getY();
    }

    public void setCoords(double x, double y){

        super.setX(x);
        super.setY(y);
    }

    @Override
    public String toString(){
        return ("Cards: " + this.getSize() + "\nFlipped?: " + cards.get(0).isFlipped());
    }
}
