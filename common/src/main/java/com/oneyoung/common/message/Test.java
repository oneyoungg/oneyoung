package com.oneyoung.common.message;

import java.io.Serializable;

/**
 * Test
 *
 * @author oneyoung
 * @date 2021/5/19 11:55
 */
public class Test implements Serializable {


    private static final long serialVersionUID = -3228493013722168647L;

    public static void main(String[] args) {
        ErrorMessage errorMessage = ErrorMessage.defaultLocalOf("TEST_EXCEPTION");
        System.out.println(errorMessage.getMessage());
    }
}
