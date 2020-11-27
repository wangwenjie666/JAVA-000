package code.lambda;

/**
 * lambda
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class LambdaDemo1 {
    public static void main(String[] args) {
        //1.不需要参数，返回值为5
        Cal cal = () -> 5;
        System.out.println(cal.add());

        //2.接收一个参数，返回值为 x *2
        Cal1 cal1 = x -> x * 2;
        System.out.println(cal1.mul(2));

        //3.接收两个参数，返回差值
        Cal2 c1 = (a, b) -> a - b;
        System.out.println(c1.sub(1, 2));

        //4.返回两数的和
        Cal2 c2 = (a, b) -> a + b;
        System.out.println(c2.sub(1, 2));

        //5.打印String 不返回任何值
        Print p = s -> System.out.println(s);
        p.print("abcd");

        Print p2 = System.out::print;
        p2.print("def");
    }

    interface Cal {
        int add();
    }

    interface Cal1 {
        int mul(int x);
    }

    interface Cal2 {
        int sub(int a, int b);
    }

    interface Print {
        void print(String str);
    }
}

