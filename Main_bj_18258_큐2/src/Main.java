import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++)
		{
			bw.write("#"+tc+"\n");
			int[] deck = new int[10001];
			int size = 10001;
			int head = 0;
			int back = 0;
			int n = Integer.parseInt(bf.readLine());
			for(int i=0;i<n;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				String str = st.nextToken();
				if(str.equals("push")) {
					int value = Integer.parseInt(st.nextToken());
					deck[back++] = value;
					back%=size;
				}else if(str.equals("size")) {
					bw.write(((back-head+size)%size)+"\n");
				}else if(str.equals("empty")) {
					bw.write((isEmpty(head,back)?"1":"0")+"\n");
				}else
				{
					if(isEmpty(head,back))
					{
						bw.write(-1+"\n");
					}
					else {
						if(str.equals("pop")) {
							bw.write(deck[head++]+"\n");
							head%=size;
						}else if(str.equals("front")) {
							bw.write(deck[head]+"\n");
						}else if(str.equals("back")) {	
							bw.write(deck[(back-1+size)%size]+"\n");
						}
					}
				}
			}
			bw.flush();
		}
		bw.close();
	}
	
	static boolean isEmpty(int head, int tail) {
		if(head==tail) return true;
		return false;
	}
}
