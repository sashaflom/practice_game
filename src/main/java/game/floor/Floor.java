package game.floor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Floor {
    private Image image;
    private HBox hBox;

    private double x1, x2;
    private double y;

    private double width, height, speed;

    public Floor(String path, double screenWidth, double screenHeight) {
        width = screenWidth;
        height = screenHeight;

        image = new Image(getClass().getResourceAsStream(path));

        x1 = 0;
        x2 = width;

        y = (screenHeight - image.getHeight());

        this.hBox = new HBox();
        hBox.setSpacing(-3);

        hBox.setLayoutX(0);
        hBox.setLayoutY(this.y);

        hBox.setPrefWidth(width);

        int tilesCount = (int) Math.ceil(screenWidth / image.getWidth()) + 1;

        for (int i = 0; i < tilesCount; i++) {
            hBox.getChildren().add(new ImageView(image));
        }
    }

    public HBox gethBox() {
        return hBox;
    }
}
