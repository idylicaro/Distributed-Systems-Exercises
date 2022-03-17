package main;

public class Clock extends Thread {
    public int time = 100;
    public String serverName;
    public void run() {
        for(;;)
        {
            this.time++;
            System.out.println(serverName + "--" + this.time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
