package game.obstacle;

import game.GameService;
import game.character.Monkey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * Керує створенням, рухом та видаленням бочок на рівні.
 */

public class BarrelGenerator {

    private static final List<Barrel> activeBarrels = new ArrayList<>();
    private static final Random random = new Random();

    private static final double BARREL_SIZE = 65;

    /*
     * Генерує нову бочку на платформі з певною ймовірністю.
     */

    public static void generateBarrel(double x, double platformY) {
        // Ймовірність появи бочки: 1 з 3
        int chance = random.nextInt(3);

        if (chance != 0) {
            return;
        }

        double y = platformY - BARREL_SIZE + 10;

        Barrel barrel = new Barrel(
                "/images/barrel.png",
                x,
                y,
                BARREL_SIZE,
                GameService.getCurrentSpeed()
        );

        GameService.getRoot().getChildren().add(barrel.getImageView());
        activeBarrels.add(barrel);
    }

    /*
     * Оновлює положення всіх активних бочок.
     * Видаляє бочки, які більше не видно на сцені.
     */

    public static void moveBarrels(double time) {
        List<Barrel> toRemove = new ArrayList<>();

        for (Barrel barrel : activeBarrels) {
            if (!barrel.update(time)) {
                toRemove.add(barrel);
            }
        }

        for (Barrel barrel : toRemove) {
            activeBarrels.remove(barrel);
            GameService.getRoot().getChildren().remove(barrel.getImageView());
        }
    }

    /*
     * Перевіряє зіткнення персонажа з будь-якою бочкою.
     *
     * @return true якщо сталося зіткнення
     */

    public static boolean checkCollision(Monkey monkey) {
        for (Barrel barrel : activeBarrels) {
            if (barrel.touches(monkey.getNode())) {
                return true;
            }
        }

        return false;
    }

    /*
     * Синхронізує швидкість бочок зі швидкістю ігрового світу.
     */
    public static void changeSpeed(double newSpeed) {
        for (Barrel barrel : activeBarrels) {
            barrel.setSpeed(newSpeed);
        }
    }
}