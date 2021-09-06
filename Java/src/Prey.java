public class Prey {
    //媛쒖껜 �쐞移�
    private int x;
    private int y;
    private final int mapWidth = 50;
    private final int mapHight = 50;
    private Gene gene;

    //�옄�떇 踰덉떇�떆 �궗�슜�븯�뒗 �깮�꽦�옄
    public Prey(int x, int y, Gene gene) {
        this.x = x;
        this.y = y;
        this.gene = gene;
    }

    //泥� Prey�깮�꽦�떆 �궗�슜
    public Prey() {
        this.x = (int) (Math.random() * mapWidth);
        this.y = (int) (Math.random() * mapHight);
        this.gene = new Gene();
    }
    public Prey(int x,int y) {
    	this.x=x;
    	this.y=y;
    }
    public void Move() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Prey ReproducebySelf() {
        return new Prey(x,y,gene.Genetic());
    }

    public int getRadius() {
        //return gene.getRadius();
    	return 5;
    }
}
