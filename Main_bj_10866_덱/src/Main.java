import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++)
		{
			System.out.println("#"+tc+" ");
			int[] deck = new int[10001];
			int size = 10001;
			int head = 0;
			int back = 0;
			int n = Integer.parseInt(bf.readLine());
			for(int i=0;i<n;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				String str = st.nextToken();
				if(str.equals("push_back")) {
					int value = Integer.parseInt(st.nextToken());
					deck[back++] = value;
					back%=size;
				}else if(str.equals("push_front")) {
					int value = Integer.parseInt(st.nextToken());
					head--;
					if(head<0) head+=size;
					deck[head] = value;
				}else if(str.equals("pop_back")) {
					back--;
					if(back<0) back+=size;
					System.out.println(deck[back]);
				}else if(str.equals("pop_front")) {
					System.out.println(deck[head++]);
					head%=size;
				}else if(str.equals("front")) {
					
				}else if(str.equals("back")) {
					
				}else if(str.equals("size")) {
					
				}else if(str.equals("empty")) {
					
				}
			}
		}
	}

}
