package code.lombok;

import lombok.*;

/**
 * lombok
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
//@Builder
@NoArgsConstructor
@Setter
//@RequiredArgsConstructor
//@AllArgsConstructor
//@Data
//    @Setter
//    @Getter
public class LombokDemo  {
    /*
        @Data 提供无参构造，get set equals hashcode tostring canEqual
            LombokDemo
            getName
            setName
            equals
            canEqual
            hashCode
            toString
        @Setter
        @Getter
     */
    private String name;

    public static void main(String[] args) {
        LombokDemo lombokDemo = new LombokDemo();
        lombokDemo.setName("hello");
        System.out.println(lombokDemo.toString());//LombokDemo(name=hello)
        System.out.println(lombokDemo.equals(new LombokDemo()));
    }

}

