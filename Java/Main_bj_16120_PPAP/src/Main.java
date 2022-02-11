import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_16120.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			String str = bf.readLine(); // 문자열을 받는다
			Stack<Character> stk = new Stack<Character>();
			String key = "PPAP";
			boolean isBoom = false;
			
			// String str을 거꾸로 확인한다.
			for (int i = str.length() - 1; i >= 0; i--) {
				char c = str.charAt(i);
				isBoom = false;
				if(c=='P')
				{
					String s = "P";
					while(!stk.isEmpty()&&s.length()<=key.length())
					{
						s+=stk.pop();
						if(s.equals(key))
						{
							isBoom = true;
							break;
						}
					}
					if(isBoom)
					{
						stk.push('P');
					}
					else
					{
						for(int j=s.length()-1;j>=0;j--)
						{
							stk.push(s.charAt(j));
						}
					}
				}
				else
					stk.push(c);
			}		
			if (stk.size()==1 && stk.peek()=='P')
				bw.write("PPAP");
			else {
				bw.write("NP");
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}

}
