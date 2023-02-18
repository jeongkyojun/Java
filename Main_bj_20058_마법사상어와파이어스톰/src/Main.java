import java.io.*;
import java.util.*;

public class Main {

	static int[] di = new int[] {-1,0,0,1};
	static int[] dj = new int[] {0,-1,1,0};
	static int sum = 0;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=t;tc++) {
			System.out.println("#tc "+tc);
			
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[][] mat = new int[1<<N][1<<N];
		sum = 0;
		for(int i=0;i<(1<<N);i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<(1<<N);j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				sum+=mat[i][j];
			}
		}
		
		st = new StringTokenizer(bf.readLine());
		for(int i=0;i<Q;i++) {
			int L = Integer.parseInt(st.nextToken());
			for(int j=1;j<=L;j++) {
				FireStorm(mat,j,N,0,(1<<N)-1,0,(1<<N)-1);
			}
			MeltDown(mat);
		}
		System.out.println(sum);
		System.out.println(Max_Iceberg(mat));
		
		}
	}
	
	static void FireStorm(int[][] mat, int L, int N, int low_x, int high_x, int low_y, int high_y) {
		if(L==0) return;
		if(L==N) {
			int sub = 1<<(L-1);
			for(int i=low_x;i<=low_x+(high_x-low_x)/2;i++)
			{
				for(int j=low_y;j<=low_y+(high_y-low_y)/2;j++) {
					int tmp = mat[i][j]; // tmp = 0,0
					mat[i][j] = mat[i+sub][j]; // 0,0 = 0,1
					mat[i+sub][j] = mat[i+sub][j+sub]; // 0,1 = 1,1
					mat[i+sub][j+sub] = mat[i][j+sub]; // 1,1 = 1,0
					mat[i][j+sub] = tmp; // 1,0 = 0,0
				}
			}
			return;
		}
		int mid_x = (low_x+high_x)/2;
		int mid_y = (low_y+high_y)/2;
		FireStorm(mat,L,N-1,low_x,mid_x,low_y,mid_y);
		FireStorm(mat,L,N-1,low_x,mid_x,mid_y+1,high_y);		
		FireStorm(mat,L,N-1,mid_x+1,high_x,low_y,mid_y);
		FireStorm(mat,L,N-1,mid_x+1,high_x,mid_y+1,high_y);
	}

	static void MeltDown(int[][] mat) {
		int[][] tmp = new int[mat.length][mat[0].length];
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				int cnt = 0;
				for(int d=0;d<4;d++) {
					if(0<=i+di[d]&&i+di[d]<mat.length&&0<=j+dj[d]&&j+dj[d]<mat.length 
							&& mat[i+di[d]][j+dj[d]]!=0) {
						cnt++;
					}
				}
				if(cnt>=3 || mat[i][j]==0) {					
					tmp[i][j] = mat[i][j];
				}
				else {
					tmp[i][j] = mat[i][j]-1;
					sum--;
				}
			}
		}
		for(int a=0;a<mat.length;a++) {
			for(int b=0;b<mat.length;b++) {
				mat[a][b] = tmp[a][b];
			}
		}
	}

	static int Max_Iceberg(int[][] mat) {
		int res = 0;
		boolean[][] chk = new boolean[mat.length][mat.length];
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat.length;j++)
			{
				if(!chk[i][j]) {
					chk[i][j] = true;
					if(mat[i][j]!=0) {
						int ice = 0;
						Queue<int[]> q = new LinkedList<int[]>();
						q.offer(new int[] {i,j});
						while(!q.isEmpty()) {
							int[] tmp = q.poll();
							ice++;
							for(int d=0;d<4;d++) {
								// 범위내에 존재
								if(0<=tmp[0]+di[d] && tmp[0]+di[d]<mat.length && 0<=tmp[1]+dj[d] && tmp[1]+dj[d]<mat.length) {
									// 이미 방문 안함, 얼음이 존재
									if(!chk[tmp[0]+di[d]][tmp[1]+dj[d]] && mat[tmp[0]+di[d]][tmp[1]+dj[d]]!=0) {
										q.offer(new int[] {tmp[0]+di[d],tmp[1]+dj[d]});
									}
									chk[tmp[0]+di[d]][tmp[1]+dj[d]] = true; // 방문처리
								}
							}
						}
						if(ice>res)
						{
							res = ice;
						}
					}
				}
			}
		}
		
		return res;
	}
	
}
