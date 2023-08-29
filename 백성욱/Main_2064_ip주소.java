package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2064_ip주소 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] ip = new int[N+1][4];
		int[] subNet = {255, 255, 255, 0};
		int[] check = new int[N];
		for(int t = 0; t < N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			for(int i= 0; i<4; i++) {
				ip[t][i] =Integer.parseInt(st.nextToken());
			}
		}
		System.arraycopy(ip[0], 0, ip[N], 0, 3);
		//d클래스 서브넷 마스크 만들기
		for(int d=0; d<=7; d++) {
			int subnet = 255 - ((1<<d)-1);
			boolean flag = true;
			for(int i =0; i<N; i++) {
				check[i] = subnet & ip[i][3];
				if(i>0 && check[i] != check[i-1]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				ip[N][3] = check[N-1];
				subNet[3] = subnet;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i= 0; i<4; i++) {
			if(i!=3) sb.append(ip[N][i]).append('.');
			else sb.append(ip[N][i]).append('\n');
		}
		for(int i= 0; i<4; i++) {
			if(i!=3) sb.append(subNet[i]).append('.');
			else sb.append(subNet[i]).append('\n');
		}
		System.out.println(sb);
	}

}
