public class Prey {
    //揶쏆뮇猿� 占쎌맄燁삼옙
    private float x;
    private float y;
    private final int mapWidth = 50;
    private final int mapHight = 50;
    private Gene gene;
    //占쎌쁽占쎈뻼 甕곕뜆�뻼占쎈뻻 占쎄텢占쎌뒠占쎈릭占쎈뮉 占쎄문占쎄쉐占쎌쁽
    public Prey(float x, float y, Gene gene) {
        this.x = x;
        this.y = y;
        this.gene = gene;
    }

    //筌ｏ옙 Prey占쎄문占쎄쉐占쎈뻻 占쎄텢占쎌뒠
    public Prey() {
        this.x = (int) (Math.random() * mapWidth);
        this.y = (int) (Math.random() * mapHight);
        this.gene = new Gene();
    }
    public Prey(float x,float y) {
    	this.x=x;
    	this.y=y;
    }
    public void Move() {

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Prey ReproducebySelf() {
        return new Prey(x,y,gene.Genetic());
    }

    public int getRadius() {
        //return gene.getRadius();
    	return 10;
    }
}
