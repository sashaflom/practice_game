package game;

import game.bonus.BananaGenerator;
import game.floor.Floor;
import game.platform.Platform;
import game.platform.PlatformsGenerator;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameService {

    private static double screenWidth = 1000;
    private static double screenHeight = 600;
    private static double fieldHeight;
    private static Stage currentStage;
    private static Group root;
    private static Background background;
    private static Floor floor;
    private static GameLoop timer;
    private static PlatformsGenerator platformsGenerator;
    private static final double START_SPEED = 100;
    private static final double SPEED_UP = 10;
    private static double currentSpeed;
    private static ScoreManager scoreManager;


    public static void setUp(Stage stage, Group group) {
        currentStage = stage;
        root = group;
        currentSpeed = START_SPEED;

        background = new Background("/images/background.png", screenWidth, screenHeight, START_SPEED);
        root.getChildren().add(background.getImageView1());
        root.getChildren().add(background.getImageView2());

        floor = new Floor("/images/floor_tile_crop.png", screenWidth, screenHeight, START_SPEED);
        root.getChildren().add(floor.getNode());
        fieldHeight = screenHeight - floor.getHeight();
        platformsGenerator = new PlatformsGenerator(50, 150);
        setUpDistance();
    }

    private static void setUpDistance() {
        Text score = new Text("Дистанція: 0 м");
        score.setLayoutX(20);
        score.setLayoutY(30);
        score.setFont(Font.font("Arial Black", FontWeight.BOLD, 20));
        score.setFill(Color.web("#FFDE4D"));
        score.setStroke(Color.web("#3D2412"));
        score.setStrokeWidth(1.5);
        DropShadow shadow = new DropShadow();
        shadow.setRadius(4.0);
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.web("#221207", 0.8)); // Тінь з прозорістю 80%

        score.setEffect(shadow);
        root.getChildren().add(score);
        scoreManager = new ScoreManager(score);
    }

    public static void moveBackground(double time) {
        background.move(time);
    }

    public static void moveFloor(double time) {
        floor.update(time);
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

    public static double getScreenWidth() {
        return screenWidth;
    }

    public static double getScreenHeight() {
        return screenHeight;
    }

    public static double getFieldHeight() {
        return fieldHeight;
    }

    public static Floor getFloor() {
        return floor;
    }

    public static void movePlatform(double time) {
        platformsGenerator.movePlatform(time);
    }

    public static boolean checkForNewPlatform() {
        return platformsGenerator.checkForNewPlatform();
    }

    public static void generatePlatform() {
        platformsGenerator.generatePlatform();
    }

    public static void moveBanana(double time) {
        BananaGenerator.moveBananas(time);
    }

    public static double getCurrentSpeed() {
        return currentSpeed;
    }

    public static void updateDistance(double time) {
        scoreManager.updateDistance(time, currentSpeed);
    }

    public static void changeSpeed() {
        if(scoreManager.checkForReachingMark()){
            currentSpeed += SPEED_UP;
            BananaGenerator.changeSpeed(currentSpeed);
            platformsGenerator.changeSpeed(currentSpeed);
            floor.setSpeed(currentSpeed);
            background.setSpeed(currentSpeed);
        }
    }
}
