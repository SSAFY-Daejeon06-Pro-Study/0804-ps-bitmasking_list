import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 1:25
 * 풀이 완료 : 3:15
 * 풀이 시간 : 1시간 50분
 *
 * 문제 해석
 * 드래곤 커브의 속성
 * 1. 시작 점
 * 2. 시작 방향
 * 3. 세대
 * 0세대 드래곤 커브
 *  - 길이 1인 선분
 * 1세대 드래곤 커브
 *  - 0세대 드래곤 커브 끝 점을 기준으로 시계 방향으로 90도 회전 후 0세대 드래곤 커브의 끝 점에 붙임
 * K세대 드래곤 커브
 *  - K - 1세대 드래곤 커브의 끝 점을 기준으로 90도 시계 방향 회전시킨 후 그것을 끝 점에 붙인 것
 * 드래곤 커브의 방향
 * 0 : 우, 1 : 상, 2 : 좌, 3 : 하
 *
 * 구해야 하는 것
 * 드래곤 커브가 N개 주어질 때 1 * 1 크기의 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : 드래곤 커브의 개수 N
 * 둘째 줄 ~ N개 줄 : 드래곤 커브의 정보
 *  x좌표, y좌표 , d : 시작 방향, g : 세대
 *
 * 제한 요소
 * 1 <= 20
 * 0 <= x, y <= 100
 * 0 <= d <= 3
 * 0 <= g <= 10
 *
 * 생각나는 풀이
 * 어떻게 구현할 것인가
 * 링크드 리스트로 이전까지의 모습을 방향만 바꿔서 tail에다 붙이면 되지 않을까
 * i번째 세대의 방향 = dir[(0세대 방향 + i) % 4] --> 여기서 잘못됨
 *
 * boolean[101][101] 배열 만들어서
 * 0세대 노드부터 방향대로 이동하면서 시작 좌표, 끝 좌표를 true로 변경하면서 다음 세대 만들면 될 듯
 * --------------------------------
 * 완전 잘못 접근함
 * 마지막 점을 기준으로 돌린 걸로 보면 됨 -> 즉 기존 방향 그대로 가는게 아니라 역으로 좌표가 바뀜
 * 탐색을 마지막 점 => 처음 점 순으로 해야 함
 *
 * 구현해야 하는 기능
 * 1. Node 클래스
 *  - 멤버 : x, y좌표, d 방향, g 세대, Node next = 다음 노드
 * 2. 101 * 101 배열
 * 3. 드래곤 커브 만드는 메서드
 * 4. 델타 배열
 *
 */
public class BOJ_15685 {
    static boolean[][] map = new boolean[101][101]; // 선 그을 배열
    static ArrayList<Integer> dirList = new ArrayList<>(); // 드래곤커브의 방향 정보
    static int[] dx = {1, 0, -1, 0}; // 델타 배열
    static int[] dy = {0, -1, 0, 1}; // 델타 배열


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());

            makeDirection(dir, gen);
            makeDragon(x, y);
        }
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 4개의 꼭짓점이 모두 드래곤 커브의 일부여야 함
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void makeDirection(int dir, int gen) {
        dirList.clear();
        dirList.add(dir); // 초기 방향
        while (gen-- > 0) { // 세대만큼 반복
            for (int i = dirList.size() - 1; i >= 0; i--) { // 다음 세대는 가장 최근에 그려진 방향에 역순으로 90도 돌아간 모습임
                dirList.add((dirList.get(i) + 1) % 4);
            }
        }
    }

    private static void makeDragon(int x, int y) {
        map[x][y] = true; // 초기 위치부터 방문체크

        for (int dir : dirList) { // 방향대로
            x += dx[dir];
            y += dy[dir];
            map[x][y] = true; // 체크
        }
    }
}
