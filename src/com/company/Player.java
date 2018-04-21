package com.company;

public class Player extends Thread implements Listener {
    protected EventHandler handler;
    protected Event currentEvent;
    public final String playerid;
    protected volatile Boolean done;

    Player(String playerid){
        this.playerid = playerid;
        this.handler = new EventHandler();
        this.currentEvent = new Event();
        this.done = true;
    }

    public void addListener(Listener listener){
        handler.addListener(listener);
    }

    public void removeListener(Listener listener){
        handler.removeListener(listener);
    }

    protected void fireEvent(Event event){
        handler.fireEvent(event);
    }

    public synchronized void handleEvent(Event event){
        currentEvent = event;
        notify();
    }

    public synchronized Boolean getDone(){
        return done;
    }
    public void run(){

    }
}
