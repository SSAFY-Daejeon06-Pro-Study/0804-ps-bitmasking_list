import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [문제 요약]
 * ip주소들이 주어질 때, 네트워크 주소와 네트워크 마스크를 구해야함
 * 
 * [문제 접근]
 * 처음에 모든 ip를 & 연산해서 '네트워크 주소'를 구하려고 했음
 * 예제의 모든 ip를 & 연산을 하니 마지막 8비트가 '10110000'가 나왔음
 * 이 방식이면 m이 4가 되어서 서브넷 마스크의 정답과 일치하지 않음
 * 
 * 도저히 모르겠어서 인터넷에 검색해 봤음
 * 인터넷을 통해 서브넷마스크를 구하고, 네트워크 주소를 구해야 한다는 것을 알게 되었음
 * 다른 비트가 나올 때 가 m의 위치가 된 다는 것을 알게 되었음
 * 
 * [문제 풀이 순서]
 * 1. 모든 ip를 비교하여 다른 비트가 나올 때 까지 확인
 * 	- 앞에서 부터 비교하여 다른 bit가 나온 위치가 m 위치임
 * 2. 서브넷 마스크를 구함
 * 	- m위치 앞을 모두 1로 채우고, m이하는 모두 0
 * 3. ip 하나와 서브넷 마스트를 & 연산으로 네트워크 주소를 구함
 * 
 * */
public class Main_2064_ip주소 {
	private static final int MAX = 4;
	private static final StringBuilder sb = new StringBuilder();;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] strs = br.readLine().split("\\.");
		int[] baseBytes = new int[MAX];

		for(int i=0; i<MAX; i++) {
			baseBytes[i] = Integer.parseInt(strs[i]);
		}

		int[][] otherBytes = new int[n-1][];
		for(int i=0; i<n-1; i++) {
			strs = br.readLine().split("\\.");
			int[] ip = new int[MAX];

			for(int j=0; j<strs.length; j++) {
				ip[j] = Integer.parseInt(strs[j]);
			}

			otherBytes[i] = ip;
		}

		int p;
		int mLo = -1;

		for(p=0; p<MAX; p++) { // 상위 byte 구역 부터 비교
			for(int i=0; i<n-1; i++) { // 비교 당하는 ip들
				if(baseBytes[p] != otherBytes[i][p]) { // 다르면 -> 다른 bit가 섞여 있음
					int binaryByte = (baseBytes[p] ^ otherBytes[i][p]);

					for(int k=0; k<8; k++) { // 8bit 비교
						if((binaryByte & (1<<k)) >= 1) { // 가장 상위에 있는 m의 위치 저장
							mLo = Math.max(mLo, k);
						}
					}
				}
			}

			if(mLo != -1) { // m의 위치를 찾았을 때,
				break;
			}
		}

		// 서브넷 마스크 구하기
		int[] mask = subnetMask(p, mLo);

		// 네트워크 주소
		for(int i=0; i<MAX; i++) {
			sb.append((baseBytes[i] & mask[i])).append(".");
		}
		sb.replace(sb.length()-1, sb.length(), System.lineSeparator());

		for(int i=0; i<MAX; i++) {
			sb.append(mask[i]).append(".");
		}
		sb.replace(sb.length()-1, sb.length(), "");

		System.out.println(sb.toString());

		br.close();
	}

	/*
	 * 서브넷 마스크를 구해서 반환
	 * @param p : m이 위치해 있는 구역 
	 * @param mLo : m의 bit 위치
	 * */
	private static int[] subnetMask(int p, int mLo) {
		int[] mask = new int[MAX];
		for(int i=0; i<MAX; i++) {
			if(i < p) {
				mask[i] = 255;
			}else if(i == p) {
				mask[i] = 255 - ((1<<(mLo+1)) - 1); // m비트 상위를 1로 채우고, 이하를 0으로 채우는 로직 
			}else {
				mask[i] = 0;
			}
		}
		return mask;
	} 
}
