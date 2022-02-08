
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
		for (int test_case = 0; test_case < T; test_case++) {
			int num = Integer.parseInt(st.nextToken());
			res.add(num);
		}
		for(int i=res.size()-1;i>=0;i--)
		{
			int a = res.get(i);
			while(!stk.isEmpty())
			{
				if(stk.peek()<=a)
				{
					stk.pop();
				}
				else
				{
					res.set(i,stk.peek());
					break;
				}
			}
			if(stk.isEmpty())
			{
				res.set(i, -1);
			}
			stk.push(a);
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
