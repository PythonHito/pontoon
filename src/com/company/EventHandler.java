package com.company;

import java.util.ArrayList;
import java.util.Collection;

public class EventHandler {
    private ArrayList<Listener> listeners;

    EventHandler(Collection<Listener> listeners){
        this.listeners = new ArrayList<>(listeners);
    }

    EventHandler(){
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener listener){
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener){
        this.listeners.remove(listener);
    }

    public void fireEvent(Event event){
        for (Listener listener : listeners){
            listener.handleEvent(event);
        }
    }
}
