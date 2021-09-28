public class Prey {
	
	private Controller controller;
    private Gene gene;
    private final int mapWidth = 50;
    private final int mapHight = 50;
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
        this.count = 1;
        this.x = (int) (Math.random() * mapWidth);
        this.y = (int) (Math.random() * mapHight);
        this.gene = new Gene();
        double random = (Math.random() * 359);
        lastDirection = Math.toRadians(random);
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
            if(temX > 10 && temX < 1235 && temY > 30 && temY < 775)
                break;
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
