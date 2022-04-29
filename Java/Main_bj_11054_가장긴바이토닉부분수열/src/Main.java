import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_11054.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		int[] mat = new int[N];
		int[] length = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] line = new int[N];
		int cnt = 0;
		boolean find;
		for(int i=0;i<N;i++)
		{
			find = true;
			int a = Integer.parseInt(st.nextToken());
			mat[i] = a;
			for(int j=0;j<cnt;j++)
			{
				if(line[j]>=a)
				{
					line[j] = a;
					length[i] = j+1;
					find = false;
					break;
				}
			}
			if(find)
			{
				line[cnt++] = a;
				length[i] = cnt;
			}
		}
		System.out.println(Arrays.toString(length));
		cnt = 0;
		int max = 0;
		for(int i=N-1;i>=0;i--)
		{
			find = true;
			int a = mat[i];
			for(int j=0;j<cnt;j++)
			{
				if(line[j]>=a)
				{
					line[j] = a;
					length[i] += j;
					if(max<length[i])
						max = length[i];
					find = false;
					break;
				}
			}
			if(find)
			{
				line[cnt++] = a;
				length[i] += cnt-1;	
				if(max<length[i])
					max = length[i];
			}
		}
		System.out.println(Arrays.toString(length));
		System.out.println(max);
	}

}
