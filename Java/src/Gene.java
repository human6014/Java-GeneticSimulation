public class Gene {
    private final int speed;
    //媛쒖껜 �겕湲�
    private final int radius;
    private final int activity;

    //Prey媛쒖껜媛� 踰덉떇�떆 �궗�슜
    public Gene(int speed, int radius, int activity) {
        this.speed = speed;
        this.radius = radius;
        this.activity = activity;
    }


    //Prey泥� 媛쒖껜 珥덇린�솕�떆 �궗�슜
    public Gene() {
        this.speed = 10;
        this.radius = 10;
        this.activity = 10;
    }

    //�옄湲� �쑀�쟾�옄瑜� 諛뷀깢�쑝濡� �옄�떇 �쑀�쟾�옄 �깮�꽦 ->�룎�뿰蹂��씠 �벑�벑 怨좊젮
    public Gene Genetic() {
        return new Gene();
    }

    public int getRadius() {
        return radius;
    }
}
