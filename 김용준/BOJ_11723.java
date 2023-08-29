import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 5:40
 * 풀이 완료 : 6:00
 * 풀이 시간 : 20분
 *
 * 문제 해석
 * 비어있는 공집합 S가 주어졌을 때 연산을 수행하는 프로그램 작성
 * 연산의 종류
 *  add x : S에 x를 추가, 있으면 연산 무시
 *  remove x : S에서 x를 제거, 없으면 연산 무시
 *  check x : S에 x가 있으면 1, 없으면 0 출력
 *  toggle x : S에 x가 있으면 제거, 없으면 추가
 *  all : S의 모든 원소를 추가
 *  empty : S의 모든 원소를 제거
 *
 * 구해야 하는 것
 * 연산의 결과 출력
 *
 * 문제 입력
 * 첫째 줄 : 연산의 수행 횟수 M
 * 둘째 줄 ~ M개 줄 : 수행할 연산
 *
 * 제한 요소
 * 1 <= M <= 3_000_000
 *
 * 생각나는 풀이
 * bit 연산
 * add => 해당 비트 true
 * remove => 해당 비트 false
 * check => 해당 비트 check
 * toggle => 해당 비트 xor
 * all => S를 2^20 - 1로 변경
 * empty => S를 0으로 변경
 *
 * 구현해야 하는 기능
 * 각 연산 기능 수행
 */
public class BOJ_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int S = 0;
        int M = Integer.parseInt(br.readLine());
        int full = (1 << 20) - 1; //0b11111111111111111111
        int idx = 0;
        char cmd;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken().charAt(1); // 연산의 2번째 글자가 다 다름
            if (st.hasMoreTokens()) idx = 1 << (Integer.parseInt(st.nextToken()) - 1);
            if (cmd == 'd') { // add
                S |= idx;
            } else if (cmd == 'e') { // remove
                S &= full - idx;
            } else if (cmd == 'h') { // check
                sb.append(((S & idx) == 0) ? 0 : 1).append('\n');
            } else if (cmd == 'o') { // toggle
                if ((S & idx) == 0) S |= idx;
                else S -= idx;
            } else if (cmd == 'l') { // all
                S = full;
            } else { // empty
                S = 0;
            }
        }

        System.out.println(sb);
    }
}
