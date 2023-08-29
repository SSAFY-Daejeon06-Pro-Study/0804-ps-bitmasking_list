package 정건준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * [문제]
 *  N개의 10억 이하 자연수로 이뤄진 수열이 있음
 *  수열을 M번 편집
 *  인덱스 L의 데이터 출력
 *  편집이 끝난 후 L이 존재하지 않으면 -1 출력
 *  I, D, C 명령에서 인덱스의 위치가 존재하지 않는 경우는 입력으로 들어오지 않음
 *  
 *  N (수열의 길이, 5 <= N <= 1000)
 *  M (편집 횟수, 1 <= M <= 1000)
 *  L (6 <= L <= N+M)
 *  
 *  [풀이]
 *  단방향 링크드 리스트로 구현,
 *  1. head 더미 노드를 만들고, 링크드 리스트 완성
 *  2. I 연산, D 연산, C 연산 구현
 *  3. M번 순회하면서 연산 수행
 *  시간 복잡도 = 1000 * 1000 = 1000000
 */
public class Solution_수열편집 {
    static class Node {
        int value;
        Node next;
        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    static Node head;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for(int t=1; t<=testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            head = new Node(0, null);

            Node tail = head;
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                Node node = new Node(Integer.parseInt(st.nextToken()), null);
                node.next = tail.next;
                tail.next = node;
                tail = node;
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int idx = Integer.parseInt(st.nextToken());
                int value = (st.countTokens() > 0) ? value = Integer.parseInt(st.nextToken()) : 0;

                if(command == 'I') I(head, idx, value);
                else if(command == 'D') D(head, idx);
                else if(command == 'C') C(head, idx, value);
            }

            sb.append('#').append(t).append(' ').append(get(head, L)).append('\n');
        }
        System.out.println(sb);
    }

    static void I(Node head, int idx, int value) {
        Node newNode = new Node(value, null);
        Node previous = head;
        for(int i=0; i<idx; i++) previous = previous.next;
        newNode.next = previous.next;
        previous.next = newNode;
    }

    static void D(Node head, int idx) {
        Node previous = head;
        for(int i=0; i<idx; i++) previous = previous.next;
        previous.next = previous.next.next;
    }

    static void C(Node head, int idx, int value) {
        Node cur = head;
        for(int i=0; i<idx+1; i++) cur = cur.next;
        cur.value = value;
    }

    static int get(Node head, int idx) {
        Node cur = head;
        for(int i=0; i<idx+1; i++) {
            cur = cur.next;
            if(cur == null) return -1;
        }
        return cur.value;
    }
}
