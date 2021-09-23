public class Predator {

    private double x;
    private double y;
    private double lastDirection;
    private int count;
    private final int activity = 1000;
    private static final int safeZoneRadius = 100;
    private static final int safeZoneX = 462;
    private static final int safeZoneY = 340;
    private final double speed = 0.5;

    private final int radius = 10;

    public Predator() {
        this.count = 1;
    }

    public Predator(double x,double y) {
        this.count = 1;
        this.x=x;
        this.y=y;
    }

    public void Move() {
        double temX;
        double temY;
        double temDic = lastDirection;
        if(activity % count == 0) {
            double random = (Math.random() * 359);
            temDic = Math.toRadians(random);
        }
        temX = x + speed * Math.cos(temDic);
        temY = y + speed * Math.sin(temDic);
        while (true) {
            double distanceFromSafe = (double) (Math.pow((temX - safeZoneX), 2) + Math.pow((temY - safeZoneY), 2));
            distanceFromSafe = Math.sqrt(distanceFromSafe);
            if(temX > 10 && temX < 1250 && temY > 30 && temY < 790 && distanceFromSafe > safeZoneRadius + radius) {
                break;
            }

            double random = (Math.random() * 359);
            temDic = Math.toRadians(random);
            temX = x + speed * Math.cos(temDic);
            temY = y + speed * Math.sin(temDic);
        }
        count++;
        if(count < 0) {
            count = 1;
        }
        x = temX;
        y = temY;
        lastDirection = temDic;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public int getRadius() {
        return radius;
    }
}
