import java.util.ArrayList;
import java.util.List;

public class Class63 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int i = 0, j = 0;
        int[] tmp = {0};
        List<List<String>> lists=new ArrayList<>();
        List<String> stringList=new ArrayList<>();
        myUni(obstacleGrid, i, j, tmp,lists,stringList);
        //todo 怎么获取到具体的路径
        lists.forEach(
                l->System.out.println(l.toString())
        );
        return tmp[0];

    }

    private static void myUni(int[][] obstacleGrid, int i, int j, int[] tmp, List<List<String>> lists, List<String> stringList) {

        if (i < obstacleGrid.length - 1 && obstacleGrid[i][j] == 0) {
            int tmpI=i;
            tmpI++;
            stringList.add("i="+tmpI+",j="+j);
            myUni(obstacleGrid, tmpI, j, tmp, lists, stringList);
        }
        if (j < obstacleGrid[0].length - 1 && obstacleGrid[i][j] == 0) {
            int tmpJ=j;
            tmpJ++;
            stringList.add("i="+i+",j="+tmpJ);
            myUni(obstacleGrid, i, tmpJ, tmp, lists, stringList);
        }
        if (i == obstacleGrid.length - 1 && j == obstacleGrid[0].length - 1) {
            stringList.add("i="+i+",j="+j);
            lists.add(stringList);
            stringList=new ArrayList<>();
            tmp[0]++;
        }
    }


}
