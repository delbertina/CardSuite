package com.delbertina;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.binarySearch;

public class Card extends Rectangle implements Comparable<Card>{
    private static int cardHeight = 100;
    private static int cardWidth =75;
    private static int cardArcHeight = 10;
    private static int cardArcWidth = 10;

    private String card1 = ("img/cardav2.jpg");
    private String card2 = ("img/card2v2.jpg");
    private String card3 = ("img/card3v2.jpg");
    private String card4 = ("img/card4v2.jpg");
    private String card5 = ("img/card5v2.jpg");
    private String card6 = ("img/card6v2.jpg");
    private String card7 = ("img/card7v2.jpg");
    private String card8 = ("img/card8v2.jpg");
    private String card9 = ("img/card9v2.jpg");
    private String cardt = ("img/card10v2.jpg");
    private String cardj = ("img/cardjv2.jpg");
    private String cardq = ("img/cardqv2.jpg");
    private String cardk = ("img/cardkv2.jpg");


    private char[] alpha = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
    private String[] backgrounds = {card1, card2, card3, card4, card5, card6, card7, card8, card9, cardt, cardj, cardq, cardk};

    private char rank;
    private String suit;
    private String color;
    //private Rectangle location;
    private boolean flipped;

    public Card(){
        super(cardWidth, cardHeight);
        this.rank = (alpha[ThreadLocalRandom.current().nextInt(0, 12)]);
        this.suit = "hearts";
        this.color = "red";
        this.flipped = false;
        super.setFill(new ImagePattern(new Image(backgrounds[new String(alpha).indexOf(rank)])));
        super.setStroke(Color.WHITE);
        super.setArcHeight(cardArcHeight);
        super.setArcWidth(cardArcWidth);
    }

    public Card(char r){
        super(cardWidth, cardHeight);
        this.rank = r;
        this.suit = "hearts";
        this.color = "red";
        this.flipped = false;
        super.setFill(new ImagePattern(new Image(backgrounds[new String(alpha).indexOf(rank)])));
        super.setStroke(Color.WHITE);
        super.setArcHeight(cardArcHeight);
        super.setArcWidth(cardArcWidth);
    }

    public Card(String s, String c){
        super(cardWidth, cardHeight);
        this.rank = (alpha[ThreadLocalRandom.current().nextInt(0, 13)]);
        this.suit = s;
        this.color = c;
        this.flipped = false;
        super.setFill(new ImagePattern(new Image(backgrounds[new String(alpha).indexOf(rank)])));
        super.setStroke(Color.WHITE);
        super.setArcHeight(cardArcHeight);
        super.setArcWidth(cardArcWidth);
    }

    public Card(char r, String s, String c){
        super(cardWidth, cardHeight);
        this.rank = r;
        this.suit = s;
        this.color = c;
        this.flipped = false;
        super.setFill(new ImagePattern(new Image(backgrounds[new String(alpha).indexOf(rank)])));
        super.setStroke(Color.WHITE);
        super.setArcHeight(cardArcHeight);
        super.setArcWidth(cardArcWidth);
    }

    public char getRank(){
        return this.rank;
    }

    public void setRank(char r){
        this.rank = r;
    }

    public void addRank(int r){
        this.rank = alpha[(r + (binarySearch(alpha, this.rank)))% 13];
    }

    public String getSuit(){
        return this.suit;
    }

    public void setSuit(String s){
        this.suit = s;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String c){
        this.color = c;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void flip(){
        this.flipped = !this.flipped;
        if(isFlipped()){
            super.setFill(new ImagePattern(new Image(backgrounds[new String(alpha).indexOf(rank)])));
        }
        else{super.setFill(Color.DARKRED);}
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
        return ("Rank: " + this.rank + "\nSuit: " + this.suit + "\nColor: " + this.color + "\nLocation: " + super.getX() + "," + super.getY() + "\nFlipped?: " + this.flipped);
    }

    @Override
    public int compareTo(Card other){
        //if suits are different
        if(!suit.equals(other.getSuit())){
            return suit.compareTo(other.getSuit());
        }
        //else if ranks are different
        else if(rank != other.getRank()){
            return ((Character) rank).compareTo(other.getRank());
        }
        //else they are the same
        else {return 0;}
    }
}
