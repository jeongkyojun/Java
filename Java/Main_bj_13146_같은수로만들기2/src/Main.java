
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
			/*
			 * 계단형으로 만들기 -> 가장 작은수부터 시작해 큰수가 나오면 상쇄, 작은수가 나오면 갱신하는대신 스택에 넣는다.
			 * 큰수가 나오는경우 스택의 값중 현재수보다 큰수가 나올때까지 뺀다.
			 */
			for (int i = 1; i < N; i++) {
				a = Integer.parseInt(bf.readLine());
				if (min < a) {
					while (!stk.isEmpty()) {
						// 수가 크면 스택에서 작은수를 제외한다.
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
					//수가 작으면 현재의 수를 스택에 보관하고 갱신한다.
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
