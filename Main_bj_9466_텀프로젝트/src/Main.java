import java.util.*;
import java.io.*;

public class Main {

	static int res;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		
		for(int tc=1;tc<=t;tc++) {			
			int n = Integer.parseInt(bf.readLine());
			res = n;
			int[] U = new int[n];
			int[] chk = new int[n];
			int[] depth = new int[n];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i=0;i<n;i++) {
				U[i] = Integer.parseInt(st.nextToken())-1;
			}
			int cnt = 1;
			for(int i=0;i<n;i++)
			{
				if(chk[i] == 0) { // 팀이 존재하지 않는다면
					System.out.print(i+" : ");
					dfs(i,U,chk, depth, cnt++,0); // 탐색 수행
					System.out.println();
				}
			}
			System.out.println(res);
		}
		
	}
	/*
	 * 이미 사이클이 존재하는 지 확인 (chk!=0인지 확인한다.)
	 * 나 스스로가 목표인지를 확인
	 * U[i] 가 사이클에 속해있는지를 확인한다.
	 * U[i] 로 이동한다
	 */
	static void dfs(int i, int[]U, int[] chk, int[] depth, int union, int d) {
		chk[i] = union; // 현재 팀에 소속
		depth[i] = d; // 깊이 지정
		if(chk[U[i]]!=0 && chk[U[i]]!=union) return; // 다음에 진행할 지점의 팀이 존재하고 그것이 현재 팀이 아닌경우
		// = 사이클이 존재한다 = 현재 값이 사이클에 들어가봤자 사이클이 생길 수 없다 = 종료
		if(U[i]==i) { // 1인 사이클 생성시
			res--; // 1만 빼고 종료
			return;
		}
		if(chk[U[i]] == union) {
			// 다음 목표가 현재 팀에 속한다 = 사이클이 생겼다
			// 현재 깊이 - 해당 사이클의 깊이 + 1 (이상~이하 이므로)
			res-= d-depth[U[i]]+1;
			return;
		}
		// 둘 다 아닌경우 다음으로 넘어간다.
		dfs(U[i],U,chk,depth,union,d+1);
	}
}
