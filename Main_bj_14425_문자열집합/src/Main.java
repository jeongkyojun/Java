import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		int res = 0;
		for(int i=0;i<n;i++)
		{
			map.put(bf.readLine(),true);
		}
		for(int i=0;i<m;i++)
		{
			if(map.containsKey(bf.readLine())) {
				res++;
			}
		}
		System.out.println(res);
	}

}
