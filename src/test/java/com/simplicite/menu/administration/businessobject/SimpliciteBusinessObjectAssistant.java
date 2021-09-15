package com.simplicite.menu.administration.businessobject;

import com.codeborne.selenide.Condition;
import com.simplicite.menu.administration.SimpliciteDomain;
import com.simplicite.menu.administration.module.SimpliciteModule;
import com.simplicite.utils.Traduction;

public class SimpliciteBusinessObjectAssistant extends SimpliciteBusinessObjects {

    public SimpliciteBusinessObjectAssistant(String code, String table, SimpliciteModule module, String prefix) {
        super(code, table, module, prefix);
    }

    @Override
    public void save() {
        work.find("*[data-action=\"validate\"]").click();
    }

    public void createObject()
    {
        work.find("button[data-action=\"ObjectCreateProcess\"]").click();
        create();
        save();
    }

    public void makeTraduction(Traduction fr, Traduction en)
    {
        en.doTraduction(fr);
        save();
    }
    public void grantObject()
    {
        save();
    }

    public void addDomain(SimpliciteDomain domain)
    {
        work.find("#obd_name").setValue(domain.getName()).pressEnter();
        work.find("#obd_nohome").selectOptionByValue("true");
        work.find(".text-left").shouldHave(Condition.exactTextCaseSensitive(domain.getName())).click();
        save();
    }
}
