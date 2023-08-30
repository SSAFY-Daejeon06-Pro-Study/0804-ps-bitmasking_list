import java.io.*;
import java.util.*;

/*
 * [문제 요약]
 * 수열을 편집하시오
 * 
 * [문제 설명]
 * 문제의 그림은 배열이지만, 내용으로는 연결 리스트이기 때문에 연결리스트 구현
 * 
 * */
public class Solution_13501_수열편집 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;

		int t = Integer.parseInt(br.readLine());

		for(int testCase = 1; testCase <= t; testCase++) {
			int answer = 0;
			LinkedListImpl list = new LinkedListImpl();

			stz = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(stz.nextToken());
			int m = Integer.parseInt(stz.nextToken());
			int l = Integer.parseInt(stz.nextToken());

			stz = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++){
				list.addLast(Integer.parseInt(stz.nextToken()));
			}

			for(int i=0; i<m; i++){
				stz = new StringTokenizer(br.readLine());

				char order = stz.nextToken().charAt(0);

				if(order == 'I'){
					int index = Integer.parseInt(stz.nextToken());
					int data = Integer.parseInt(stz.nextToken());

					list.add(data, index);
				}else if(order == 'D'){
					int index = Integer.parseInt(stz.nextToken());
					list.delete(index);
				}else{
					int index = Integer.parseInt(stz.nextToken());
					int data = Integer.parseInt(stz.nextToken());

					list.change(data, index);
				}
			}

			LinkedListImpl.Node node = list.search(l);

			if(node == null){
				answer = -1;
			}else{
				answer = node.data;
			}

			System.out.printf("#%d %d"+System.lineSeparator(), testCase, answer);
		}

		br.close();
	}

	private static class LinkedListImpl{

		private Node head;
		private Node tail;
		private int size;

		Node search(int index){
			Node tmp = head;
			for(int i=0; i<index; i++){
				if(tmp != null) {
					tmp = tmp.next;
				}else{
					return null;
				}
			}
			return tmp;
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
		}

		private void delete(int index){
			Node tmp1 = search(index-1);
			Node tmp2 = tmp1.next;

			tmp1.next = tmp2.next;
			tmp2.next = null;
			size--;
		}

		private void change(int data, int index){
			Node tmp = search(index);
			tmp.data = data;
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
