package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background {

    private Image image;
    private ImageView imageView;
    private double x1, x2;
    private double y;
    private double width, height, speed;

    public Background(String path, double screenWidth, double screenHeight) {
        width = screenWidth;
        height = screenHeight;
        y = 0;
        x1 = 0;
        x2 = width;
        image = new Image(getClass().getResourceAsStream(path));
        imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
