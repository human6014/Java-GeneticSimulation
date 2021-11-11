public class Prey {

	private Gene gene;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
	private final int preadtorRadius = 18;
	private float x[] = new float[4];
	private float y[] = new float[4];
	private double lastDirection;
	private int countDescendent;
	private int count;

	// 자식 생성 시 사용
	public Prey(float x[], float y[], Gene gene) {
		this.count = 1;
		boolean flag = false;
		float temX[] = new float[4];
		float temY[] = new float[4];
		double random = (Math.random() * 359);
		double Direction = Math.toRadians(random);
		double temDirection = Math.toRadians(90 + random);
		/*
		for (int i = 0; i < 4; i++) {
			this.x[i] = x[i];
			this.y[i] = y[i];
		}
		 */
		while (!flag) {

			temX[0] = (float) x[0];
			temY[0] = (float) y[0];
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
		}
		for (int i = 0; i < 4; i++) {
			this.x[i] =  temX[i];
			this.y[i] =  temY[i];
		}
		this.gene = gene;
	}

	public Prey() { // 첫 시뮬레이션 시 사용
		gene = new Gene();
		this.count = 1;

		double random = (Math.random() * 359);
		double Direction = Math.toRadians(random);
		double temDirection = Math.toRadians(90 + random);

		float temX[] = new float[4];
		float temY[] = new float[4];
		boolean flag = false;

		while (!flag) {
			temX[0] = (float) (Math.random() * mapWidth - 70 + 60);
			temY[0] = (float) (Math.random() * mapHeight - 80 + 90);

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

		}
		for (int i = 0; i < 4; i++) {
			this.x[i] =  temX[i];
			this.y[i] =  temY[i];
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
		boolean safeCheck = false;

		if (count % gene.getActivity() == 0) {
			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
		}
		for (int i = 0; i < 4; i++) {
			temX[i] = this.x[i] + (float)gene.getSpeed() * (float)Math.cos(temDic) * Controller.Acceleration;
			temY[i] = this.y[i] + (float)gene.getSpeed() * (float)Math.sin(temDic) * Controller.Acceleration;
		}
		for (int i = 0; i < 4; i++) {
			if ((int) temX[i] > 10 && (int) temY[i] > 35 && (int) temX[i] < mapWidth - 10 && (int) temY[i] < mapHeight - 20) {
				safeCheck = true;
			}
		}
		if (!safeCheck) {
			for (int i = 0; i < 4; i++) {
				temX[i] = this.x[i];
				temY[i] = this.y[i];
			}
			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
			temSupDic = Math.toRadians(random + 90);
		}


		int count = 0;
		while (true) {
			flag = true;
			for (int i = 0; i < 4; i++) {
				if ((int) temX[i] > 10 && (int) temY[i] > 35 && (int) temX[i] < mapWidth - 10 && (int) temY[i] < mapHeight - 20) {
					check = i;
				}
				if ((int) temX[i] < 10 || (int) temY[i] < 35 || (int) temX[i] > mapWidth - 10 || (int) temY[i] > mapHeight - 20) {
					flag = false;
				}
			}

			if (flag == true) {
				break;
			}

			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
			temSupDic = Math.toRadians(random + 90);

			temX[0] = temX[check];
			temY[0] = temY[check];

			temX[1] = temX[0] + (float)gene.getWidth() * (float)Math.cos(temDic);
			temY[1] = temY[0] + (float)gene.getWidth() * (float)Math.sin(temDic);

			temX[2] = (temX[0] + (float)gene.getHeight() * (float)Math.cos(temSupDic)) + (float)gene.getWidth() * (float)Math.cos(temDic);
			temY[2] = (temY[0] + (float)gene.getHeight() * (float)Math.sin(temSupDic)) + (float)gene.getWidth() * (float)Math.sin(temDic);

			temX[3] = temX[0] + (float)gene.getHeight() * (float)Math.cos(temSupDic);
			temY[3] = temY[0] + (float)gene.getHeight() * (float)Math.sin(temSupDic);
		}
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

	public float[] hitPointX() {
		float[] answer = new float[4];
		double Direction =  (lastDirection);
		double temDirection = Math.toRadians(90 + Math.toDegrees(Direction));
		answer[0] = (float) ((x[0] - 1.41 * preadtorRadius * (float) Math.cos(temDirection)) - 1.41 * preadtorRadius * (float) Math.cos(Direction));
		answer[1] = (float) ((x[1] - 1.41 * preadtorRadius * (float) Math.cos(temDirection)) + 1.41 * preadtorRadius * (float) Math.cos(Direction));
		answer[2] = (float) ((x[2] + 1.41 * preadtorRadius * (float) Math.cos(temDirection)) + 1.41 * preadtorRadius * (float) Math.cos(Direction));
		answer[3] = (float) ((x[3] + 1.41 * preadtorRadius * (float) Math.cos(temDirection)) - 1.41 * preadtorRadius * (float) Math.cos(Direction));
		
		return answer;
	}

	public float[] hitPointY() {
		float[] answer = new float[4];
		double Direction =  (lastDirection);
		double temDirection = Math.toRadians(90 +Math.toDegrees(Direction));
		answer[0] = (float) ((y[0] - 1.41 * preadtorRadius * (float) Math.sin(temDirection)) - 1.41 * preadtorRadius * (float) Math.sin(Direction));
		answer[1] = (float) ((y[1] - 1.41 * preadtorRadius * (float) Math.sin(temDirection)) + 1.41 * preadtorRadius * (float) Math.sin(Direction));
		answer[2] = (float) ((y[2] + 1.41 * preadtorRadius * (float) Math.sin(temDirection)) + 1.41 * preadtorRadius * (float) Math.sin(Direction));
		answer[3] = (float) ((y[3] + 1.41 * preadtorRadius * (float) Math.sin(temDirection)) - 1.41 * preadtorRadius * (float) Math.sin(Direction));
		
		return answer;
	}

	public float[] getX() {
		return x;
	}

	public float[] getY() {
		return y;
	}

	public double getWidth() {
		return gene.getWidth();
	}
	public double getHeight() {
		return gene.getHeight();
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
