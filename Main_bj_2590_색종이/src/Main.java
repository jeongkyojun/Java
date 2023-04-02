import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int[] paper_num = new int[7];
		for (int i = 1; i <= 6; i++) {
			paper_num[i] = Integer.parseInt(bf.readLine());
		}

		// #### 6번
		// 하나만으로 한칸을 차지한다.
		paper_num[0] = paper_num[6]+paper_num[5]+paper_num[4];
		
		// ##### 5번
		int tmp = paper_num[5] * 11; // 좌 5개, 하 5개, 대각 1개
		// 5번은 1번으로만 채울 수 있다.
		sub1(paper_num, tmp);
		
		System.out.println(Arrays.toString(paper_num));
		
		// #### 4번
		// 4번 색종이 1개당 2번색종이 5개를 채울수 있다.
		tmp = paper_num[4] * 5; // 2번색종이의 필요량 좌 2, 하단 2, 대각 1 : 5개
		if (paper_num[2] < tmp) { // 필요량이 더 많다.
			// 모자란 값은 1번색종이로 채운다.
			paper_num[4] -= paper_num[2] / 5; // 우선 남는대로 먼저 채운다.
			paper_num[2] %= 5;
			tmp = (paper_num[4] * 5 - paper_num[2])*4;
			paper_num[2] = 0;
			sub1(paper_num, tmp);
		} else {
			// 다 채워도 남는다.
			paper_num[2] -= tmp;
		}
		
		System.out.println(Arrays.toString(paper_num));
		
		// 3번,2번,1번 색종이는 자체적으로 채울 수 있다.
		
		// #### 3번
		// 3번 색종이는 자체적으로 먼저 합친다.
		paper_num[0] += paper_num[3] / 4; //3짜리 4개당 1개
		paper_num[3] %= 4;
		if (paper_num[3] != 0) {
			paper_num[0]++; // 남은 1칸 또한 채운다.
			// 남는 색종이는 2 1개 + 1 5개로 처리한다.
			if (paper_num[2] < 1) {
				// 2가 한장도 없는경우
				sub1(paper_num, 36-paper_num[3]*9);
			} else {
				int min  = Integer.min(paper_num[2],7-paper_num[3]*2);
				paper_num[2] -= min;
				tmp = 36-paper_num[3]*9-min*4;
				sub1(paper_num,tmp);
			}
		}
				
		// #### 2
		paper_num[0] += paper_num[2] / 9;
		paper_num[2]%=9;
		if(paper_num[2]!=0) {
			paper_num[0]++;
			tmp = 36-paper_num[2]*4;
			sub1(paper_num,tmp);
		}

		// #### 1
		paper_num[0] += paper_num[1] / 36;
		if (paper_num[1] % 36 != 0) {
			paper_num[0]++;
		}
		System.out.println(paper_num[0]);
	}
	
	static void sub1(int[] paper_num, int tmp) {
		if (paper_num[1] < tmp) {
			paper_num[1] = 0;
		} else {
			// 그만큼 사용하였다.
			paper_num[1] -= tmp;
		}
	}
}
