package com.softuni.exodia.utils;

import java.math.BigDecimal;

public final class Constants {

    public static final String DEFAULT_DECIMAL_ZERO_DEFINITION = "DECIMAL(10, 2) DEFAULT '0.00'";
    public static final BigDecimal BIG_DECIMAL_HUNDRED = new BigDecimal("100");
    public static final String SRC_ABSOLUTE_PATH = System.getProperty("user.dir");
    public static final String SESSION_USERNAME_ATTRIBUTE = "username";
    public static final String SESSION_USER_ID_ATTRIBUTE = "user-id";

}
