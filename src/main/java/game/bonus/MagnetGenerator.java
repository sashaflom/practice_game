package game.bonus;

import game.GameService;
import game.character.Monkey;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MagnetGenerator {

    private static List<Magnet> activeMagnets = new ArrayList<>();
    private static ImageView currentMagnet = null;
    private static Random random = new Random();
    private static final double SIZE = 50;

    public static void generateMagnet(double x, double y){
        if(!activeMagnets.isEmpty()) return;
        int generate = random.nextInt(10);
        if(generate != 1) return;
        Magnet magnet = new Magnet("/images/magnet.png", x, y, SIZE, GameService.getCurrentSpeed());
        GameService.getRoot().getChildren().add(magnet.getImageView());
        activeMagnets.add(magnet);
    }


    public static void moveMagnets(double time) {
        List<Magnet> toRemove = new ArrayList<>();
        if(!activeMagnets.isEmpty()){
            for (Magnet magnet : activeMagnets){
                if(!magnet.update(time)){
                    toRemove.add(magnet);
                }
            }
            if(!toRemove.isEmpty()) {
                for(Magnet magnet : toRemove){
                    activeMagnets.remove(magnet);
                    GameService.getRoot().getChildren().remove(magnet.getImageView());
                }
            }
        }
    }

    public static int collectMagnet(Monkey monkey) {
        int collected = 0;
        List<Magnet> toRemove = new ArrayList<>();

        for (Magnet magnet : activeMagnets) {
            if (monkey.getNode().getBoundsInParent().intersects(magnet.getImageView().getBoundsInParent())) {
                collected++;
                ImageView collectedMagnet = new ImageView(magnet.getImage());
                collectedMagnet.setLayoutX(300);
                collectedMagnet.setLayoutY(10);
                collectedMagnet.setFitWidth(SIZE);
                collectedMagnet.setFitHeight(SIZE);
                if(currentMagnet != null){
                    GameService.getRoot().getChildren().remove(currentMagnet);
                }
                currentMagnet = collectedMagnet;
                GameService.getRoot().getChildren().add(collectedMagnet);
                toRemove.add(magnet);
            }
        }

        for (Magnet magnet : toRemove) {
            activeMagnets.remove(magnet);
            GameService.getRoot().getChildren().remove(magnet.getImageView());
        }

        return collected;
    }

    public static void changeSpeed(double newSpeed){
        if(!activeMagnets.isEmpty()) for (Magnet magnet : activeMagnets) magnet.setSpeed(newSpeed);
    }

    public static ImageView getCurrentMagnet() {
        return currentMagnet;
    }
}
