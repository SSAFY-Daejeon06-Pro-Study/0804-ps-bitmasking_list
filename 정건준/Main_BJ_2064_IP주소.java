import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * [문제]
 * 네트워크는 서브넷 주소와 서브넷 마스크로 표현
 * IP 주소는 4개의 바이트로 구성, 각각을 10진수로 나타내고 10진수 사이에 .을 찍어 표현
 * 서브넷 주소와 서브넷 마스크 역시 IP 주소와 같은 형식
 *
 * 네트워크에는 2^m개의 IP가 존재
 * 이 경우 서브넷 주소는 32-m 자리가 임의의 수로 구성되며 뒤의 m 자리는 0으로 채워짐
 * 반면 서브넷 마스크는 32-m 자리가 1로 채워져 있고, 뒤의 m 자리는 0으로 채워짐
 * 2^m개 IP의 서브넷 주소는 모두 같음
 *
 * 같은 네트워크에 속한 IP 주소들이 주어졌을 때, 서브넷 주소와 서브넷 마스크를 출력
 * 답이 여러 개인 경우 가장 크기가 작은 네트워크의 서브넷 주소와 서브넷 마스크 출력
 *
 * [풀이]
 * 네트워크에 속한 IP의 서브넷 주소는 모두 같음
 * 따라서 주어진 IP 주소에서 모두 같은 비트 영역을 1로 만들고, 나머지는 0으로 만들면 서브넷 마스크를 구함
 * 서브넷 주소 = 주어진 IP 주소 중 하나 & 서브넷 마스크
 *
 * IP 주소는 int 형 변수, 32비트로 표현 (0번째 비트 ~ 31번째 비트)
 * Ip = 0번째 IP 주소
 *
 * 서브넷 마스크를 구하는 로직
 * 1. 서브넷 마스크를 모두 1로 초기화
 * 2. 서브넷 마스크의 비트가 1인 부분까지 순회 (for i=31; i>=0; i--)
 * 2-1. 서브넷 마스크의 i 비트가 0이면 break
 * 2-2. flag가 true면 i 비트 0으로 설정, continue
 * 2-3. Ip1의 i비트와 Ip2의 i비트가 다르면 flag = true
 */

public class Main_BJ_2064_IP주소 {

    static int subNetMask;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] ipArr = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), ".");
            ipArr[i] += (Integer.parseInt(st.nextToken()) << 24);
            ipArr[i] += (Integer.parseInt(st.nextToken()) << 16);
            ipArr[i] += (Integer.parseInt(st.nextToken()) << 8);
            ipArr[i] += (Integer.parseInt(st.nextToken()));
        }

        subNetMask = Integer.MAX_VALUE * 2 + 1;
        int ip = ipArr[0];
        for(int i=1; i<N; i++) calSubNetMask(ip, ipArr[i]);

        int subNetIp = ip & subNetMask;
        System.out.print(printIp(subNetIp));
        System.out.print(printIp(subNetMask));
    }

    static void calSubNetMask(int ip, int nextIp) {
        boolean zeroFlag = false;

        for(int i=31; i>=0; i--) {
            if((subNetMask & (1<<i)) == 0) break;

            if((ip & (1 << i)) != (nextIp & (1 << i))) zeroFlag = true;

            if(zeroFlag) subNetMask = subNetMask & (~(1<<i));
        }
    }

    static String printIp(int ip) {
        StringBuilder sb = new StringBuilder();
        int[] decimalIpArr = new int[4];

        for(int i=0; i<4; i++) {
            int decimalIp = 0;
            int weight = 1;
            for(int j=0; j<8; j++) {
                if((ip & (1 << j)) > 0) decimalIp += weight;
                weight *= 2;
            }

            decimalIpArr[i] = decimalIp;
            ip = ip >>> 8;
        }

        for(int i=3; i>=1; i--) sb.append(decimalIpArr[i]).append('.');
        sb.append(decimalIpArr[0]).append('\n');
        return sb.toString();
    }
}