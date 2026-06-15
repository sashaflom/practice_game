package game.avalanche;

import game.character.Monkey;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BananaAvalanche {

    private final ImageView node;
    private final Image[] frames;

    private int currentFrame = 0;
    private double animationTimer = 0;

    private double x;
    private final double y;

    private static final double WIDTH = 520;
    private static final double HEIGHT = 280;

    private static final double START_X = -470;
    private static final double MIN_X = -490;

    private static final double NORMAL_SPEED = 3;
    private static final double DANGER_SPEED = 6;
    private static final double DANGER_X = -180;

    private static final double BOOST_PUSH_BACK_SPEED = 80;

    private static final double FRAME_TIME = 0.12;

    public BananaAvalanche(double groundY) {
        frames = new Image[9];

        for (int i = 0; i < frames.length; i++) {
            frames[i] = new Image(
                    getClass().getResourceAsStream("/images/b" + (i + 1) + ".png")
            );
        }

        node = new ImageView(frames[0]);
        node.setFitWidth(WIDTH);
        node.setFitHeight(HEIGHT);
        node.setPreserveRatio(true);

        x = START_X;

        y = groundY - HEIGHT + 40;

        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    public void update(double time, Monkey monkey) {
        updateAnimation(time);

        double speed = x >= DANGER_X ? DANGER_SPEED : NORMAL_SPEED;

        if (monkey.isDashing()) {
            x -= BOOST_PUSH_BACK_SPEED * time;
        } else {
            x += speed * time;
        }

        if (x < MIN_X) {
            x = MIN_X;
        }

        node.setLayoutX(x);
    }

    private void updateAnimation(double time) {
        animationTimer += time;

        if (animationTimer >= FRAME_TIME) {
            animationTimer = 0;
            currentFrame++;

            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }

            node.setImage(frames[currentFrame]);
        }
    }

    public boolean touches(Monkey monkey) {

        double avalancheLeft = node.getBoundsInParent().getMinX() + WIDTH - 20;
        double avalancheRight = node.getBoundsInParent().getMaxX() - 20;

        double avalancheTop = node.getBoundsInParent().getMaxY() - 80;
        double avalancheBottom = node.getBoundsInParent().getMaxY();

        double monkeyLeft = monkey.getNode().getBoundsInParent().getMinX();
        double monkeyRight = monkey.getNode().getBoundsInParent().getMaxX();

        double monkeyTop = monkey.getNode().getBoundsInParent().getMinY();
        double monkeyBottom = monkey.getNode().getBoundsInParent().getMaxY();

        return avalancheRight >= monkeyLeft &&
                avalancheLeft <= monkeyRight &&
                avalancheBottom >= monkeyTop &&
                avalancheTop <= monkeyBottom;
    }

    public Node getNode() {
        return node;
    }
}