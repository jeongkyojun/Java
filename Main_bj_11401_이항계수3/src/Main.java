import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			System.out.println(nCr(n,r,1234567891));
		}
	}
	
	/*
	 * 페르마의 소정리
	 * a^(p-1) = 1(mod p)
	 *  -> a^(p-2) = a^(-1) (mod p)
	 *  즉, nCr 에서 n! * (r!(n-r)!)^-1 (mod p) 는
	 *  n! * (r!(n-r)!)^(p-2) 와 합동이다.
	 */
	static long nCr(int n, int r, int p) {
		if(r==0) // r==0이면 1 반환
			return 1L;
		long[] fac = new long[n+1];
		fac[0] = 1;
		
		for(int i=1;i<=n;i++)
		{
			fac[i] = fac[i-1]*i % p;
		}
		// n! * r!^p-2 * (n-r)!^p-2의 값
		return (fac[n]*power(fac[r],p-2,p)%p*power(fac[n-r],p-2,p)%p)%p;
	}
	
	// 비트연산을 수행하여 자리수의 값만큼만 구한다.
	// 3^10 -> 10 = 1010 이므로
	// 3 	x	(1)
	// 9 	o	(2)
	// 81 	x	(4)
	// 7128	o	(8)
	static long power(long x, long y, long p) {
		long res = 1L;
		
		x=x%p; // p로 나눈 나머지로 구한다.
		
		while(y>0) { // y가 0보다 큰 경우
			if(y%2==1) // y가 홀수일때
				res = (res*x)%p; // x값 끼리 곱한다.
			y = y>>1; // y의 비트는 감소 -> 1024 -> 512 -> 256 -> 128 ...
			x=(x*x)%p; // x값은 제곱된다.
		}
		return res;
	}
}
