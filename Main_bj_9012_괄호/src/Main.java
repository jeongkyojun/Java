import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		for(int i=0;i<n;i++)
		{
			String str = bf.readLine();
			boolean key = false;
			int open = 0;
			for(int j=0;j<str.length();j++)
			{
				if(str.charAt(j)=='(')
					open++;
				if(str.charAt(j)==')')
				{
					open--;
					if(open<0)
					{
						key = true;
						break;
					}
				}
			}
			if(key) System.out.println("NO");
			else {
				if(open>0)
					System.out.println("NO");
				else
					System.out.println("YES");
			}
			
		}
	}

}
