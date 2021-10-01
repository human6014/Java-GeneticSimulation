public class Prey {
	
	private Controller controller;
    private Gene gene;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
    private double x;
    private double y;
    private double lastDirection;
    private int countDescendent;
    private int count;

    public Prey(double x, double y, Gene gene) {
        this.count = 1;
        this.x = x;
        this.y = y;
        this.gene = gene;
        double random = (Math.random() * 359);
        lastDirection = Math.toRadians(random);
        countDescendent = 0;
    }
    public Prey() {
    	gene=new Gene();
        this.count = 1;
        double temx;
        double temy;
        while (true) {
            temx = (int) (Math.random() * mapWidth-gene.getRadius()-30 + 60);
            temy = (int) (Math.random() * mapHeight-gene.getRadius()-30 + 60);
            double distanceFromSafe = (double) (Math.pow((temx - 630), 2) + Math.pow((temy - 400), 2));
            distanceFromSafe = Math.sqrt(distanceFromSafe);
            if(temx > 10 && temx < mapWidth-gene.getRadius()-10 && temy > 30 && temy < mapHeight-gene.getRadius()-10 && distanceFromSafe >= 150/2+gene.getRadius())
                break;
        }
        this.x = temx;
        this.y = temy;
    }
    public Prey(double x,double y) {
        this.count = 1;
    	this.x=x;
    	this.y=y;
        this.gene = new Gene();
        double random = (Math.random() * 359);
        lastDirection = Math.toRadians(random);
    }
    public void Move() {
        double temX;
        double temY;
        double temDic = lastDirection;
        if(count % gene.getActivity() == 0) {
            double random = (Math.random() * 359);
            temDic = Math.toRadians(random);
        }
        temX = x + gene.getSpeed() * Math.cos(temDic) * Controller.Acceleration;
        temY = y + gene.getSpeed() * Math.sin(temDic) * Controller.Acceleration;
        while (true) {
        	if(temX > 10 && temX < mapWidth-gene.getRadius()-10 && temY > 30 && temY < mapHeight-gene.getRadius()-10) {
        		break;
        	}
            double random = (Math.random() * 359);
            temDic = Math.toRadians(random);
            temX = x + gene.getSpeed() * Math.cos(temDic) * Controller.Acceleration;
            temY = y + gene.getSpeed() * Math.sin(temDic) * Controller.Acceleration;
        }
        count++;
        if(count < 0) {
            count = 1;
        }
        x = temX;
        y = temY;
        lastDirection = temDic;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getRadius() {
    	return gene.getRadius();
    }
    public double getSpeed() {
    	return gene.getSpeed();
    }
    public int SgetActivity() {
    	return gene.getActivity();
    }
    public double getDegree() {
		return lastDirection;
	}
    public Prey reproduceBySelf() {
    	countDescendent++;
        return new Prey(x,y,gene.Genetic(controller.mutationRate));
    }

    public boolean isBreedingComplete() {
        if (countDescendent < 3) {
            return false;
        }
        return true;
    }
}
