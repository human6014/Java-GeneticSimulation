
public class Predator {
    //媛쒖껜 �쐞移�
    private int x;
    private int y;
    //�떆裕щ젅�씠�뀡 留� �겕湲�
    private final int mapWidth = 50;
    private final int mapHight = 50;
    //媛쒖껜 �겕湲�
    private final int radius = 10;

    public Predator() {
        this.x = (int) (Math.random() * mapWidth);
        this.y = (int) (Math.random() * mapHight);
    }
    public Predator(int x,int y) {
    	this.x=x;
    	this.y=y;
    }
    public void Move() {
        x++;

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getRadius() {
        return radius;
    }
}
