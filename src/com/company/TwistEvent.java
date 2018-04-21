package com.company;

public class TwistEvent extends Event {
    public final Boolean twist;
    public final String playerid;

    TwistEvent(Boolean twist, String playerid){
        this.twist = twist;
        this.playerid = playerid;
    }

}
