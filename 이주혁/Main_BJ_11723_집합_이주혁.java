package 자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 집합 {
	private static int S;
	private static BufferedWriter bw;
	
	private static void func(String cmd, String[] input) throws IOException {
		
		switch (cmd) {
		case "add":
			// x 삽입
			int x = Integer.parseInt(input[1]);
			if((S & 1 << x) == 0) S |= 1 << x;
			break;

		case "check":
			// x 가지고 있는지 체크
			
			x = Integer.parseInt(input[1]);
			if((S & 1 << x) > 0) bw.write(1 + "\n");
			else bw.write(0 + "\n");
			break;
	
		case "remove":
			// x 가지고 있다면 삭제
			
			x = Integer.parseInt(input[1]);
			if((S & 1 << x) > 0) S -= 1 << x;
			break;
		
		case "toggle":
			// 전체 반전
			x = Integer.parseInt(input[1]);
			if((S & 1 << x) > 0) S -= 1 << x;
			else S |= 1 << x;
			
			break;
		
		case "empty":
			// 공집합 만들기
			S = 0;
			break;
		
		case "all":
			// 전체집합 만들기
			S = (int)Math.pow(2, 21) - 1;
			break;
		}

	}
	/**
	 * 
	 * 비트연산자
	 * 
	 * | (or)
	 * & (and)
	 * ^ (xor) => 다를때 1, 같으면 0
	 * << i (shift) => i번 왼쪽으로 밀겠다. (i번 2제곱)
	 * >> i (shift) => i번 오른쪽으로 밀겠다.   
	 * 
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = 0;
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			
			String[] input = br.readLine().split(" ");
			func(input[0], input);
			
		}
		
		br.close();
		bw.close();
		
		
	}
	
}
