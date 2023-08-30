package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 *
 * N개의 10억 이하 자연수로 이뤄진 수열이 주어진다.
 * 이 수열은 M번의 편집을 거쳐 완성된다.
 * 
 * 단방향 링크드 리스트를 구현해서
 * 입력되는 명령어에 맞게 작동
 * 
 * 명령어 I A B : A번 Index 앞에 B를 추가하고 한칸씩 뒤로 이동한다.
 * 명령어 D A : A번 Index 자리를 지우고 한칸 씩 앞으로 이동한다.
 * 명령어 C A B : A번 Index 자리의 값을 B로 바꾼다.
 * 
 * 편집이 끝난 후 인덱스 L이 존재하지 않으면 -1을 출력한다.
 * 존재하면 L의 데이터를 출력.
 * 
 * 
 * @author SSAFY
 *
 */
public class 수열편집 {
	
	static class Node {
		
		static int size;
		
		int num;
		Node next;
		
		
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
			size++;
		}
		
		private Node add(int index, int num) {
			if(index == 0) {
				Node temp = new Node(num, this);
				return temp;
			}
			
			Node node = this;
			
			for(int i=0; i < index-1; i++) node = node.next;

			Node nextNode = node.next;
			
			Node temp = new Node(num, nextNode);

			node.next = temp;
			
			return this;
			
		}
		
		private void delete(int index) {
			Node node = this;
			
			for(int i=0; i< index-1; i++) node = node.next;
			
			Node deleteNode = node.next;
			
			node.next = deleteNode.next;
			
			size--;
			
		}
		
		private void change(int index, int num) {
			Node node = this;
			for(int i=0; i< index; i++) node = node.next;
			node.num = num;
			
		}
		
		private int get(int index) {
			
			Node node = this;
			
			for(int i=0; i< index; i++) node = node.next;
			
			
			return node.num;
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); 
			int M = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			Node node = null;
			node.size = 0;
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				if(node == null) {
					node = new Node(Integer.parseInt(st.nextToken()), null);
				} else {
					node = node.add(i, Integer.parseInt(st.nextToken()));
				}
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				
				String cmd = st.nextToken();
				switch (cmd) {
				case "I":
					node = node.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					break;
				case "C":
					node.change(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));			
					break;
				case "D":
					node.delete(Integer.parseInt(st.nextToken()));
					break;
				}
			}
			
			if(node.size <= L) {
				sb.append('#').append(t).append(' ').append(-1).append('\n');
			} else {
				sb.append('#').append(t).append(' ').append(node.get(L)).append('\n');
			}
			
		}
		System.out.println(sb);
			
		
	}
	
}