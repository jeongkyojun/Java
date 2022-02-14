import java.io.*;
import java.util.*;

public class Main {

	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2961.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			min = Integer.MAX_VALUE;
			int taste = Integer.parseInt(bf.readLine()); // 맛의 가지수 저장
			
			int[] bit = new int[taste];
			int[] sour = new int[taste];
			
			// 각자의 맛을 배열에 담는다.
			for(int i=0;i<taste;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine()); // st에서 문제 발생?
				sour[i] = Integer.parseInt(st.nextToken());
				bit[i] = Integer.parseInt(st.nextToken());
			}
			// 0001 0010 0011 0100 1000
			
			// 비트연산자 사용(이름은 permutation이지만 사실 Subset이다.)
			permutation(bit,sour,0,0);
			System.out.println(min);
		}
	
	}
	
	static void permutation(int[] vec,int[]vec2, int n,int flag)
	{
		if(n==vec.length) // 모든 개수를 탐색한 경우
		{
			int bit = 0; // 쓴맛(더한다)
			int sour = 1; // 신맛(곱한다.
			int cnt = 0; // 카운트(벡터값을 꺼내기 위해 사용한다)
			while(flag>0) // 지금보는거지만 for문을 쓰는게 더 좋았을듯 싶다.
			{
				if((flag&1)==1)
				{
					bit+=vec[cnt];
					sour*=vec2[cnt];
				}
				cnt++;
				flag = flag>>1;
			}
			if(bit!=0) // 아무것도 고르지 않은경우 제외
			{
				if(min>Math.abs(bit-sour)) // 차이가 양수인것 같으니
				{
					min = Math.abs(bit-sour); // 절댓값으로 뺀다.
				}
			}
			return;
		}
		permutation(vec,vec2,n+1,flag<<1); // 선택하지 않은경우
		permutation(vec,vec2,n+1,flag<<1|1); // 선택한 경우
	}
}
