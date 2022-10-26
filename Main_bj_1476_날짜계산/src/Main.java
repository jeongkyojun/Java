
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args)throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/Input_bj_1476.txt")));
		//BufferedReader bf = new BufferedReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int[] mod = new int[3];
			for(int i=0;i<3;i++)
			{
				mod[i] = Integer.parseInt(st.nextToken());
			}

			/*
			 * n = 15*x + a
			 * n = 28*y + b
			 * n = 19*z + c 일때,
			 * 
			 * 15*x + a-b = 28*y 가 성립
			 * 15*x + a-c = 19*z 가 성립
			 * 
			 * 따라서, (15*x+ a-b)%28 ==0 , (15*x + a-c)%19 == 0
			 * 위 두 식을 만족하는 x에 대해
			 * 15*x + a의 최솟값이 정답이다.
			 * 
			 * 15,19,28,은 서로소이므로 
			 * 최대 공약수는 15*19*28이 되며, 
			 * 이때 x의 범위는 0 ~ 19*28 이 된다.
			 */
			for (int i = 0; i < 19 * 28; i++)
			{
				int res = 15 * i +mod[0]-mod[1]; // 15 * x + a-b = 28 * y
				if (res % 28 == 0)
				{
					res = 15 * i +mod[0]-mod[2]; // 15 * x + a - c = 19*z
					if (res % 19 == 0)
					{
						System.out.println(15*i+mod[0]); // 15*x + a
						break;
					}
				}
			}
		}
	}

}
