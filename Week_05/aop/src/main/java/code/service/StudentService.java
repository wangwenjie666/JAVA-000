package code.service;

import code.entity.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * service
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Slf4j
public class StudentService implements StuService {

    @Resource(name = "student")
    private Student student;

    public void getStudent(){
        log.info("执行目标方法 --- student id = {},name = {}", student.getId(), student.getName());
    }

    public void annotationFn(){
        log.info("执行目标方法 --- student id = {},name = {}", student.getId(), student.getName());
    }

    @Override
    public void m() {
        System.out.println("mmmm");
    }
}
