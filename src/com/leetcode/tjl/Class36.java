import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Class36 {
    public static void main(String[] args) {
        char[][] board = {{'.', '.', '4', '.', '.', '.', '6', '3', '.' }, {'.', '.', '.', '.', '.', '.', '.', '.', '.' }, {'5', '.', '.', '.', '.', '.', '.', '9', '.' }, {'.', '.', '.', '5', '6', '.', '.', '.', '.' }, {'4', '.', '3', '.', '.', '.', '.', '.', '1' }, {'.', '.', '.', '7', '.', '.', '.', '.', '.' }, {'.', '.', '.', '5', '.', '.', '.', '.', '.' }, {'.', '.', '.', '.', '.', '.', '.', '.', '.' }, {'.', '.', '.', '.', '.', '.', '.', '.', '.' }};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println(isValidSudoku2(board));
    }

    public static boolean isValidSudoku2(char[][] board) {
        Map<Integer, List<Character>> map = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            List<Character> listRow = new ArrayList<>();
            List<Character> listCol = new ArrayList<>();

            for (int j = 0; j < 9; j++) {
                char tmpN=board[i][j];
                if ( tmpN != '.') {
                    if (listRow.contains(tmpN)) {
                        return false;
                    }
                    listRow.add(tmpN);


                    int tmp = (i / 3 + 1) * 10 + j / 3 + 1;
                    map.computeIfAbsent(tmp, k -> new ArrayList<>());
                    if (map.get(tmp).contains(tmpN)) {
                        return false;
                    }
                    map.get(tmp).add(tmpN);
                }
                char tmpNc=board[j][i];

                if (tmpNc != '.') {
                    if (listCol.contains(tmpNc)) {
                        return false;
                    }
                    listCol.add(tmpNc);
                }
            }

        }
        return true;
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            List<Character> list = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (list.contains(board[i][j])) {
                        return false;
                    }
                    list.add(board[i][j]);
                }
            }
            if (!myCheck(list)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            List<Character> list = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    if (list.contains(board[j][i])) {
                        return false;
                    }
                    list.add(board[j][i]);
                }
            }
            if (!myCheck(list)) {
                return false;
            }
        }
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp = (i / 3 + 1) * 10 + j / 3 + 1;
                map.computeIfAbsent(tmp, k -> new ArrayList<>());
                if (board[i][j] != '.') {
                    if (map.get(tmp).contains(board[i][j])) {
                        return false;
                    }
                    map.get(tmp).add(board[i][j]);
                }
            }
        }
        for (List<Character> list : map.values()) {
            if (!myCheck(list)) {
                return false;
            }
        }
        return true;
    }

    private static boolean myCheck(List<Character> list) {
        Set<Character> s = new HashSet<Character>();
        AtomicBoolean flag = new AtomicBoolean(true);
        list.forEach(
                c -> {
                    if (s.contains(c)) {
                        flag.set(false);
                    }
                    s.add(c);
                }
        );
        return flag.get();
    }


}
