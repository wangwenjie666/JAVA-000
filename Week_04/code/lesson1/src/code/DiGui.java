package code;

/**
 * 递归：菲波那切数列优化
 * 优化点：使用变量保存结果
 *
 * @author wangwenjie
 * @date 2020-11-07
 */
public class DiGui {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        System.out.println();
        int result = fibo3(36);
        System.out.println("执行结果：" + result);//14930352

        System.out.println("时间：" + (System.currentTimeMillis() - start));//38
    }

    //执行41ms
    public static int fibo(int num) {
        if (num == 1 || num == 2)
            return 1;
        return fibo(num - 1) + fibo(num - 2);
    }

    //执行1ms
    public static int fibo2(int num) {
        //int [36] int[0] =1 int[1] =1
        int[] res = new int[36];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < num; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[res.length - 1];
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
