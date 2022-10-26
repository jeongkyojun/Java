import java.util.*;
import java.io.*;

public class Main {

	/*
	 * 1			1
	 * 2			2
	 * 3			3
	 * 1 [5] 1		4 = 1 , (3-1)*2+1 , 1
	 * 2 [3] 2		5 = 2 , (3-2)*2+1 , 2
	 * 3 [1] 3		6 = 3 , (3-3)*2+1 , 3
	 * 
	 * 7 = 1 [6] 1
	 * 8 = 2 [6] 2
	 * 9 = 3 [6] 3
	 * 10 = 4 [6] 4 = 1 [3] 1 [6] 1 [3] 1
	 * 
	 * 매 줄마다 규칙을 세워서 1 or 2 or 3 이 나오게 한다.
	 * 주의할점 : 앞 뿐만 아니라 뒤에도 공백이 존재한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2448.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#"+test_case+" ");
			
			
			int n = Integer.parseInt(bf.readLine());
			dot(n);
			
			System.out.println();
		}

	}

	static void dot(int n) {
		// StringBuilder를 통해 한번에 출력한다.
		StringBuilder sb = new StringBuilder();

		// 줄 별로 sb에 담는다.
		for (int i = 1; i <= n; i++) {
			// 앞 공백
			for (int j = 0; j < n - i; j++) {
				sb.append(" ");
			}
			
			// 별
			sb.append(star(i));
			
			// 뒷 공백
			for (int j = 0; j < n - i; j++) {
				sb.append(" ");
			}
			
			// 줄바꿈
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

	static String star(int n) {
		StringBuilder sb = new StringBuilder();
		
		// 1,2,3 인경우 바로 반환
		if (n == 1)
			return "*";
		else if (n == 2)
			return "* *";
		else if (n == 3)
			return "*****";
		else if(n>0) {
			// 4 이상인 경우 큰 값부터 제외한다 (3,6,12,24,48 ... )
			
			int i = 3;
			while (i < (n+1) / 2) {
				i = i << 1;
			}
			// 가장 큰 i값 을 구한다
			
			// n에서 i값을 뺀 값을 재귀함수로 호출한다.
			sb.append(star(n-i));
			
			// 규칙에 맞게 공백을 담는다.
			for (int j = 0; j < (i - (n - i)) * 2 + 1; j++) {
				sb.append(" ");
			}
			
			// 마찬가지로 별찍기 호출
			sb.append(star(n-i));
		}
		return sb.toString();
	}
}
