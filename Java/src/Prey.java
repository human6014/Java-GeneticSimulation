public class Prey {

	private Gene gene;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
	private float x[] = new float[4];
	private float y[] = new float[4];
	private double lastDirection;
	private int countDescendent;
	private final int preadtorRadius = 10;
	private int count;

	// 자식 생성 시 사용
	public Prey(float x[], float y[], Gene gene) {
		this.count = 1;
		for (int i = 0; i < 4; i++) {
			this.x[i] = x[i];
			this.y[i] = y[i];
		}
		this.gene = gene;
		double random = (Math.random() * 359);
		lastDirection = Math.toRadians(random);
		countDescendent = 0;
	}

	public Prey() { // 첫 시뮬레이션 시 사용
		gene = new Gene();
		this.count = 1;

		double random = (Math.random() * 359);
		//double random = 45;
		double Direction = Math.toRadians(random);
		double temDirection = Math.toRadians(90 + random);

		float temX[] = new float[4];
		float temY[] = new float[4];
		boolean flag = false;

		while (!flag) {

			temX[0] = (float) (Math.random() * mapWidth - 70 + 60);
			temY[0] = (float) (Math.random() * mapHeight - 80 + 90);
			//temX[0] = (float) (410);
			//temY[0] = (float) (135);

			temX[1] = temX[0] + (float)gene.getWidth() *(float) Math.cos(Direction);
			temY[1] = temY[0] + (float)gene.getWidth() *(float) Math.sin(Direction);

			temX[2] = (temX[0] + (float)gene.getHeight() *(float) Math.cos(temDirection)) + (float)gene.getWidth() *(float) Math.cos(Direction);
			temY[2] = (temY[0] + (float)gene.getHeight() * (float)Math.sin(temDirection)) + (float)gene.getWidth() *(float) Math.sin(Direction);

			temX[3] = temX[0] + (float) gene.getHeight() *(float) Math.cos(temDirection);
			temY[3] = temY[0] + (float)gene.getHeight() *(float) Math.sin(temDirection);
			for (int i = 0; i < 4; i++) {
				if ((int) temX[i] > 10 && (int) temY[i] > 35 && (int) temX[i] < mapWidth - 10&& (int) temY[i] < mapHeight - 20) {
					lastDirection = Direction;
					flag = true;
					break;
				}
			}
			//System.out.println("----------------------------------");
			//System.out.println("temX[0]" + (int) temX[0] + "\ttemY[0]" + (int) temY[0]);
			//System.out.println("temX[1]" + (int) temX[1] + "\ttemY[0]" + (int) temY[1]);
			//System.out.println("temX[2]" + (int) temX[2] + "\ttemY[0]" + (int) temY[2]);
			//System.out.println("temX[3]" + (int) temX[3] + "\ttemY[0]" + (int) temY[3]);
			//System.out.println("degree : " + random);
			//System.out.println("----------------------------------");

		}

		float x01 = temX[1] - temX[0];
		float x03 = temX[3] - temX[0];

		float y01 = temY[1] - temY[0];
		float y03 = temY[3] - temY[0];

		float dot = dot((float)x01, (float)y01, (float)x03, (float)y03);
		System.out.println(dot);
		System.out.println("----------------------------------");
		for (int i = 0; i < 4; i++) {
			this.x[i] = (int) temX[i];
			this.y[i] = (int) temY[i];
			//System.out.println("X[i]" + (int) x[i] + "\tY[i]" + (int) y[i]);
		}

	}

	public void Move() {

		boolean flag = true;
		int check = 0;
		float temX[] = new float[4];
		float temY[] = new float[4];
		double temDic = lastDirection;
		double temDegree = Math.toDegrees(temDic);
		double temSupDic = Math.toRadians(temDegree + 90);

		if (count % gene.getActivity() == 0) {
			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
		}
		for (int i = 0; i < 4; i++) {
			temX[i] = this.x[i] + (float)gene.getSpeed() * (float)Math.cos(temDic) * Controller.Acceleration;
			temY[i] = this.y[i] + (float)gene.getSpeed() * (float)Math.sin(temDic) * Controller.Acceleration;
		}
		int count = 0;
		while (true) {
			flag = true;
			for (int i = 0; i < 4; i++) {
				if ((int) temX[i] > 10 && (int) temY[i] > 35 && (int) temX[i] < mapWidth - 10
						&& (int) temY[i] < mapHeight - 20) {
					check = i;
				}

				if ((int) temX[i] < 10 || (int) temY[i] < 35 || (int) temX[i] > mapWidth - 10
						|| (int) temY[i] > mapHeight - 20) {
					flag = false;
				}
			}

			if (flag == true) {
				break;
			}

			double random = (Math.random() * 269 + 90);
			temDic = Math.toRadians(random);
			temSupDic = Math.toRadians(random + 90);

			temX[0] = temX[check];
			temY[0] = temY[check];

			temX[1] = temX[0] + (float)gene.getWidth() * (float)Math.cos(temDic);
			temY[1] = temY[0] + (float)gene.getWidth() * (float)Math.sin(temDic);

			temX[2] = (temX[0] + (float)gene.getHeight() * (float)Math.cos(temSupDic)) + (float)gene.getWidth() *(float) Math.cos(temDic);
			temY[2] = (temY[0] + (float)gene.getHeight() * (float)Math.sin(temSupDic)) + (float)gene.getWidth() * (float)Math.sin(temDic);

			temX[3] = temX[0] + (float)gene.getHeight() * (float)Math.cos(temSupDic);
			temY[3] = temY[0] + (float)gene.getHeight() * (float)Math.sin(temSupDic);
		}
		float x01 = temX[1] - temX[0];
		float x03 = temX[3] - temX[0];

		float y01 = temY[1] - temY[0];
		float y03 = temY[3] - temY[0];

		int dot = dot(x01, y01,x03, y03);

		count++;
		if (count < 0) {
			count = 1;
		}
		for (int i = 0; i < 4; i++) {
			x[i] = temX[i];
			y[i] = temY[i];
		}
		lastDirection = temDic;

	}
	public int dot(float x1, float y1, float x2, float y2) {
		return (int)((x1 * x2) + (y1 * y2));
	}

	public float[] hitPointX() {
		float[] answer = new float[3];
		float tem = (float) Math.toDegrees(lastDirection);
		tem += 45;
		tem = (float) Math.toRadians(tem);
		answer[0] = (float) (x[0] + 1.41 * preadtorRadius * Math.cos(tem));
		answer[1] = (float) (x[1] + 1.41 * preadtorRadius * Math.cos(tem));
		answer[2] = (float) (x[3] + 1.41 * preadtorRadius * Math.cos(tem));
		return answer;
	}

	public float[] hitPointY() {
		float[] answer = new float[3];
		float tem = (float)Math.toDegrees(lastDirection);
		tem += 45;
		tem = (float)Math.toRadians(tem);
		answer[0] = (float) (y[0] + 1.41 * preadtorRadius * Math.sin(tem));
		answer[1] = (float) (y[1] - 1.41 * preadtorRadius * Math.sin(tem));
		answer[2] = (float) (y[3] - 1.41 * preadtorRadius * Math.sin(tem));
		return answer;
	}

	public float[] getX() {
		return x;
	}

	public float[] getY() {
		return y;
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
		return new Prey(x, y, gene.Genetic(Controller.mutationRate));
	}

	public boolean isBreedingComplete() {
		if (countDescendent < 3) {
			return false;
		}
		return true;
	}
}
