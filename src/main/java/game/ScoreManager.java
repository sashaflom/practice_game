package game;

public class ScoreManager {

    private double totalDistance;
    private int totalBananas;

    public ScoreManager(){
        totalDistance = 0.0;
        totalBananas = 0;
    }

    public void updateDistance(double time, double currentSpeed){
        totalDistance += currentSpeed*time;
        // System.out.println(getDistanceInMeters());
    }

    public int getDistanceInMeters(){
        return (int) (totalDistance/10.0);
    }

}
