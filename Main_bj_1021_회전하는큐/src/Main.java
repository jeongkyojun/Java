import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		for(int tc = 1;tc<=t;tc++) {
			System.out.print("#"+tc+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken()); // 큐의 길이
			int m = Integer.parseInt(st.nextToken()); // 뽑아낼 수의 개수
			int res = 0;
			st = new StringTokenizer(bf.readLine()); // 뽑아낼 원소의 위치
			int[] nums = new int[m];
			for(int i=0;i<m;i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<m;i++) {
				int num = nums[i];
				int front = num-1; // 항상 0이상 n이하일 수 밖에 없다.
				int back = n+1-num;
				int tmp = front<back?front:back;
				res+=tmp;
				if(front<back) tmp*=-1;
				for(int j=i+1;j<m;j++) {
					nums[j]+=tmp; // 값만큼 이동
					nums[j]--;
					nums[j]%=n;
					if(nums[j]<=0) nums[j]+=n;
				}
				n--;
			}
			System.out.println(res);
		}
	}
}
