public class Gene {
	private final double speed;
	private final int activity;
	private final double width;
	private final double height;

	public Gene(double speed, double width, double height, int activity) {
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.activity = activity;
	}

	public Gene() {
		this.speed = ((Math.random() * (2 - 0.05)) + 0.05);
		this.width = 30;
		this.height = 20;
		this.activity = 800;
	}

	public Gene Genetic(double mutationProbability) {
		double mutation = mutationProbability * 1000;

		double childSpeed = this.speed;
		int childActivity = this.activity;
		double childWidth = this.width;
		double childHeight = this.height;
		int probability;

		int tem = (int) (Math.random() * 100000 + 1);
		if (tem < mutation) {
			probability = (int) (Math.random() * 10 + 1);
			if (probability > 5)
				childActivity = this.activity + (int) (Math.random() * 2 + 1);
			else {
				childActivity = this.activity - (int) (Math.random() * 2 + 1);
				if (childActivity < 0)
					childActivity = 3;
			}
		}
		tem = (int) (Math.random() * 100000 + 1);
		if (tem < mutation) {
			probability = (int) (Math.random() * 10 + 1);
			if (probability > 5)
				childSpeed = this.speed + (Math.random() * 0.05 + 0.0001);
			else {
				childSpeed = this.speed - (Math.random() * 0.05 + 0.0001);
				if (childSpeed < 0)
					childSpeed = 0.0001;
			}
		}
		tem = (int) (Math.random() * 100000 + 1);
		if (tem < mutation) {
			probability = (int) (Math.random() * 10 + 1);
			if (probability > 5) {
				childWidth = this.width + (int) (Math.random() * 3 + 1);
				childHeight = this.height + (int) (Math.random() * 3 + 1);
			} else {
				childWidth = this.width + (int) (Math.random() * 3 + 1);
				childHeight = this.height + (int) (Math.random() * 3 + 1);
				if (childWidth < 0 || childHeight < 0) {
					childWidth = 1;
					childHeight = 1;
				}
			}
		}
		return new Gene(childSpeed, childWidth, childHeight, childActivity);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getSpeed() {
		return speed;
	}

	public int getActivity() {
		return activity;
	}
}
