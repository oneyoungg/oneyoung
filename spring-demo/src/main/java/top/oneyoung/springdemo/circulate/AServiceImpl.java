package top.oneyoung.springdemo.circulate;

/**
 * OrderServiceImpl
 *
 * @author oneyoung
 * @since 2023/8/30 14:28
 */

public class AServiceImpl implements AService {

    public AServiceImpl() {
        System.out.println("AServiceImpl create");
    }

    private BService bService;

    public BService getBService() {
        return bService;
    }

    public void setBService(BService bService) {
        System.out.println("AServiceImpl set bService");
        this.bService = bService;
    }
}
