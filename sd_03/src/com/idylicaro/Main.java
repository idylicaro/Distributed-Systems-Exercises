package com.idylicaro;

public class Main {

    public static void main(String[] args) {
	Race.maxJumpSize = 2;
    Race.raceDistance = 100;

    new Thread(new FrogThread(10)).start();
    new Thread(new FrogThread(20)).start();
    new Thread(new FrogThread(30)).start();
    new Thread(new FrogThread(40)).start();
    new Thread(new FrogThread(50)).start();
    }
}

