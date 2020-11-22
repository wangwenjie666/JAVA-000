package code.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream流
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class StreamDemo {
    public static void main(String[] args) {
        //流的创建
        //1
        Stream<Integer> stream1 = Stream.of(1, 2, 3);

        int[] arr = new int[]{1, 2, 3, 5};
        Stream<int[]> stream2 = Stream.of(arr);

        //2
        IntStream stream3 = Arrays.stream(arr);

        //3
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.stream();

        //中间操作
        //1.选择 过滤
        Stream<Integer> stream = list.stream();
        List<Integer> collect = stream.filter(r -> r == 2)//过滤符合条件的数据，
                .distinct()//去重
                .limit(10)//限制个数
                .skip(0)//跳过
                .collect(Collectors.toList());
        System.out.println(collect); //[2]

        //2.映射
        List<Double> collect1 = list.stream()
                .map(x -> Math.pow(x, 2))
                .sorted() //3.排序
                .collect(Collectors.toList());
        System.out.println(collect1);

        //4.终止操作
        long count = list.stream()
                .filter(r -> r < 5)
                .count();
        System.out.println(count);

        BinaryOperator<Integer> operator = (a, b) -> a + b;
        //reduce
        Integer reduce = list.stream()
                .reduce(0, operator);
        System.out.println(reduce);
    }
}
