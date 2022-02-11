package com.idylicaro;

public class CalculaThread implements Runnable{
    private Integer[][] matrix;
    private int row;
    private int maxColLength;

    private int result = 0;

    public CalculaThread(Integer[][] matrix, int row, int maxColLength) {
        this.matrix = matrix;
        this.row = row;
        this.maxColLength = maxColLength;
    }

    @Override
    public void run() {
        for(int i = 0; i< this.maxColLength; i++){
            result += matrix[row][i];
        }
        System.out.println("The Thread of line " + row + " had the result:"+ result );
    }
}
