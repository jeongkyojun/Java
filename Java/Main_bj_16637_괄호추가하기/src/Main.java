
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
			// 줄의 길이
			int n = Integer.parseInt(bf.readLine());
			String s = bf.readLine();
			char[] cal = new char[n/2]; // 9 = 4, 7 = 3
			int[] num = new int[n/2+1]; // 9 = 5, 7 = 4
			int[] num2 = new int[n/2]; // num2[i] = num[i] + num[i+1]
			int p = 0, q = 0;
			for(int i=0;i<s.length();i++)
			{
				if(i%2==0)
				{
					num[p] = s.charAt(i) - 0x30;
					if(p>=1)
					{
						switch(cal[q-1])
						{
						case'+':
							num2[p-1] = num[p-1]+num[p];
							break;
						case'*':
							num2[p-1] = num[p-1]*num[p];
							break;
						case'-':
							num2[p-1] = num[p-1]-num[p];
							break;
						}
					}
					p++;
				}
				else
				{
					cal[q++] = s.charAt(i); 
				}
			}
			Queue<Integer> Q1 = new LinkedList<Integer>();
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}

}
