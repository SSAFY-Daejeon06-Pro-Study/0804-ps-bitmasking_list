import java.io.*;
import java.util.*;


public class 암호문 {

    static class Node {
        String password;

        public Node(String password) {
            this.password = password;
        }
    }

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            ArrayList<Node> nodeList = new ArrayList<>();

            // Node 정보 입력
            for (int i = 0; i < N; i++) {
                nodeList.add(new Node(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {

                String cmd = st.nextToken();

                switch (cmd) {

                    case "I":
                        int x = Integer.parseInt(st.nextToken());
                        int y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            nodeList.add(x, new Node(st.nextToken()));
                            x++;
                        }
                        break;

                    case "D":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            nodeList.remove(x);
                        }
                        break;

                    case "A":
                        y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            nodeList.add(new Node(st.nextToken()));
                        }
                        break;
                }
            }

            sb.append('#').append(t).append(' ');
            // 상위 10개 노드 출력
            for (int i = 0; i < Math.min(10, nodeList.size()); i++) {
                sb.append(nodeList.get(i).password).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
