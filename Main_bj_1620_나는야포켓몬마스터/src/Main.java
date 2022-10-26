import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1620.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1;tc<=T;tc++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			Map<String, Integer> names = new HashMap<String, Integer>();
			String[] nums = new String[n+1];
			for(int i=0;i<n;i++)
			{
				String str = bf.readLine();
				names.put(str, i+1);
				nums[i+1] = str;
			}
			
			for(int i=0;i<f;i++)
			{
				String str = bf.readLine();
				if(('a'<=str.charAt(0)&&str.charAt(0)<='z') || ('A'<=str.charAt(0)&&str.charAt(0)<='Z'))
				{
					System.out.println(names.get(str));
				}
				else
				{
					System.out.println(nums[Integer.parseInt(str)]);
				}
			}
			
		}
	}

}
