package top.oneyoung.designpattern.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * MainTest
 *
 * @author oneyoung
 * @since 2021/12/31 2:43 PM
 */
public class MainTest {

    public static void main(String[] args) {
        String filmName = "中国医生";
        staticProxy(filmName);
        System.out.println("=================");
        dynamicJdkProxy(filmName);
        System.out.println("=================");
        dynamicCgLibProxy(filmName);
    }

    public static void staticProxy(String filmName) {
        Cinema defaultCinema = new DefaultCinema();
        Cinema proxyCinema = new CinemaProxy(defaultCinema);
        proxyCinema.play(filmName);
    }

    public static void dynamicJdkProxy(String filmName) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        DefaultCinema defaultCinema = new DefaultCinema();
        JdkCinemaProxy jdkCinemaProxy = new JdkCinemaProxy(defaultCinema);
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Cinema.class}, jdkCinemaProxy);
        Cinema cinema = (Cinema) o;
        cinema.play(filmName);
    }

    public static void dynamicCgLibProxy(String filmName) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./generate");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DefaultCinema.class);
        enhancer.setCallback(new CglibCinemaProxy());
        DefaultCinema defaultCinema = (DefaultCinema) enhancer.create();
        defaultCinema.play(filmName);
        System.out.println(defaultCinema.getClass());
    }


}