import java.io.*;
import java.time.format.ResolverStyle;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2812.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(bf.readLine()); // 테스트케이스 받기
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int N = Integer.parseInt(st.nextToken()); // 주어지는 숫자의 자리수
			int K = Integer.parseInt(st.nextToken()); // 지워야 하는 수
			
			Stack<Character> stk = new Stack<Character>();
			String str = bf.readLine();

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				// 앞의 숫자가 뒤의 숫자보다 작은경우 삭제, 큰경우 보관
				while (K > 0 && !stk.isEmpty()) {
					if (c <= stk.peek()) {
						break;
					} else {
						stk.pop();
						K--;
					}
				}
				// 남은 숫자가 있는경우 버린다.
				if(str.length()-i>K)
					stk.push(c);
			}
			
			StringBuilder sb = new StringBuilder();
			while (!stk.isEmpty()) {
				// 거꾸로 담겨있으므로 앞부분에 붙인다.
				sb.insert(0, stk.pop());
			}

			bw.write("#" + test_case + " " + sb.toString());
			bw.write("\n");
			bw.flush();
		}
	}
}
