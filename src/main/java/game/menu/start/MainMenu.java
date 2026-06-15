package game.menu.start;

import game.utils.GameRunner;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;

public class MainMenu {
    private Image backgroundImg;
    private Image startBtnImg;

    private ImageView startBtn;
    private ImageView background;

    public MainMenu(String backgroundPath, String startBtnPath, double stageHeight, double stageWidth, Stage stage, Group root) {
        backgroundImg = new Image(getClass().getResourceAsStream(backgroundPath));
        startBtnImg = new Image(getClass().getResourceAsStream(startBtnPath));

        background = new ImageView(backgroundImg);
        background.setFitHeight(stageHeight);
        background.setFitWidth(stageWidth);
        root.getChildren().add(background);

        startBtn = new ImageView(startBtnImg);

        double btnHeight = startBtnImg.getHeight();
        startBtn.setY(((stageHeight / 3) * 2) - (btnHeight / 2));

        double btnWidth = startBtnImg.getWidth();
        startBtn.setX((stageWidth  / 2) - (btnWidth / 2));

        root.getChildren().add(startBtn);

        startBtn.setOnMouseClicked((MouseEvent event) -> {
            root.getChildren().remove(startBtn);
            root.getChildren().remove(background);
            GameRunner.run(stage, root);
        });
    }

}
