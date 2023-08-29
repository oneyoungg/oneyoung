package top.oneyoung.springdemo.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * ColorFactoryBean
 *
 * @author oneyoung
 * @since 2023/8/29 10:23
 */
public class ProductFactoryBean implements FactoryBean<Product> {

    @Override
    public Product getObject() throws Exception {
        Product product = new Product();
        product.setName("default");
        return product;
    }

    @Override
    public Class<?> getObjectType() {
        return Product.class;
    }
}
