package oneyoung.designpattern.proxy;

/**
 * CinemaProxy
 *
 * @author oneyoung
 * @since 2021/12/31 2:40 PM
 */
public class CinemaProxy implements Cinema {

    private final Cinema cinema;

    public CinemaProxy(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void play(String filmName) {
        CinemaProxyTask.playStartAd();
        cinema.play(filmName);
        CinemaProxyTask.playEndAd();
    }

}
