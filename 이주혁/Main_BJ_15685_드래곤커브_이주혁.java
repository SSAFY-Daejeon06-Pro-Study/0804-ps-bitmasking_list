package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤커브2 {
    private static int[][] map;
    private static int[] dx = {1, 0, -1, 0}; // 동, 북, 서, 남
    private static int[] dy = {0, -1, 0, 1}; // 동, 북, 서, 남

    private static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        // 드래곤 커브를 그리는 방향을 계산하여 리스트에 저장
        for (int i = 0; i < g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                int newDirection = (directions.get(j) + 1) % 4;
                directions.add(newDirection);
            }
        }

        // 드래곤 커브를 그림
        map[y][x] = 1; // 시작점
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            drawDragonCurve(x, y, d, g);
        }

        
        int cnt = 0;
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                if (map[r][c] == 1 && map[r + 1][c] == 1 && map[r][c + 1] == 1 && map[r + 1][c + 1] == 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
