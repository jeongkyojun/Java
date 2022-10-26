import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2133.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=T;tc++)
		{
			
			int N = Integer.parseInt(bf.readLine());
			int[][] mat = new int[N+1][4];
			
			mat[1][0] = 1;
			mat[1][2] = 2;
		}
	}

}
