package 정건준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 공집합 S, 집합에 들어갈수 있는 원소 x (1<=x<=20)
 * 집합을 20개의 비트(int형 변수)로 표현
 * 원소 1 - 0비트, 원소 20 - 19비트
 *
 * add(x) -> x-1번째 비트를 1로
 * remove(x) -> x-1번째 비트를 0으로
 * check(x) -> x-1번째 비트 상태 반환
 * toggle(x) -> x-1번째 비트 토글(! 연산)
 * all(x) -> int형 변수의 값을 1,048,575로 설정
 * empty() -> int형 변수의 값을 0으로 설정
 *
 * 시간 복잡도 = 모든 연산의 시간복잡도는 O(1)이므로 O(M)
 */
public class Main_BJ_11723 {
    static int set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = (st.countTokens() > 0) ? Integer.parseInt(st.nextToken()) : 0;

            if(command.equals("all")) {
                all();
            }
            else if(command.equals("empty")) {
                empty();
            }
            else if(command.equals("check")) {
                sb.append(check(x)).append('\n');
            }
            else if(command.equals("add")) {
                add(x);
            }
            else if(command.equals("remove")) {
                remove(x);
            }
            else if(command.equals("toggle")) {
                toggle(x);
            }
        }

        System.out.println(sb);
    }

    static void add(int x) {
        x = x-1;
        set |= (1 << x);
    }

    static void remove(int x) {
        x = x-1;
        set &= ~(1 << x);
    }

    static int check(int x) {
        x = x-1;
        if((set & (1 << x)) > 0) return 1;
        return 0;
    }

    static void toggle(int x) {
        if(check(x) == 0) add(x);
        else remove(x);
    }

    static void all() {
        set = 1048575;
    }

    static void empty() {
        set = 0;
    }
}
