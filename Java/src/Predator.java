public class Predator {

	private Controller controller;
    private double x;
    private double y;
    private double lastDirection;
    private int count;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
    private final int safeZoneRadius = 150;
    private final int safeZoneX = 630;
    private final int safeZoneY = 400;
    private final int radius = 35;
    private final int activity = 1000;
    private final double speed = 0.3;

    public Predator() {
        this.count = 1;
        double temx;
        double temy;
        while (true) {
            temx = (int) (Math.random() * mapWidth-radius-30 + 60);
            temy = (int) (Math.random() * mapHeight-radius-30 + 60);
            double distanceFromSafe = (double) (Math.pow((temx - safeZoneX), 2) + Math.pow((temy - safeZoneY), 2));
            distanceFromSafe = Math.sqrt(distanceFromSafe);
            if(temx > 10 && temx < mapWidth-radius-10 && temy > 30 && temy < mapHeight-radius-10 && distanceFromSafe >= safeZoneRadius/2+radius)
                break;
        }
        this.x = temx;
        this.y = temy;
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
        temX = x + speed * Math.cos(temDic) * Controller.Acceleration;
        temY = y + speed * Math.sin(temDic) * Controller.Acceleration;
        while (true) {
            double distanceFromSafe = (double) (Math.pow((temX - safeZoneX), 2) + Math.pow((temY - safeZoneY), 2));
            distanceFromSafe = Math.sqrt(distanceFromSafe);
            if(temX > 10 && temX < mapWidth-radius-10 && temY > 30 && temY < mapHeight-radius-10 && distanceFromSafe >= safeZoneRadius/2+radius)
                break;

            double random = (Math.random() * 359);
            temDic = Math.toRadians(random);
            temX = x + speed * Math.cos(temDic) * Controller.Acceleration;
            temY = y + speed * Math.sin(temDic) * Controller.Acceleration;
        }
        count++;
        if(count < 0) count = 1;
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
	public double getDegree() {
		return lastDirection;
	}
}
