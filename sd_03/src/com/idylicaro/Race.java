package com.idylicaro;

public class Race {
    static int maxJumpSize;
    static int raceDistance;
    static int countCompleted = 1;

    public static void completed (){
        Race.countCompleted = Race.countCompleted + 1;
    }


    public Race(int maxJumpSize, int raceDistance) {
        Race.maxJumpSize = maxJumpSize;
        Race.raceDistance = raceDistance;
    }
}
