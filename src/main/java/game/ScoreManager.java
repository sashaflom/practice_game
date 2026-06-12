package game;

import javafx.scene.text.Text;

public class ScoreManager {

    private double totalDistance;
    private int totalBananas;
    private static final int SPEED_MARK = 50;
    private int lastMark;
    private Text score;
    private Text bananas;


    public ScoreManager(Text score, Text bananas){
        this.score = score;
        this.bananas = bananas;
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

}
