import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++){
			System.out.print("#"+tc+" ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			boolean[][] chk = new boolean[n][m];
			boolean[] party = new boolean[m];
			int res = m;
			int[] U = new int[n];
			for(int i=0;i<n;i++) {
				U[i] = i;
			}
			st = new StringTokenizer(bf.readLine());
			int p_n = Integer.parseInt(st.nextToken());
			// 알고있는 인원을 -1그룹으로 저장
			for(int i=0;i<p_n;i++) {
				int p = Integer.parseInt(st.nextToken())-1;
				U[p] = -1;
			}
			// Union_find 수행
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(bf.readLine());
				int p = Integer.parseInt(st.nextToken());
				int bef = 0;
				for(int j=0;j<p;j++) {
					int tmp = Integer.parseInt(st.nextToken())-1;
					chk[tmp][i] = true; // 각 인원이 어떤 파티에 참여했는지 확인한다.
					if(j!=0)
					{
						// 같은 파티에 참석한 인원을 모두 같은 그룹으로 묶는다
						Union(U,tmp,bef);
					}
					bef = tmp;
				}
			}
			for(int i=0;i<n;i++)
			{
				if(find_Union(U,i)==-1) // 만약 진실을 알고있는경우
				{
					for(int j=0;j<m;j++)
					{
						if(chk[i][j]) // 참여한 파티를 확인하고
						{
							if(!party[j]) // 중복되지 않았다면
							{
								res--; // 뺀다.
								party[j] = true;
							}
						}
					}
				}
			}
			System.out.println(res);
		}
	}
	static int find_Union(int[] U, int i) {
		if(U[i]==-1) return -1;
		if(U[i]==i) return i;
		return U[i] = find_Union(U,U[i]);
	}

	static boolean Union(int[] U, int a, int b) {
		int U_a = find_Union(U,a);
		int U_b = find_Union(U,b);
		
		if(U_a==U_b) return true;
		// 둘중 한명이 -1 그룹이면 무조건 -1 그룹에 넣는다.
		if(U_a==-1)
			U[U_b] = U_a;
		else
			U[U_a] = U_b;
		return false;
	}
}
