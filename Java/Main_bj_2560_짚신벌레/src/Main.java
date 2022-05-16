import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2560.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken()); // 성체가 되는 시간
			int b = Integer.parseInt(st.nextToken()); // 개체를 만들지 않는 시간
			int d = Integer.parseInt(st.nextToken()); // 수명
			int N = Integer.parseInt(st.nextToken()); // 최종 일자
			int[] mat = new int[d+1];
			int res = 1;
			mat[0] = 1;
			// System.out.println(Arrays.toString(mat));
			int head = 0;
			int tail = 0;
			int add = 0;
			/*
			 * head 와 tail을 이용한 투포인트 계산 사용
			 */
			// 현재 오류 존재 (수정필요)
			for (int i = 1; i <= N; i++) {
				head++;
				//System.out.println("head : "+head+" , tail : "+tail+" , add : "+add);
				if(i>=a)
				{
					add+=mat[(head-a)%mat.length]%1000;
					add%=1000;
				}
				if(i>=b)
				{
					add-=mat[(head-b)%mat.length]%1000;
					if(add<0)
						add+=1000;
				}
				mat[head%mat.length]+=add;
				mat[head%mat.length]%=1000;
				
				res+=add;
				res%=1000;
				
				if(i>=d)
				{
					res-=mat[tail%mat.length]%1000;
					if(res<0)
						res+=1000;
					mat[(tail++)%mat.length] = 0;
				}
				/*
				System.out.println(Arrays.toString(mat));
				System.out.print("[");
				for(int x=0;x<mat.length;x++)
				{
					if(tail%mat.length==x || head%mat.length==x)
						System.out.print("*");
					else
						System.out.print(" ");
					if(x!=mat.length-1)
						System.out.print(", ");
				}
				System.out.println("]");
				*/
			}
			System.out.println(res);
		}
	}

}
