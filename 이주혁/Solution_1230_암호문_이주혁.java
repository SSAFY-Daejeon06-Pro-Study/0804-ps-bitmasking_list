package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 목표) 
 * 
 * 양방향 링크드 리스트를 구현해보자
 * 
 * 고찰)
 * 
 * 왜 LinkedList인가? 
 * 
 * 이 문제는 리스트의 특정 원소에 접근해서 삽입, 삭제 연산을 진행하지만 그 원소로 무언가 연산을 하는 문제가 아니다.
 * 즉, LinkedList의 단점 중 하나인 데이터 접근 시간에 대한 성능이 높지 않아도 되는 문제.
 * 
 * (LinkedList는 접근 뿐만 아니라 삽입, 삭제를 할 때도 해당 인덱스까지 도달하는 시간이 걸리므로 O(N)이긴 하다.)
 * 
 * 리스트의 임의의 위치에서 삽입, 삭제가 빈번하게 일어난다는 점에서 배열보다 LinekdList가 적합하다.
 * 심지어 원소의 삽입, 삭제가 될때마다 데이터를 이동시키고 새로운 배열을 생성하는 Array, List와 달리
 * LinkedList를 사용하면 데이터 이동이 필요가 없다.
 * 
 * 따라서 순서에 따라 나열되어있는 자료들이 있고, 임의의 위치에 원소를 삽입하거나, 삭제하는 경우 LinkedList 사용을 고려해볼만하다.
 * 
 * LinkedList의 장점)
 * - 자료의 삽입/삭제 용이
 * - 리스트 내 자료 이동이 불필요
 * - 사용 후 기억 장소의 재사용 가능
 * 
 * LinkedList의 단점)
 * - 포인터의 사용으로 인해 저장 공간 낭비 (포인터는 단지 주소값을 담고있는데 얼마나 낭비되는거지?)
 * - 특정 자료의 탐색 시간이 많이 소요된다.
 * 
 * 
 * 궁금한점)
 * ArrayList를 사용해서 풀면 성능이 어떻게 될까?
 * => ArrayList가 메모리, 속도 측면에서 빠름..
 *	
 * ArrayList : 31,284 kb, 145 ms
 * LinkedList : 32,196 kb, 154 ms
 * 
 */
public class 암호문 {
	
	static class Node {
		static int size;
		static Node head;
		static Node tail;
		String password;
		Node prev;
		Node next;
		
		public Node(String password, Node prev, Node next) {
			this.password = password;
			this.prev = prev;
			this.next = next;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1; t<=10; t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
				
			Node node = new Node(st.nextToken(), null, null);
			node.head = node;
			
			// Node 정보 입력
			for(int i=1; i<N; i++) {
				Node temp = new Node(st.nextToken(), null, null);
				node.next = temp;
				temp.prev = node;
				
				node = temp;
			}
			
			// 꼬리 && size
			Node.tail = node;
			Node.size = N;
			
			int M = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<M; i++) {
				node = node.head;

				String cmd = st.nextToken();
				
				switch(cmd) {
				
				case "I":
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					
					
					// x번째 node 구하기.
					for(int j=1; j<x; j++) node = node.next;
					
					// 짬통 노드
					Node nextNode = null;
					
					// 맨 앞에 삽입하는 경우 (Head 교체)
					if(x == 0) {
						
						// 앞에서 y개를 추가 한 노드에 현재 노드를 붙인다.
						nextNode = node;
						
						// 새 head 생성
						Node newHead = new Node(st.nextToken(), null, null);
						Node.head = newHead;
						
						node = newHead;
						
						// 새 head에 y개 노드 붙임
						for(int j=1; j<y; j++) {
							
							Node temp = new Node(st.nextToken(), node, null);
							node.next = temp;
							node = temp;
							
						}
						
						//y개 붙인 후 짬통노드 붙임
						node.next = nextNode;
						nextNode.prev = node;
						
					}
					
					// 맨 뒤에 삽입하는 경우
					else if(x == Node.size) {
						
						// 맨 뒤에서부터 붙인다.
						node = Node.tail;

						for(int j=0; j<y; j++) {
							
							Node temp = new Node(st.nextToken(), node, null);
							node.next = temp;
							node = temp;
							
						}
						
						// tail 정보 갱신
						node.tail = node;
						
					}
					else {
						
						// 중간에 삽입하는 경우
						nextNode = node.next;

						for(int j=0; j<y; j++) {
							
							Node temp = new Node(st.nextToken(), node, null);
							node.next = temp;
							node = temp;
							
						}
						
						node.next = nextNode;
						nextNode.prev = node;
					}
					
					
					
					Node.size += y;
					
					break;
					
				case "D":
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					
					// x번째 node 구하기.
					for(int j=1; j<=x; j++) node = node.next;
					
					nextNode = null;
					
					// 맨 앞부터 삭제하는 경우
					if(x == 0) {

						// 맨 앞을 포함해 y개의 삭제할 Node 건너뛰기
						for(int j=1; j<=y; j++) {
							node.prev = null;
							node = node.next;
						}
						
						node.prev = null;
						node.head = node;
						
					}
					
					// 삭제 범위에 꼬리가 포함되는 경우
					else if(x + y == Node.size) {
						//x번째의 next를 null로 만든다.
						node.next = null;
						
						//x번째가 꼬리가 된다.
						Node.tail = node;
					}
					else {
						// x 번째 노드 저장
						Node temp = node;
						
						// x 번째 노드의 next를
						// x번째부터 y개 삭제 한뒤의 노드의 prev에 연결한다.
						for(int j=0; j<=y; j++) node = node.next;
						
						temp.next = node;
						node.prev = temp;

					}
					
					break;
				
				
				case "A":
					
					// 꼬리에서부터 이어붙인다. 
					node = Node.tail;
					
					y = Integer.parseInt(st.nextToken());
					
					for(int j=0; j<y; j++) {
						Node temp = new Node(st.nextToken(), node, null);
						node.next = temp;
						node= temp;
					}
					
					Node.tail = node;
					
					break;
				
				}
			}
			
			
			sb.append('#').append(t).append(' ');
			node = Node.head;
			// 상위 10개 노드 출력
			for(int i=0; i<10; i++) {
				sb.append(node.password).append(' ');
				node = node.next;
			}
			sb.append('\n');
			
		}
		
		System.out.println(sb);
		
		
	}
	
}
