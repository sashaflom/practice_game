package game.floor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Floor {
    private Image image;
    private HBox tileContainer;
    private double x1, x2;
    private double y;

    private double width, height, speed;

    public Floor(String path, double screenWidth, double screenHeight) {
        width = screenWidth;
        height = screenHeight;
        speed = 50;

        image = new Image(getClass().getResourceAsStream(path));

        x1 = 0;
        x2 = width;

        y = (screenHeight - image.getHeight());

        this.tileContainer = new HBox();
        tileContainer.setSpacing(-3);

        tileContainer.setLayoutX(0);
        tileContainer.setLayoutY(this.y);

        tileContainer.setPrefWidth(width);

        int hiddenTilesCount = 3;

        int tilesCount = (int) Math.ceil(screenWidth / image.getWidth()) + hiddenTilesCount;

        for (int i = 0; i < tilesCount; i++) {
            tileContainer.getChildren().add(new ImageView(image));
        }
    }

    public HBox getNode() {
        return tileContainer;
    }

    public void update(double time) {
        double movement = speed * time;

        tileContainer.setLayoutX(tileContainer.getLayoutX() - movement);

        double effectiveWidth = image.getWidth() + tileContainer.getSpacing();

        if (tileContainer.getLayoutX() <= -effectiveWidth) {
            tileContainer.setLayoutX(tileContainer.getLayoutX() + effectiveWidth);
        }
    }
}
