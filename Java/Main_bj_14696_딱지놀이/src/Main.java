import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_14696.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int R = Integer.parseInt(bf.readLine());

			for (int round = 1; round <= R; round++) {
				int[] A = new int[5];
				int[] B = new int[5];
				StringTokenizer st = new StringTokenizer(bf.readLine());

				int a = Integer.parseInt(st.nextToken());
				for (int i = 0; i < a; i++) {
					A[Integer.parseInt(st.nextToken())]++;
				}
				st = new StringTokenizer(bf.readLine());

				int b = Integer.parseInt(st.nextToken());
				for (int i = 0; i < b; i++) {
					B[Integer.parseInt(st.nextToken())]++;
				}

				for (int i = 4; i >= 0; i--) {
					int n = A[i] - B[i];
					if (n > 0) {
						System.out.println("A");
						break;
					} else if (n < 0) {
						System.out.println("B");
						break;
					}
					else if(i==0)
					{
						System.out.println("D");
					}
				}
			}
			
			System.out.println();
		}
	}

}
