package game.utils;

import game.GameLoop;
import game.GameService;
import javafx.scene.Group;
import javafx.stage.Stage;

public class GameRunner {
    public static void run(Stage stage, Group root) {
        GameLoop timer = new GameLoop();
        GameService.setUp(stage, root, timer);
        stage.show();
        timer.start();
    }
}
