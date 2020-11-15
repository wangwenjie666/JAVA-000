package code.handler;

import code.parser.OneBeanDefinitionParser;
import code.tag.OneBeanElementTag;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * handler
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
public final class OneBeanHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser(OneBeanElementTag.ROOT_TAG, new OneBeanDefinitionParser());
    }
}
