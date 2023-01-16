import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[][] pos = new int[n][2];
		int[] U = new int[n];
		int line = 0;
		for(int i=0;i<n;i++) {
			U[i] = i;
			st = new StringTokenizer(bf.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
			for(int j=0;j<i;j++)
			{
				double len = Math.sqrt(Math.pow(pos[j][0] - pos[i][0],2)+Math.pow(pos[j][1]-pos[j][1],2));
				len*=100;
				len = Math.round(len);
				len = len/100;
				System.out.println(len);
			}
		}
		// 합치는 과정 수행
		for(int i=0;i<t;i++) {
			st = new StringTokenizer(bf.readLine());
			if(!union(U,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())))
			{
				line++;
			}
		}
	}
	
	static int find_union(int[] U, int i) {
		if(U[i]==i) return i;
		else return U[i] = find_union(U, U[i]);
	}
	static boolean union(int[] U, int a, int b) {
		int Union_a = find_union(U,a);
		int Union_b = find_union(U,b);
		
		if(Union_a==Union_b) return true;
		U[Union_a] = Union_b;
		return false;
	}
}
