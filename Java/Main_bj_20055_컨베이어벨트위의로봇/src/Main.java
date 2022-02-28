import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20055.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());////////////////////////////
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#"+test_case+" ");////////////////////////////
			
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int zero = 0;

			int[] conveyor = new int[2 * N + 1];
			boolean[] Robot = new boolean[2 * N + 1];
			int stop = N;
			st = new StringTokenizer(bf.readLine());
			for (int i = 1; i <= 2*N; i++) {
				conveyor[i] = Integer.parseInt(st.nextToken());
			}
			/*
			System.out.println("0");
			System.out.println("C : "+Arrays.toString(conveyor));
			System.out.println("R : "+Arrays.toString(Robot));
			System.out.println();
			*/
			int turn = 0;
			int z = 0;
			//for(int x= 0;x<10;x++) {
			while (true) {
				//System.out.println("turn ## "+turn);
				turn++;
				conveyor[0] = conveyor[2 * N];
				Robot[0] = Robot[2*N];
				// 1. 컨베이어 이동
				for (int i = 2*N; i >= 1; i--) {
					conveyor[i] = conveyor[i-1]; // 1->2 ... 2N-1 -> 2N
					Robot[i] = Robot[i-1]; // 로봇들도 이동
				}
				
				conveyor[0] = 0;
				Robot[0] = false;
				/*
				System.out.println("1");
				System.out.println("C : "+Arrays.toString(conveyor));
				System.out.println("R : "+Arrays.toString(Robot));
				System.out.println();
				*/
				// 2. 로봇이동
				for (int i = N; i >= 1; i--) {
					// 참이면 N-1이되거나, 앞에 도착할때까지 간다.
					if (Robot[i]) {
						if (i == N) // 컨베이어 벨트에 있는경우 바로 내린다.
						{
							Robot[i] = false;
						}
						else {
							// 다음칸에 로봇이 없고, 내구도가 0이아닐때
							if(!Robot[i+1] && conveyor[i+1]>0)
							{
								Robot[i] = false;
								Robot[i+1] = true;
								if(i+1==N) // 내리는곳이면 내린다.
									Robot[i+1] = false;
								conveyor[i+1]--;
								if(conveyor[i+1]==0)
									z++;
							}
							
							/*
							int next_i = i;
							// 로봇을 컨베이어 끝까지 내리거나 앞에 로봇이있을때까지 민다.
							while (next_i <= N && !Robot[next_i+1] && (next_i==N||conveyor[next_i+1]>0)) {
								if(next_i == N)
								{
									Robot[next_i] = false; // 로봇을 내린다.
									break;
								}
								else
								{
									
									Robot[next_i++] = false;// 이동시킨다
									Robot[next_i] = true; // 옮겼다
									conveyor[next_i]--; //내구도가 1 줄었다.
									if(conveyor[next_i]==0) // 내구도가 0이면 카운트+1
										z++;
								}
								
								
								System.out.println("-------------------");
								System.out.println("move");
								System.out.println("C : "+Arrays.toString(conveyor));
								System.out.println("R : "+Arrays.toString(Robot));
								System.out.println("i : "+next_i+" , "+N);
								System.out.println("-------------------");
								
								
							}
							*/
						}
					}
				}
				/*
				System.out.println("2");
				System.out.println("C : "+Arrays.toString(conveyor));
				System.out.println("R : "+Arrays.toString(Robot));
				System.out.println();
				*/
				// 3. 로봇을 올린다.
				if(conveyor[1]>0 && !Robot[1])
				{
					conveyor[1]--;
					if(conveyor[1]==0) // 내구도가 0이면 카운트+1
						z++;
					Robot[1] = true;
				}
				/*
				System.out.println("3");
				System.out.println("C : "+Arrays.toString(conveyor));
				System.out.println("R : "+Arrays.toString(Robot));
				System.out.println();
				*/
				// 4. 0짜리가 K개 이상이면 종료
				if(z>=K)
					break;
				//System.out.println("z : "+z);
			}
			System.out.println(turn);
		}///////////////////////////////////
	}

}
