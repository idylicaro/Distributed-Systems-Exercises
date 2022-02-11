package com.idylicaro.sd_01;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private ProgressBar loadingBar;

    @FXML
    private Label loadingText;

    @FXML
    protected void onHelloButtonClick() {
        tLoadingBar.start();
        tChangingLabel.start();
    }

    final Thread tLoadingBar = new Thread() {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                loadingBar.setProgress(i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    final Thread tChangingLabel = new Thread() {
        public void run() {
            int txtFlag = 0;
            while (tLoadingBar.isAlive()) {
                switch (txtFlag) {
                    case 0:
                        loadingText.setText(".");
                        break;
                    case 1:
                        loadingText.setText("..");
                        break;
                    case 2:
                        loadingText.setText("...");
                }

                txtFlag++;
                if (txtFlag == 3) txtFlag = 0;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    };


}