package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_수열편집 {
	static Node head;// 노드의 시작점
    static int cnt; // 노드의 수
	
	static class Node {
        int num;
        Node prev;
        Node next;

        Node() {}

        Node(int v, Node prev, Node next) {
            this.num = v;
            this.prev = prev;
            this.next = next;
        }
    }
	
	private static void insert(int idx, int num) {
		 idx --;
         if (idx == -1) {//헤드일때
             if (cnt > 0) {
                 Node newNode = new Node(num, null, head);
                 head.prev = newNode;//필요없지 않나?
                 head = newNode;
             } else {
                 head = new Node(num, null, null);
             }
         } else {
             Node cur = head;
             while (idx-- > 0) {
                 cur = cur.next;
             }
             Node node = new Node(num, cur, cur.next);
             if (cur.next != null) {
                 cur.next.prev = node;
             }
             cur.next = node;
         }
         cnt++;
	}
	
	private static void change(int idx, int num) {
		Node cur = head;
        for (int i = 0; i <idx; i++) {
            cur = cur.next;
        }
        cur.num = num;

	}
	
	private static void delete(int idx) {
		Node cur = head;
		
        while (idx-- > 0) {
            cur = cur.next;
        }
        
        Node prev = cur.prev;
        Node next = cur.next;
        
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        if (prev == null) {
            head = next;
        }
        cnt--;

	}
	
	private static int find(int idx) {
		Node cur = head;
		for (int i = 0; i < idx; i++) {
            if (cur == null) {
                break;
            }
            cur = cur.next;
        }
        if (cur == null) {
            return -1;
        } else {
            return cur.num;
        }
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			Node current = head;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(i == 0) {
					head = new Node(num, null, null);
					current = head;
				}else {
					current.next = new Node(num, current, null);
					current = current.next;
				}
				cnt++;
			}
			for(int i = 0; i<M; i++) {
				st =new StringTokenizer(br.readLine());
				String order = st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int num = 0;
				if(!order.equals("D")) num = Integer.parseInt(st.nextToken());
				
				if(order.equals("C")) change(idx, num);
				else if(order.equals("I")) insert(idx, num);
				else delete(idx);
				
			}
			
			
			System.out.printf("#%d %d%n", tc, find(L));
		}
	}
	


}
