package main.bj_1759;

// 암호만들기
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1759.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int L = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[] m = new char[C];
			char[] n = new char[C];
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < C; i++) {
				m[i] = (st.nextToken().charAt(0));
			}
			Arrays.sort(m);

			Comb(m, n, 0, 0, L, 0,0);
		}
	}

	static void Comb(char[] m, char[] n, int p, int cnt, int L, int a,int b) {
		if (cnt == L) {
			if (a > 0&&b>1) {
				for (int i = 0; i < L; i++)
					System.out.print(n[i]);
				System.out.println();
			}
			return;
		}
		for (int i = p; i < m.length; i++) {
			n[cnt] = m[i];
			if(m[i]=='a'||m[i]=='e'||m[i]=='i'||m[i]=='o'||m[i]=='u')
			{
				// 모음인 경우 a+1
				Comb(m, n, i + 1, cnt + 1, L,a+1,b);
			}
			else // 자음인경우 b+1
			{
				Comb(m, n, i + 1, cnt + 1, L,a,b+1);
			}
		}
	}

}
