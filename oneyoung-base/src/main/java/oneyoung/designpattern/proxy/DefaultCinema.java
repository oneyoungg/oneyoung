package oneyoung.designpattern.proxy;

/**
 * DefaultFilmPlayer
 *
 * @author oneyoung
 * @since 2021/12/31 2:38 PM
 */
public class DefaultCinema implements Cinema {

    @Override
    public void play(String filmName) {
        System.out.println("现在正在播放电影：" + filmName);
    }
}
