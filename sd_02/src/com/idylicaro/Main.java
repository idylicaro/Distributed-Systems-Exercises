package com.idylicaro;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random generator = new Random();

        int rowLen = 3, colLen = 5;
        Integer[][] matrix = new Integer[rowLen][colLen];
        for(int i = 0; i < rowLen; i++)
            for(int j = 0; j < colLen; j++)
                matrix[i][j] = generator.nextInt(15);

        new Thread(new CalculaThread(matrix,0, colLen)).start();
        new Thread(new CalculaThread(matrix,1, colLen)).start();
        new Thread(new CalculaThread(matrix,2, colLen)).start();
        System.out.println("Finish");
    }
}
