
public class Predator {
    //媛쒖껜 �쐞移�
    private float x;
    private float y;
    //�떆裕щ젅�씠�뀡 留� �겕湲�
    private final int mapWidth = 50;
    private final int mapHight = 50;
    //媛쒖껜 �겕湲�
    private final int radius = 10;

    public Predator() {
        this.x = (int) (Math.random() * mapWidth);
        this.y = (int) (Math.random() * mapHight);
    }
    public Predator(float x,float y) {
    	this.x=x;
    	this.y=y;
    }
    public void Move() {
        x++;

    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getRadius() {
        return radius;
    }
}
