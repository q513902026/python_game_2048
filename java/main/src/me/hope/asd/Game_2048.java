package me.hope.asd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game_2048 {
	public static final int NUMBER = 2;
	public static boolean W = false, A = false, S = false, D = false,
			GAME_FLAG = false;
	public static int[][] line = new int[4][4];
	private static int times = 16;
	private static int score = 0;
	public static Random rand = new Random(System.currentTimeMillis());
	public static void main(String[] args) {

		int steps = 0;
		System.out.println("==-init-==");
		// -------init---------
		List<Num> listn = new ArrayList<Num>();
		for (int i = 1; i <= 4; i++) {
			for (int i2 = 1; i2 <= 4; i2++) {
				Num n = new Num(i, i2, 0);
				listn.add(n);
			}
		}
		for (Num n : listn) {
			line[n.getX() - 1][n.getY() - 1] = n.getValue();
		}

		//System.out.println("out:");
		/*for (Num n : listn) {
			System.out.println(n);
		}*/
		createNum(line);
		createNum(line);
		
		
		//System.out.println("out2:");
		/*for (Num n : listn) {
			System.out.println(n);
		}
		System.out.println("==-out-==");*/
		
		/*
		 * ==-out-== Y X 2 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		System.out.print(draw2048(line));
		Scanner scan = new Scanner(System.in);
		while (!GAME_FLAG) {
			String reString = scan.next().toLowerCase();
			if (reString.equalsIgnoreCase("exit")) {
				break;
			} else if (reString.equalsIgnoreCase("w")) {
				steps++;
				Game_2048.runCommand(listn, CommandType.W);
			} else if (reString.equalsIgnoreCase("a")) {
				steps++;
				Game_2048.runCommand(listn, CommandType.A);
			} else if (reString.equalsIgnoreCase("s")) {
				steps++;
				Game_2048.runCommand(listn, CommandType.S);
			} else if (reString.equalsIgnoreCase("d")) {
				steps++;
				Game_2048.runCommand(listn, CommandType.D);
			}
			System.out.println(draw2048(line));
			
		}
		System.out.println("请输入您的大名:");
		String name = scan.next();
		System.out.println("恭喜您(" + name + "),您一共走了" + steps + "步。" + "您的分数是"
				+ score);
		scan.close();
	}

	public static int[][] runCommand(List<Num> list, CommandType type) {
		int flag;
		switch (type) {
		case A:
			for (int x = 0; x < 4; x++) {
				flag = 10;
				for (int i = 0; i < 3; i++) {
					for (int y = 1; y < 4; y++) {
						int temp = line[x][y];
						int temp2 = line[x][y - 1];
						if (temp2 == 0) {
							line[x][y - 1] = temp;
							line[x][y] = 0;
						} else if ((temp == temp2) && (y != flag)
								&& (y != flag - 1)) {
							line[x][y - 1] = temp * 2;
							line[x][y] = 0;
							flag = y;
							score += (temp * 2);
							times++;
						}
					}
				}
			}
			A = true;
			createNum(line);
			break;
		case D:
			for (int x = 0; x < 4; x++) {
				flag = 10;
				for (int i = 0; i < 3; i++) {
					for (int y = 2; y >= 0; y--) {
						int temp = line[x][y];
						int temp2 = line[x][y + 1];
						if (temp2 == 0) {
							line[x][y + 1] = temp;
							line[x][y] = 0;
						} else if ((temp == temp2) && (y != flag)
								&& (y != flag + 1)) {
							line[x][y + 1] = temp * 2;
							line[x][y] = 0;
							flag = y;
							score += (temp * 2);
							times++;
						}
					}
				}
			}
			D = true;
			createNum(line);
			break;
		case W:
			for (int y = 0; y < 4; y++) {
				flag = 10;
				for (int i = 0; i < 3; i++) {
					for (int x = 1; x < 4; x++) {
						int temp = line[x][y];
						int temp2 = line[x - 1][y];
						if (temp2 == 0) {
							line[x - 1][y] = temp;
							line[x][y] = 0;
						} else if ((temp == temp2) && (x != flag)
								&& (x != flag - 1)) {
							line[x - 1][x] = temp * 2;
							line[x][y] = 0;
							flag = x;
							score += (temp * 2);
							times++;
						}
					}
				}
			}
			W = true;
			createNum(line);
			break;
		case S:
			for (int y = 0; y < 4; y++) {
				flag = 10;
				for (int i = 0; i < 5; i++) {
					for (int x = 2; x >= 0; x--) {
						int temp = line[x][y];
						int temp2 = line[x + 1][y];
						if (temp2 == 0) {
							line[x + 1][y] = temp;
							line[x][y] = 0;
						} else if ((temp == temp2) && (x != flag)
								&& (x != flag + 1)) {
							line[x + 1][y] = temp * 2;
							line[x][y] = 0;
							flag = x;
							score += (temp * 2);
							times++;
						}
					}
				}
			}
			S = true;
			createNum(line);
			break;
		default:
			break;
		}
		return line;
	}

	public static void createNum(int[][] num) {
		boolean flag = false;
		int x ,y;
		if (times > 0) {
			while (!flag) {
				x = rand.nextInt(4);
				y = rand.nextInt(4);
				if (num[x][y] == 0) {
					num[x][y] = NUMBER;
					times--;
					flag = true;
					W = A = S = D = false;
				}

			}
		} else if (W && A && S && D) {
			GAME_FLAG = true;
			System.out.println("游戏结束!");
		}
	}

	public static String draw2048(List<Num> list) {
		StringBuffer sb = new StringBuffer();
		for (Num n : list) {
			sb.append(n.getValue());
			if (n.getY() == 4) {
				sb.append("\n");
			} else {
				sb.append("\t");
			}

		}
		return sb.toString();
	}

	public static String draw2048(int[][] num) {
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				sb.append(String.valueOf(num[x][y]));
				if (y == 3) {
					sb.append("\n");
				} else {
					sb.append("\t");
				}
			}
		}
		return sb.toString();
	}

	enum CommandType {
		W, A, S, D
	}
}
