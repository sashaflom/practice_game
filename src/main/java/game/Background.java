package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background {

    private Image image;
    private ImageView imageView1, imageView2;
    private double x1, x2;
    private double y;
    private double width, height, speed;

    public Background(String path, double screenWidth, double screenHeight, double speed) {
        width = screenWidth;
        height = screenHeight;
        y = 0;
        x1 = 0;
        x2 = width;
        this.speed = speed;
        image = new Image(getClass().getResourceAsStream(path));
        imageView1 = new ImageView(image);
        imageView1.setX(0);
        imageView1.setY(0);
        imageView1.setFitWidth(width);
        imageView1.setFitHeight(height);
        imageView2 = new ImageView(image);
        imageView2.setX(width);
        imageView2.setY(0);
        imageView2.setFitWidth(width);
        imageView2.setFitHeight(height);
    }

    public void move(double time){
        move(time, 1.0);
    }

    public void move(double time, double speedMultiplier){
        double movement = speed * speedMultiplier * time;
        x1 -= movement;
        x2 -= movement;
        if(x1 <= -width) x1 = x2 + width;
        if(x2 <= -width) x2 = x1 + width;
        imageView1.setX(x1);
        imageView2.setX(x2);
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

    public ImageView getImageView1() {
        return imageView1;
    }

    public void setImageView1(ImageView imageView1) {
        this.imageView1 = imageView1;
    }

    public ImageView getImageView2() {
        return imageView2;
    }

    public void setImageView2(ImageView imageView2) {
        this.imageView2 = imageView2;
    }
}
