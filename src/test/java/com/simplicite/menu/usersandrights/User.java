package com.simplicite.menu.usersandrights;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.simplicite.menu.MainMenuProperties.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class User{

    public static void click() {
        domain.click();
    }

    public static String createUser(String name) {
        SelenideElement user = mainmenu.find("[data-obj=\"User\"]");
        user.click();
        mainmenu.find("[data-obj=\"User\"][data-state=\"\"]").click();
        work.find("button[data-action=\"create\"]").click();

        work.find("#field_usr_login").setValue(name);
        save();
        String alert = work.find("span.msg-label").text();
        String[] split = alert.split(": ");
        String password = split[1];
        work.find("button[data-action=\"UserStatusActivate\"]").click();
        $("button[data-action=\"YES\"]").click();
        $("button[data-action=\"YES\"]").click();
        return password;
    }


    /*public void associateGroup(Group... groups) {
        work.find("[data-action=\"associate-User-rsp_login_id-Group-rsp_group_id\"]").click();
        for (var group : groups) {
            $("#grp_name").setValue(group.getName());
            $("[data-list=\"list_Group_ref_ajax_Group\"]").find("[data-field=\"grp_name\"]").click();
        }
        $("button[data-action=\"multiselect\"]").click();
        SelenideElement buttonsaveclose = $("#dlgmodal_create_Responsability_link_ajax_Responsability")
                .find("button[data-action=\"saveclose\"]").shouldBe(Condition.visible);
        actions().moveToElement(buttonsaveclose).click(buttonsaveclose).perform();
    }*/
}
