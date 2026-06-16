package game.obstacle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Клас перешкоди "Бочка".
 * Використовується для створення небезпечних об'єктів на рівні.
 */

public class Barrel {

    private final ImageView imageView;

    private double x;
    private double y;
    private double size;
    private double speed;

    public Barrel(String path, double x, double y, double size, double speed) {
        Image image = new Image(getClass().getResourceAsStream(path));

        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;

        imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    /*
     * Переміщує бочку разом з ігровим світом.
     * @return true якщо бочка ще знаходиться на екрані
     */

    public boolean update(double time) {
        double movement = speed * time;
        x -= movement;
        imageView.setLayoutX(x);

        return x > -size;
    }

    /*
     * Перевіряє зіткнення бочки з персонажем.
     */
    public boolean touches(javafx.scene.Node monkeyNode) {
        return imageView.getBoundsInParent().intersects(monkeyNode.getBoundsInParent());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}