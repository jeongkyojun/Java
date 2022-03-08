import java.util.*;
import java.io.*;

public class Main {

	static int min;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17136.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			int[][] mat = new int[10][10];
			boolean[][] B = new boolean[10][10];
			int[] perm = new int[5];
			boolean[] chk = new boolean[5];

			for (int i = 0; i < 10; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 10; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
					if (mat[i][j] == 1)
						B[i][j] = true;
				}
			}
			min = -1;
			Search(mat, B, 0, 0, 0, 5, 5, 5, 5, 5, 0);
			System.out.println(min);
		}
	}

	static void Search(int[][] mat, boolean[][] B, int s_i, int s_j, int paper, int one, int two, int three, int four,
			int five, int res) {
		if(min!=-1 && res>min)
			return;
		// 예비 입력
		boolean[][] tmp = new boolean[10][10];
		int[][] tmp_mat = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				tmp[i][j] = B[i][j];
				tmp_mat[i][j] = mat[i][j];
			}
		}
		for (int i = s_i; i < s_i + paper; i++) {
			for (int j = s_j; j < s_j + paper; j++) {
				tmp[i][j] = false;
				tmp_mat[i][j] = paper+1;
			}
		}

		boolean find = false;
		// 종료조건 탐색
		/*
		for(int i=0;i<10;i++)
		{
			System.out.println(Arrays.toString(tmp_mat[i])+"\t"+Arrays.toString(tmp[i]));
		}
		System.out.println();
		*/
		find : for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (tmp[i][j]) {
					//System.out.println("find - continue");
					find = true;
					break find;
				}
			}
		}
		if(!find)
		{
			if(min==-1||min>res)
				min = res;
			return;
		}
		
		int n = 0;
		rec: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				//System.out.println(i+" , "+j);
				if (tmp[i][j]) {
					//System.out.println("find "+i+" , "+j);
					for (int x = 1; x <= 5; x++) {
						if(i+x>10||j+x>10)
							break;
						boolean f = false;
						search : for(int a=i;a<i+x;a++)
						{
							for(int b=j;b<j+x;b++)
							{
								if(!tmp[a][b])
								{
									f = true;
									break search;
								}
							}
						}
						if(f)
							break;
						n = x;
					}
					//System.out.println("n : "+n);
					switch (n) {
					case 5:
						if (0 <= i + 4 && i + 4 < 10 && 0 <= j + 4 && j + 4 < 10 && five > 0)
							Search(tmp_mat, tmp, i, j, 5, one, two, three, four, five - 1, res + 1);
					case 4:
						if (0 <= i + 3 && i + 3 < 10 && 0 <= j + 3 && j + 3 < 10 && four > 0)
							Search(tmp_mat, tmp, i, j, 4, one, two, three, four - 1, five, res + 1);
					case 3:
						if (0 <= i + 2 && i + 2 < 10 && 0 <= j + 2 && j + 2 < 10 && three > 0)
							Search(tmp_mat, tmp, i, j, 3, one, two, three - 1, four, five, res + 1);
					case 2:
						if (0 <= i + 1 && i + 1 < 10 && 0 <= j + 1 && j + 1 < 10 && two > 0)
							Search(tmp_mat, tmp, i, j, 2, one, two - 1, three, four, five, res + 1);
					case 1:
						if (one > 0)
							Search(tmp_mat, tmp, i, j, 1, one - 1, two, three, four, five, res + 1);
						break;
					}

					break rec;
				}
			}
		}
	}
}
