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

import static com.codeborne.selenide.Selenide.*;
import static com.simplicite.utils.DataStore.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyTestWatcher.class)
@ExtendWith({ScreenShooterExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.DisplayName.class)
public class SimpliciteTutorial1Test {

    @BeforeAll
    public static void setUpAll() {
        try {
            var in = new FileReader("src/test/resources/config.properties");
            PROPERTIES.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserSize = PROPERTIES.getProperty("browsersize");
        Configuration.browser = PROPERTIES.getProperty("browser");
        Configuration.headless = PROPERTIES.getProperty("headless").equals("true");
        Configuration.savePageSource = false;
        Configuration.pageLoadTimeout = Integer.parseInt(PROPERTIES.getProperty("pageLoadTimeout"));
        Configuration.timeout = Integer.parseInt(PROPERTIES.getProperty("timeout"));
    }

    @AfterAll
    public static void setDownAll() {
        try {
            var out = new FileWriter("src/test/resources/config.properties");
            PROPERTIES.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        open(PROPERTIES.getProperty("url"));
        if ($("#auth-main").exists()) {
            Authentication.connect(PROPERTIES.getProperty("name"), PROPERTIES.getProperty("password"));
        }
    }

    @AfterEach
    public void close() {
        sleep(1000);
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('c');
    }

    @Test
    @Order(0)
    public void newSession() {
        String name = PROPERTIES.getProperty("name");
        PROPERTIES.setProperty("password", NEW_PASSWORD);

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
        BusinessObject.addField(SUPPLIERAREA1, "code", "trnSupCode", 3, true, true);
        BusinessObject.save();
    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createUser() {
        User.click();
        String password = User.createUser(PROPERTIES.getProperty("firstusername"));
        User.associateGroup(SUPERADMIN);

        PROPERTIES.setProperty("firstuserpassword", password);
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click('c');

        Authentication.connect(PROPERTIES.getProperty("firstusername"), password);
        Authentication.changePassword(PROPERTIES.getProperty("firstuserpassword"));
        assertTrue(Authentication.authentificationSucced("usertest"));
        Authentication.deconnection();
        Authentication.connect(PROPERTIES.getProperty("name"), PROPERTIES.getProperty("password"));
    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelSupplier() {
        BusinessObject.click();
        BusinessObject.find(SUPPLIER);
        BusinessObject.clickEditor();
        BusinessObject.addField(SUPPLIERAREA1, "nom", "trnSupNom", 3, false, false);
        BusinessObject.addField(SUPPLIERAREA1, "téléphone", "trnSupTelephone", 22, false, false);
        BusinessObject.addField(SUPPLIERAREA1, "logo", "trnSupLogo", 20, false, false);
        BusinessObject.addField(SUPPLIERAREA1, "site", "trnSupSite", 10, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelProduct() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(PRODUCT, "trn_product", TRAINING, "prd", DOMAIN);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(PRODUCTAREA1, "référence", "trnPrdReference", 3, true, true);
        BusinessObject.addField(PRODUCTAREA1, "prix", "trnPrdPrix", 2, true, false);
        BusinessObject.addField(PRODUCTAREA1, "stock", "trnPrdStock", 1, true, false);
        BusinessObject.addField(PRODUCTAREA1, "nom", "trnPrdNom", 3, false, false);
        BusinessObject.addField(PRODUCTAREA1, "description", "trnPrdDescription", 13, false, false);
        BusinessObject.addField(PRODUCTAREA1, "photo", "trnPrdPhoto", 20, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelClient() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(CLIENT, "trn_client", TRAINING, "cli", DOMAIN);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(CLIENTAREA1, "nom", "trnCliNom", 3, true, true);
        BusinessObject.addField(CLIENTAREA1, "prénom", "trnCliPrenom", 3, true, true);
        BusinessObject.addField(CLIENTAREA1, "mail", "trnCliMail", 12, false, false);
        BusinessObject.addField(CLIENTAREA1, "téléphone", "trnCliTelephone", 22, false, false);
        BusinessObject.addField(CLIENTAREA1, "adresse", "trnCliAdresse", 25, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelOrder() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(ORDER, "trn_order", TRAINING, "ord", DOMAIN);
        BusinessObject.navigateToEditor();
        BusinessObject.addField(ORDERAREA1, "numéro", "trnOrdNumero", 3, true, true);
        BusinessObject.addField(ORDERAREA1, "quantité", "trnOrdQuantite", 1, true, false);
        BusinessObject.addField(ORDERAREA1, "date", "trnOrdDate", 4, false, false);
        BusinessObject.save();
    }

    @Test
    @Order(6)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createLink() {
        Link.addLink(ORDER, CLIENT);
        Link.addLink(ORDER, PRODUCT);
        Link.addLink(PRODUCT, SUPPLIER, "TrnSupNom");
    }

    @Test
    @Order(7)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createArea() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        BusinessObject.clickEditor();
        BusinessObject.addRow();
        BusinessObject.addArea(ORDERAREA2);
        BusinessObject.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "trnCliNom");
        BusinessObject.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "trnCliPrenom");
        BusinessObject.addArea(ORDERAREA3);
        BusinessObject.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdStock");
        BusinessObject.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdReference");
        BusinessObject.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdNom");
        BusinessObject.save();
    }

    @Test
    @Order(8)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createAction() {
        Action.click();
        Action.createAction(INCREASESTOCK, TRAINING, "javascript:alert(\"To be implemented...\")");
        Action.addFunction(PRODUCT, "TRN_PRD_INCREASE_STOCK_A");
        Function.associateGroup(TRAINING, INCREASESTOCK, SUPERADMIN);
    }

    @Test
    @Order(9)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void changeTemplateHtml() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        BusinessObject.clickEditor();
        BusinessObject.setEditorTemplate(ORDERTEMPLATEHTML);
        BusinessObject.closeEditor();
    }
    @Test
    @Order(10)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createDiagram(){
        BusinessObject.click();
        BusinessObject.find(ORDER);
        BusinessObject.clickEditor();
        BusinessObject.addField(ORDERAREA1, "State", FIELDORDERSTATE, 7, true, false);
        BusinessObject.modifyField(FIELDORDERSTATE);
        BusinessObject.editEnum(LISTORDERSTATE);
        BusinessObject.save();
        BusinessObject.closeEditor();
        BusinessObject.addStateModel(LISTACCESSORDERSTATE, SUPERADMIN, LISTTRADORDERSTATE);
    }

    @Disabled
    @Test
    public void createObject(){
        BusinessObject.click();
        BusinessObject.createObjectAssistant(ORDERTEST, ORDERTABLETEST, TRAINING, "ord", DOMAIN);
        BusinessObject.navigateToEditor();
       BusinessObject.addField(ORDERAREA1TEST, "State", FIELDORDERSTATETEST, 7, true, false);
        BusinessObject.save();
        BusinessObject.modifyField(FIELDORDERSTATETEST);
        BusinessObject.editEnum(LISTORDERSTATE);
        BusinessObject.save();
        BusinessObject.closeEditor();
        BusinessObject.addStateModel(LISTACCESSORDERSTATE, SUPERADMIN, LISTTRADORDERSTATETEST);
    }
}
