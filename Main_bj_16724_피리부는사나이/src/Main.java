import java.util.*;
import java.io.*;

public class Main {
	static int[] di = new int[] {-1,1,0,0}; // UDLR
	static int[] dj = new int[] {0,0,-1,1};
	static int res = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] mat = new int[n][m];
		int[][] chk = new int[n][m];
		for(int i=0;i<n;i++) {
			String str = bf.readLine();
			for(int j=0;j<m;j++) {
				switch(str.charAt(j)) {
				case 'U':
					mat[i][j] = 0;
					break;
				case 'D':
					mat[i][j] = 1;
					break;
				case 'L':
					mat[i][j] = 2;
					break;
				case 'R':
					mat[i][j] = 3;
					break;
				}
			}
		}
		int cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(chk[i][j] == 0) {
					chk[i][j] = ++cnt;
					search(mat,chk,i,j,cnt);
				}
			}
		}
		System.out.println(res);
	}
	static int search(int[][]mat, int[][] chk, int i, int j,int cnt) {
		// 막힐때까지 진행하며 막히기 전까지는 그대로 진행한다.
		if(chk[i+di[mat[i][j]]][j+dj[mat[i][j]]] !=0) { // 0이 아니다 = 이미 방문했었다.
			if(chk[i+di[mat[i][j]]][j+dj[mat[i][j]]]==cnt) //방문할때 순환된다 = 새로운 길
			{
				res++;
				chk[i][j] = cnt;
			}
			else // 순환이 안된다.
			{
				chk[i][j] = chk[i+di[mat[i][j]]][j+dj[mat[i][j]]];
			}			
			return chk[i][j];
		}
		// 0이다 = 방문한적 없다
		chk[i+di[mat[i][j]]][j+dj[mat[i][j]]] = cnt;
		return search(mat,chk,i+di[mat[i][j]],j+dj[mat[i][j]],cnt);
	}

}
