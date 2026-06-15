package game.floor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Floor {
    private Image image;
    private static final double IMAGE_SIZE = 80;
    private HBox tileContainer;
    private double x1, x2;
    private double y;

    private double width, height, speed;

    private static final double SPACING = -3;

    public Floor(String path, double screenWidth, double screenHeight, double speed) {
        width = screenWidth;
        this.speed = speed;

        image = new Image(getClass().getResourceAsStream(path));

        x1 = 0;
        x2 = width;

        height = IMAGE_SIZE;
        y = (screenHeight - height);

        this.tileContainer = new HBox();
        tileContainer.setSpacing(SPACING);

        tileContainer.setLayoutX(0);
        tileContainer.setLayoutY(this.y);

        tileContainer.setPrefWidth(width);

        int hiddenTilesCount = 3;

        int tilesCount = (int) Math.ceil(screenWidth / IMAGE_SIZE) + hiddenTilesCount;

        for (int i = 0; i < tilesCount; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(IMAGE_SIZE);
            imageView.setFitHeight(IMAGE_SIZE);
            tileContainer.getChildren().add(imageView);
        }
    }

    public HBox getNode() {
        return tileContainer;
    }

    public double getY() {
        return y;
    }

    public void update(double time) {
        update(time, 1.0);
    }

    public void update(double time, double speedMultiplier) {
        double movement = speed * speedMultiplier * time;

        tileContainer.setLayoutX(tileContainer.getLayoutX() - movement);

        double effectiveWidth = IMAGE_SIZE + SPACING;

        if (tileContainer.getLayoutX() <= -effectiveWidth) {
            tileContainer.setLayoutX(tileContainer.getLayoutX() + effectiveWidth);
        }
    }

    public double getHeight() {
        return height;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
