package code.lambda;

/**
 * 泛型
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class LambdaDemo2 {
    public static void main(String[] args) {
        MathOpt<Integer> add = (a, b) -> a + b;
        MathOpt<Integer> sub = (a, b) -> a - b;
        MathOpt<Integer> mul = (a, b) -> a * b;
        MathOpt<Integer> div = (a, b) -> a / b;
        MathOpt<Double> db = (a, b) -> Math.pow(a, b);

        System.out.println(operate(1, 2, add));
        System.out.println(operate(5, 3, sub));
        System.out.println(operate(1, 2, mul));
        System.out.println(operate(4, 2, div));
        System.out.println(operate(2, 2, db));
    }

    interface MathOpt<T> {
        T operation(int a, int b);
    }

    public static <T> T operate(int a, int b, MathOpt<T> mathOpt) {
        return mathOpt.operation(a, b);
    }
}
