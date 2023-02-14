import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		
		// 제일 위 카드를 버리고, 그 뒤 제일 위 카드를 제일 아래로 옮긴다
		// pop, push(pop) 반복
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=0;i<n;i++)
		{
			q.offer(i+1);
		}
		int tmp = 0;
		while(!q.isEmpty()) {
			tmp = q.poll();
			if(!q.isEmpty()) {
				q.offer(q.poll());
			}
		}
		System.out.println(tmp);
	}

}
