package code.parser;

import code.entity.OneConfiguration;
import code.tag.OneBeanElementTag;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
public final class OneBeanDefinitionParser extends AbstractBeanDefinitionParser {
    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(OneConfiguration.class);
        factory.addConstructorArgValue(element.getAttribute(OneBeanElementTag.PHONE_TAG));
        factory.addConstructorArgValue(element.getAttribute(OneBeanElementTag.NAME_TAG));
        factory.addConstructorArgValue(element.getAttribute(OneBeanElementTag.GIRLFRIENDS_TAG).split(","));
        return factory.getBeanDefinition();
    }
}
