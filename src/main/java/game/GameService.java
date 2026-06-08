package game;

import game.floor.Floor;
import javafx.scene.Group;
import javafx.stage.Stage;

public class GameService {

    private static Stage currentStage;
    private static Group root;
    private static Background background;
    private static Floor floor;
    private static GameLoop timer;


    public static void setUp(Stage stage, Group group){
        currentStage = stage;
        root = group;
//        stage.setWidth(1000);
//        stage.setHeight(600);

        background = new Background("/images/background.png", 1000, 600);
        root.getChildren().add(background.getImageView1());
        root.getChildren().add(background.getImageView2());

        floor = new Floor("/images/floor_tile_crop.png", 1000, 600);
        root.getChildren().add(floor.gethBox());
    }

    public static void moveBackground(double time){
        background.move(time);
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        GameService.currentStage = currentStage;
    }

    public static Group getRoot() {
        return root;
    }

    public static void setRoot(Group root) {
        GameService.root = root;
    }

    public static Background getBackground() {
        return background;
    }

    public static void setBackground(Background background) {
        GameService.background = background;
    }
}
