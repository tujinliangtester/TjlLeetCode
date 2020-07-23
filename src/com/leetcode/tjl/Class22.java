import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Class22 {
    public static void main(String[] args) {
        System.out.println(new Date());
        List<String> stringList = generateParenthesis2(7);
        System.out.println(new Date());

        System.out.println(stringList);
        System.out.println(stringList.size());
    }

    //超时了。。。
    public static List<String> generateParenthesis2(int n) {
        //换一种笨办法算了，先把所有可能的组合弄出来，然后再逐一判定
        //其实内心还是不想这样操作，因为感觉思考的地方太少了，算法的价值不大

        //因为首和尾是定的，分别是 （ 和 ） ，总共的组合情况是 2^2*（n-1）

        List<List<Character>> characterList = new LinkedList<>();
        List<Character> list = new LinkedList<>();
        list.add('(');
        characterList.add(list);

        int tmpMax = 2 * n - 2;
        for (int i = 0; i < tmpMax; i++) {
            int j = 0;
            int sizeTmp = characterList.size();
            for (; j < sizeTmp; j++) {

                List<Character> listTmp = new LinkedList<>(characterList.get(j));
                listTmp.add('(');
                characterList.add(listTmp);

                listTmp = new LinkedList<>(characterList.get(j));
                listTmp.add(')');
                characterList.add(listTmp);
            }
            characterList = characterList.subList(j, characterList.size());
        }
        List<String> stringList = new ArrayList<>();
        characterList.stream()
                .filter(
                        cl -> {
                            return cl.stream()
                                    .filter(
                                            tmpC -> {
                                                return tmpC == '(';
                                            }
                                    )
                                    .count() == n;
                        }
                )
                .forEach(
                        cl -> {
                            cl.add(')');
                            stringList.add(cl.stream().map(Object::toString).collect(Collectors.joining()));
                        }
                );
        return myCheck(stringList);
    }

    private static List<String> myCheck(List<String> stringList) {
        return stringList.stream()
                .filter(
                        sl -> {
                            return isValid(sl);
                        }
                )
                .collect(Collectors.toList());
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (
                    s.charAt(i) == '(' ||
                            s.charAt(i) == '[' ||
                            s.charAt(i) == '{'
            ) {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
//            else if (s.charAt(i)==']'){
//                if (stack.empty()||stack.peek()!='['){
//                    return false;
//                }
//                stack.pop();
//            }
//            else if (s.charAt(i)=='}'){
//                if (stack.empty()||stack.peek()!='{'){
//                    return false;
//                }
//                stack.pop();
//            }

        }
        return stack.empty();
    }

    private static void genChildren(MyNode myNode) {
        myNode.childrenNode.forEach(
                node -> {
                    List<MyNode> myNodeListTmp = new ArrayList<>();
                    myNodeListTmp.add(new MyNode('('));
                    myNodeListTmp.add(new MyNode(')'));
                    node.childrenNode = myNodeListTmp;
                }
        );
    }

    static class MyNode {
        char c;
        List<MyNode> childrenNode;

        MyNode(char c) {
            this.c = c;
        }
    }


    public static List<String> generateParenthesis(int n) {
        //经过思考，其实这里就转换成了小学的加法组合题了，
        // 比如3=3+0=2+1=1+2 然后再对第二个因子分解  2=2+0=1+1  不断迭代，最终形成的组合就是所求的答案（当然可能会有去重的步骤，待验证）
        //或者说  直接用树结构来处理，是不是会更好？应该是更好的，因为跟模型的实际情况更接近 树+列表子元素？
        //测试时发现，出错了，因为少考虑了一种情况，就是一个括号内包含多个独立括号的情况。那如果是这样的话，用这种方式本身就不可取了，
        // 因为暂时找不到规律

        //分层处理，通过值相等来判定组合关系
        List<List<List<Integer>>> listLayer = new ArrayList<>();
        List<List<Integer>> listList = parseN(n);
        listLayer.add(listList);
        while (listList.size() > 0) {
            List<List<Integer>> tmpListList = new ArrayList<>();
            for (int i = 0; i < listList.size(); i++) {

                List<Integer> li = listList.get(i);
                List<List<Integer>> listList1 = parseN(li.get(li.size() - 1));
                if (listList1 != null) {
                    tmpListList = Stream.of(tmpListList, listList1).flatMap(Collection::stream).collect(Collectors.toList());
                }
            }
            listLayer.add(tmpListList);
            listList = tmpListList;
        }
        for (int i = listLayer.size() - 1; i > 0; i--) {
            List<List<Integer>> li = listLayer.get(i);
            List<List<Integer>> liPre = listLayer.get(i - 1);
            List<List<Integer>> liPreTmp = listLayer.get(i);
            List<Integer> indexList = new ArrayList<>();
            for (int k = 0; k < li.size(); k++) {
                List<Integer> l = li.get(k);
                int tmpN = l.stream().mapToInt(Integer::intValue).sum();
                for (int j = 0; j < liPre.size(); j++) {
                    //如果求和结果等于前一组中，某项的第二个值（可以保证之前的组中每项都是两个元素），那么就认为是其分解项
                    if (liPre.get(j).get(1) == tmpN) {
                        indexList.addAll(Collections.singleton(j));
                        //先拷贝，再拼接到列表中
                        List<Integer> tmpli = new ArrayList<>(liPre.get(j));
                        tmpli.remove(tmpli.size() - 1);
                        tmpli.addAll(l);
                        liPreTmp.addAll(Collections.singleton(tmpli));
                    }
                }
            }
            indexList.forEach(
                    ind -> {
                        liPre.remove(ind);
                    }
            );
            liPre.addAll(liPreTmp);
        }
        List<String> stringList = myConvert(listLayer.get(0), n);
        return stringList;
    }

    private static List<String> myConvert(List<List<Integer>> lists, int m) {
        List<String> stringList = new ArrayList<>();
        lists.stream().filter(
                li -> {
                    return li.stream().mapToInt(Integer::intValue).sum() == m;
                }
        )
                .forEach(
                        li -> {

                            StringBuilder sb = new StringBuilder();
                            li.forEach(
                                    n -> {
                                        for (int i = 0; i < n; i++) {
                                            sb.append('(');
                                        }
                                        for (int i = 0; i < n; i++) {
                                            sb.append(')');
                                        }
                                    }
                            );
                            stringList.add(sb.toString());
                        }
                );
        return stringList.stream().distinct().collect(Collectors.toList());
    }

    public List<List<Integer>> parseAndConcat(List<Integer> list) {
        List<List<Integer>> lists = new ArrayList<>();
        int n = list.get(list.size() - 1);
        if (n < 2) {
            lists.add(list);
        } else {
            List<List<Integer>> listList = parseN(n);
            listList.forEach(
                    li -> {
                        List<Integer> tmpList = new ArrayList<>();
                        for (int i = 0; i < list.size() - 1; i++) {
                            tmpList.add(list.get(i));
                        }
                        tmpList.add(li.get(0));
                        tmpList.add(li.get(1));
                        lists.add(tmpList);
                    }
            );
        }
        return lists;
    }

    /**
     * 解析n，将n分成二分，并返回二维列表
     *
     * @param n
     * @return
     */
    public static List<List<Integer>> parseN(int n) {
        if (n < 2) {
            return null;
        }
        List<List<Integer>> listList = new ArrayList<>();
        int m = n;
        while (m > 0) {
            List<Integer> integerList = new ArrayList<>();
            integerList.add(m);
            integerList.add(n - m);
            listList.add(integerList);
            m--;
        }
        return listList;
    }
}
