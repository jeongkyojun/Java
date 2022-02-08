
import java.io.*;
import java.util.*;

class Main {
	/*
	 * 
	 * 1. 숫자가 나오면 그대로 출력한다.
	 * 2. (나오면 스택에 push한다.
	 * 3. * 또는 /가 나오면 스택에 push한다.
	 * 4. + - 연산이 나오면 여는 괄호('('), 여는 괄호가 없다면 스택의 끝까지 출력하고 그 연산자를 스택에 push한다.
	 * 5. 닫는 괄호(')')가 나오면 여는 괄호('(')가 나올때까지 pop하여 출력한다.
	 * 
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_1918.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine()); // 임시용

		for (int test_case = 1; test_case <= T; test_case++) // 임시용 (테스트케이스 생성
		{
			Stack<Character> stk = new Stack<Character>();
			Stack<Boolean> stk2 = new Stack<Boolean>();
			boolean mul = false;
			String str = bf.readLine();
			for (int i = 0; i < str.length(); i++) {
				char C = str.charAt(i);

				if ('A' <= C && C <= 'Z') // 알파벳은 출력
				{
					System.out.print(C);
				} else {
					switch (C) {
					case '+': // 앞에 *나 / 가 있으면 '(' 가 나오거나 빌때까지 모두 꺼낸다.
					case '-':
						while (!stk.isEmpty()) {
							char _c = stk.pop();
							if (_c == '(') {
								stk.push(_c);
								break;
							} else
								System.out.print(_c);
						}
						stk.push(C); // 다 꺼내서 출력한뒤, 스택에 값을 넣는다.
						break;
					case '*':
					case '/':
						if(mul)
						{
							while (!stk.isEmpty()) {
								char _c = stk.pop();
								if (_c == '('||_c=='+'||_c=='-') {
									stk.push(_c);
									break;
								} else
									System.out.print(_c);
							}
						}
						stk.push(C);
						mul = true;
						break;
					case ')': // '(' 가 나올때까지 꺼낸다. 그리고 넘어간다.
						while (!stk.isEmpty()) {
							char _c = stk.pop();
							if (_c == '(')
								break;
							else
								System.out.print(_c);
						}
						mul = stk2.pop();
						break;
					case '(':
						stk2.push(mul);
						mul = false;
					default:
						stk.push(C);
					}
				}
			}

			while (!stk.isEmpty())
				System.out.print(stk.pop());
			System.out.println();
		} // 임시용

	}
}
