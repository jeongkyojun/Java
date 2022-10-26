
import java.util.*;
import java.io.*;

보류

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_20304.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine()); // 암호의 범위
			int m = Integer.parseInt(bf.readLine());
			int[] mat = new int[m];
			int[] v = new int[N + 1];
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < m; i++) {
				mat[i] = Integer.parseInt(st.nextToken());
			}
			int max = -1;
			int res = 0;

			search : for (int i = 1; i <= N; i++) {
				if(v[i]!=0) continue; // 이미 적힌 수면 pass
				int set = Integer.MAX_VALUE;
				int p = 0;
				for (int j = 0; j < m; j++) { // 보안도중 최솟값을 정해 p에 담는다.
					if(mat[j]==i) // 시도한 수면 pass
						continue search;
					int a = makeR(i);
					if(a<set)
					{
						set = a;
						p = i;
					}
					int b = makeR(mat[j] ^ i);
					if(b<set)
					{
						set = b;
						p = mat[j];
					}
					v[i] = b;
					v[mat[j]] = a;
					
					
				}
				//System.out.println(i+"의 안전도 : "+set);
				if(set>max)
				{
					max = set;
					res = p;
				}
			}
			System.out.println(max);
		}
	}

	static int makeR(int n) {
		int res = 0;
		while (n > 0) {
			if ((n & 1) == 1)
				res++;
			n = n >> 1;
		}
		return res;
	}
}
