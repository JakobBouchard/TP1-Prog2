module com.example.tp11861544jb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;
    requires java.management;


    opens Frontend to javafx.fxml;
    exports Frontend;
}