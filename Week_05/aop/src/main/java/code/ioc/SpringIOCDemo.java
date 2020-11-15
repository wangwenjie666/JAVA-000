package code.ioc;

import code.entity.Classt;
import code.entity.Student;
import code.service.StuService;
import code.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ioc容器注入spring对象
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class SpringIOCDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //根据id获取容器中的bean
        Student student = (Student) context.getBean("student");
        log.info("student = {}", student);

        //获取list
        Classt list = (Classt) context.getBean("studentList");
        log.info("list = {}", list);

        //aop 调用student.getStudent方法 执行额外操作
        StuService stuService = context.getBean(StuService.class);
        log.info("stuService class = {}", stuService.getClass());

        StuService studentService = (StuService) context.getBean("studentService");
        log.info("service class = {}", studentService.getClass());
//        studentService.getStudent();

        /*实现Ordered接口配置切面优先级*/
        /*
            [main] INFO code.aop.Aop1_1 - 前置通知执行
            [main] INFO code.aop.Aop1 - 环绕通知执行 begin
            [main] INFO code.service.StudentService - 执行目标方法 --- student id = 1,name = 张麻子
            [main] INFO code.aop.Aop1 - 环绕通知执行 end
            [main] INFO code.aop.Aop1 - 返回后通知执行
            [main] INFO code.aop.Aop1_1 - 后置通知执行
        * */
        StudentService service = context.getBean("studentService",StudentService.class);
        service.annotationFn();

//        log.info("studentService class = {},instanceOf StudentService = {}",
//                studentService.getClass(), studentService instanceof StudentService); //StudentService$$EnhancerBySpringCGLIB$$4c803fc8

//        StuService stuService = context.getBean(StuService.class);
//        log.info("stuService class = {}", stuService.getClass());

    }
}
