
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_13146.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(bf.readLine());
			long res = 0;
			Stack<Integer> stk = new Stack<Integer>();
			int a = Integer.parseInt(bf.readLine());
			int min = a;
			for (int i = 1; i < N; i++) {
				a = Integer.parseInt(bf.readLine());
				if (min < a) {
					while (!stk.isEmpty()) {
						int n = stk.pop();
						if (n > a) {
							stk.push(n);
							break;
						}

					}
					res += a - min;
					min = a;
				}
				if (min > a) {
					stk.push(min);
					min = a;
				}
			}
			System.out.println("min : " + min);
			System.out.println(stk.toString());
			while (!stk.isEmpty()) {
				a = stk.pop();
				res += a - min;
				min = a;
			}
			System.out.println(res);
		}
	
	}
}
