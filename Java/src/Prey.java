public class Prey {

	private Gene gene;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
	private int xPoint[] = new int[4];
	private int yPoint[] = new int[4];
	private double lastDirection;
	private int countDescendent;
	private int count;

	// 자식 생성 시 사용
	public Prey(int xPoint[], int yPoint[], Gene gene) {
		this.count = 1;
		for (int i = 0; i < 4; i++) {
			this.xPoint[i] = (int) xPoint[i];
			this.yPoint[i] = (int) yPoint[i];
		}
		this.gene = gene;
		double random = (Math.random() * 359);
		lastDirection = Math.toRadians(random);
		countDescendent = 0;
	}

	public Prey() { // 첫 시뮬레이션 사용 시 사용
		gene = new Gene();
		this.count = 1;
		double random = (Math.random() * 359);
		lastDirection = Math.toRadians(random);
		double supportTem = 90 + random;
		double temDirection = Math.toRadians(supportTem);
		double temX[] = new double[4];
		double temY[] = new double[4];
		boolean flag = false;

		while (!flag) {

			temX[0] = (double) (Math.random() * mapWidth - 30 + 60);
			temY[0] = (double) (Math.random() * mapHeight - 30 + 60);

			temX[1] = (temX[0] + gene.getHeight() * Math.cos(lastDirection));
			temY[1] = (temY[0] + gene.getHeight() * Math.sin(lastDirection));

			temX[2] = (temX[0] + gene.getWidth() * Math.cos(temDirection) + gene.getHeight() * Math.cos(lastDirection));
			temY[2] = (temY[0] + gene.getWidth() * Math.sin(temDirection) + gene.getHeight() * Math.sin(lastDirection));

			temX[3] = (temX[0] + gene.getWidth() * Math.cos(temDirection));
			temY[3] = (temY[0] + gene.getWidth() * Math.sin(temDirection));
			for (int i = 0; i < 4; i++) {
				if (temX[i] > 0 && temY[i] > 0 && temX[i] < mapWidth && temY[i] < mapHeight) {
					flag = true;
					break;
				}
			}
			System.out.println("temX[0]" + (int) temX[0] + "\ttemY[0]" + (int) temY[0]);
			System.out.println("temX[1]" + (int) temX[1] + "\ttemY[0]" + (int) temY[1]);
			System.out.println("temX[2]" + (int) temX[2] + "\ttemY[0]" + (int) temY[2]);
			System.out.println("temX[3]" + (int) temX[3] + "\ttemY[0]" + (int) temY[3]);
			System.out.println("----------------------------------");

		}

		for (int i = 0; i < 4; i++) {
			this.xPoint[i] = (int) temX[i];
			this.yPoint[i] = (int) temY[i];
		}
	}

	public void Move() {

		boolean flag = false;
		double temX[] = new double[4];
		double temY[] = new double[4];
		double temDic = lastDirection;
		/*
		 * if (count % gene.getActivity() == 0) { double random = (Math.random() * 359);
		 * temDic = Math.toRadians(random); }
		 */
		for (int i = 0; i < 4; i++) {
			temX[i] = this.xPoint[i] + gene.getSpeed() * Math.cos(temDic) * Controller.Acceleration;
			temY[i] = this.yPoint[i] + gene.getSpeed() * Math.sin(temDic) * Controller.Acceleration;
		}

		while (!flag) {
			System.out.println("\ttemX[0] : " + (int) temX[0] + "\ttemY[0] : " + (int) temY[0] + "\tcos : "
					+ Math.cos(temDic) + "\tsin : " + Math.sin(temDic));
			for (int i = 0; i < 4; i++) {
				if (temX[i] > 0 && temY[i] > 0 && temX[i] < mapWidth && temY[i] < mapHeight) {
					flag = true;
					break;
				}
			}
			if (flag == true)
				break;

			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
			double supportTem = 90 + temDic;
			double temDirection = Math.toRadians(supportTem);

			// 각도 설정
			// 좌표 구하고->기존 한 점을 바탕으로 4개의 좌표를 구함
			// 그 좌표를 기준으로 재설정한 각도만크 이동
			// 평가
			
			temX[0] = xPoint[0] + Math.cos(temDic) * gene.getSpeed();
			temY[0] = yPoint[0] + Math.sin(temDic) * gene.getSpeed();

			temX[1] = (xPoint[1] + Math.cos(temDic) * gene.getSpeed());
			temY[1] = (yPoint[1] + Math.sin(temDic) * gene.getSpeed());

			temX[2] = (xPoint[2] + Math.cos(temDic) * gene.getSpeed());
			temY[2] = (yPoint[2] + Math.sin(temDic) * gene.getSpeed());

			temX[3] = (xPoint[3] + Math.cos(temDic) * gene.getSpeed());
			temY[3] = (yPoint[3] + Math.sin(temDic) * gene.getSpeed());

			/*
			 * for (int i = 0; i < 4; i++) { temX[i] += gene.getSpeed() * Math.cos(temDic) *
			 * Controller.Acceleration; temY[i] += gene.getSpeed() * Math.sin(temDic) *
			 * Controller.Acceleration; }
			 */
		}

		count++;
		if (count < 0) {
			count = 1;
		}
		for (int i = 0; i < 4; i++) {
			xPoint[i] = (int) temX[i];
			yPoint[i] = (int) temY[i];
		}
		lastDirection = temDic;

	}

	public int[] getX() {
		return xPoint;
	}

	public int[] getY() {
		return yPoint;
	}

	// public double getRadius() { return gene.getRadius(); }

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
		return new Prey(xPoint, yPoint, gene.Genetic(Controller.mutationRate));
	}

	public boolean isBreedingComplete() {
		if (countDescendent < 3) {
			return false;
		}
		return true;
	}
}
