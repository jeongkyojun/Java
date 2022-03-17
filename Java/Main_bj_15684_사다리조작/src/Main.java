import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15684.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			//세로선 개수 N, 가로선 개수 M
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken()); // 세로선 개수
			int M = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken()); // 놓을수 있는 위치의 개수
			
			int[][] mat = new int[N][H];
		}

	}

}
