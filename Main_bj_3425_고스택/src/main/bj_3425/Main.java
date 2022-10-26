package main.bj_3425;

import java.util.*;
import java.io.*;
import java.math.*;
/*
 * 문제
 * 고스택은 숫자만을 저장할 수 있고, 다음과 같은 10가지 연산을 수행할 수 있다.
 * 
 * 편의상 스택의 가장 위에 저장된 수를 첫 번째 수라고 하고, 
 * 그 다음은 차례대로 두 번째 수, 세 번째 수라고 한다.
 */

public class Main {
	// NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 109)
	static boolean NUM(int x) {
		Gostack.push(x);
		return true;
	}

	// POP: 스택 가장 위의 숫자를 제거한다.
	static boolean POP() {
		if (Gostack.isEmpty()) {
			return false;
		} else {
			Gostack.pop();
			return true;
		}
	}

	// INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
	static boolean INV() {
		if (Gostack.isEmpty())
			return false;
		int i = Gostack.pop();
		Gostack.push(i * -1);
		return true;
	}

	// DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
	static boolean DUP() {
		if (Gostack.isEmpty())
			return false;
		Gostack.push(Gostack.peek());
		return true;
	}

	// SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
	static boolean SWP() {
		if (Gostack.isEmpty())
			return false;
		int two = Gostack.pop();
		if (Gostack.isEmpty()) {
			Gostack.push(two);
			return false;
		}
		int one = Gostack.pop();
		Gostack.push(two);
		Gostack.push(one);
		return true;
	}

	// ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
	static boolean ADD() {
		if (Gostack.isEmpty())
			return false;
		int two = Gostack.pop();
		if (Gostack.isEmpty()) {
			Gostack.push(two);
			return false;
		}
		int one = Gostack.pop();
		if (Math.abs(two + one) > 1000000000)
			return false;
		Gostack.push(one + two);
		return true;
	}

	// SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
	static boolean SUB() {
		if (Gostack.isEmpty())
			return false;
		int one = Gostack.pop();
		if (Gostack.isEmpty()) {
			Gostack.push(one);
			return false;
		}
		int two = Gostack.pop();
		if (Math.abs(two - one) > 1000000000)
			return false;
		Gostack.push(two - one);
		return true;
	}

	// MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
	static boolean MUL() {
		if (Gostack.isEmpty())
			return false;
		int two = Gostack.pop();
		if (Gostack.isEmpty()) {
			Gostack.push(two);
			return false;
		}
		int one = Gostack.pop();
		BigDecimal a = new BigDecimal(two);
		BigDecimal b = new BigDecimal(one);
		BigDecimal res = a.multiply(b);
		
		if (res.abs().compareTo(new BigDecimal(1000000000))==1)
			return false;
		Gostack.push(two * one);
		return true;
	}

	// DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다.
	// 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
	static boolean DIV() {
		int m_cnt = 0;
		if (Gostack.isEmpty())
			return false;
		int two = Gostack.pop();
		if (two == 0)
			return false;
		if (two < 0)
			m_cnt++;
		if (Gostack.isEmpty()) {
			Gostack.push(two);
			return false;
		}
		int one = Gostack.pop();
		if (one < 0)
			m_cnt++;
		int res = (int) Math.pow(-1, m_cnt) * ((Math.abs(one) / Math.abs(two)));
		if (Math.abs(res) > 1000000000)
			return false;
		Gostack.push(res);
		return true;
	}

	// MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다.
	// 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
	static boolean MOD() {
		int m_cnt = 0;
		if (Gostack.isEmpty())
			return false;
		int two = Gostack.pop();
		if (two == 0)
			return false;
		if (Gostack.isEmpty()) {
			Gostack.push(two);
			return false;
		}
		int one = Gostack.pop();	
		if (one < 0)
			m_cnt++;
		int res = (int) Math.pow(-1, m_cnt) * ((Math.abs(one) % Math.abs(two)));
		if (Math.abs(res) > 1000000000)
			return false;
		Gostack.push(res);
		return true;
	}

	static Stack<Integer> Gostack = new Stack<Integer>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_3425.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		Q: while (true) {
			String[] Key = new String[100001];
			int com = 0;
			while (true) {
				String str = bf.readLine();
				if (str.equals("END"))
					break;
				else if (str.equals("QUIT"))
					break Q;
				else {
					Key[com++] = str;
				}
			}
			int N = Integer.parseInt(bf.readLine());
			for (int i = 0; i < N; i++) {
				Gostack.clear();
				int a = Integer.parseInt(bf.readLine());
				Gostack.push(a);
				// System.out.println(Gostack.toString());
				boolean key = true;
				for (int j = 0; j < com; j++) {
					StringTokenizer st = new StringTokenizer(Key[j]);
					String str = st.nextToken();
					switch (str) {
					case "NUM":
						int x = Integer.parseInt(st.nextToken());
						key = NUM(x);
						break;
					case "POP":
						key = POP();
						break;
					case "INV":
						key = INV();
						break;
					case "DUP":
						key = DUP();
						break;
					case "SWP":
						key = SWP();
						break;
					case "ADD":
						key = ADD();
						break;
					case "SUB":
						key = SUB();
						break;
					case "MUL":
						key = MUL();
						break;
					case "DIV":
						key = DIV();
						break;
					case "MOD":
						key = MOD();
						break;
					default:
						key = false;
						break;
					}
					//System.out.println(Gostack.toString());
					if (!key)
						break;
				}
				if (!key || Gostack.size() != 1)
					System.out.println("ERROR");
				else
					System.out.println(Gostack.peek());
			}
			System.out.println();
			bf.readLine();
		}
	}

}
