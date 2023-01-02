import java.util.*;
import java.io.*;

public class Main {

	static int num;
	static long res;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int end = Integer.parseInt(bf.readLine());
		num = 0;
		res = -1;
		for(int i=1;i<12;i++)
		{
			getNum(i,0,10,end, 0);
		}
		System.out.println(res);
	}

	static void getNum(int n,int p,int now, int end,long number)
	{
		if(num>end)
			return;
		if(n==p)
		{
			if(num==end) res = number;
			num++;
			return;
		}
		for(long i=0;i<now;i++) {
			getNum(n,p+1,(int)i,end,number*10+i);
		}
	}
}
