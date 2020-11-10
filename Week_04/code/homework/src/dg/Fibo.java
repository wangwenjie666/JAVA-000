package dg;

/**
 * 菲波那切数列，使用优化后的
 *  递归使用参考 Week_04/code/lesson1/src/code/DiGui.java
 *
 * @author wangwenjie
 * @date 2020-11-09
 */
public class Fibo {

    public static int sum() {
        return fibo3(36);
    }

    //执行0ms
    public static int fibo3(int num) {
        int _idx1 = 1;
        int _idx2 = 1;
        int res = 0;
        for (int i = 2; i < num; i++) {
            res = _idx1 + _idx2;
            _idx1 = _idx2;
            _idx2 = res;
        }
        return res;
    }
}
