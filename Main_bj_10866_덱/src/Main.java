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
//				System.out.print(str+" ");
				if(str.equals("push_back")) {
					int value = Integer.parseInt(st.nextToken());
					deck[back++] = value;
					back%=size;
//					System.out.println();
				}else if(str.equals("push_front")) {
					int value = Integer.parseInt(st.nextToken());
					head--;
					if(head<0) head+=size;
					deck[head] = value;
//					System.out.println();
				}else if(str.equals("pop_back")) {
					if(isEmpty(head,back))
					{
						System.out.println("-1");
					}else {	
						back--;
						if(back<0) back+=size;
						System.out.println(deck[back]);
					}
				}else if(str.equals("pop_front")) {
					if(isEmpty(head,back))
					{
						System.out.println("-1");
					}else {	
						System.out.println(deck[head++]);
						head%=size;
					}
				}else if(str.equals("front")) {
					if(isEmpty(head,back))
					{
						System.out.println("-1");
					}else {	
						System.out.println(deck[head]);
					}
				}else if(str.equals("back")) {
					if(isEmpty(head,back))
					{
						System.out.println("-1");
					}else {	
						System.out.println(deck[(back-1+size)%size]);
					}
				}else if(str.equals("size")) {
					System.out.println((back-head+size)%size);
				}else if(str.equals("empty")) {
					System.out.println(isEmpty(head,back)?"1":"0");
				}
			}
		}
	}
	
	static boolean isEmpty(int head, int tail) {
		if(head==tail) return true;
		return false;
	}
}
