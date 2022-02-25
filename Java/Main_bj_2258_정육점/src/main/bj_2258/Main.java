package main.bj_2258;

import java.io.*;
import java.util.*;

보류문제(왜 9%에서 틀리는걸까?)

class Meat implements Comparable<Meat> {
	int w;
	int price;

	Meat(int w, int price) {
		this.w = w;
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + w + " , " + price + "]";
	}

	@Override
	public int compareTo(Meat o) {
		// TODO Auto-generated method stub
		if (price - o.price == 0)
			return o.w-w;
		return price - o.price;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2258.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Meat[] mat = new Meat[N+1];

		mat[0] = new Meat(0,0);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			mat[i] = new Meat(w, p);
		}

		Arrays.sort(mat);
		System.out.println(Arrays.toString(mat));
		int bef = 0;
		int bef2 = 0;
		int res = -1;
		for (int i = 1; i < N; i++) {
			if (M <= mat[i].w + bef) {
				res = mat[i].price;
				break;
			}
			// i==N-1이면 마지막이므로 굳이 저장할 필요가 없다
			if (i < N - 1) {
				if (mat[i].price == mat[i-1].price) // 이전과 값이 같은경우
				{
					bef2 += mat[i].w; // 무게별로 따로 저장
				} else {
					bef += mat[i].w;
					bef += bef2;
					bef2 = 0;
				}
			}
		}
		System.out.println(res);
	}

}
