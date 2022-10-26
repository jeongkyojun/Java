import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17219.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		Map<String,String> pw = new HashMap<String,String>();
		for(int i=0;i<a;i++)
		{
			st = new StringTokenizer(bf.readLine());
			pw.put(st.nextToken(), st.nextToken());
		}
		for(int i=0;i<b;i++)
		{
			System.out.println(pw.get(bf.readLine()));
		}
	}

}
