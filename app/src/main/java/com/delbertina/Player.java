package com.delbertina;

public class Player {
    public Hand hand;
    private String name;
    private boolean playing;

    private static int players = 0;

    public Player(){
        this.hand = new Hand();
        this.name = "Player" + (players+1);
        this.playing = true;

        players++;
    }

    public Player(String s){
        this.hand = new Hand();
        this.name = s;
        this.playing = true;
    }

    public void hit(Deck d){
        hand.addCard(d.deal(true));
    }

    public void stand(){
        playing = false;
    }

    public boolean isPlaying(){
        if(hand.isBust()) {playing = false;}
        if (hand.isBlackjack()) {playing = false;}

        return playing;
    }

    public int getPoints(){
        return hand.getPoints();
    }

    public void addPoints(int i){
        hand.addPoints(i);
    }

    public void setPoints(int i){
        hand.setPoints(i);
    }

    public void changeName(String s){
        name = s;
    }

    @Override
    public String toString(){
        return ("Name: " + name + "\nCards: " + hand.getSize());
    }
}
