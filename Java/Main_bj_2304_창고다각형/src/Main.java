import java.util.*;
import java.io.*;

class tower implements Comparable<tower> {
	int point;
	int height;

	tower() {
		this(0, 0);
	}

	tower(int point, int height) {
		this.point = point;
		this.height = height;
	}

	@Override
	public int compareTo(tower o) {
		// TODO Auto-generated method stub
		return point - o.point;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2304.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(bf.readLine());
			tower[] t = new tower[N];
			int big_point = 0;
			int max = -1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int point = Integer.parseInt(st.nextToken());
				int height = Integer.parseInt(st.nextToken());
				if (max < height)
				{
					max = height; // 타워의 높이
					big_point = point; // 타워의 가장 높은 크기의 인덱스
				}
				t[i] = new tower(point, height); // 배열에 저장
			}
			Arrays.sort(t);
			max = 0;
			int sum = 0;
			int befpoint = 0;
			
			for (int i = 0; i < N; i++) {
					if (max <= t[i].height) {
						sum += max * (t[i].point - befpoint);
						max = t[i].height;
						befpoint = t[i].point;
					}
					if (t[i].point == big_point)
					{
						sum +=t[i].height;
						break;
					}
				 
			}
			max = 0;
			befpoint = 1000;
			for(int i = N-1;i>=0;i--)
			{
				if (max <= t[i].height) {
					sum += max * (befpoint-t[i].point);
					max = t[i].height;
					befpoint = t[i].point;
				}
				if (t[i].point == big_point)
				{
					break;
				}
			}
			System.out.println(sum);
		}
	}

}
