import java.util.*;

public class Main {
    static int[][] exitDistance(char[][] plan) {
        int rows = plan.length, cols = plan[0].length;
        int[][] dist = new int[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                } else {
                    dist[r][c] = -1; // wall or not yet reached
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d], nc = cur[1] + dc[d];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && plan[nr][nc] == '.' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };
        for (int[] row : exitDistance(plan)) {
            System.out.println(Arrays.toString(row));
        }
        // [2, -1, 1, 0]
        // [1, 2, 2, -1]
        // [0, -1, 3, 4]
    }
}
