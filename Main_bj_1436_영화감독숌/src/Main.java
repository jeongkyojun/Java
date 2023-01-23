import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(bf.readLine());

		for (int t = 0; t < tc; t++) {
			System.out.print("#"+t+" ");
			int n = Integer.parseInt(bf.readLine());
			int num = 0;
			for (long i = 0; i < Long.MAX_VALUE; i++) {
				long tmp = i;
				while (tmp > 0) {
					if (tmp % 10 == 6 && (tmp / 10) % 10 == 6 && (tmp / 100) % 10 == 6) {
						num++;
					} else if ((tmp / 10) % 10 == 6 && (tmp / 100) % 10 == 6 && (tmp / 1000) % 10 == 6) {
						num++;
					} else if ((tmp / 100) % 10 == 6 && (tmp / 1000) % 10 == 6 && (tmp / 10000) % 10 == 6) {
						num++;
					} else if ((tmp / 1000) % 10 == 6 && (tmp / 10000) % 10 == 6 && (tmp / 100000) % 10 == 6) {
						num++;
					} else if ((tmp / 10000) % 10 == 6 && (tmp / 100000) % 10 == 6 && (tmp / 1000000) % 10 == 6) {
						num++;
					} else if ((tmp / 100000) % 10 == 6 && (tmp / 1000000) % 10 == 6 && (tmp / 10000000) % 10 == 6) {
						num++;
					} else if ((tmp / 1000000) % 10 == 6 && (tmp / 10000000) % 10 == 6 && (tmp / 100000000) % 10 == 6) {
						num++;
					}
					tmp/= 1000000;
				}
				if (num == n) {
					System.out.println(i);
					break;
				}
			}
		}
	}

}
