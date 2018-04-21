package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalPlayer extends Player {
    LocalPlayer(String playerid){
        super(playerid);
    }

    private void PromptInput(){
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //TODO: Handle properly
        try {
            Boolean twist;
            while (true) {
                System.out.print("What do you do now, my goi?: ");
                String temp = input.readLine();

                if (temp.equals("twist")) {
                    twist = true;
                    break;
                } else if (temp.equals("stick")) {
                    twist = false;
                    break;
                } else {
                    //TODO: make less stupid
                    System.out.println("Come on, actual input please");
                }
            }

            TwistEvent event = new TwistEvent(twist, playerid);
            this.fireEvent(event);

        } catch (IOException e){
            System.exit(1);
        }
    }

    //Holy shit this is cool
    public synchronized void run(){
        while (true) {
            try {
                //TODO: Unhardcode
                if (currentEvent.getClass().getSimpleName().equals("Event")) {
                    wait( );
                } else {
                    done = false;
                    if (currentEvent.getClass().getSimpleName().equals("PromptInput")){
                        PromptInput();
                    }
                    currentEvent = new Event();
                    done = true;
                    wait(100);
                }
            } catch (InterruptedException ie) {
                return;
            }
        }
    }
}
