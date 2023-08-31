package top.oneyoung.springdemo.circulate;

/**
 * ShopServiceImpl
 *
 * @author oneyoung
 * @since 2023/8/30 14:28
 */
public class BServiceImpl implements BService {

    public BServiceImpl() {
        System.out.println("BServiceImpl create");
    }

    private AService aService;

    public AService getAService() {
        return aService;
    }

    public void setAService(AService aService) {
        System.out.println("BServiceImpl set aService");
        this.aService = aService;
    }
}
