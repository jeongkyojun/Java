import java.util.*;
import java.io.*;

public class Main {

	static List<String> strs;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2800.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String str = bf.readLine();
			
			int point = 0;
			int[] stk = new int[11];
			int top = -1;
			
			int[] open = new int[str.length()];
			int[] close = new int[str.length()];
			// 괄호 위치 표시 - 스택 사용
			for (int i = 0; i < str.length(); i++) {
				char a = str.charAt(i);
				if (a == '(')
				{
					open[point] = i;
					stk[++top] = point++;
				}
				if (a == ')')
				{
					close[stk[top--]] = i;
				}
			}
			strs = new ArrayList<String>();
			// 괄호 지우기 - 부분집합 사용
			Subset(str, open, close, 0, point, 0);
			// 정렬
			Collections.sort(strs);
			// 중복제거
			for(int i=0;i<strs.size();i++)
			{
				if(i>=1 && strs.get(i).equals(strs.get(i-1)))
					continue;
				else
					System.out.println(strs.get(i));
			}
		}

	}

	static void Subset(String str, int[] open, int[] close, int p, int n, int chk) {
		if (p == n) {
			if (chk == 0)
				return;
			String res = "";
			for (int i = 0; i < str.length(); i++) {
				int cnt=0;
				// 괄호를 확인한다
				if (str.charAt(i) == '(') {
					for (int j = 0; j < n; j++) {
						if (open[j] == i) { // 몇번째 괄호인지를 확인한다
							cnt = j;
							break;
						}
					}
					if(((1<<cnt)&chk)!=0) // 해당 괄호가 지우는괄호인지 확인한다.
						continue;
				}
				if (str.charAt(i) == ')') {
					for (int j = 0; j < n; j++) {
						if (close[j] == i) {
							cnt = j;
							break;
						}
					}
					if(((1<<cnt)&chk)!=0)
						continue;
				}
				res += str.charAt(i);
			}
			strs.add(res);
			return;
		}
		Subset(str, open, close, p + 1, n, chk);
		Subset(str, open, close, p + 1, n, chk | 1 << p);
	}
}
