package com.simplicite.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.simplicite.account.Authentication;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.administration.SimpliciteModuleAssitant;
import com.simplicite.menu.usersandrights.SimpliciteGroup;
import com.simplicite.optionmenu.Cache;
import com.simplicite.optionmenu.DropDownMenu;
import com.simplicite.utils.Icon;
import com.simplicite.utils.Traduction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith({ScreenShooterExtension.class})
public class SimpliciteTest {
    String url = "http://localhost";
    int port = 80;
    SimpliciteModuleAssitant moduleAssitant = new SimpliciteModuleAssitant("trainv2", "trv");
    SimpliciteGroup group = new SimpliciteGroup("TRV_SUPERADMIN", moduleAssitant);
    SimpliciteDomain domain = new SimpliciteDomain("TrvDomain", Icon.CONSOLE, moduleAssitant);

    @BeforeAll
    public static void setUpAll() {

        Configuration.browserSize = "1280x800";
        Configuration.browser = FIREFOX;
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        open(url + ":" + port);
    }

    @Test
    public void authentication() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldNot(exist, Duration.ofSeconds(2));
        auth.authentificationSucced();
        //assertEquals(url + "/ui/", url());
    }

    @Test
    public void nonAuthentication() {
        Authentication auth = new Authentication("error", "error");
        auth.Connect();
        SelenideElement element = $(".auth-signin-error");
        element.shouldBe(exist);
        assertFalse(auth.authentificationSucced());
        assertNotEquals(url + "/ui/", url());
    }

    @Test
    public void createModule() {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        moduleAssitant.click();
        moduleAssitant.createModule();
        moduleAssitant.createGroup(group);
        moduleAssitant.createDomain(domain, Traduction.FORMATION, Traduction.TRAINING);
        moduleAssitant.addGroupToDomain(group);
        moduleAssitant.addIconToDomain(domain.getIcon());
    }

    @Test
    public void deleteModule(){
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        moduleAssitant.click();
        moduleAssitant.delete();
    }
    @Test
    public void clearCache()
    {
        Authentication auth = new Authentication("designer", "designer1903");
        auth.Connect();
        DropDownMenu drop = new DropDownMenu();
        drop.click(4);
        Cache.click(auth, 'u');
        auth.authentificationSucced();
    }

}
