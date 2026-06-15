package game;

import javafx.scene.text.Text;

public class ScoreManager {

    private double totalDistance;
    private int totalBananas;
    private static final int SPEED_MARK = 50;
    private int lastMark;
    private Text score;
    private Text bananas;
    private Text acceleration;
    private int bananasForAcceleration;
    private static final int ACCELERATION_AMOUNT = 10;


    public ScoreManager(Text score, Text bananas, Text acceleration){
        this.score = score;
        this.bananas = bananas;
        this.acceleration = acceleration;
        totalDistance = 0.0;
        totalBananas = 0;
        lastMark = 0;
        bananasForAcceleration = ACCELERATION_AMOUNT;
        acceleration.setText("до прискорення: " + bananasForAcceleration);
    }

    public void updateDistance(double time, double currentSpeed){
        totalDistance += currentSpeed*time;
        score.setText("Дистанція: " + getDistanceInMeters() + " м");
        // System.out.println(getDistanceInMeters());
    }

    public int getDistanceInMeters(){
        return (int) (totalDistance/10.0);
    }

    public boolean checkForReachingMark(){
        int currentMeters = getDistanceInMeters();
        if(currentMeters - lastMark >= SPEED_MARK){
            lastMark = currentMeters;
            return true;
        }
        return false;
    }

    public void addBananas(int amount) {
        totalBananas += amount;
        bananasForAcceleration -= amount;
        bananas.setText(String.valueOf(totalBananas));
        acceleration.setText("до прискорення: " + bananasForAcceleration);
    }

}
