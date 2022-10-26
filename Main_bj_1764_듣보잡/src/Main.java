import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1764.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String,Integer> m = new HashMap<String, Integer>();
		List<String> lst = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
				
		for(int f = 0;f<a;f++)
		{
			String name = bf.readLine();
			m.put(name, 1);
		}
		for(int f =0; f<b;f++)
		{
			String name = bf.readLine();
			if(m.containsKey(name))
				lst.add(name);
		}
		Collections.sort(lst);
		System.out.println(lst.size());
		for(int i=0;i<lst.size();i++)
		{
			System.out.println(lst.get(i));
		}
	}

}
