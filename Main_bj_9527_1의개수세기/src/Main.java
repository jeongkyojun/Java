import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		long[] f_x = new long[50];
		f_x[0] = 0;
		f_x[1] = 1;
		for(int i=2;i<50;i++) {
			f_x[i] = f_x[i-1]+f_x[i-1]+(1<<(i-2));
		}
		for(int i=0;i<50;i++) {
			System.out.println(f_x[i]);
		}
	}

}
