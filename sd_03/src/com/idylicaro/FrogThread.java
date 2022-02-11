package com.idylicaro;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FrogThread implements Runnable {
    private final int frogNumber;

    public double getFrogDistance() {
        return frogDistance;
    }

    public void setFrogDistance(double frogJumpedDistance) {
        double newDistance = getFrogDistance() + frogJumpedDistance;
        System.out.println("[UPDATE] ("+ java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) +")" + " - The frog "+ frogNumber + " jumped " + String.format("%,.2f", frogJumpedDistance) + "m" + " | (Now) traversed "+ String.format("%,.2f", newDistance) + "m in total");
        this.frogDistance = newDistance;
    }

    private double frogDistance = 0;

    public FrogThread(int frogNumber) {
        this.frogNumber = frogNumber;
    }

    private void raceRun(){
        //(new Random().nextInt());
        boolean isCompleted = false;

        while (!isCompleted){
            if (frogDistance >= Race.raceDistance){
                isCompleted=true;
                System.out.println("[COMPLETED] - The frog "+ frogNumber + " completed race in: "+ (Race.countCompleted++) + "!");
//                Race.countCompleted += 1;
            }else{
                setFrogDistance(new Random().nextDouble(Race.maxJumpSize));
            }
        }
    }

    @Override
    public void run() {
        raceRun();
    }
}
