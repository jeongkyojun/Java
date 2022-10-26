import java.io.*;
import java.util.*;

class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2628.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case <=T;test_case++)
		{
			System.out.println("##"+test_case);
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int W = Integer.parseInt(st.nextToken()); // 가로, 1로 자른다.
			int H = Integer.parseInt(st.nextToken()); // 세로, 0으로 자른다.
			int N = Integer.parseInt(bf.readLine());
			int[] dir = new int[N]; // 방향
			int[] point = new int[N]; // 지점
			int W_size = 0;
			int H_size = 0;
			for(int i=0;i<N;i++)
			{
				st = new StringTokenizer(bf.readLine());
				dir[i] = Integer.parseInt(st.nextToken());
				switch(dir[i])
				{
				case 0:
					H_size++;
					break;
				case 1:
					W_size++;
					break;
				}
				point[i] = Integer.parseInt(st.nextToken());
			}
			int[] H_cut = new int[H_size];
			int[] W_cut = new int[W_size];
			H_size = 0;
			W_size = 0;
			for(int i=0;i<N;i++)
			{
				switch(dir[i])
				{
				case 0:
					H_cut[H_size++] = point[i];
					break;
				case 1:
					W_cut[W_size++] = point[i];
					break;
				}
			}
			Arrays.sort(H_cut);
			Arrays.sort(W_cut);
			int max_W = -1;
			int max_H = -1;
			int p = 0;
			for(int i=0;i<W_size;i++)
			{
				if(W_cut[i]-p>max_W)
				{
					max_W = W_cut[i]-p;
				}
				p = W_cut[i];
			}
			if(W-p>max_W)
			{
				max_W = W-p;
			}
			p =0;
			for(int i=0;i<H_size;i++)
			{
				if(H_cut[i]-p>max_H)
				{
					max_H = H_cut[i]-p;
				}
				p = H_cut[i];
			}
			if(H-p>max_H)
			{
				max_H = H-p;
			}
			
			System.out.println(max_H*max_W);
		}
	}

}
