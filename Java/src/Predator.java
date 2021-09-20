public class Predator {

    private double x;
    private double y;
    private double lastDirection;
    private boolean count;
    private static final int safeZoneRadius = 100;
    private static final int safeZoneX = 590;
    private static final int safeZoneY = 360;
    private final int mapWidth = 50;
    private final int mapHight = 50;

    private final int radius = 10;

    public Predator() {
        this.count = true;
        this.x = (double) (Math.random() * mapWidth);
        this.y = (double) (Math.random() * mapHight);
    }
    public Predator(double x,double y) {
        this.count = true;
        this.x=x;
        this.y=y;
    }
    public void Move() {
        if(count == true) {
            while(true) {
                double random = (Math.random() * 359);
                random = Math.toRadians(random);
                lastDirection = random;
                x += 0.3 * Math.cos(random);
                y += 0.3 * Math.sin(random);
                //揶쏆뮇猿쒎첎占� 筌띾벊�뱽 甕곗�щ선占쎄텆 野껋럩�뒭x
                if(x > 10 && x < 1250 && y > 30 && y < 790)
                {
                    //揶쏆뮇猿쒎첎占� 占쎈툧占쎌읈筌욑옙占쏙옙占쎈퓠 占쎌뿳占쎈뮉 野껋럩�뒭x
                    double distanceFromSafe = (double) (Math.pow((x - safeZoneX), 2)
                    									+ Math.pow((y - safeZoneY), 2));
                    distanceFromSafe = Math.sqrt(distanceFromSafe);
                    if (distanceFromSafe > safeZoneRadius - radius) {
                        break;
                    }
                }

            }
            count = false;

        }
        else
        {
            x += 0.3 * Math.cos(lastDirection);
            y += 0.3 * Math.sin(lastDirection);
            double distanceFromSafe = (double) (Math.pow((x - safeZoneX), 2)
            									+ Math.pow((y - safeZoneY), 2));
            distanceFromSafe = Math.sqrt(distanceFromSafe);

            if(x < 10 || x > 1250 || y < 30 || y > 790 || distanceFromSafe < safeZoneRadius - radius)
            {
                while(true) {
                    double random = (Math.random() * 359);
                    random = Math.toRadians(random);
                    lastDirection = random;
                    x += 0.3 * Math.cos(random);
                    y += 0.3 * Math.sin(random);
                    distanceFromSafe = (double) (Math.pow((x - safeZoneX), 2)
                    							+ Math.pow((y - safeZoneY), 2));
                    distanceFromSafe = Math.sqrt(distanceFromSafe);
                    if(x > 10 && x < 1250 && y > 30 && y < 790 && distanceFromSafe > safeZoneRadius - radius)
                        break;
                }
                count = false;
            }
        }
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
