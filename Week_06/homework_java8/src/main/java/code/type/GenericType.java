package code.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型
 *  获取泛型的Class 创建类实例
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class GenericType {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Dao dao = new Dao();

        Class<? extends Dao> clazz = dao.getClass();
        System.out.println(clazz);//class code.type.GenericType$Dao
        //父类
        System.out.println(clazz.getSuperclass());//class code.type.GenericType$GenericDao

        Type genericSuperclass = clazz.getGenericSuperclass();
        //泛型class
        System.out.println(genericSuperclass);//code.type.GenericType$GenericDao<code.type.GenericType$Person>

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        System.out.println(parameterizedType);//code.type.GenericType$GenericDao<code.type.GenericType$Person>

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class classt = (Class) actualTypeArguments[0];
        System.out.println(classt);//class code.type.GenericType$Person

        //根据泛型获取类创建类的实例
        Object _person = classt.newInstance();
        if(_person instanceof Person){
            Person p = (Person) _person;
            System.out.println(p.name);
        }
    }

    public static class GenericDao<T> {

    }

    public static class Dao extends GenericDao<Person> {

    }

    public static class Person {
        private String name = "zs";
    }
}
