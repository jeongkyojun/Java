import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		boolean[] chk = new boolean[100001];
		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(n);
		chk[n] = true;
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			//System.out.println(time+"-----");
			for (int j = 0; j < size; j++) {
				int tmp = q.poll();
				int warp = tmp;
				boolean check = false;
				while(warp<=100000 && 0<warp) {
					if(warp*2 == k) {
						q.clear();
						check = true;
						System.out.println(time);
						break;
					}
					if(warp*2<=100000 && !chk[warp*2]) {
						chk[warp*2] = true;
						q.offer(warp*2);
					}
					if (warp + 1 <= 100000) {
						if (!chk[warp + 1]) {
							q.offer(warp + 1);
							chk[warp + 1] = true;
						}
					}
					if (warp - 1 >= 0 && warp - 1 <= 100000) {
						if (!chk[warp - 1]) {
							q.offer(warp - 1);
							chk[warp - 1] = true;
						}
					}
					warp*=2;
				}
				if(check) break;
				//System.out.println(tmp);
				if (tmp == k) {
					q.clear();
					System.out.println(time);
					break;
				}
				// tmp+1, tmp-1
				if (tmp + 1 <= 100000) {
					if (!chk[tmp + 1]) {
						q.offer(tmp + 1);
						chk[tmp + 1] = true;
					}
				}
				if (tmp - 1 >= 0 && tmp - 1 <= 100000) {
					if (!chk[tmp - 1]) {
						q.offer(tmp - 1);
						chk[tmp - 1] = true;
					}
				}
			}
			time++;
		}
	}
}
