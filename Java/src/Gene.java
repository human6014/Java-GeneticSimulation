public class Gene {
    private final double speed;
    private final double radius;
    private final int activity;

    public Gene(double speed, double radius, int activity) {
        this.speed = speed;
        this.radius = radius;
        this.activity = activity;
    }

    public Gene() {
        this.speed = ((Math.random() * (2 - 0.05)) + 0.05);
        this.radius = 30;
        this.activity = 800;
    }

    public Gene Genetic(double mutationProbability) {
        double mutation = mutationProbability * 1000;
        double childSpeed = this.speed;
        int childActivity = this.activity;
        double childRadius = this.radius;
        int probability;

        //�솢�룞�꽦 �쑀�쟾�옄 �깮�꽦
        int tem =  (int)(Math.random() * 100000 + 1);
        if (tem < mutation) {
            probability = (int)(Math.random() * 10 + 1);
            //�솢�룞�꽦 利앷�
            if (probability > 5) {
                childActivity = this.activity + 5;
            }
            //�솢�룞�꽦 媛먯냼
            else {
                childActivity = this.activity - 5;
                if(childActivity < 0) {
                    childActivity = 1;
                }

            }
        }

        //�냽�룄 �쑀�쟾�옄 �깮�꽦
        tem =  (int)(Math.random() * 100000 + 1);
        if (tem < mutation) {
            probability = (int)(Math.random() * 10 + 1);
            //�냽�룄 利앷�
            if (probability > 5) {
                childSpeed = this.speed + 0.05;
            }
            //�냽�룄 媛먯냼
            else {
                childSpeed = this.speed - 0.05;
                if(childSpeed < 0) {
                    childSpeed = 0.005;
                }
            }

        }

        //紐⑥뼇 �쑀�쟾�옄 �깮�꽦
        tem =  (int)(Math.random() * 100000 + 1);
        if (tem < mutation) {
            probability = (int)(Math.random() * 10 + 1);
            //紐⑥뼇 利앷�
            if (probability > 5) {
                childRadius = this.radius + 5;

            }
            //紐⑥뼇 媛먯냼
            else {
                childRadius = this.radius - 5;
                if(childRadius < 0) {
                    childRadius = 3;
                }
            }
        }

        return new Gene(childSpeed, childRadius, childActivity);
    }

    public double getRadius() {
        return radius;
    }

    public double getSpeed() {
        return speed;
    }

    public int getActivity() {
        return activity;
    }
}
