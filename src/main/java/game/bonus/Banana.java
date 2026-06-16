package game.bonus;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Banana {

    private Image image;
    private ImageView imageView;
    private double x, y, size, speed;

    public Banana(String path, double x, double y, double size, double speed){
        image = new Image(getClass().getResourceAsStream(path));
        imageView = new ImageView(image);
        this.x = x;
        imageView.setLayoutX(x);
        this.y = y;
        imageView.setLayoutY(y);
        this.size = size;
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.speed = speed;
    }

    public boolean update(double time) {
        return update(time, 1.0);
    }

    public boolean update(double time, double speedMultiplier) {
        double movement = speed * speedMultiplier * time;
        x -= movement;

        imageView.setLayoutX(imageView.getLayoutX() - movement);

        if(x <= -size) return false;
        return true;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
