import java.util.*;
import java.io.*;

너무 어렵다 ㅋㅋㅋㅋㅋㅋㅋㅋ
public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19240.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			boolean[][] mat = new boolean[N][N];
			int[] U = new int[N];
			boolean Able = true;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				mat[a][b] = true;
				mat[b][a] = true;
			}
			Able = check(mat, U);
			if (Able)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

	static boolean check(boolean[][] mat, int[] chk) {
		chk[0] = 1;
		Queue<Integer> pq = new LinkedList<Integer>();
		pq.offer(0);
		while (!pq.isEmpty()) {
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				int tmp = pq.poll();
				for (int j = 0; j < mat.length; j++) {
					if (mat[tmp][j]) {
						if (chk[j] == 0) {
							if(chk[tmp]==1)
								chk[j] = 2;
							else if(chk[tmp]==2)
								chk[j]= 1;
							else
								System.out.println("error");
							pq.offer(j);
						}
						if (chk[j] == chk[tmp])
							return false;
					}
				}
			}
		}
		return true;
	}
}
