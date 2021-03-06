package top.oneyoung.common.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oneyoung
 */
@Getter
@Setter
@ToString
public class Header implements Serializable {
    private static final long serialVersionUID = 7843391182330161826L;
    /**
     * ARMS traceId
     */
    private String traceId;
    /**
     * ARMS rpcId
     */
    private String rpcId;
    /**
     * 返回值时间
     */
    private Date date;
}
