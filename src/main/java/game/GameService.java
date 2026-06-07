package game;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameService {

    private static Stage currentStage;
    private static Group root;


    public static void setUp(Stage stage, Group group){
        currentStage = stage;
        root = group;
        stage.setWidth(1000);
        stage.setHeight(600);
        Image image = new Image(GameService.class.getResourceAsStream("/images/background.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(600);
        root.getChildren().add(imageView);
    }

}
