import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2239.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int[][] mat = new int[9][9];
			int[][] point = new int[81][2];
			int p_cnt = 0;
			for (int i = 0; i < 9; i++) {
				String str = bf.readLine();
				for (int j = 0; j < 9; j++) {
					mat[i][j] = str.charAt(j) - '0';
					if (mat[i][j] == 0) {
						point[p_cnt++] = new int[] { i, j };
					}
				}
			}
			sudoku(mat, point, p_cnt, 0);
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
					System.out.print(mat[i][j]);
				System.out.println();
			}
		}
	}

	static boolean sudoku(int[][] mat, int[][] point, int n, int cnt) {
		if (n == cnt) // 모든 경우의 수를 입력하면 true를 반환한다.
			return true;

		int pi = point[cnt][0];
		int pj = point[cnt][1];
		
		int r = (pi/3) *3;
		int c = (pj/3) *3;
		for (int i = 1; i <= 9; i++) {
			boolean find = false;
			// 숫자 i를 적용할 수 있는지 확인한다.
			for(int j=0;j<9;j++)
			{
				if(mat[pi][j]==i ||mat[j][pj] == i){
					find = true;
					break;
				}
			}
			if(!find) {
				search : for(int a=r;a<r+3;a++)
				{
					for(int b=c;b<c+3;b++)
					{
						if(mat[a][b] == i)
						{
							find = true;
							break search;
						}
					}
				}
			}
			if(!find) {
				mat[pi][pj] = i;
				if(sudoku(mat,point,n,cnt+1)) // 만약 끝까지 찾으면 true를 반환한다.
					return true;
				mat[pi][pj] = 0; // 안되면 되돌린다.
			}
		}
		// 어떤 숫자도 적용할 수 없으면 false를 반환한다.
		return false;
	}
}
