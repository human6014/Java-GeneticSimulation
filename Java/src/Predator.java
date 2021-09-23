public class Predator {

    private double x;
    private double y;
    private double lastDirection;
    private boolean count;
    private static final int safeZoneRadius = 70;
    private static final int safeZoneX = 630;
    private static final int safeZoneY = 400;
    private final int mapWidth = 50;
    private final int mapHight = 50;

    private final int radius = 10;

    public Predator() {
        this.count = true;
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
                //�뤆�룇裕뉒뙼�뭿泥롥뜝占� 嶺뚮씭踰딉옙諭� �뵓怨쀯옙�됱꽑�뜝�럡�뀊 �뇦猿뗫윪占쎈뮡x
                if(x > 10 && x < 1250 && y > 30 && y < 790)
                {
                    //�뤆�룇裕뉒뙼�뭿泥롥뜝占� �뜝�럥�닱�뜝�럩�쓧嶺뚯쉻�삕�뜝�룞�삕�뜝�럥�뱺 �뜝�럩肉녑뜝�럥裕� �뇦猿뗫윪占쎈뮡x
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
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
