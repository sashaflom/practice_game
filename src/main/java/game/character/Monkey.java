package game.character;
//mvn javafx:run
import game.platform.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Monkey {

    private final ImageView node;

    private final Image[] runFrames;
    private final Image flyingImage;

    private double x = 180;
    private double y;
    private double previousY;

    private double velocityY = 0;
    private final double groundY;
    private Platform currentPlatform;
    private Platform ignoredPlatform;

    private boolean onGround = true;
    private boolean gliding = false;

    private double animationTimer = 0;
    private int currentFrame = 0;

    private static final double WIDTH = 75;
    private static final double HEIGHT = 75;
    private static final double FLYING_SIZE = 78;

    private static final double GRAVITY = 1200;
    private static final double JUMP_FORCE = -650;
    private static final double DROP_FORCE = 250;
    private static final double COLLISION_INSET_X = 18;
    private static final double LANDING_TOLERANCE = 12;

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
        previousY = y;
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
            currentPlatform = null;
            ignoredPlatform = null;
        }

        node.setY(y);
    }

    private void updateAnimation(double time) {
        if (!onGround) {

            node.setImage(flyingImage);

            node.setFitWidth(FLYING_SIZE);
            node.setFitHeight(FLYING_SIZE);

            return;
        }

        node.setFitWidth(WIDTH);
        node.setFitHeight(HEIGHT);

        animationTimer += time;

        if (animationTimer >= 0.045) {
            animationTimer = 0;
            currentFrame++;

            if (currentFrame >= runFrames.length) {
                currentFrame = 0;
            }

            node.setImage(runFrames[currentFrame]);
        }
    }

    public void jump() {
        if (onGround) {
            velocityY = JUMP_FORCE;
            onGround = false;
            gliding = false;
            currentPlatform = null;
            ignoredPlatform = null;

            node.setFitWidth(FLYING_SIZE);
            node.setFitHeight(FLYING_SIZE);
            node.setImage(flyingImage);
        }
    }

    public void dropDown() {
        if (onGround && currentPlatform != null) {
            ignoredPlatform = currentPlatform;
            currentPlatform = null;
            onGround = false;
            gliding = false;
            velocityY = DROP_FORCE;
            y += 4;
            node.setY(y);
        }
    }

    public void checkPlatformCollisions(List<Platform> platforms) {
        if (onGround && currentPlatform != null) {
            if (!platforms.contains(currentPlatform) || !overlapsHorizontally(currentPlatform)) {
                leaveCurrentPlatform();
            } else {
                standOnPlatform(currentPlatform);
                return;
            }
        }

        if (velocityY < 0) {
            return;
        }

        double previousBottom = previousY + HEIGHT;
        double currentBottom = y + HEIGHT;

        for (Platform platform : platforms) {
            if (platform == ignoredPlatform) {
                if (currentBottom > platform.getY() + platform.getHeight()) {
                    ignoredPlatform = null;
                }
                continue;
            }

            if (canLandOn(platform, previousBottom, currentBottom) && overlapsHorizontally(platform)) {
                landOnPlatform(platform);
                return;
            }
        }
    }

    private boolean canLandOn(Platform platform, double previousBottom, double currentBottom) {
        double platformTop = platform.getY();
        boolean reachedPlatform = currentBottom >= platformTop - LANDING_TOLERANCE;
        boolean wasAbovePlatform = previousBottom <= platformTop + LANDING_TOLERANCE;
        boolean monkeyIsNotUnderPlatform = y < platformTop;

        return reachedPlatform && wasAbovePlatform && monkeyIsNotUnderPlatform;
    }

    private boolean overlapsHorizontally(Platform platform) {
        double monkeyLeft = x + COLLISION_INSET_X;
        double monkeyRight = x + WIDTH - COLLISION_INSET_X;
        double platformLeft = platform.getX();
        double platformRight = platform.getX() + platform.getWidth();

        return monkeyRight >= platformLeft && monkeyLeft <= platformRight;
    }

    private void landOnPlatform(Platform platform) {
        standOnPlatform(platform);
        currentPlatform = platform;
        ignoredPlatform = null;
    }

    private void standOnPlatform(Platform platform) {
        y = platform.getY() - HEIGHT;
        velocityY = 0;
        onGround = true;
        gliding = false;
        node.setFitWidth(WIDTH);
        node.setFitHeight(HEIGHT);
        node.setY(y);
    }

    private void leaveCurrentPlatform() {
        currentPlatform = null;
        onGround = false;
        gliding = false;
        ignoredPlatform = null;
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
