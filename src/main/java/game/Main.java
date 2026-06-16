package game;

import game.menu.start.MainMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
