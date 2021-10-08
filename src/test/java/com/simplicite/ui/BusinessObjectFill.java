package com.simplicite.ui;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.simplicite.menu.MainMenuProperties.create;
import static com.simplicite.menu.MainMenuProperties.save;

public class BusinessObjectFill {

    public static void click(String domaine, String name) {
        var domainelement = $("[data-domain=\"" + domaine + "\"]");
        if (!domainelement.isEnabled())
            domainelement.click();
        $("[data-obj=\"" + name + "\"]").click();
    }

    public static void createSupplier(String site, String logo, String telephone, String nom, String code){
        create();
        $("#field_trnSupSite").setValue(site);
        $("#field_trnSupTelephone").setValue(telephone);
        $("#field_trnSupNom").setValue(nom);
        $("#field_trnSupCode").setValue(code);
        save();
    }

    public static void createProduct(String description, String nom, String stock, String prix, String reference, String trnsuppliercode){
        create();
        $("#field_trnPrdDescription").setValue(description);
        $("#field_trnPrdNom").setValue(nom);
        $("#field_trnPrdStock").setValue(stock);
        $("#field_trnPrdPrix").setValue(prix);
        $("#field_trnPrdReference").setValue(reference);
        $("#field_trnPrdSupId__trnSupCode").click();
        find("trnSupCode", trnsuppliercode);
        save();
    }

    public static void createClient(String adress, String telephone, String mail, String prenom, String nom){
        create();
        $("#field_trnCliAdresse").setValue(adress);
        $("#field_trnCliTelephone").setValue(telephone);
        $("#field_trnCliMail").setValue(mail);
        $("#field_trnCliPrenom").setValue(prenom);
        $("#field_trnCliNom").setValue(nom);
        save();
    }

    public static void createOrder(String trnprdref, String trncliprenom, String date, String quantite, String numero){
        create();
        $("#field_trnOrdPrdId__trnPrdNom").click();
        find("trnPrdReference", trnprdref);
        $("#field_trnOrdCliId__trnCliPrenom").click();
        find("trnCliPrenom", trncliprenom);

        $("#field_trnOrdDate").setValue(date);
        $("#field_trnOrdQuantite").setValue(quantite);
        $("#field_trnOrdNumero").setValue(numero);
        save();
    }

    public static void find(String id, String idtext) {
        $("#" + id).setValue(idtext).pressEnter();
        $("[data-field=\"" + id+ "\"]").click();
    }

    public static void search(String id, String idtext) {
        $("[data-field=\"" + id+ "\"]").click();
    }

    public static void performAction(String name) {
        $("[data-action=\"" + name + "\"]").click();
        SelenideElement element = $("#dlgmodal").find("[data-action=\"YES\"]");
        actions().pause(Duration.ofSeconds(1)).moveToElement(element).click().perform();
    }
}