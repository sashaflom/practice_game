module game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens game to javafx.fxml;
    exports game;
    exports game.platform;
    opens game.platform to javafx.fxml;
}