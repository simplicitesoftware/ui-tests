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
    public static String NEW_PASSWORD;
    public static boolean FIRST_AUTH = true;
    public static final String SUPERADMIN = "SUPERADMIN";
    public static final String INCREASESTOCK = "IncreaseStock";
    public static final String FIELDORDERSTATE = "trnOrdState";
    public static final String REDO = "RedoLog-R";
    public static final String HOME = "TrnHome";
    public static final String PIVOTTABLE = "TrnTcOrders";
    public static final String OLD_PASSWORD = "simplicite";
    private static final int val = 18;
    public static final String ORDERTEST = "TrnOrder" + val;
    public static final String ORDERAREA1TEST = "TrnOrder" + val + "-1";
    public static final String FIELDORDERSTATETEST = "trnOrdState" + val;
    public static final String ORDERTABLETEST = "trn_order" + val;
    public static final String[][] LISTTRADORDERSTATETEST = {
            {"TRNORDSTATE" + val + "-CANCELED-PROCESSING", "Switch to Processing"},
            {"TRNORDSTATE" + val + "-CANCELED-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE" + val + "-PROCESSING-CANCELED", "Cancel"},
            {"TRNORDSTATE" + val + "-PROCESSING-VALIDATED", "Validate"},
            {"TRNORDSTATE" + val + "-SENT-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE" + val + "-VALIDATED-CANCELED", "Cancel"},
            {"TRNORDSTATE" + val + "-VALIDATED-PROCESSING", "Back to Processing"},
            {"TRNORDSTATE" + val + "-VALIDATED-SENT", "Send"}
    };
    public static final String PROCESSING = "PROCESSING";
    public static final String CANCELED = "CANCELED";
    public static final String VALIDATED = "VALIDATED";
    public static final String SENT = "SENT";
    public static final String THEME = "TrnTheme";

    public static final int[][] LISTACCESSORDERSTATE = {{0, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}};
    public static final String[] LISTORDERSTATE = {"Processing", "Canceled", "Validated", "Sent"};
    public static final String[][] LISTTRADORDERSTATE = {
            {"TRNORDSTATE-CANCELED-PROCESSING", "Switch to Processing"},
            {"TRNORDSTATE-CANCELED-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE-PROCESSING-CANCELED", "Cancel"},
            {"TRNORDSTATE-PROCESSING-VALIDATED", "Validate"},
            {"TRNORDSTATE-SENT-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE-VALIDATED-CANCELED", "Cancel"},
            {"TRNORDSTATE-VALIDATED-PROCESSING", "Back to Processing"},
            {"TRNORDSTATE-VALIDATED-SENT", "Send"}
    };

    public static final String ORDERTEMPLATEHTML = """
            <div class="row">
                <div class="col-sm-6">
                  <div class="area" data-area="1:="></div>
                </div>
                <div class="col-sm-6">
                  <div class="area" data-area="2"></div>
                  <div class="area" data-area="3"></div>
                </div>
              </div>""";

    public static String USERNAME;

    public static void initUser() {
        NEW_PASSWORD = PROPERTIES.getProperty("password");
        USERNAME = PROPERTIES.getProperty("name");
    }
}
