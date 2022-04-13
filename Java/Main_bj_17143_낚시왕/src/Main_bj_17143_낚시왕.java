import java.util.*;
import java.io.*;

public class Main_bj_17143_낚시왕 {

	static int[] di = new int[] {-1,1,0,0}; // 위 아래 오른쪽 왼쪽
	static int[] dj = new int[] {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17143.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] mat = new int[R][C];
			int[][] shark = new int[M][5]; // 상어의 정보를 저장
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				for(int j=0;j<5;j++)
				{
					shark[i][j] = Integer.parseInt(st.nextToken());
					if(j==0||j==1||j==3)
						shark[i][j]--;
				}
				mat[shark[i][0]][shark[i][1]] = i+1; // 각 칸에 크기를 입력
			}
			/*
			for(int j=0;j<R;j++)
			{
				System.out.println(Arrays.toString(mat[j]));
			}
			System.out.println();
			*/
			int res = 0;
			for(int i=0;i<C;i++) // 낚시왕은 오른쪽으로 한칸씩 이동한다.
			{
				for(int j=0;j<R;j++) // 가장 땅에서 가까운 물고기를 먼저 낚는다.
				{
					//System.out.println(j+" , "+i);
					if(mat[j][i]!=0)
					{
						//System.out.println("catch "+mat[j][i]);
						res+= shark[mat[j][i]-1][4]; // 상어의 무게 추가
						shark[mat[j][i]-1][0] = -1; // 잡음 처리
						mat[j][i] = 0;
						break;
					}
				}
				//System.out.println(res);
				/*
				System.out.println("#"+i+" catch");
				for(int j=0;j<R;j++)
				{
					System.out.println(Arrays.toString(mat[j]));
				}
				System.out.println();
				*/
				// 상어 이동
				
				for(int j=0;j<M;j++) // 물고기의 현재 위치를 0으로 지운다.
				{
					if(shark[j][0]==-1) continue;
					mat[shark[j][0]][shark[j][1]] = 0; // 일단 사라진다.
				}
				for(int j=0;j<M;j++) // 물고기들이 이동을 시작한다.
				{
					int ni = shark[j][0];
					int nj = shark[j][1];
					int s = shark[j][2];
					int d = shark[j][3];
					int z = shark[j][4];
					if(ni==-1) continue;
					
					//System.out.print("shark "+j+" "+Arrays.toString(shark[j]));
					
					// 반복문으로 속력만큼 한칸씩 이동한다.
					while(s>0)
					{
						if(0<= ni+di[d] && ni+di[d]<R && 0<= nj+dj[d] && nj+dj[d]<C)
						{
							ni+=di[d];
							nj+=dj[d];
							s--;
						}
						else // 벽에 막히면 방향을 반대로 이동시킨다.
						{
							switch(d)
							{
							case 0:
								d = 1;
								break;
							case 1:
								d = 0;
								break;
							case 2:
								d = 3;
								break;
							case 3:
								d = 2;
								break;
							}
						}
					}
					// 물고기의 정보를 이동한 위치와 방향으로 갱신한다.
					shark[j][0] = ni;
					shark[j][1] = nj;
					shark[j][3] = d;
					// 다른 상어가 있는지 확인
					if(mat[ni][nj]!=0)
					{
						int num = mat[ni][nj];
						if(shark[num-1][4] < z) // 서로간의 크기를 비교한다.
						{
							// 현재 물고기가 더 큰경우
							//System.out.print("( eat "+num+" )");
							mat[ni][nj] = j+1; // 자리를 교체
							shark[num-1][0] = -1; // 먼저 있던 작은 물고기는 잡아먹힌다.
						}
						else
						{
							//System.out.print("( eated by "+num+" )");
							shark[j][0] = -1; // 현재의 물고기가 잡아먹힌다.
						}
						//System.out.println(" -> "+Arrays.toString(shark[j]));
					}
					else // 없으면 담는다.
					{
						//System.out.println(" -> "+Arrays.toString(shark[j]));
						mat[ni][nj] = j+1; // 0인 값을 현재 물고기 번호로 갱신한다.
					}
				}
				/*
				for(int j=0;j<R;j++)
				{
					System.out.println(Arrays.toString(mat[j]));
				}
				System.out.println();
				*/
			}
			System.out.println(res); // 낚은 물고기 값을 출력한다.
		}
	}

}
