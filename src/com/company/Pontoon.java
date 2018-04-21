package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Pontoon extends Thread implements Listener {

    private EventHandler handler;
    private Event currentEvent;
    private HashMap<String, Player> players;

    Pontoon(String[] playerids){
        handler = new EventHandler();
        for (String playerid: playerids){

        }

    }

    public void addListener(Listener listener){
        handler.addListener(listener);
    }

    public void removeListener(Listener listener){
        handler.removeListener(listener);
    }

    private void fireEvent(Event event){
        handler.fireEvent(event);
    }

    public void handleEvent(Event event){
        currentEvent = event;
        notify();
    }

    //TODO: Make less terrible
    static private ArrayList<Integer> sumDeck(Deck deck){
        HashMap<Value, Integer> cardValues = new HashMap<>();
        cardValues.put(Value.Two, 2);
        cardValues.put(Value.Three, 3);
        cardValues.put(Value.Four, 4);
        cardValues.put(Value.Five, 5);
        cardValues.put(Value.Six, 6);
        cardValues.put(Value.Seven, 7);
        cardValues.put(Value.Eight, 8);
        cardValues.put(Value.Nine, 9);
        cardValues.put(Value.Ten, 10);
        cardValues.put(Value.Jack, 10);
        cardValues.put(Value.Queen, 10);
        cardValues.put(Value.King, 10);

        ArrayList<Integer> sums = new ArrayList<>();
        sums.add(0);

        ArrayList<Card> cards = deck.getCards();
        for (Card card : cards){
            int size = sums.size();
            for (int i = 0; i < size; i++){
                int currentSum = sums.get(i);
                if (card.getNumber() == Value.A){
                    sums.set(i, currentSum + 1);
                    sums.add(currentSum + 11);
                    System.out.println("Derp");
                } else{
                    sums.set(i, currentSum + cardValues.get(card.getNumber()));
                }
            }
        }
        return sums;
    }

    private void TwistEvent(TwistEvent event, ArrayList<String> waitingOn){
        if (waitingOn.contains(event.playerid)){
            if (event.twist.equals(true)){

            }
        }
    }

    public void run() {
        //Initailise local and networked players
        HashMap<String, Player> players = new HashMap<>();
        Player player1 = new LocalPlayer("Dave");
        players.put(player1.playerid, player1);

        for (Player player : players.values()) {
            addListener((Listener) player);
        }

        //Initailise decks
        Deck dealerDeck = new Deck();
        dealerDeck.shuffle();

        HashMap<String, Deck> playerDecks = new HashMap<>();

        for (Player player : players.values()) {
            playerDecks.put(player.playerid, new Deck(new ArrayList<Card>()));
        }

        //Start all player threads
        for (Player player : players.values()) {
            player.start();
        }

        while (true) {
            //Tell everyone that the game is starting
            GameStart gamestart = new GameStart();
            fireEvent(gamestart);

            //Hand out first 2 cards to everyone
            for (Deck deck : playerDecks.values()){
                deck.add(dealerDeck.take(2));
            }

            while (true) {
                //Get input from players
                ArrayList<Integer> sums = sumDeck(davePlayer);

                Event event;
                if (Collections.min(sums) <= 21) {
                    event = new PromptInput(sums);
                } else {
                    event = new Event();
                }

                fireEvent(event);

                ArrayList<String> waitingOn = new ArrayList<>(players.keySet());
                //Wait and then respond to input
                while (true) {
                    try {
                        //TODO: Unhardcode
                        if (currentEvent.getClass().getSimpleName().equals("Event")) {
                            this.wait();
                        } else {
                            if (currentEvent.getClass().getSimpleName().equals("TwistEvent")) {
                                TwistEvent(currentEvent, waitingOn);
                                currentEvent = new Event();
                            }
                            this.wait(100);
                        }
                    } catch (InterruptedException ie) {
                        return;
                    }
                }
            }
        }
    }


    public static void main(String[] args){
        Pontoon pontoon = new Pontoon();
        pontoon.start();
    }
}
