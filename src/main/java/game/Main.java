package game;

import game.menu.start.MainMenu;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, GameService.getScreenWidth(), GameService.getScreenHeight());

        stage.setTitle("Jungle Dash: Kong's Record");
        stage.setScene(scene);

//        GameLoop timer = new GameLoop();
//        GameService.setUp(stage, root);
//        timer.start();
        MainMenu mainMenu = new MainMenu(
                "/images/main_menu_bg.png",
                "/images/main_menu_start_btn.png",
                GameService.getScreenHeight(),
                GameService.getScreenWidth(),
                stage,
                root);

        stage.show();
    }



}
