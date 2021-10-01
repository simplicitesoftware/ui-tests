package com.simplicite.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.Action;
import com.simplicite.menu.administration.BusinessObject;
import com.simplicite.menu.administration.Link;
import com.simplicite.menu.administration.Module;
import com.simplicite.menu.usersandrights.Function;
import com.simplicite.menu.usersandrights.User;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.simplicite.utils.DataStore.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyTestWatcher.class)
@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpliciteTutorialTest {
    static Properties properties = new Properties();

    @BeforeAll
    public static void setUpAll() {
        try {
            var in = new FileReader("src/test/resources/config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = properties.getProperty("browsersize");
        Configuration.browser = properties.getProperty("browser");
        Configuration.headless = properties.getProperty("headless").equals("true");
        Configuration.savePageSource = false;
        Configuration.pageLoadTimeout = 60000;
    }

    @BeforeEach
    public void setUp() {
        open(properties.getProperty("url"));
        if ($("#auth-main").exists()) {
            Authentication.connect(properties.getProperty("name"), properties.getProperty("password"));
        }
    }

    @AfterEach
    public void close() {
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');
    }

    @AfterAll
    public static void setDownAll() {
        try {
            var out = new FileWriter("src/test/resources/config.properties");
            properties.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(0)
    public void newSession() {
        String name = properties.getProperty("name");
        properties.setProperty("password", NEW_PASSWORD);

        Authentication.changePassword(NEW_PASSWORD);
        $(".logged-scope").click();
        $(".logged-scope").find("[data-home=\"Home\"]").click();
        $(".scope-icon > img[src*=\"code=VIEW_ADMIN\"]").shouldBe(Condition.exist, Duration.ofSeconds(6));
        assertTrue(Authentication.authentificationSucced(name));
    }

    @Test
    @Order(1)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createModule() {
        Module.click();
        Module.createModuleAssistant(TRAINING, "trn", SUPERADMIN, DOMAIN, "img/color/console");
        assertTrue(Module.isSuccess(TRAINING));
    }

    @Test
    @Order(2)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createBusinessObject() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(SUPPLIER, "trn_supplier", TRAINING, "sup", DOMAIN);
        assertTrue(BusinessObject.isSuccess(SUPPLIER));
    }

    @Test
    @Order(3)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void editTemplate() {
        BusinessObject.click();
        BusinessObject.find(SUPPLIER);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(SUPPLIERAREA1,"code", 3, true, true);
        BusinessObject.save();
    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createUser() {
        User.click();
        String password = User.createUser(properties.getProperty("firstusername"));

        properties.setProperty("firstuserpassword", password);
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('u');

        Authentication.connect(properties.getProperty("firstusername"), password);
        Authentication.changePassword(properties.getProperty("firstuserpassword"));
        assertTrue(Authentication.authentificationSucced("usertest"));
        Authentication.deconnection();
        Authentication.connect(properties.getProperty("name"), properties.getProperty("password"));
    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelSupplier() {
        BusinessObject.click();
        BusinessObject.find(SUPPLIER);
        BusinessObject.clickEditor();
        BusinessObject.addField(SUPPLIERAREA1,"nom", 3, false, false);
        BusinessObject.addField(SUPPLIERAREA1,"téléphone", 22, false, false);
        BusinessObject.addField(SUPPLIERAREA1,"logo", 20, false, false);
        BusinessObject.addField(SUPPLIERAREA1,"site", 10, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelProduct() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(PRODUCT, "trn_product", TRAINING, "prd", DOMAIN);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(PRODUCTAREA1,"référence", 3, true, true);
        BusinessObject.addField(PRODUCTAREA1,"prix", 2, true, false);
        BusinessObject.addField(PRODUCTAREA1,"stock", 1, true, false);
        BusinessObject.addField(PRODUCTAREA1,"nom", 3, false, false);
        BusinessObject.addField(PRODUCTAREA1,"description", 13, false, false);
        BusinessObject.addField(PRODUCTAREA1,"photo", 20, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelClient() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(CLIENT, "trn_client", TRAINING, "cli", DOMAIN);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(CLIENTAREA1,"nom", 3, true, true);
        BusinessObject.addField(CLIENTAREA1,"prénom", 3, true, true);
        BusinessObject.addField(CLIENTAREA1,"mail", 12, false, false);
        BusinessObject.addField(CLIENTAREA1,"téléphone", 22, false, false);
        BusinessObject.addField(CLIENTAREA1,"adresse", 25, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelOrder() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(ORDER, "trn_order", TRAINING, "ord", DOMAIN);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(ORDERAREA1,"numéro", 3, true, true);
        BusinessObject.addField(ORDERAREA1,"quantité", 1, true, false);
        BusinessObject.addField(ORDERAREA1,"date", 4, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(6)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createLink(){
        Link.addLink(ORDER, CLIENT);
        Link.addLink(ORDER, PRODUCT);
        Link.addLink(PRODUCT, SUPPLIER, "TrnSupNom");
    }

    @Test
    @Order(7)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createArea(){
        BusinessObject.click();
        BusinessObject.find(ORDER);
        BusinessObject.clickEditor();
        BusinessObject.addRow();
        BusinessObject.addArea(ORDERAREA2);
        BusinessObject.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "trnCliNom");
        BusinessObject.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "trnCliPrnom");
        BusinessObject.addArea(ORDERAREA3);
        BusinessObject.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdNom");
        BusinessObject.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdStock");
    }

    @Test
    @Order(8)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createAction(){
        Action.click();
        Action.createAction(INCREASESTOCK, TRAINING,"javascript:alert(\"To be implemented...\")");
        Action.addFunction(PRODUCT, "TRN_PRD_INCREASE_STOCK_A");
        Function.associateGroup(TRAINING, INCREASESTOCK, SUPERADMIN);
    }
}
