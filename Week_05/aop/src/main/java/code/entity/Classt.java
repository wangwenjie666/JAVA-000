package code.entity;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.List;

/**
 * class
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Data
public class Classt {
    private List<Student> studentList;


}
