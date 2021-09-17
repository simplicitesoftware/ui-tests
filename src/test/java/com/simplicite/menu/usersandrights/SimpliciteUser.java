package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simplicite.menu.MainMenuProperties;
import com.simplicite.optionmenu.Cache;

import static com.codeborne.selenide.Selenide.$;

public class SimpliciteUser implements MainMenuProperties {
    SelenideElement domain = mainmenu.find("[data-domain=\"DomainGrant\"]");
    private String name;
    private String pswd;

    public SimpliciteUser(String name)
    {
        this.name = name;
    }

    @Override
    public void click() {
        domain.click();
    }

    @Override
    public void create() {
        click();
        SelenideElement user = mainmenu.find("[data-obj=\"User\"]");
        user.click();
        mainmenu.find("[data-obj=\"User\"][data-state=\"\"]").click();
        work.find("button[data-action=\"create\"]").click();
        work.find("#field_usr_login").setValue(name);
        save();
        String alert = work.find("span.msg-label").text();
        String[] split = alert.split(": ");
        pswd = split[1];
        work.find("button[data-action=\"UserStatusActivate\"]").click();
        $("button[data-action=\"YES\"]").click();
        $("button[data-action=\"YES\"]").click();
    }

    @Override
    public void find() {
    }

    public void associateGroup(SimpliciteGroup...groups)
    {
        work.find("[data-action=\"associate-User-rsp_login_id-Group-rsp_group_id\"]").click();
        for ( var group: groups) {
            $("#grp_name").setValue(group.getName());
            $("[data-list=\"list_Group_ref_ajax_Group\"]").find("[data-field=\"grp_name\"]").click();
        }
        $("button[data-action=\"multiselect\"]").click();
        $("#dlgmodal_create_Responsability_link_ajax_Responsability")
                .find("button[data-action=\"saveclose\"]").shouldBe(Condition.visible).hover().click();
    }

    public String getPassword() {
        return pswd;
    }

    public String getName()
    {
        return name;
    }
}
