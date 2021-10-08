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

    private static final int val = 6;
    public static final String ORDERTEST = "TrnOrder" + val;
    public static final String ORDERAREA1TEST = "TrnOrder" + val + "-1";
    public static final String FIELDORDERSTATETEST = "trnOrdState" + val;
    public static final String ORDERTABLETEST = "trn_order1" + val;
    public static final String[][] LISTTRADORDERSTATETEST = {
            {"TRNORDSTATE"+ val+"-CANCELED-PROCESSING", "Switch to Processing"},
            {"TRNORDSTATE"+ val+"-CANCELED-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE"+ val+"-PROCESSING-CANCELED", "Back to Processing"},
            {"TRNORDSTATE"+ val+"-PROCESSING-VALIDATED", "Validate"},
            {"TRNORDSTATE"+ val+"-SENT-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE"+ val+"-VALIDATED-CANCELED", "Cancel"},
            {"TRNORDSTATE"+ val+"-VALIDATED-PROCESSING", "Back to Processing"},
            {"TRNORDSTATE"+ val+"-VALIDATED-SENT", "Send"}
    };
    public static final int[][] LISTACCESSORDERSTATE = {{0, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}};
    public static final String[] LISTORDERSTATE = {"Processing", "Canceled", "Validated", "Sent"};
    public static final String[][] LISTTRADORDERSTATE = {
            {"TRNORDSTATE-CANCELED-PROCESSING", "Switch to Processing"},
            {"TRNORDSTATE-CANCELED-VALIDATED", "Back to Validated"},
            {"TRNORDSTATE-PROCESSING-CANCELED", "Back to Processing"},
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
}
