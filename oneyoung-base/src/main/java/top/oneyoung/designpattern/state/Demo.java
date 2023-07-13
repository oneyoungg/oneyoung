package top.oneyoung.designpattern.state;

/**
 * Demo
 *
 * @author oneyoung
 * @since 2023/7/13 16:05
 */
public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }
}
