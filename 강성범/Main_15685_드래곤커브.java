import java.io.*;
import java.util.*;

/*
* [문제 요약]
* 네 개의 꼭짓점이 모두 드래곤 커브인 '1*1 정사각형'의 개수
*
* [문제 설명]
* 드래곤 커브는 시작 점, 시작 방향, 세대가 주어짐
* 입력에서 주어지는 x, y축 반대인 것을 고려해야 함
*
* [방향]
* 0 : →
* 1 : ↑
* 2 : ←
* 3 : ↓
* 차례로 우, 상, 좌, 하
*
* [드래곤 커브의 방향성]
* 전 세대의 시계 방향으로 90도 회전해서 끝점에 붙임
* 그림을 그리고 나서 알 수 있는점
*   1. 다음 세대의 길이는 이전 세대의 길이만큼 증가됨
*       - 3세대의 길이는 2세대의 길이 4에서 4만큼 추가되어서 8이 됨
*   2. 증가되는 길이의 방향은 이전 세대의 '(역순 방향 +1)%4'임
*
* [네 개의 꼭짓점이 모두 정사각형인 것이 무엇인가?]
* 1*1 크기의 정사각형을 찾아야됨
* 좌측 상단에서 시작해서 우, 하, 우하가 true인 것을 찾음
*
* [제약 사항]
* 격자는 100*100 고정
* 드래곤 커브가 격자 밖으로 나가는 일은 없음
* 드래곤 커브는 서로 겹칠 수 있음
* 드래곤 커브의 개수(N)은 20 이하
*
* */
public class Main_15685_드래곤커브 {

    private static final int[] DX = {0, -1, 0, 1};
    private static final int[] DY = {1, 0, -1, 0};

    private static final int MAX = 102;
    private static boolean[][] map = new boolean[MAX][MAX];

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        n = Integer.parseInt(br.readLine());

        while(n-- > 0){
            stz = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stz.nextToken());
            int x = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());
            int g = Integer.parseInt(stz.nextToken());

            List<Integer> dirOrder = new ArrayList<>();
            dirOrder.add(d);

            map[x][y] = true;

            int preEndX = x + DX[d];
            int preEndY = y + DY[d];
            map[preEndX][preEndY] = true;

            for(int i = 1; i <= g; i++){ // 세대 증가
                int preSize = dirOrder.size();
                
                for(int j = preSize-1; j>=0; j--){ // 방향 역순
                    int nextDir = (dirOrder.get(j) + 1) % DX.length;

                    preEndX += DX[nextDir];
                    preEndY += DY[nextDir];

                    map[preEndX][preEndY] = true;

                    // 이미 저장된 사이즈(preSize)에서 반복하기 때문에 추가해도 상관 없음
                    dirOrder.add(nextDir);
                }

            }
        }

        bw.write(String.valueOf(squareCount()));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int squareCount(){
        int count = 0;
        for(int i=0; i<MAX-1; i++){
            for (int j = 0; j < MAX-1; j++) {
                if(map[i][j] && map[i+1][j]  && map[i][j+1] && map[i+1][j+1]){
                    count++;
                }
            }
        }
        return count;
    }
}