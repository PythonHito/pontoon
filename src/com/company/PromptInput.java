package com.company;

import java.util.ArrayList;

public class PromptInput extends Event {
    public final ArrayList<Integer> sums;

    PromptInput(ArrayList<Integer> sums){
        this.sums = sums;
    }
}
