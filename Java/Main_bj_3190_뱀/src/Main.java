import java.util.*;
import java.io.*;

public class Main {
	static int[] di = new int[] {-1,0,1,0};//상 우 하 좌
	static int[] dj = new int[] {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_bj_3190.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1; test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine());
			int apple = Integer.parseInt(bf.readLine());
			int[][] mat = new int[N+1][N+1];
			Queue<int[]> snake = new LinkedList<int[]>();
			
			snake.offer(new int[] {1,1}); // 뱀은 1,1에 위치한다.
			mat[1][1] = 2;
			int d = 1; // 처음 방향은 오른쪽
			
			for(int i=0;i<apple;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int ai = Integer.parseInt(st.nextToken());
				int aj = Integer.parseInt(st.nextToken());
				mat[ai][aj] = 1;
			}
			int move = Integer.parseInt(bf.readLine());
			int[] time = new int[10001];
			for(int i=0;i<move;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int X = Integer.parseInt(st.nextToken());
				String C = st.nextToken();
				if(C.charAt(0)=='D')
				{
					time[X] = 1;
				}
				else
					time[X] = -1;
			}
			int res = 0;
			/*
			System.out.println("---------start---------");
			for(int i=1;i<=N;i++)
			{
				for(int j=1;j<=N;j++)
				{
					System.out.print(mat[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("-----------------------");
			*/
			game : for(int turn = 0; turn<=10000;turn++)
			{
				// 방향 조정
				if(time[turn]!=0)
				{
					d+=4;
					d+=time[turn];
					d%=4;
				}
				int size = snake.size();	// 큐의 사이즈
				int[] before = new int[] {-1,-1}; // 이전위치
				boolean eatApple = false; // 사과를 먹었는지여부
				//System.out.println("size : "+size);

				for(int i=0;i<size;i++)
				{
					int[] tmp = snake.poll();
					int[] s = new int[] {tmp[0],tmp[1]}; // s에 큐의 값을 꺼낸다.
					mat[s[0]][s[1]] = 0; // 현재 위치를 지운다.
					//System.out.println("s : "+Arrays.toString(s));
					
					if(i==0) // 머리의 경우
					{
						// 현재값을 이전값에 미리 저장
						before = new int[] {s[0],s[1]};
						
						// 만약 몸에 닿으면 종료한다.
						if(s[0]+di[d]<=0||s[0]+di[d]>N||
								s[1]+dj[d]<=0||s[1]+dj[d]>N
								||mat[s[0]+di[d]][s[1]+dj[d]]==2)
						{
							res = turn+1;
							break game;
						}
						
						// 사과를 먹었는가?
						if(mat[s[0]+di[d]][s[1]+dj[d]]==1)
						{
							eatApple = true;
						}

						s[0]+=di[d];
						s[1]+=dj[d];
						mat[s[0]][s[1]] = 2;
						//System.out.println("offer "+Arrays.toString(s));
						snake.offer(s);
					}
					else
					{
						snake.offer(before);
						// 이전값 = 현재 위치가 이동해야 되는 값
						mat[before[0]][before[1]] = 2;
						
						//System.out.println("offer "+Arrays.toString(before));
						// 현재값을 이전값에 저장
						before = new int[] {s[0],s[1]};
						
						// 현재값이 된 이전값을 큐에 담는다.
										
					}
					
					if(i==size-1&&eatApple)
					{
						//System.out.println("add "+Arrays.toString(before));
						mat[before[0]][before[1]] = 2;
						// 늘어난 꼬리 추가
						snake.offer(before);
					}
					
				}
				/*
				System.out.println("-----------------------");
				for(int a=1;a<=N;a++)
				{
					for(int b=1;b<=N;b++)
					{
						System.out.print(mat[a][b]+" ");
					}
					System.out.println();
				}
				System.out.println("-----------------------");
				*/
			}
			System.out.println(res);
		}
	}

}
