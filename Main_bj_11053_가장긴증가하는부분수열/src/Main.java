import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		int[] mat = new int[N];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] line = new int[N];
		int cnt = 0;
		boolean find;
		for(int i=0;i<N;i++)
		{
			find = true;
			int a = Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++)
			{
				if(line[j]>=a)
				{
					line[j] = a;
					find = false;
					break;
				}
			}
			if(find)
				line[cnt++] = a;
			System.out.println(Arrays.toString(line));
		}
		System.out.println(cnt);
	}

}
