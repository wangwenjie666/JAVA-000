package code.entity;

import com.alibaba.fastjson.parser.JSONToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.ls.LSOutput;

/**
 * student
 *
 * @author wangwenjie
 * @date 2020-11-14
 */
@Data
public class Student {
    private Integer id;
    private String name;
}
