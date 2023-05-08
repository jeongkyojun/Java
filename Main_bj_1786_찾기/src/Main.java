import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String T = bf.readLine();
		String P = bf.readLine();
		for (int i = 0; i < T.length(); i++) {

		}
	}

	static int[] kmp(String T, String P) {
		List<Integer> ans = new ArrayList<Integer>();
		int[] pi = getPi(P); // 문자의 접두사와 접미사 계산, 확인
		// begin은 문자열의 피벗을 나타내고, match는 일치하는 문자의 개수를 나타낸다.
		int begin = 0, matched = 0;
		int n = T.length();
		int m = P.length();
		while (begin <= n - m) {
			// 탐색할 문자열과 원본 문자열에서 현재 위치의 문자가 동일한 경우
			if (matched < m && T.charAt(begin + matched) == P.charAt(matched)) {
				// T.charAt(begin+matched)와 P.charAt(matched)를 비교한다.
				++matched; // 일치하면 matched를 더한다.
				// 문자열이 모두 일치하는 경우
				if (matched == m) {
					ans.add(begin); // 집어넣는다.
				}
			} else {
				// 일치하지 않는경우, 다음 위치의 문자 부터 탐색
				if (matched == 0) // 처음부터 안맞으면 다음으로 넘어간다.
				{
					++begin;
				}
				// 문자열이 불일치 하므로 접두사, 접미사 의 길이 만큼 건너 뜀!
				else {
					// 현재 불일치가 발생한 위치는 begin + matched
					// 여기서 접두, 접미사의 길이인 pi[matched - 1] 을 빼주면 다음 탐색 시작 위치
					begin += matched - pi[matched - 1];
					matched = pi[matched - 1];
				}
			}
		}

		return ans.stream().mapToInt(Integer::intValue).toArray();
	}
	
	static int[] getPi(String search){
	    int m = search.size();
	    vector<int> pi(m, 0);
	    
	    int begin = 1, matched = 0;
	    
	    while(begin + matched < m){
	        // 탐색 문자열과 탐색 문자열 자신을 매칭시킴!
	        if(search[begin + matched] == search[matched]){
	            matched++;
	            pi[begin + matched - 1] = matched;	// 매칭을 진행하면서, 접두 접미사 배열을 바로 갱신
	        }
	        else{
	            if(matched == 0)
	                begin++;
	            else{
	                // KMP 문자열 탐색 알고리즘 과 동일하게 불일치 발생 시
	                // 매칭을 진행하면서 구했던 접두 접미사 길이 만큼 탐색을 건너뛸 수 있다
	                begin += matched - pi[matched - 1];
	                matched = pi[matched - 1];
	            }
	        }
	    }
	    
	    return pi;
	}
	
}
