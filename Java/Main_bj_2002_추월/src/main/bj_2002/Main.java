package main.bj_2002;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2002.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.println("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine());
			Map<String,Integer> m_car = new HashMap<String,Integer>();
			for(int i=0;i<N;i++)
			{
				String str = bf.readLine();
				m_car.put(str, i);
			}
			int[] out = new int[N];
			for(int i=0;i<N;i++)
			{
				String str = bf.readLine();
				out[i] = m_car.get(str);
			}
			
			System.out.println(Arrays.toString(out));
			int now = 0;
			int res = 0;
			boolean[] isOut = new boolean[N];
			for(int i=0;i<N;i++)
			{
				// 나온차는 표시
				isOut[out[i]] = true;
				if(out[i]>now)
				{
					// 추월차인경우 +1
					res++;
				}
				// 현재 가장 빠른차인경우
				else if(out[i]==now)
				{
					// 안나온 차 중 가장 먼저번호차로 조정
					for(int j=now;j<N;j++)
					{
						if(!isOut[j])
						{
							now = j;
							break;
						}
					}
				}
			}
			System.out.println(res);
		}
	}

}
