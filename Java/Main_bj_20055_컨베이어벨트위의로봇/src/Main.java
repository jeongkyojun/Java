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
			int turn = 0;
			int z = 0;
			while (true) {
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

						}
					}
				}

				// 3. 로봇을 올린다.
				if(conveyor[1]>0)
				{
					conveyor[1]--;
					if(conveyor[1]==0) // 내구도가 0이면 카운트+1
						z++;
					Robot[1] = true;
				}

				// 4. 0짜리가 K개 이상이면 종료
				if(z>=K)
					break;
			}
			System.out.println(turn);
		}///////////////////////////////////
	}

}
