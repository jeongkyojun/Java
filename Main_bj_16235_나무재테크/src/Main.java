import java.util.*;
import java.io.*;

class tree implements Comparable<tree> {
	int i;
	int j;
	int age;

	tree() {
		this(0, 0, 0);
	};

	tree(int i, int j, int age) {
		this.i = i;
		this.j = j;
		this.age = age;
	}

	@Override
	public int compareTo(tree o) {
		// TODO Auto-generated method stub
		return Integer.compare(age, o.age);
	}
}

public class Main {

	static int[] di = new int[] {-1,-1,0,1,1,1,0,-1};
	static int[] dj = new int[] {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16235.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken()); // 해
			int[][] mat = new int[N][N]; // 양분
			int[][] dead = new int[N][N]; // 죽은 나무의 양분
			int[][] energy = new int[N][N];
			PriorityQueue<tree> pq = new PriorityQueue<tree>();
			Queue<tree> trees = new LinkedList<tree>();
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken()); // 겨울에 보충되는 양분
					energy[i][j] = 5; // 각 칸의 양분의 초기값은 5
				}
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int ti = Integer.parseInt(st.nextToken());
				int tj = Integer.parseInt(st.nextToken());
				int age = Integer.parseInt(st.nextToken());
				pq.offer(new tree(ti-1,tj-1,age)); // 나무를 담는다.
			}

			for (int year = 0; year < K; year++) {
				//System.out.println(year+"year");
				for (int season = 0; season < 4; season++) {
					int size = pq.size();
					next:for (int x = 0; x < size; x++) {
						System.out.println(x+"-"+season);
						tree tmp = pq.poll();
						switch (season) {
						case 0:
							// 양분을 못먹으면 죽는다.
							if(energy[tmp.i][tmp.j]<tmp.age) {
								dead[tmp.i][tmp.j]+=tmp.age/2;
								continue next;
							}
							// 봄에는 양분을 먹고 나이가 증가
							energy[tmp.i][tmp.j]-=tmp.age;
							tmp.age++;
							break;
						case 1:
						// 여름에는 죽은 나무가 양분으로 변한다. (나이/2)
							for(int i=0;i<N;i++)
							{
								for(int j=0;j<N;j++)
								{
									energy[i][j]+=dead[i][j];
								}
							}
							break;
						case 2:
						// 가을에는 나이가 5의 배수인 나무가 번식한다.
							if(tmp.age%5 == 0)
							{
								for(int d=0;d<8;d++)
								{
									if(0<=tmp.i+di[d]&&tmp.i+di[d]<N && 0<=tmp.j+dj[d] && tmp.j+dj[d]<N)
									{
										trees.offer(new tree(tmp.i+di[d],tmp.j+dj[d],1));
									}
								}
							}
							break;
						case 3:
							for(int i=0;i<N;i++)
							{
								for(int j=0;j<N;j++)
								{
									energy[i][j]+=mat[i][j];
								}
							}
							break;
						}	
						trees.offer(tmp);
					}
					// 계절이 끝나면 갱신된다.
					for(int i=0;i<trees.size();i++)
					{
						pq.offer(trees.poll());
					}
				}
			}
			System.out.println(pq.size());
		}
	}

}
