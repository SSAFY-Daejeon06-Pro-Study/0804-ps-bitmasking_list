import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 풀이 시작 : 7:12
 * 풀이 완료 : 7:42
 * 풀이 시간 : 30분
 *
 * 문제 해석
 * 정수로 표현되는 암호문이 있고, 암호문을 N개 모아놓은 암호문 뭉치가 있다
 * 암호문 뭉치는 아래 3가지 작업을 통해 제어할 수 있다
 *  - I x y s : 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입한다. s는 암호문들 (총 y번 주어짐)
 *  - D x y : 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다
 *  - A y s : 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 암호문들 (총 y번 주어짐)
 * 위의 규칙에 맞게 작성된 명령어를 나열하여 만든 문자열이 주어졌을 때 암호문 뭉치를 수정하고 수정된 결과의 처음 10개 암호문을 출력해야 함
 *
 * 구해야 하는 것
 * 위의 규칙에 맞게 작성된 명령어를 나열하여 만든 문자열이 주어졌을 때 암호문 뭉치를 수정하고 수정된 결과의 처음 10개 암호문을 출력해야 함
 *
 * 문제 입력
 * 테케당 입력
 * 첫째 줄 : 원본 암호문 뭉치 속 암호문 개수 N
 * 둘째 줄 : 원본 암호문 뭉치
 * 셋째 줄 : 명령어의 개수 M
 * 넷째 줄 : 명령어
 *
 * 제한 요소
 * 2000 <= N <= 4000
 * 250 <= M <= 500
 * 0 <= 암호 범위 <= 999999
 *
 * 생각나는 풀이
 * LinkedList 구현
 *  - Node 클래스
 *      - 멤버 : 암호 value, Node next
 * tail의 정보를 갖고 있어야 A 명령 수행하기 편함
 * search()메서드를 만들어서 사용
 *
 * 구현해야 하는 기능
 * 1. Node 클래스
 * 2. search() 메서드
 * 3. I, D, A 기능
 *
 */
class SWEA_1230 {
    static Node tail;
    static Node head;
    static StringTokenizer st;
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

		int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            head = new Node(0, null); // dummy node

            Node now = head;
            for (int i = 0; i < N; i++) {
                now.next = new Node(Integer.parseInt(st.nextToken()), null);
                now = now.next;
                tail = now; // add 연산하기 편하게 tail 추적
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while (M-- > 0) {
                char cmd = st.nextToken().charAt(0);
                if (cmd == 'I') {
                    insert(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                } else if (cmd == 'D') {
                    delete(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                } else {
                    add(Integer.parseInt(st.nextToken()));
                }
            }
            sb.append('#').append(test_case).append(' ');
            now = head.next;
            for (int i = 0; i < 10; i++) {
                sb.append(now.value).append(' ');
                now = now.next;
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 삽입 연산
    private static void insert(int x, int y) {
        Node node = search(x);
        Node end = node.next;
        if (node.equals(tail)) { // 만약 삽입할 위치가 tail이라면 add메서드 수행
            add(y);
        } else {
            for (int i = 0; i < y; i++) { // 중간이라면 중간 삽입
                node.next = new Node(Integer.parseInt(st.nextToken()), null);
                node = node.next;
            }
            node.next = end;
        }
    }

    // 삭제 연산
    private static void delete(int x, int y) {
        Node node = search(x);
        Node end = node.next;

        for (int i = 0; i < y; i++) {
            Node next = end.next;
            end.value = 0;
            end.next = null;
            end = next;
        }
        if (end == null) tail = node; // 삭제하고 뒤의 노드가 null => 현재 인덱스가 tail이 됨
        node.next = end;
    }

    // 추가 연산
    private static void add(int y) {
        // tail을 계속 갱신해줬기 때문에 앞에서부터 끝까지 탐색하지 않고 바로 tail 뒤에 붙여줌
        for (int i = 0; i < y; i++) {
            tail.next = new Node(Integer.parseInt(st.nextToken()), null);
            tail = tail.next;
        }
    }

    // 해당 인덱스의 노드를 찾는 search 메서드
    private static Node search(int idx) {
        Node node = head;
        for (int i = 0; i < idx; i++) {
            node = node.next;
        }
        return node;
    }
}
