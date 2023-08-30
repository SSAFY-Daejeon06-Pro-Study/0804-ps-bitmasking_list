import java.io.*;
import java.util.*;

/*
 * [문제 요약]
 * 암호문 뭉치가 있고, 이를 수정할 명령어가 주어질 때 만들어지는 결과물
 * 
 * [문제 설명]
 * 수정할 명령어가 세 개 주어짐
 * 	1. I(삽입) x, y, s : 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입. s는 덧붙일 암호문
 * 	2. D(삭제) x, y : 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제
 * 	3. A(추가) y, s : 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙
 * 
 * 삽입, 삭제, 추가가 연결 리스트로 쉽게 만들 수 있기 때문에
 * 연결리스트를 구현해서 사용
 * 
 * 
 * */
public class Solution_1230_암호문3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;

		int t = 10;

		for(int testCase = 1; testCase <= t; testCase++) {
			LinkedListImpl list = new LinkedListImpl();

			int n = Integer.parseInt(br.readLine());
			stz = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++){
				int num = Integer.parseInt(stz.nextToken());
				list.addLast(num);
			}

			int orderNumber = Integer.parseInt(br.readLine());
			stz = new StringTokenizer(br.readLine());
			while (orderNumber-- > 0){
				char order = stz.nextToken().charAt(0);

				if(order == 'I'){
					int x = Integer.parseInt(stz.nextToken());
					int y = Integer.parseInt(stz.nextToken());

					for(int i=0; i<y; i++){
						int s = Integer.parseInt(stz.nextToken());
						list.add(s, x+i);
					}

				} else if(order == 'D'){
					int x = Integer.parseInt(stz.nextToken());
					int y = Integer.parseInt(stz.nextToken());
					while (y-- > 0){
						list.delete(x);
					}
					
				}else {
					int y = Integer.parseInt(stz.nextToken());
					while (y-- > 0){
						int s = Integer.parseInt(stz.nextToken());
						list.addLast(s);
					}
				}
			}

			System.out.printf("#%d %s"+System.lineSeparator(), testCase, list.searchTopTen());
		}

		br.close();
	}

	private static class LinkedListImpl{

		private static Node head;
		private static Node tail;
		private static int size;

		private Node search(int index){
			if(index < 0 || index >= size){
				throw new RuntimeException("잘못된 인덱스");
			}

			Node tmp = head;
			for(int i=0; i< index; i++) {
				tmp = tmp.next;
			}
			return tmp;
		}

		private String searchTopTen(){
			StringBuilder sb = new StringBuilder();
			Node tmp = head;

			for(int i=0; i<10; i++){
				sb.append(tmp.data).append(" ");
				tmp = tmp.next;
			}
			return sb.toString();
		}

		private void addFirst(int data){
			Node node = new Node(data, null);
			node.next = head;
			head = node;
			size++;

			if(head.next == null){
				tail = head;
			}
		}

		private void addLast(int data){
			if(size == 0){
				addFirst(data);
				return;
			}

			Node node = new Node(data, null);
			tail.next = node;
			tail = node;
			size++;
		}

		private void add(int data, int index){
			if(index == 0){
				addFirst(data);
				return;
			}

			Node tmp = search(index-1);
			Node node = new Node(data, tmp.next);
			tmp.next = node;
			size++;

			if(node.next == null){
				tail = node;
			}
		}

		private void delete(int index){
			Node tmp = search(index-1);
			tmp.next = tmp.next.next;
			size--;

			if(tmp.next == null){
				tail = tmp;
			}
		}

		private class Node{
			int data;
			Node next;

			public Node(int data, Node next) {
				this.data = data;
				this.next = next;
			}
		}
	}
}
