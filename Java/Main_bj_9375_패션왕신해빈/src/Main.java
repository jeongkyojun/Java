import java.io.*;
import java.util.*;

public class Main {

	static int sum = 0;
	static int[] R;
	static int size = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_9375.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			sum = 0;
			size = 0;
			int N = Integer.parseInt(bf.readLine());
			String[]str = new String[N];
			int[] F = new int[N];
			R = new int[N];
			
			boolean isIn = false;
			for (int i = 0; i < N; i++) {
				R[i] = i;
				StringTokenizer st = new StringTokenizer(bf.readLine());
				st.nextToken();
				String s = st.nextToken();
				isIn = false;
				for(int j=0;j<size;j++)
				{
					if(s.equals(str[j]))
					{
						F[j]++;
						isIn = true;
						break;
					}
				}
				if(!isIn)
				{
					F[size] = 1;
					str[size++] = s;
				}
			}
			// 안입은경우 까지 포함하면 F[]{1,2} -> F[]{2,3}이 된다.
			// 2*3에서 하나도 안입은경우 1을 빼면 조합의 합이 나온다.
			sum = 1;
			for(int i=0;i<size;i++)
			{
				sum*=F[i]+1;
			}
			sum--;
			// 조합 수행시 시간초과 발생
			/*
			for (int i = 1; i <= size; i++) {
				Subset(0, 0, i, F);
			}
			*/
			bw.write("" + sum);
			bw.write("\n");
			bw.flush();
		}
	}

	static void Subset(int p, int s, int n, int[] F) {
		if (s == n) {
			int a = 1;
			for (int i = 0; i < n; i++) {
				a *= F[R[i]];
			}
			sum += a;
			return;
		}
		for (int i = p; i < size; i++) {
			R[s] = i;
			Subset(i + 1, s + 1, n, F);
		}
	}
}
