package top.oneyoung.base.thread;

/**
 * User
 *
 * @author oneyoung
 * @since 2023-08-24 22:53
 */

public class User {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("user finalize");
    }
}
