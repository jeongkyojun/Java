import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// a = 0x61, z = 0x61+25
		String str = bf.readLine();
		int[] top = new int[26];
		int[][] chk = new int[str.length() + 1][26];
		for (int i = 1; i <= str.length(); i++) {
			// 누적합 설정
			for (int j = 0; j < 26; j++) {
				chk[i][j] += chk[i - 1][j];
			}
			chk[i][(str.charAt(i-1) - 0x61)]++;
		}
		int t = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(bf.readLine());
			int n = st.nextToken().charAt(0) - 0x61;
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write((chk[b+1][n] - chk[a][n])+"\n");
		}
		bw.flush();
	}

}
