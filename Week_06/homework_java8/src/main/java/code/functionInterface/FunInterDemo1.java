package code.functionInterface;

import javafx.util.Builder;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口demo
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class FunInterDemo1 {
    public static void main(String[] args) {
        //1.Predicate 断言，有参数 无返回值
        Predicate<String> predicate = str -> "".equals(str);
        System.out.println(predicate.test("a"));

        //2.Function 函数 ，一个参数 有返回值
        Function<Integer, Double> function = x -> Math.pow(x, 2);
        System.out.println(function.apply(3));

        //3.Consumer 消费 有参数 ，无返回值
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("HelloWorld");

        //4.Supplier 无参数有返回值 get()
        Supplier<String> supplier = () -> "java8 in action";
        System.out.println(supplier.get());

        Map<String, Object> mapInstance = getMapInstance(HashMap::new);
        mapInstance.put("name", "aaa");

        MapInstance<Map<String,Object>> instance = HashMap::new;
        Map<String, Object> map = instance.getMap();

    }

    static Map<String, Object> getMapInstance(Supplier<Map<String, Object>> mapSupplier) {
        return mapSupplier.get();
    }

}

