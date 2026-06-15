package game.platform;

import game.GameService;
import game.bonus.Banana;
import game.bonus.BananaGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.Random;

public class Platform {

    /**
     * deprecated
     * */
//    private List<ImageView> blocks;
    private double x, y, width, height, speed;
    private int blockAmount;
    private Image image;
    private static final double IMAGE_SIZE = 70;

    /*h-box nesting tiles*/
    private final HBox tileContainer;

    /*spacing const removes space between tiles*/
    private static final double SPACING = -3;

    public Platform(String path, double x, double y, int blockAmount, double speed){
        this.x = x;
        this.y = y;

        this.blockAmount = blockAmount;
        this.speed = speed;

        image = new Image(getClass().getResourceAsStream(path));

        width = IMAGE_SIZE * blockAmount;
        height = IMAGE_SIZE;

        tileContainer = new HBox();
        tileContainer.setLayoutX(x);
        tileContainer.setLayoutY(y);
        tileContainer.setSpacing(SPACING);

        double bananaX = x;
        int height = new Random().nextInt(2);
        double bananaY;
        if(height == 0){
            bananaY = y - 40;
        }else{
            bananaY = y - 100;
        }
        if(bananaY < 0) bananaY = 0;

        for (int i = 0; i < blockAmount; i++){
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(IMAGE_SIZE);
            imageView.setFitHeight(IMAGE_SIZE);
            tileContainer.getChildren().add(imageView);
            BananaGenerator.generateBanana(bananaX + 10, bananaY);
            bananaX += IMAGE_SIZE;
        }
    }

    public HBox getNode() {
        return tileContainer;
    }

    /**
     * deprecated
     * */
//    public void setBlocks(List<ImageView> blocks) {
//        this.blocks = blocks;
//    }

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

    /**
     * deprecated
     * */
//    public ImageView getImageView() {
//        return imageView;
//    }
//
//    public void setImageView(ImageView imageView) {
//        this.imageView = imageView;
//    }

    public boolean update(double time) {
        double movement = speed * time;
        x -= movement;

//        double effectiveWidth = image.getWidth() + SPACING;

        this.tileContainer.setLayoutX(tileContainer.getLayoutX() - movement);

        if(x <= -width) return false;
        return true;
    }
}
