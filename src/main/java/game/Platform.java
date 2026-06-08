package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Platform {

    private List<ImageView> blocks;
    private double x, y, width, height, speed;
    private int blockAmount;
    private Image image;
    private ImageView imageView;

    public Platform(String path, double x, double y, int blockAmount, double speed){
        this.x = x;
        this.y = y;
        this.blockAmount = blockAmount;
        this.speed = speed;
        blocks = new ArrayList<>();
        image = new Image(getClass().getResourceAsStream(path));
        width = image.getWidth() * blockAmount;
        height = image.getHeight();
        for (int i = 0; i < blockAmount; i++){
            ImageView block = new ImageView(image);
            block.setX(x + image.getWidth()*i);
            block.setY(y);
            block.setFitWidth(image.getWidth());
            block.setFitHeight(image.getHeight());
            blocks.add(block);
        }
    }

    public List<ImageView> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<ImageView> blocks) {
        this.blocks = blocks;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
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

    public int getBlockAmount() {
        return blockAmount;
    }

    public void setBlockAmount(int blockAmount) {
        this.blockAmount = blockAmount;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public boolean update(double time) {
        double movement = speed * time;
        x -= movement;
        for(ImageView block : blocks){
            block.setX(block.getX() - movement);
        }
        if(x <= -width) return false;
        return true;
    }
}
