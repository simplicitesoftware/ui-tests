package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.menu.administration.Module;
import com.simplicite.menu.administration.*;
import com.simplicite.menu.domaininterface.FieldStyle;
import com.simplicite.menu.domaininterface.PivotTable;
import com.simplicite.menu.domaininterface.Theme;
import com.simplicite.menu.domaininterface.Views;
import com.simplicite.menu.templateeditor.TemplateEditorBO;
import com.simplicite.menu.templateeditor.TemplateEditorView;
import com.simplicite.menu.usersandrights.Function;
import com.simplicite.menu.usersandrights.User;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.optionmenu.ModuleActive;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
        //Configuration.startMaximized = true;
        Configuration.browser = PROPERTIES.getProperty("browser");
        Configuration.headless = PROPERTIES.getProperty("headless").equals("true");
        Configuration.savePageSource = false;
        Configuration.pageLoadTimeout = Integer.parseInt(PROPERTIES.getProperty("pageLoadTimeout"));
        Configuration.timeout = Integer.parseInt(PROPERTIES.getProperty("timeout"));
        Configuration.pollingInterval = Integer.parseInt(PROPERTIES.getProperty("pollingInterval"));
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
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(SUPPLIERAREA1, "code", "trnSupCode", 3, true, true);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createUser() {

        Authentication.changeState("Home", "VIEW_ADMIN");
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

        Authentication.changeState("ViewDesign", "design");
    }

    @Test
    @Order(4)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelSupplier() {
        BusinessObject.click();
        BusinessObject.find(SUPPLIER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addField(SUPPLIERAREA1, "nom", "trnSupNom", 3, false, false);
        TemplateEditorBO.addField(SUPPLIERAREA1, "téléphone", "trnSupTelephone", 22, false, false);
        TemplateEditorBO.addField(SUPPLIERAREA1, "logo", "trnSupLogo", 20, false, false);
        TemplateEditorBO.addField(SUPPLIERAREA1, "site", "trnSupSite", 10, false, false);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelProduct() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(PRODUCT, "trn_product", TRAINING, "prd", DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(PRODUCTAREA1, "référence", "trnPrdReference", 3, true, true);
        TemplateEditorBO.addField(PRODUCTAREA1, "prix", "trnPrdPrix", 2, true, false);
        TemplateEditorBO.addField(PRODUCTAREA1, "stock", "trnPrdStock", 1, true, false);
        TemplateEditorBO.addField(PRODUCTAREA1, "nom", "trnPrdNom", 3, false, false);
        TemplateEditorBO.addField(PRODUCTAREA1, "description", "trnPrdDescription", 13, false, false);
        TemplateEditorBO.addField(PRODUCTAREA1, "photo", "trnPrdPhoto", 20, false, false);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelClient() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(CLIENT, "trn_client", TRAINING, "cli", DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(CLIENTAREA1, "nom", "trnCliNom", 3, true, true);
        TemplateEditorBO.addField(CLIENTAREA1, "prénom", "trnCliPrenom", 3, true, true);
        TemplateEditorBO.addField(CLIENTAREA1, "mail", "trnCliMail", 12, false, false);
        TemplateEditorBO.addField(CLIENTAREA1, "téléphone", "trnCliTelephone", 22, false, false);
        TemplateEditorBO.addField(CLIENTAREA1, "adresse", "trnCliAdresse", 25, false, false);
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(5)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void enrichModelOrder() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(ORDER, "trn_order", TRAINING, "ord", DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(ORDERAREA1, "numéro", "trnOrdNumero", 3, true, true);
        TemplateEditorBO.addField(ORDERAREA1, "quantité", "trnOrdQuantite", 1, true, false);
        TemplateEditorBO.addField(ORDERAREA1, "date", "trnOrdDate", 4, false, false);
        TemplateEditorBO.saveEditor();
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
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addRow();
        TemplateEditorBO.addArea();
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "trnCliNom");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA2, "unusedjoin0", "trnCliPrenom");
        TemplateEditorBO.addArea();
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdNom");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdReference");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdStock");
        TemplateEditorBO.saveEditor();
    }

    @Test
    @Order(8)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createAction() {
        Action.click();
        Action.createAction(INCREASESTOCK, TRAINING, "javascript:alert(\"To be implemented...\")");
        Action.addFunction(PRODUCT, "TRN_PRD_INCREASE_STOCK_A");
        Function.associateGroup(SUPERADMIN);
    }

    @Test
    @Order(9)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void changeTemplateHtml() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.setEditorTemplate(ORDERTEMPLATEHTML);
        TemplateEditorBO.closeEditor();
    }

    @Test
    @Order(10)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createDiagram() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addField(ORDERAREA1, "State", FIELDORDERSTATE, 7, true, false);
        TemplateEditorBO.saveEditor();

        TemplateEditorBO.modifyField(FIELDORDERSTATE);
        TemplateEditorBO.editEnum(LISTORDERSTATE);

        TemplateEditorBO.saveEditor();
        TemplateEditorBO.closeEditor();

        TemplateEditorBO.addStateModel(LISTACCESSORDERSTATE, SUPERADMIN, LISTTRADORDERSTATE);
    }

    @Test
    @Order(11)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createHistoric() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.accessToOption();
        MainMenuProperties.save();

        SystemProperties.click();
        SystemProperties.find("LOG_ACTIVITY");
        assertTrue(SystemProperties.verifyValue());

        Authentication.changeState("Home", "VIEW_ADMIN");
        ModuleActive.click();
        ModuleActive.showAll();
        Function.click();
        Function.find(REDO);
        Function.setModuleName(TRAINING);
        MainMenuProperties.save();

        Function.associateGroup(SUPERADMIN);
        MainMenuProperties.save();

        ModuleActive.click();
        ModuleActive.select("Training");
        Authentication.changeState("ViewDesign", "design");
    }

    @Test
    @Order(12)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createPivotTable() {
        BusinessObject.click();
        BusinessObject.find(ORDER);
        TemplateEditorBO.clickEditor();
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdSupId");
        TemplateEditorBO.addFieldUnusedJoin(ORDERAREA3, "unusedjoin1", "trnPrdSupId__trnSupNom");
        TemplateEditorBO.saveEditor();

        PivotTable.click();
        PivotTable.createPivotTable(PIVOTTABLE, ORDER, TRAINING);
        PivotTable.createPivotAxis(ORDER, "trnSupNom", 'C', 10);
        PivotTable.createPivotAxis(ORDER, "trnPrdNom", 'C', 20);
        PivotTable.createPivotAxis(ORDER, FIELDORDERSTATE, 'L', 30);
    }

    @Test
    @Order(13)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createView() {
        Views.click();
        Views.createView(HOME, TRAINING, 10);
        TemplateEditorView.navigateToEditor();
        TemplateEditorView.modifyArea("1:=");
        TemplateEditorView.setCrossTable(PIVOTTABLE, ORDER);

        TemplateEditorView.addField();
        TemplateEditorView.setProcessSearch(HOME, ORDER);
        TemplateEditorView.modifyArea("2");
        TemplateEditorView.setProcessSearchFilter(FIELDORDERSTATE, "PROCESSING");

        TemplateEditorView.saveEditor();

        Domain.click();
        Domain.find(DOMAIN);
        Domain.setHomePage(HOME);
    }

    @Test
    @Order(14)
    @EnabledIf("com.simplicite.test.MyTestWatcher#isFailedtest")
    public void createTheme() {
        Theme.click();
        Theme.createTheme(THEME);

        Views.click();
        Views.find(HOME);
        Views.setTheme(THEME);

        FieldStyle.click();
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, PROCESSING, "orangbg");
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, CANCELED, "greybg");
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, VALIDATED, "greenbg");
        FieldStyle.createFieldStyle(ORDER, FIELDORDERSTATE, SENT, "bluebg");

        ListOfValue.click();
        ListOfValue.find(FIELDORDERSTATE);
        ListOfValue.modifyListCodeIcon(PROCESSING, "icon/color/btn_orange");
        ListOfValue.modifyListCodeIcon(CANCELED, "icon/color/btn_grey");
        ListOfValue.modifyListCodeIcon(VALIDATED, "icon/color/btn_green");
        ListOfValue.modifyListCodeIcon(SENT, "icon/color/btn_blue");
    }
    @Disabled
    @Test
    public void createObject() {
        BusinessObject.click();
        BusinessObject.createObjectAssistant(ORDERTEST, ORDERTABLETEST, TRAINING, "ord", DOMAIN);
        TemplateEditorBO.navigateToEditor();
        TemplateEditorBO.addField(ORDERAREA1TEST, "State", FIELDORDERSTATETEST, 7, true, false);
        TemplateEditorBO.saveEditor();
        TemplateEditorBO.modifyField(FIELDORDERSTATETEST);
        TemplateEditorBO.editEnum(LISTORDERSTATE);
        TemplateEditorBO.saveEditor();
        TemplateEditorBO.closeEditor();
        TemplateEditorBO.addStateModel(LISTACCESSORDERSTATE, SUPERADMIN, LISTTRADORDERSTATETEST);
    }
}
