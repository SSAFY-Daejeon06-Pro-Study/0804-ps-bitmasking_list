import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] computers = new int[4][N]; // 컴퓨터 ip 저장할 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(),"."); // StringTokenizer의 delimiter로 .을 주면 . 기준으로 잘라줌
            for (int j = 0; j < 4; j++) {
                computers[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int[] subnetMask = {255, 255, 255, 255};

        // ip의 앞에서부터 탐색
        // 서브넷 마스크와 컴퓨터 ip를 &연산한 값의 결과가 다른 값이 나올 때까지 탐색
        // 다른 값이 나온다면 그 이전 값까지가 동일한 네트워크 대역이라는 얘기
        fo : for (int i = 0; i < 4; i++) {
            for (int j = 7; j >= 0; j--) {
                boolean isZero = false, isOne = false; // &연산해서 0이 나왔는지, 1이 나왔는지
                for (int k = 0; k < N; k++) {
                    if ((computers[i][k] & (1 << j)) == 0) isZero = true;
                    else isOne = true;
                    if (isOne && isZero) { // 둘 다 나옴 => 현재 비트부터 네트워크 주소로 사용 못함
                        while (j >= 0) { // 서브넷 마스크의 현재 클래스의 현재 비트 이후로는 전부 0이어야 함
                            subnetMask[i] -= (1 << j--);
                        }

                        while (++i < 4) { // 서브넷 마스크의 다음 클래스부터는 전부 0이어야 함
                            subnetMask[i] = 0;
                        }
                        break fo; // 답을 찾았으므로 탐색 종료
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            computers[i][0] &= subnetMask[i]; // 네트워크 주소 = 서브넷 마스크와 &연산하여 나온 값
        }
        System.out.printf("%d.%d.%d.%d%n", computers[0][0], computers[1][0], computers[2][0], computers[3][0]);
        System.out.printf("%d.%d.%d.%d%n", subnetMask[0], subnetMask[1], subnetMask[2], subnetMask[3]);
    }
}
