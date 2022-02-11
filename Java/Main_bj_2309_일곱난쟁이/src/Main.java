
import java.io.*;
import java.util.*;

public class Main {

	static boolean isStop = false;
	static int[] R;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2309.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int[] gnome = new int[9];
		R = new int[7];
		for(int i=0;i<9;i++)
		{
			gnome[i] = Integer.parseInt(bf.readLine());
		}
		Arrays.sort(gnome);
		Comb(7,0,0,gnome,0);
	}
	
	static void Comb(int K, int P,int point, int[] gnome,int sum)
	{
		if(P==K)
		{
		
			if(sum==100)
			{
				for(int i=0;i<R.length;i++)
				{
					System.out.println(gnome[R[i]]);
				}
				isStop = true;
			}
			return;
		}
		if(!isStop)
		{

			for(int i=point;i<gnome.length;i++)
			{
				R[P] = i;
				Comb(7,P+1,i+1,gnome,sum+gnome[i]);
			}
		}
	}

}
