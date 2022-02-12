
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2527.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 4;
		for (int test_case = 1; test_case <= T; test_case++) {
			int[] s1 = new int[4];
			int[] s2 = new int[4];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i=0;i<4;i++)
			{
				s1[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<4;i++)
			{
				s2[i] = Integer.parseInt(st.nextToken());
			}
			
			if(s1[0]<s2[0])
			{
				if(s1[1]<s2[1])
				{
					
				}
				else if(s1[1]==s2[1])
				{
					
				}
				else // s1[1]>s2[1]
				{
					
				}
			}
			else if(s1[0]==s2[0]) // 한 변씩 일치하는경우 -> 포함 또는 동일, 또는 겹치는관계
			{
				
			}
			else // s1[0]>s2[0]
			{
				
			}
		}
	}

}
