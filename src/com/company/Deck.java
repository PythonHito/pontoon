package com.company;

import java.util.Collections;
import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;

    Deck(ArrayList<Card> cards){
        this.cards = cards;
    }

    Deck(){
        this.cards = new ArrayList<>();

        for (Suit suit : Suit.values()){
            for (Value number : Value.values()){
                this.cards.add(new Card(suit, number));
            }
        }
        for (Special special : Special.values()){
            this.cards.add(new Card(special));
        }
    }

    public ArrayList<Card> getCards(){
        return new ArrayList<Card>(this.cards);
    }

    //TODO: toString

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    //TODO: Fix take methods
    public ArrayList<Card> take(int[] indexes){
        ArrayList<Card> takeDeck = new ArrayList<>();
        for (int index : indexes){
            takeDeck.add(this.cards.get(index));
            this.cards.remove(index);
        }
        return takeDeck;
    }

    public ArrayList<Card> take(int numOfCards){
        ArrayList<Card> takeDeck = new ArrayList<>();
        for (int index = 0; index < numOfCards; index++){
            takeDeck.add(this.cards.get(index));
        }

        for (int index = 0; index < numOfCards; index++){
            this.cards.remove(0);
        }
        return takeDeck;
    }

    public void add(ArrayList<Card> cards, int offset) {
        for (Card card : cards) {
            this.cards.add(offset, card);
        }
    }

    public void add(ArrayList<Card> cards) {
        for (Card card : cards) {
            this.cards.add(0, card);
        }
    }

    public void add(Card card) {
        this.cards.add(0, card);
    }

    static public void main(String args[]){
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println(deck.getCards());
    }

}
