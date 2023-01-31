import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		for(int i=0;i<n;i++) {
			map.put(Integer.parseInt(st.nextToken()),1);
		}
		
		st = new StringTokenizer(bf.readLine());
		int num = m;
		for(int i=0;i<m;i++) {
			if(map.containsKey(Integer.parseInt(st.nextToken()))) {
				num--;
				n--;
			}
		}
		System.out.println(num+n);
	}

}
