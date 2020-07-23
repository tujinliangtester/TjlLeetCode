import jdk.nashorn.internal.runtime.regexp.joni.constants.MetaChar;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Class37 {
    char[] metaChar=new char[]{'1','2','3','4','5','6','7','8','9'};
    List<Character> metaList = new ArrayList() {
        {
            add('1');
            add('2');
            add('3');
            add('4');
            add('5');
            add('6');
            add('7');
            add('8');
            add('9');
        }
    };
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    List<Character> list = getMaybe(board, i, j);
                    if (list.size() == 0) {
                        return;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        char[][] newBoard = board.clone();
                        newBoard[i][j] = list.get(k);
                        solveSudoku(newBoard);
                    }
                }
            }
        }
    }

    public List<Character> getMaybe(char[][] board, int i, int j) {
        List<Character> list = new ArrayList(Arrays.asList(new Character[metaList.size()]));
        Collections.copy(list, metaList);
        for (int k = 0; k < 9; k++) {
            if (board[i][k] != '.') {
                list.remove((Object)board[i][k]);
            }
            if (board[k][j] != '.') {
                list.remove((Object)board[i][k]);

            }
            if (k / 3 == i / 3) {
                for (int l = (j / 3) * 3; l < (j / 3) * 3 + 3; l++) {
                    if (board[k][l] != '.') {
                        list.remove((Object)board[i][k]);
                    }
                }
            }
        }
        return list;
    }
}
