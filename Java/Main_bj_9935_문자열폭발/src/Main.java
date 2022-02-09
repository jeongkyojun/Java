import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_9935.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String str = bf.readLine(); // 문자열을 받는다
			Stack<Character> stk = new Stack<Character>();
			String key = bf.readLine(); // 폭발 키워드를 받는다.
			
			System.out.println("key : "+key);
			// String str을 거꾸로 확인한다.
			for (int i = str.length() - 1; i >= 0; i--) {
				char c = str.charAt(i);
				//받아온 글자가 key의 맨 첫글자와 같은경우
				if (c == key.charAt(0)) {
					String s = "";
					boolean isBoom = false;
					s += c;
					// key가 한글자인경우 처리
					if (s.equals(key))
					{
						isBoom = true;
					}
					//key가 한글자 이상인 경우 추가 처리(스택을 꺼내면서 확인한다.)
					while (!isBoom&&!stk.isEmpty() && s.length() < key.length()) {
						s += stk.pop();
						if (s.equals(key))
						{
							isBoom = true;
						}
					}
					if (!isBoom) {
						for (int j = s.length() - 1; j >= 0; j--) {
							stk.push(s.charAt(j));
						}
					}
				} else {
					stk.push(c);
				}
			}
			
			if (stk.isEmpty())
				bw.write("FRULA");
			else {
				while (!stk.isEmpty())
					bw.write(stk.pop());
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}

}
