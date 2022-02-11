
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_1662.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine()); // 테스트케이스
		for (int test_case = 1; test_case <= T; test_case++) { //반복문
			System.out.print("#"+test_case+" "); // 테스트케이스 관련 출력
			/*
			 * ( 가 나오면 문자열의 개수를 stk1에 담는다. 마지막 문자는 stk2에 담는다.
			 * 이후 )가 나오면 나오기 전까지의 문자열의 개수를 구한다. 이를 stk2의 숫자 하나를 꺼내 곱한다.
			 */
			String str = bf.readLine();
			Stack<Integer> stk = new Stack<Integer>(); // 더해야 할 문자열의 개수
			Stack<Integer> stk2 = new Stack<Integer>(); // 곱해야 할 숫자
			int num=0;
			for(int i=0;i<str.length();i++)
			{
				char c = str.charAt(i);
				if(c == '(')
				{
					// 열린괄호가 나오면 지금까지의 자릿수-1을 스택 1에 넣고(더해야될 자릿수)
					// 열린괄호 직전의 숫자를 스택 2에 넣는다(곱해야할 자릿수)
					
					//System.out.print("input - num : "+num);
					stk.push(num-1); // 자리수 저장
					stk2.push(str.charAt(i-1)-'0'); // 곱셈
					//System.out.println("  stk push : "+stk.peek()+" stk2 push : "+stk2.peek());
					num = 0;
				}
				else if(c==')')
				{
					// 닫힌괄호가 나오면 지금까지의 자릿수 x 스택2의 값 + 스택1의 값으로 합친다.
					
					//System.out.print("num "+num);
					num = num*stk2.pop()+stk.pop();
					//System.out.println(" -> "+num);
				}
				else
				{
					num++;//괄호가 나오기 전까지는 자리수를 센다.
				}
			}
			System.out.println(num);
		} //테스트케이스 닫는괄호
	}

}
