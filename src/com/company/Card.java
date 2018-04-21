package com.company;

enum Suit {
    Hearts, Diamonds, Clubs, Spades
}

enum Value {
    A, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
}

enum Special {
    RedJoker, BlackJoker
}

public class Card {

    private final Suit suit;
    private final Value number;
    private final Special special;

    Card(Suit suit, Value number){
        this.suit = suit;
        this.number = number;
        this.special = null;
    }

    Card(Special special){
        this.suit = null;
        this.number = null;
        this.special = special;
    }

    Card() {
        this.suit = null;
        this.number = null;
        this.special = null;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public Value getNumber(){
        return this.number;
    }

    public Special getSpecial(){
        return this.special;
    }

    //TODO: DO IT IT DO
    @Override
    public String toString() {
        return this.suit + " - " + this.number;
    }
}
