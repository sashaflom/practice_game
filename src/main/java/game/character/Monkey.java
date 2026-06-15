package game.character;
//mvn javafx:run
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monkey {

    private final ImageView node;

    private final Image[] runFrames;
    private final Image flyingImage;

    private double x = 180;
    private double y;

    private double velocityY = 0;
    private final double groundY;

    private boolean onGround = true;
    private boolean gliding = false;

    private double animationTimer = 0;
    private int currentFrame = 0;

    private static final double WIDTH = 140;
    private static final double HEIGHT = 140;

    private static final double GRAVITY = 1200;
    private static final double JUMP_FORCE = -600;

    public Monkey(double groundY) {
        this.groundY = groundY;
        this.y = groundY - HEIGHT;

        runFrames = new Image[13];

        for (int i = 0; i < runFrames.length; i++) {
            runFrames[i] = loadImage("/images/run_" + (i + 1) + ".png");
        }

        flyingImage = loadImage("/images/flying.png");

        node = new ImageView(runFrames[0]);
        node.setFitWidth(WIDTH);
        node.setFitHeight(HEIGHT);
        node.setPreserveRatio(true);
        node.setX(x);
        node.setY(y);
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream(path));
    }

    public Node getNode() {
        return node;
    }

    public void update(double time) {
        updatePhysics(time);
        updateAnimation(time);
    }

    private void updatePhysics(double time) {
        double currentGravity =
                gliding && velocityY > 0
                        ? GRAVITY * 0.35
                        : GRAVITY;

        velocityY += currentGravity * time;
        y += velocityY * time;

        if (y >= groundY - HEIGHT) {
            y = groundY - HEIGHT;
            velocityY = 0;
            onGround = true;
            gliding = false;
        }

        node.setY(y);
    }

    private void updateAnimation(double time) {
        if (!onGround) {

            node.setImage(flyingImage);

            node.setFitWidth(190);
            node.setFitHeight(190);

            return;
        }

        animationTimer += time;

        if (animationTimer >= 0.045) {
            animationTimer = 0;
            currentFrame++;

            if (currentFrame >= runFrames.length) {
                currentFrame = 0;
            }

            node.setFitWidth(WIDTH);
            node.setFitHeight(HEIGHT);

            node.setImage(runFrames[currentFrame]);
        }
    }

    public void jump() {
        if (onGround) {
            velocityY = JUMP_FORCE;
            onGround = false;
            gliding = false;

            node.setFitWidth(190);
            node.setFitHeight(190);
            node.setImage(flyingImage);
        }
    }

    public void startGlide() {
        if (!onGround && velocityY > 0) {
            gliding = true;
        }
    }

    public void stopGlide() {
        gliding = false;
    }

    public void dash() {
        // поки заглушка
    }

    public double getWorldSpeedMultiplier() {
        return 1.0;
    }

    public double getHeight(){ return HEIGHT; }

    public double getJumpForce(){
        return -JUMP_FORCE;
    }
}