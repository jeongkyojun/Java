
import java.io.*;
import java.util.*;


public class Main {

	static int Max(int a, int b)
	{
		if(a>b)
			return a;
		return b;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_16637.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case = 1;test_case <=T;test_case++)
		{
			int n = Integer.parseInt(bf.readLine());
			String s = bf.readLine();
			Stack<Integer> stk = new Stack<Integer>();
			Stack<Character> c_stk = new Stack<Character>();
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c<='9'&&'0'<=c)
				{
					stk.push(c-0x30);
				}
				else
				{
					switch(c)
					{
					case '+':
						break;
					case '-':
						break;
					case '*':
						break;
					}
				}
			}
			
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}

}
