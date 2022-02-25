package main.bj_1132;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;

class NUMS implements Comparable<NUMS> {
	char alph;
	long num;

	NUMS() {
		this(0, '0');
	}

	NUMS(int num, char alph) {
		this.num = num;
		this.alph = alph;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+alph+" : "+num+"]";
	}

	@Override
	public int compareTo(NUMS o) {
		// TODO Auto-generated method stub
		return (num<o.num)?1:-1;
	}
}

public class Main {

	static long max;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1132.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");

			/*
			 * 필요한 것 맨 앞자리에 나오는 알파벳의 집합 위 집합을 이용해 집합을 제외한 수들 중 0의 값을 부여해야 한다. 아래의 코드에서 어떤것을
			 * 추가해야 할까?
			 * 
			 */
			int n = Integer.parseInt(bf.readLine());
			NUMS[] a = new NUMS[27];
			int nums = 0; // 채워야할 알파벳의 개수
			int t = 0; // 맨 앞인 알파벳의 개수
			boolean[] isFirst = new boolean[27];
			boolean[] list = new boolean[27];
			
			for(int i=0;i<27;i++)
			{
				a[i] = new NUMS(0,(char)('A'+i));
			}
			for (int i = 0; i < n; i++) {
				String st = new String(bf.readLine());
				for (int j = 0; j < st.length(); j++) {
					if(!list[st.charAt(j)-'A'])
					{
						nums++;
						list[st.charAt(j)-'A'] = true;
					}	
					if(j==0 && !isFirst[st.charAt(j)-'A'])
					{
						isFirst[st.charAt(j)-'A'] = true;
						//System.out.println(st.charAt(j));
						t++;
					}
					a[st.charAt(j) - 'A'].num += Math.pow(10, st.length() - j - 1);
				}
			}
			int f = nums-t;
			//System.out.println("nums : "+nums+" , f : "+f);
			// nums = 10인경우 0
			// nums = 1 인경우 9
			Arrays.sort(a);
			//System.out.println(Arrays.toString(a));
			int num = 9;
			BigDecimal res = new BigDecimal(0);
			for (int i = 0; i < 27; i++) {
				if(nums==10&&f==1)
				{
					if(!isFirst[a[i].alph-'A'])
					{
						//System.out.println(a[i].alph+" is false");
						continue;
					}
					//System.out.println(a[i].alph+" is true");
					//System.out.println(num+" -> "+a[i].alph);
					BigDecimal a_num = new BigDecimal(a[i].num);
					BigDecimal b_num = new BigDecimal(num);
					num--;
					res = res.add(a_num.multiply(b_num));
					t--;
				}
				else
				{
					//System.out.println(num+" -> "+a[i].alph);
					BigDecimal a_num = new BigDecimal(a[i].num);
					BigDecimal b_num = new BigDecimal(num);
					num--;
					res = res.add(a_num.multiply(b_num));
					if(isFirst[a[i].alph-'A'])
						t--;
					else
						f--;
				}
				if(num==0)
					break;
			}
			System.out.println(res.toString());
		}
	}

}
