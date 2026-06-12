package game.bonus;

import game.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BananaGenerator {

    private static List<Banana> activeBananas = new ArrayList<>();
    private static Random random = new Random();
    private static final double SIZE = 50;

    public static void generateBanana(double x, double y){
        int generate = random.nextInt(2);
        if(generate != 1) return;
        Banana banana = new Banana("/images/banana.png", x, y, SIZE, GameService.getCurrentSpeed());
        GameService.getRoot().getChildren().add(banana.getImageView());
        activeBananas.add(banana);
    }


    public static void moveBananas(double time) {
        List<Banana> toRemove = new ArrayList<>();
        if(!activeBananas.isEmpty()){
            for (Banana banana : activeBananas){
                if(!banana.update(time)){
                    toRemove.add(banana);
                }
            }
            if(!toRemove.isEmpty()) {
                for(Banana banana : toRemove){
                    activeBananas.remove(banana);
                    GameService.getRoot().getChildren().remove(banana);
                }
            }
        }
    }

    public static void changeSpeed(double newSpeed){
        if(!activeBananas.isEmpty()) for (Banana banana : activeBananas) banana.setSpeed(newSpeed);
    }
}
