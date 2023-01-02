import java.util.*;

public class Test02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[5];
		String n;
		Map<int[], Integer> map = new HashMap<int[],Integer>();
		int[] num = new int[] {1,1};
		getMap(num,map);
		System.out.println(map.get(num));
	}
	
	static void getMap(int[] num,Map<int[], Integer>map)
	{
		map.put(num,2);
	}
}
