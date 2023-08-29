package 정건준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * [문제]
 * 0 ~ 999999 사이의 수로 표현되는 암호문이 있음
 * 암호문을 N개 모아 놓은 암호문 뭉치가 있음
 * 암호문은 특수 처리기로만 수정 가능
 * 처리기는 3개의 명령어로 제어
 * 
 * [풀이]
 * 암호문을 링크드 리스트의 노드로 표현
 * Node = int value, int next
 * 
 * 명령어를 보면 링크 이동 시 이전 노드 이동, 순환 이동이 없음
 * 단방향 링크드 리스트 구현(더미 노드 사용)
 * 
 * I 메서드(int x, int y, nodeArr) O(N)
 * 0. pre = head
 * 1. pre x번 이동, pre = pre.next
 * 2. nodeArr[y-1].next = pre.next;
 * 3. pre.next = nodeArr[0]
 * 4. y-1번 순회, for(int i=0; i<y-1; i++) nodeArr[i].next = nodeArr[i+1]
 * 
 * D 메서드(int x, int y) O(N)
 * 0. pre = head, nextOfPre = head
 * 1. pre x번 이동, pre = pre.next
 * 2. next x+y+1번 이동, nextOfPre = nextOfPre.next
 * 3. pre.next = nextOfPre
 * 
 * A 메서드(int y, nodeArr) O(N)
 * 0. tail = head;
 * 1. 마지막 노드로 이동, for(; tail.next != null; tail=tail.next);
 * 2. tail.next = nodeArr[0]
 * 4. y-1번 순회, for(int i=0; i<y-1; i++) nodeArr[i].next = nodeArr[i+1]
 */

public class Solution_암호문3 {

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

		for(int t=1; t<=10; t++) {
			head = new Node(0, null);
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			Node tail = head;
			for(int i=0; i<N; i++) {
				int value = Integer.parseInt(st.nextToken());
				Node newNode = new Node(value, null);
				tail.next = newNode;
				tail = newNode;
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for(int i=0; i<M; i++) {
				String command = st.nextToken();
				
				if(command.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					Node[] nodeArr = new Node[y];
					for(int j=0; j<y; j++) {
						int value = Integer.parseInt(st.nextToken());
						nodeArr[j] = new Node(value, null);
					}
					I(x, y, nodeArr);
				}
				else if(command.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					D(x, y);
				}
				else if(command.equals("A")) {
					int y = Integer.parseInt(st.nextToken());
					Node[] nodeArr = new Node[y];
					for(int j=0; j<y; j++) {
						int value = Integer.parseInt(st.nextToken());
						nodeArr[j] = new Node(value, null);
					}
					A(y, nodeArr);
				}
			}

			sb.append('#').append(t).append(' ');
			sb.append(print());
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void I(int x, int y, Node[] nodeArr) {
		Node pre = head;
		for(int i=0; i<x; i++) pre = pre.next;

		nodeArr[y-1].next = pre.next;
		pre.next = nodeArr[0];
		for(int i=0; i<y-1; i++) nodeArr[i].next = nodeArr[i+1];
	}
	
	static void D(int x, int y) {
		Node pre = head;
		Node nextOfPre = head;
		for(int i=0; i<x; i++) pre = pre.next;
		for(int i=0; i<x+y+1; i++) nextOfPre = nextOfPre.next;
		
		pre.next = nextOfPre;
	}
	
	static void A(int y, Node[] nodeArr) {
		Node tail = head;
		for(; tail.next != null; tail=tail.next);
		
		tail.next = nodeArr[0];
		for(int i=0; i<y-1; i++) nodeArr[i].next = nodeArr[i+1];
	}
	
	static String print() {
		StringBuilder sb = new StringBuilder();
		int count = 0;

		for(Node cur=head.next; cur != null; cur=cur.next) {
			sb.append(cur.value).append(' ');
			count++;
			if(count == 10) break;
		}

		return sb.toString();
	}
}
