package game;

import game.bonus.BananaGenerator;
import game.character.Monkey;
import game.floor.Floor;
import game.platform.PlatformsGenerator;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import game.avalanche.BananaAvalanche;
import game.obstacle.BarrelGenerator;

public class GameService {

    private static double screenWidth = 1000;
    private static double screenHeight = 600;
    private static double fieldHeight;
    private static Stage currentStage;
    private static Group root;
    private static Background background;
    private static Floor floor;
    private static Monkey monkey;
    private static GameLoop timer;
    private static boolean spacePressed;
    private static PlatformsGenerator platformsGenerator;
    private static final double START_SPEED = 100;
    private static final double SPEED_UP = 10;
    private static double currentSpeed;
    private static ScoreManager scoreManager;
    private static BananaAvalanche avalanche;
    private static boolean gameOver = false;
    private static Text gameOverText;


    public static void setUp(Stage stage, Group group) {
        currentStage = stage;
        root = group;
        currentSpeed = START_SPEED;
        gameOver = false;
        gameOverText = null;

        background = new Background("/images/background.png", screenWidth, screenHeight, START_SPEED);
        root.getChildren().add(background.getImageView1());
        root.getChildren().add(background.getImageView2());

        floor = new Floor("/images/floor_tile_crop.png", screenWidth, screenHeight, START_SPEED);
        root.getChildren().add(floor.getNode());
        fieldHeight = screenHeight - floor.getHeight();

        avalanche = new BananaAvalanche(floor.getY());
        root.getChildren().add(avalanche.getNode());

        monkey = new Monkey(floor.getY());
        root.getChildren().add(monkey.getNode());

        setUpControls();
        platformsGenerator = new PlatformsGenerator(monkey.getHeight(), 150);
        scoreManager = new ScoreManager(setUpDistance(), setUpBananas());
    }

    private static Text setUpBananas() {
        Image image = new Image(GameService.class.getResourceAsStream("/images/banana.png"));
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(20);
        imageView.setLayoutY(35);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        root.getChildren().add(imageView);
        Text bananas = new Text("0");
        bananas.setLayoutX(55);
        bananas.setLayoutY(58);
        bananas.setFont(Font.font("Arial Black", FontWeight.BOLD, 20));
        bananas.setFill(Color.web("#FFDE4D"));
        bananas.setStroke(Color.web("#3D2412"));
        bananas.setStrokeWidth(1.5);
        DropShadow shadow = new DropShadow();
        shadow.setRadius(4.0);
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.web("#221207", 0.8)); // Тінь з прозорістю 80%

        bananas.setEffect(shadow);
        root.getChildren().add(bananas);
        return bananas;
    }

    private static Text setUpDistance() {
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
        return score;
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

            if (event.getCode() == KeyCode.B) {
                monkey.dropDown();
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

    public static void checkPlatformCollisions() {
        monkey.checkPlatformCollisions(platformsGenerator.getActivePlatforms());
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

    public static void collectBananas() {
        int collectedBananas = BananaGenerator.collectBananas(monkey);
        if (collectedBananas > 0) {
            scoreManager.addBananas(collectedBananas);
        }
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
            BarrelGenerator.changeSpeed(currentSpeed);
            floor.setSpeed(currentSpeed);
            background.setSpeed(currentSpeed);
        }
    }

    public static void updateAvalanche(double time) {
        avalanche.update(time, monkey);
    }

    public static void checkGameOver() {
        if (avalanche.touches(monkey)) {
            gameOver();
        }
    }

    private static void gameOver() {
        if (gameOver) {
            return;
        }

        gameOver = true;

        gameOverText = new Text("GAME OVER");
        gameOverText.setLayoutX(screenWidth / 2 - 140);
        gameOverText.setLayoutY(screenHeight / 2);
        gameOverText.setFont(Font.font("Arial Black", FontWeight.BOLD, 45));
        gameOverText.setFill(Color.RED);
        gameOverText.setStroke(Color.BLACK);
        gameOverText.setStrokeWidth(2);

        root.getChildren().add(gameOverText);

        // Потім тут заміниш на меню Ігоря
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void moveBarrels(double time) {
        BarrelGenerator.moveBarrels(time);
    }

    public static void checkBarrelCollisions() {
        if (BarrelGenerator.checkCollision(monkey)) {
            gameOver();
        }
    }

}
