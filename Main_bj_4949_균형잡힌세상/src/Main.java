import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			String str = bf.readLine();
			char[] stack = new char[101];
			int top = -1;
			boolean key = false;
			if(str.equals(".")) break;
			for(int i=0;i<str.length();i++) {
				if(str.charAt(i)=='('||str.charAt(i)=='[')
					stack[++top] = str.charAt(i);
				if(str.charAt(i)==')')
				{
					if(top==-1)
					{
						key = true;
						break;
					}
					char pop = stack[top--];
					if(pop!='(')
					{
						key = true;
						break;
					}
				}
				if(str.charAt(i)==']')
				{
					if(top==-1)
					{
						key = true;
						break;
					}
					char pop = stack[top--];
					if(pop!='[')
					{
						key = true;
						break;
					}
				}
			}
			if(key)
				System.out.println("no");
			else
			{
				if(top==-1)
					System.out.println("yes");
				else
					System.out.println("no");
			}
		}
	}

}
