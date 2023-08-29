import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 7:50
 * 풀이 완료 : 8:11
 * 풀이 시간 : 21분
 *
 * 문제 해석
 * N개의 10억 이하의 자연수로 이루어진 수열이 있음
 * M번의 편집을 거쳐 완성
 * 편집 방법
 * - I x y : x번 인덱스 앞에 y를 추가하고 한 칸씩 뒤로 이동
 * - D x : x번 인덱스 자리를 지우고 앞으로 한 칸씩 이동
 * - C x y : x번 인덱스 자리 값을 y로 변경
 *
 * 구해야 하는 것
 * M번의 편집을 마치고 난 수열의 L번 인덱스 값
 * 만약 L이 존재하지 않으면 -1 출력
 *
 * 문제 입력
 * 첫째 줄 : 테케 수 t
 * 테케당 입력
 * 첫째 줄 : 수열 길이 N, 추가 횟수 M, 출력할 인덱스 번호 L
 * 둘째 줄 : 수열의 초기 값
 *
 * 제한 요소
 * 1 <= T <= 50
 * 5 <= N <= 1000
 * 1 <= M <= 1000
 * 6 <= L <= N + M
 *
 * 생각나는 풀이
 * linkedlist 구현
 *
 * 구현해야 하는 기능
 * linkedlist 구현
 *
 */
class SWEA_13501 {
    static int N;
    static Node head;
    static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            head = new Node(Integer.parseInt(st.nextToken()), null);
            Node now = head;

            for (int i = 0; i < N - 1; i++) {
                now.next = new Node(Integer.parseInt(st.nextToken()), null);
                now = now.next;
            }
            now = null;

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                if (cmd == 'I') {
                    insert(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                } else if (cmd == 'D') {
                    delete(Integer.parseInt(st.nextToken()));
                } else {
                    change(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
            }

            sb.append('#').append(test_case).append(' ').append(N > L ? search(L).value : -1).append('\n');
        }

        System.out.println(sb);
    }


    private static void insert(int idx, int value) {
        if (idx == 0) {
            Node now = new Node(value, head);
            head = now;
        } else {
            Node prev = search(idx - 1);
            Node next = prev.next;
            prev.next = new Node(value, next);
        }
        N++;
    }

    private static void delete(int idx) {
        if (idx == 0) {
            Node now = head.next;
            head = now;
        } else {
            Node prev = search(idx - 1);
            Node now = prev.next;
            prev.next = now.next;
        }
        N--;
    }

    private static void change(int idx, int value) {
        search(idx).value = value;
    }

    private static Node search(int idx) {
        Node now = head;
        for (int i = 0; i < idx; i++) {
            now = now.next;
        }

        return now;
    }
}
