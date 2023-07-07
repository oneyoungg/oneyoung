package top.oneyoung.start.test;

import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.AttributeAccessor;

import java.util.Map;

/**
 * AttributeAccessorImpl
 *
 * @author oneyoung
 * @since 2023/5/14 12:51
 */
public class Attribute implements AttributeAccessor {

    private final Map<String, Object> attributes = new java.util.concurrent.ConcurrentHashMap<>(16);

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public Object removeAttribute(String name) {
        return attributes.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        return attributes.keySet().toArray(new String[0]);
    }

    public static void main(String[] args) {
        Attribute attribute = new Attribute();
        attribute.setAttribute("name", "1");
        System.out.println(attribute.getAttribute("name"));
        attribute.computeAttribute("1", v -> Long.parseLong(v) + 1);
        System.out.println(attribute.getAttribute("1"));
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    }
}
