import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_11399.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int b = 0;
		int res = 0;
		for(int i=0;i<n;i++)
		{
			int num = Integer.parseInt(st.nextToken());
			pq.offer(num);
		}
		for(int i=0;i<n;i++)
		{
			int num = pq.poll();
			res += num+b;
			b +=num;
		}
		System.out.println(res);
	}

}
