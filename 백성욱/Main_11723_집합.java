import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * boolean형 배열로 할 수 있는건 비트마스킹으로 가능
 */

public class Main_11723_집합 {
	static int binary;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb =new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		binary = 0;
		for(int i = 0; i<M; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int num = 0;
			if(order.equals("add") || order.equals("remove")|| order.equals("check")|| order.equals("toggle")) {
				num = Integer.parseInt(st.nextToken())-1;
			}
			
			if(order.equals("add")) add(num);
			else if(order.equals("remove")) remove(num);
			else if(order.equals("check")) check(num);
			else if(order.equals("toggle")) toggle(num);
			else if(order.equals("all")) all();
			else if(order.equals("empty")) empty();
//			System.out.println(Integer.toBinaryString(binary));
		}
		System.out.println(sb);
	}
	
	private static void add(int mark){
		if((binary & 1<<mark) == 1<<mark) return; 
		binary += 1<<mark;
	}
	
	private static void remove(int mark) {
		if((binary & 1<<mark) == 0) return;
		binary -= 1<<mark;
	}
	
	private static void check(int mark) {
		if((binary & 1<<mark) == 1<<mark) sb.append('1').append('\n');
		else sb.append('0').append('\n');
//		System.out.println(Integer.toBinaryString(binary));
	}
	
	
	private static void toggle(int mark) {
		if((binary & 1<<mark) == 1<<mark) binary -= 1<<mark;
		else binary += 1<<mark;

	}
	
	private static void all() {
		binary = (1<<20)-1;
//		System.out.println("all");

	}
	
	private static void empty() {
		binary = 0;
//		System.out.println("empty");
	}

}
