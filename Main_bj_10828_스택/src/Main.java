import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		int top = -1;
		int[] arr = new int[10001];
		for(int i=0;i<t;i++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			String cmd = st.nextToken();
			switch(cmd) {
			case "push":
				int val = Integer.parseInt(st.nextToken());
				arr[++top] = val;
				break;
			case "pop":
				if(top>-1)
					System.out.println(arr[top--]);
				else
					System.out.println("-1");
				break;
			case "top":
				if(top>-1)
					System.out.println(arr[top]);
				else
					System.out.println("-1");
				break;
			case "size":
				System.out.println(top+1);
				break;
			case "empty":
				System.out.println(top==-1?1:0);
				break;		
			}
		}

	}

}
