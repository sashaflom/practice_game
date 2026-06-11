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

    private static final double SPACING = -3;

    public Floor(String path, double screenWidth, double screenHeight) {
        width = screenWidth;
        speed = 50;

        image = new Image(getClass().getResourceAsStream(path));

        x1 = 0;
        x2 = width;

        height = image.getHeight();
        y = (screenHeight - height);

        this.tileContainer = new HBox();
        tileContainer.setSpacing(SPACING);

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

        double effectiveWidth = image.getWidth() + SPACING;

        if (tileContainer.getLayoutX() <= -effectiveWidth) {
            tileContainer.setLayoutX(tileContainer.getLayoutX() + effectiveWidth);
        }
    }

    public double getHeight() {
        return height;
    }
}
