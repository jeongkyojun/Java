import java.io.*;
import java.util.*;

보류
public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2342.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int res = 0;
		int left = 0;
		int right = 0;
		boolean isleft = true;
		
		/*
		 * 중앙 이동 : 2
		 * 다른점에서 인접한 점 : 3
		 * 반대편 : 4
		 * 같은지점 : 1
		 */
		while(true)
		{
			int n = Integer.parseInt(st.nextToken());
			if(n==0)
				break;
		}
		System.out.println(res);
	}

}
