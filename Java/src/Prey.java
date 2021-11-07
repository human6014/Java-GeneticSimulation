public class Prey {

	private Gene gene;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
	private double xPoint[] = new double[4];
	private double yPoint[] = new double[4];
	private double lastDirection;
	private int countDescendent;
	private int count;

	// 자식 생성 시 사용
	public Prey(double xPoint[], double yPoint[], Gene gene) {
		this.count = 1;
		for (int i = 0; i < 4; i++) {
			this.xPoint[i] =  xPoint[i];
			this.yPoint[i] =  yPoint[i];
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
		double Direction = Math.toRadians(random);
		double temDirection = Math.toRadians(90-random);
		
		double temX[] = new double[4];
		double temY[] = new double[4];
		boolean flag = false;
		
		while (!flag) {

			temX[0] = (double) (Math.random() * mapWidth - 70 + 60);
			temY[0] = (double) (Math.random() * mapHeight - 80 + 90);

			temX[1] = temX[0] + gene.getHeight() * Math.cos(Direction);
			temY[1] = temY[0] + gene.getHeight() * Math.sin(Direction);

			temX[2] = (temX[0] - gene.getWidth() * Math.cos(temDirection)) + gene.getHeight() * Math.cos(Direction);
			temY[2] = (temY[0] + gene.getWidth() * Math.sin(temDirection)) + gene.getHeight() * Math.sin(Direction);

			temX[3] = temX[0] - gene.getWidth() * Math.cos(temDirection);
			temY[3] = temY[0] + gene.getWidth() * Math.sin(temDirection);
			for (int i = 0; i < 4; i++) {
				if ((int) temX[i] > 10 && (int) temY[i] > 35 && (int) temX[i] < mapWidth - 10 && (int) temY[i] < mapHeight - 20) {
					lastDirection=Direction;
					flag = true;
					break;
				}
			}
			System.out.println("----------------------------------");
			System.out.println("temX[0]" + (int) temX[0] + "\ttemY[0]" + (int) temY[0]);
			System.out.println("temX[1]" + (int) temX[1] + "\ttemY[0]" + (int) temY[1]);
			System.out.println("temX[2]" + (int) temX[2] + "\ttemY[0]" + (int) temY[2]);
			System.out.println("temX[3]" + (int) temX[3] + "\ttemY[0]" + (int) temY[3]);
			System.out.println("degree : "+random);
			System.out.println("----------------------------------");
			 
		}

		for (int i = 0; i < 4; i++) {
			this.xPoint[i] = (int) temX[i];
			this.yPoint[i] = (int) temY[i];
			System.out.println("X[i]" + (int) xPoint[i] + "\tY[i]" + (int) yPoint[i]);
		}
		
	}

	public void Move() {
		
		boolean flag = true;
		int check = 0;
		double temX[] = new double[4];
		double temY[] = new double[4];
		double temDic = lastDirection;
		double temDegree = Math.toDegrees(temDic);
		double temSupDic = Math.toRadians(temDegree + 90);


		if (count % gene.getActivity() == 0) {
			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
		}
		for (int i = 0; i < 4; i++) {
			temX[i] = this.xPoint[i] + gene.getSpeed() * Math.cos(temDic) * Controller.Acceleration;
			temY[i] = this.yPoint[i] + gene.getSpeed() * Math.sin(temDic) * Controller.Acceleration;
		}
		int count = 0;
		while (true) {

			System.out.println("\ttemX[0] : " + (int) temX[0] + "\ttemY[0] : " + (int) temY[0] + "\tcos : "
					+ Math.cos(temDic) + "\tsin : " + Math.sin(temDic));

			flag = true;
			for (int i = 0; i < 4; i++) {
				if ((int) temX[i] > 10 && (int) temY[i] > 35 && (int) temX[i] < mapWidth-10 && (int) temY[i] < mapHeight-20) {
					check = i;
				}

				if ((int) temX[i] < 10 || (int) temY[i] < 35 || (int) temX[i] > mapWidth-10 || (int) temY[i] > mapHeight-20) {
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

			temX[1] = temX[0] + gene.getHeight() * Math.cos(temDic);
			temY[1] = temY[0] + gene.getHeight() * Math.sin(temDic);

			temX[2] = (temX[0] - gene.getWidth() * Math.cos(temSupDic)) + gene.getHeight() * Math.cos(temDic);
			temY[2] = (temY[0] - gene.getWidth() * Math.sin(temSupDic)) + gene.getHeight() * Math.sin(temDic);

			temX[3] = temX[0] - gene.getWidth() * Math.cos(temSupDic);
			temY[3] = temY[0] - gene.getWidth() * Math.sin(temSupDic);

		}


		count++;
		if (count < 0) {
			count = 1;
		}
		for (int i = 0; i < 4; i++) {
			xPoint[i] =  temX[i];
			yPoint[i] =  temY[i];
		}
		lastDirection = temDic;

	}
	/*
	public void Move() {
		
		boolean flag = false;
		double temX[] = new double[4];
		double temY[] = new double[4];
		double temDic = lastDirection;
		if (count % gene.getActivity() == 0) {
			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
		}
		for (int i = 0; i < 4; i++) {
			temX[i] = this.xPoint[i] + gene.getSpeed() * Math.cos(temDic) * Controller.Acceleration;
			temY[i] = this.yPoint[i] + gene.getSpeed() * Math.sin(temDic) * Controller.Acceleration;
		}
		int count = 0;
		/*
		while (!flag) {
			count++;
			System.out.println("\ttemX[0] : " + (int) temX[0] + "\ttemY[0] : " + (int) temY[0] + "\tcos : "
					+ Math.cos(temDic) + "\tsin : " + Math.sin(temDic));
			for (int i = 0; i < 4; i++) {
				if ((int) temX[i] > 65 && (int) temY[i] > 90 && (int) temX[i] < mapWidth - 50 && (int) temY[i] < mapHeight - 70) {
					flag = true;
					break;
				}
			}
			if (flag == true)
				break;

			if (count % 100 == 0)
				break;
			double random = (Math.random() * 359);
			temDic = Math.toRadians(random);
			double temDirection = Math.toRadians(90-random);
			
			for (int i = 0; i < 4; i++) {
				temX[i] = this.xPoint[i] + gene.getSpeed() * Math.cos(temDic) * Controller.Acceleration;
				temY[i] = this.yPoint[i] + gene.getSpeed() * Math.sin(temDic) * Controller.Acceleration;
			}
			// 각도 설정
			// 좌표 구하고->기존 한 점을 바탕으로 4개의 좌표를 구함
			// 그 좌표를 기준으로 재설정한 각도만크 이동
			// 평가

			
			/*
			 * for (int i = 0; i < 4; i++) { temX[i] += gene.getSpeed() * Math.cos(temDic) *
			 * Controller.Acceleration; temY[i] += gene.getSpeed() * Math.sin(temDic) *
			 * Controller.Acceleration; }
			 
		}

		count++;
		if (count < 0) {
			count = 1;
		}
		for (int i = 0; i < 4; i++) {
			xPoint[i] =  temX[i];
			yPoint[i] =  temY[i];
		}
		lastDirection = temDic;

	}
*/
	public double[] getX() {
		return xPoint;
	}

	public double[] getY() {
		return yPoint;
	}

	public int getX0() {
		return (int)xPoint[0];
	}
	public int getY0() {
		return (int)yPoint[0];
	}
	public int getX1() {
		return (int)xPoint[1];
	}
	public int getY1() {
		return (int)yPoint[1];
	}
	public int getX2() {
		return (int)xPoint[2];
	}
	public int getY2() {
		return (int)yPoint[2];
	}
	public int getX3() {
		return (int)xPoint[3];
	}
	public int getY3() {
		return (int)yPoint[3];
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
