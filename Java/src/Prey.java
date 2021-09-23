public class Prey {

    private double x;
    private double y;
    private final int mapWidth = 50;
    private final int mapHight = 50;
    private double lastDirection;
    private boolean count;
    private Gene gene;

    public Prey(double x, double y, Gene gene) {
        this.count = true;
        this.x = x;
        this.y = y;
        this.gene = gene;
    }
    public Prey() {
        this.count = true;
        this.x = (int) (Math.random() * mapWidth);
        this.y = (int) (Math.random() * mapHight);
        this.gene = new Gene();
    }
    public Prey(double x,double y) {
        this.count = true;
    	this.x=x;
    	this.y=y;
        this.gene = new Gene();
    }
    public void Move() {
        if(count == true) {
            while(true) {
                double random = (Math.random() * 359);
                random = Math.toRadians(random);
                lastDirection = random;
                x += gene.getSpeed() * Math.cos(random);
                y += gene.getSpeed() * Math.sin(random);

                //筌띾벊�뱽 甕곗�щ선占쎄돌筌욑옙 占쎈륫占쎈뮉 野껋럩�뒭
                if(x > 10 && x < 1250 && y > 30 && y < 790) {
                    break;
                }

            }
            count = false;
        }
        else
        {
            x += gene.getSpeed() * Math.cos(lastDirection);
            y += gene.getSpeed() * Math.sin(lastDirection);

            if(x < 10 || x > 1250 || y < 30 || y > 790)
            {
                while(true) {
                    double random = (Math.random() * 359);
                    random = Math.toRadians(random);
                    lastDirection = random;
                    x += gene.getSpeed() * Math.cos(random);
                    y += gene.getSpeed() * Math.sin(random);

                    break;
                }
                count = false;
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Prey reproduceBySelf() {
        return new Prey(x,y,gene.Genetic());
    }

    public double getRadius() {
    	return gene.getRadius();
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }
}
