module com.idylicaro.sd_01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.idylicaro.sd_01 to javafx.fxml;
    exports com.idylicaro.sd_01;
}