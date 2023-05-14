package main.bj_2258;

import java.io.*;
import java.util.*;


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

		PriorityQueue<Meat> mat = new PriorityQueue<Meat>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			mat.offer(new Meat(w, p));
		}
		System.out.println(mat.poll().w);
	}

}
