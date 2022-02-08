
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_17298.txt") ));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> stk = new Stack<Integer>();
		List<Integer> res = new ArrayList<Integer>();
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		// 1. 리스트에 값을 담는다.
		for (int test_case = 0; test_case < T; test_case++) {
			int num = Integer.parseInt(st.nextToken());
			res.add(num);
		}
		
		// 2. 역순으로 리스트의 값 a를 꺼낸다.
		// 3. 스택에 있는 값에서 가장 먼저 나오는 a보다 큰 값이 존재하면, 그값을 a와 바꾼다.
		// 3-2. 스택에 a보다 큰 값이 없어 스택이 빈다면, -1을 집어넣는다.
		// 4. a를 스택에 넣는다.
		// 5. 2번부터 4번까지를 반복한다.
		
		for(int i=res.size()-1;i>=0;i--) // 5. 2번부터 4번까지 반복한다.
		{
			int a = res.get(i); // 2. 역순으로 리스트의 값 a를 꺼낸다.
			while(!stk.isEmpty())
			{
				// 스택에 가장 위의 값이 a보다 크지 않다면, pop을 수행해서 제거한다 ( 이후에도 쓸 일이 없기 때문이다. )
				if(stk.peek()<=a)
				{
					stk.pop();
				}
				else
				{
					// 3. a 보다 큰 경우, a값이 있는 위치에 스택의 값을 집어넣는다.
					res.set(i,stk.peek());
					break;
				}
			}
			if(stk.isEmpty()) // 3-2. 만약 스택이 비어서 반복문이 종료되면, -1을 넣는다.
			{
				res.set(i, -1);
			}
			stk.push(a); // 4. a를 스택에 넣는다.
		}
		for(int i=0;i<res.size();i++)
		{
			bw.write(res.get(i)+" ");
		}
		bw.write("\n");
		bw.flush();
		bw.close();
	}

}
