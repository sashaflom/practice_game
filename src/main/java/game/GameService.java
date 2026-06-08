package game;

import game.character.Monkey;
import game.floor.Floor;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameService {

    private static Stage currentStage;
    private static Group root;
    private static Background background;
    private static Floor floor;
    private static Monkey monkey;
    private static GameLoop timer;
    private static boolean spacePressed;


    public static void setUp(Stage stage, Group group) {
        currentStage = stage;
        root = group;
//        stage.setWidth(1000);
//        stage.setHeight(600);

        background = new Background("/images/background.png", 1000, 600);
        root.getChildren().add(background.getImageView1());
        root.getChildren().add(background.getImageView2());

        floor = new Floor("/images/floor_tile_crop.png", 1000, 600);
        root.getChildren().add(floor.getNode());

        monkey = new Monkey(floor.getGroundY());
        root.getChildren().add(monkey.getNode());

        setUpControls();
    }

    public static void moveBackground(double time) {
        background.move(time, monkey.getWorldSpeedMultiplier());
    }

    public static void moveFloor(double time) {
        floor.update(time, monkey.getWorldSpeedMultiplier());
    }

    public static void updateMonkey(double time) {
        monkey.update(time);
    }

    private static void setUpControls() {
        currentStage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!spacePressed) {
                    monkey.jump();
                }
                spacePressed = true;
                monkey.startGlide();
            }

            if (event.getCode() == KeyCode.V || event.getCode() == KeyCode.M || event.getCode() == KeyCode.W) {
                monkey.dash();
            }
        });

        currentStage.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                spacePressed = false;
                monkey.stopGlide();
            }
        });
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

    public static Monkey getMonkey() {
        return monkey;
    }
}
