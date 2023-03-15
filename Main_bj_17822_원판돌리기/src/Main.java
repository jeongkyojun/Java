import java.io.*;
import java.util.*;

public class Main {
	static int[] di = new int[] { -1, 1, 0, 0 };
	static int[] dj = new int[] { 0, 0, 1, -1 };
	static boolean isChanged = false;
	static int sum = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= test; tc++) {

			StringTokenizer st = new StringTokenizer(bf.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			int[][] board = new int[n + 1][m + 2];
			sum = 0;
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 1; j <= m; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					sum += board[i][j];
				}
				board[i][0] = board[i][m];
				board[i][m + 1] = board[i][1];
			}
			for (int i = 0; i < t; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken()); // 번호가 x의 배수
				int d = Integer.parseInt(st.nextToken()); // 시계 & 반시계
				int k = Integer.parseInt(st.nextToken()); // k번 돌린다
				
				// 원판 돌리기
				rotate(board,n,m,x,d,k);

				// 겹친 부분확인
				boolean[][] chk = new boolean[n + 1][m + 2];
				isChanged = false;
				for (int a = 1; a <= n; a++) {
					for (int b = 1; b <= m; b++) {
						if (!chk[a][b]) {
							find(board, a, b, chk);
						}
					}
				}
				if (!isChanged) {
					board_change(board, n, m);
				}
			}
			System.out.println("#" + tc + " : " + sum);
		}
	}

	static void rotate(int[][] board,int n, int m, int x, int d, int k) {
		for (int w = 1; w * x <= n; w++) { // x의 배수 = w*x
			// 시계방향 : +1방향으로 이동
			// 반시계방향 : -1방향으로 이동
			if (d == 0) {
				for (int l = 0; l < k; l++) {
					int tmp = board[w * x][m];
					for (int j = m; j > 1; j--) {
						board[w * x][j] = board[w * x][j - 1]; // j 값을 j-1에서 당겨온다.
					}
					board[w * x][1] = tmp; // j=1위치에 맨 처음 값을 넣는다.
				}
			} else {
				for (int l = 0; l < k; l++) {
					int tmp = board[w * x][1];
					for (int j = 1; j < m; j++) {
						board[w * x][j] = board[w * x][j + 1];
					}
					board[w * x][m] = tmp;
				}
			}
			board[w * x][m + 1] = board[w * x][1];
			board[w * x][0] = board[w * x][m];
		}
	}
	
	static void find(int[][] board, int i, int j, boolean[][] chk) {
		if (board[i][j] == 0)
			return;
		chk[i][j] = true;
		boolean key = false;
		for (int d = 0; d < 4; d++) {
			if (0 < i + di[d] && i + di[d] < board.length && 0 <= j + dj[d] && j + dj[d] < board[i].length
					&& board[i + di[d]][j + dj[d]] == board[i][j]) {
				if (!chk[i + di[d]][j + dj[d]])
					find(board, i + di[d], j + dj[d], chk);
				key = true;
			}
		}
		if (key) {
			if (j > 0 && j < board[i].length - 1) {
				sum -= board[i][j];
				isChanged = true;
				board[i][j] = 0;
			}
		}
	}
	
	static void board_change(int[][] board, int n, int m) {
		double num = 0;
		double sum = 0;
		for (int a = 1; a <= n; a++) {
			for (int b = 1; b <= m; b++) {
				if(board[a][b]!=0)
					num++;
				sum+=board[a][b];
			}
		}
		double average = (double) sum /num;
		for (int a = 1; a <= n; a++) {
			for (int b = 1; b <= m; b++) {
				if (board[a][b] == 0)
					continue;
				if (board[a][b] < average) {
					board[a][b]++;
				} else if (board[a][b] > average) {
					board[a][b]--;
				}
			}
		}
	}
	
}
