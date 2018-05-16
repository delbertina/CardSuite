import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Hand {

    private char[] alpha = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
    private String[] suits = {"hearts", "clubs", "diamonds", "spades"};
    private String[] color = {"red", "black"};

    private int handHeight = 300;
    private int handWidth = 900;

    private ArrayList<Card> cards;
    private Rectangle location;

    private int points;
    private boolean playing;

    public Hand(){
        this.cards = new ArrayList<Card>();
        this.location = new Rectangle(handWidth, handHeight);

        this.points = 0;
        this.playing = true;
    }

    public int getSize(){
        return cards.size();
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int p){
        points = p;
    }

    public void addPoints(int p){
        points += p;
    }

    public void addPoints(Card c, Boolean b){
        //if it's a 1 then take boolean
        if(c.getRank() == '1'){
            if(b){
                addPoints(1);
            }
            else{
                addPoints(11);
            }
        }
        //else add normal points
        else{
            if(c.getRank() == 't' || c.getRank() == 'j' || c.getRank() == 'q' || c.getRank() == 'k'){
                addPoints(10);
            }
            else{
                addPoints(Character.getNumericValue(c.getRank()));
            }
        }
    }

    public boolean isBust(){
        if (points>21){
            return true;
        }
        else return false;
    }

    public boolean isBlackjack(){
        if(points==21){
            return true;
        }
        else return false;
    }

    public void stand(){
        playing = false;
    }

    public boolean isPlaying(){
        if(isBust()) {playing = false;}
        else if(isBlackjack()) {playing = false;}
        //else{playing = true;}

        return playing;
    }

    public Card getCard(int i){
        return cards.get(i);
    }

    public void addCard(Card c){
        c.setX(getxCoord() + ((handWidth/12)*cards.size()));
        c.setY(getyCoord() + ((cards.size()%2)*20));
        cards.add(c);

        //add points
    }

    public Card subCard(boolean b){
        if(b){
            return cards.remove(0);
        }
        else{
            return cards.remove(cards.size()-1);
        }
    }

    public Card subCard(int i){
        return cards.remove(i);
    }

    public void resetHand(Deck d){
        playing = true;
        d.addCard(false,cards);
        cards.clear();
        points = 0;
    }

    public double getxCoord(){
        return this.location.getX();
    }

    public double getyCoord(){
        return this.location.getY();
    }

    public void setCoords(double x, double y){
        this.location = new Rectangle(x,y, handWidth, handHeight);
    }

    @Override
    public String toString(){
        return ("Cards: " + cards.size() + "\nLocation: " + this.location.getX() + "," + this.location.getY());
    }
}
