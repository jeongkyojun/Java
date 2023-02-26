import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		// a = 0x61, z = 0x61+25
		String str = bf.readLine();
		int[][] chk = new int[str.length()+1][26];
		for (int i = 0; i <str.length(); i++) {
			chk[i+1][(str.charAt(i) - 0x61)]++;
			for (int j = 0; j < 26; j++) {
				chk[i+1][j] += chk[i][j];
			}
		}
		int t = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(bf.readLine());
			int n = st.nextToken().charAt(0)-0x61;
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(chk[b+1][n]-chk[a][n]);
		}
	}

}
