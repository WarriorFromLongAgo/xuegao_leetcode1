package com.xuegao.数据结构与算法.剑指.shudu;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public void solveSudoku(char[][] board) {
        // 预处理
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    line[i][val - 1] = true;
                    column[j][val - 1] = true;
                    box[i / 3 * 3 + j / 3][val - 1] = true;
                } else {
                    space.add(new Point(i, j));
                }
            }
        }
        // 深度搜索
        shuduDfs(board, 0);
    }

    private static boolean[][] line = new boolean[9][9];
    private static boolean[][] column = new boolean[9][9];
    private static boolean[][] box = new boolean[9][9];
    private static List<Point> space = new ArrayList();
    static boolean isValid = false;

    static class Point {

        private int x;

        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static void shuduDfs(char[][] board, int num) {
        if (num == space.size()) {
            isValid = true;
            return;
        }
        Point point = space.get(num);
        int x = point.getX();
        int y = point.getY();
        for (int val = 1; val <= 9 && !isValid; val++) {
            if (!line[x][val - 1] && !column[y][val - 1] && !box[x / 3 * 3 + y / 3][val - 1]) {
                line[x][val - 1] = column[y][val - 1] = box[x / 3 * 3 + y / 3][val - 1] = true;
                board[x][y] = (char) ('0' + val);
                shuduDfs(board, num + 1);
                line[x][val - 1] = column[y][val - 1] = box[x / 3 * 3 + y / 3][val - 1] = false;
            }
        }
    }
}

// 作者：podongfeng
// 链接：https://juejin.cn/post/7000751617280737311
// 来源：掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。