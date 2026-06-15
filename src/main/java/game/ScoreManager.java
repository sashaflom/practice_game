package game;

import javafx.scene.text.Text;

public class ScoreManager {

    private double totalDistance;
    private int totalBananas;
    private static final int SPEED_MARK = 200;
    private int lastMark;
    private Text score;
    private Text bananas;
    private Text acceleration;
    private static final int ACCELERATION_AMOUNT = 20;
    private int bananasForAcceleration;
    private boolean isMagnet = false;


    public ScoreManager(Text score, Text bananas, Text acceleration){
        this.score = score;
        this.bananas = bananas;
        this.acceleration = acceleration;
        acceleration.setText("до прискорення: " + ACCELERATION_AMOUNT);
        bananasForAcceleration = ACCELERATION_AMOUNT;
        totalDistance = 0.0;
        totalBananas = 0;
        lastMark = 0;
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
        bananas.setText(String.valueOf(totalBananas));
        if(bananasForAcceleration - amount >= 0) {
            bananasForAcceleration -= amount;
            acceleration.setText("до прискорення: " + bananasForAcceleration);
        }
    }

    public boolean checkForAcceleration() {
        return bananasForAcceleration == 0;
    }

    public void comeBackFromAcceleration() {
        bananasForAcceleration = ACCELERATION_AMOUNT;
        acceleration.setText("до прискорення: " + bananasForAcceleration);
    }

    public void setMagnet(boolean set){
        isMagnet = set;
    }

    public boolean checkForMagnet() {
        return isMagnet;
    }

    public boolean isMagnet() {
        return isMagnet;
    }
}
