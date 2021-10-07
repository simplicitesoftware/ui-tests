package com.simplicite.utils;

import java.util.Properties;

public class DataStore {

    public static Properties PROPERTIES = new Properties();

    public static final String ORDER = "TrnOrder";
    public static final String CLIENT = "TrnClient";
    public static final String PRODUCT = "TrnProduct";
    public static final String SUPPLIER = "TrnSupplier";
    public static final String TRAINING = "Training";
    public static final String DOMAIN = "TrnDomain";
    public static final String SUPPLIERAREA1 = "TrnSupplier-1";
    public static final String PRODUCTAREA1 = "TrnProduct-1";
    public static final String CLIENTAREA1 = "TrnClient-1";
    public static final String ORDERAREA1 = "TrnOrder-1";
    public static final String ORDERAREA2 = "TrnOrder-2";
    public static final String ORDERAREA3 = "TrnOrder-3";
    public static final String NEW_PASSWORD = "designer1903";
    public static final String SUPERADMIN = "SUPERADMIN";
    public static final String INCREASESTOCK = "IncreaseStock";
    public static final String FIELDORDERSTATE = "trnOrdState";

    public static final String[] LISTORDERSTATE = {"Processing", "Canceled", "Validated", "Sent"};
    public static final String ORDERTEMPLATEHTML = "<div class=\"row\">\n"+
            "    <div class=\"col-sm-6\">\n"+
            "      <div class=\"area\" data-area=\"1:=\"></div>\n"+
            "    </div>\n"+
            "    <div class=\"col-sm-6\">\n"+
            "      <div class=\"area\" data-area=\"2\"></div>\n"+
            "      <div class=\"area\" data-area=\"3\"></div>\n"+
            "    </div>\n"+
            "  </div>";
}
