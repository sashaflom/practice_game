package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, GameService.getScreenWidth(), GameService.getScreenHeight());

        stage.setTitle("Jungle Dash: Kong's Record");
        stage.setScene(scene);

        GameLoop timer = new GameLoop();
        GameService.setUp(stage, root);
        stage.show();
        timer.start();
    }
}
