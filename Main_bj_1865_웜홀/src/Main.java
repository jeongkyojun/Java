import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());
		for(int t=1;t<=tc;t++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken()); // 노드의 숫자
			int m = Integer.parseInt(st.nextToken()); // 도로의 개수
			int w = Integer.parseInt(st.nextToken()); // 웜홀의 개수
			int[][] road = new int[n+1][n+1]; // 500*500
			int[] node = new int[n+1];
			boolean[][] chk = new boolean[n+1][n+1];
			for(int i=0;i<n;i++)
				node[i] = 5000001;
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(bf.readLine());
				int a =Integer.parseInt(st.nextToken());
				int b =Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if(road[a][b]==0 || road[a][b]>v) {
					road[a][b] = v;
					road[b][a] = v;
				}
			}
			for(int i=0;i<w;i++) { // 방향이 존재
				st = new StringTokenizer(bf.readLine());
				int a =Integer.parseInt(st.nextToken());
				int b =Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken()) * -1;
				if(road[a][b]==0 || road[a][b]>v) {
					road[a][b] = v;
					road[b][a] = v;
				}

			}
		}
	}
	static void Velman_Fod(int[][] road, int[] node, int n) {
		node[n] = 0;
		
	}
	
}
