package com.simplicite.ui;

import com.codeborne.selenide.Condition;
import com.simplicite.utils.Component;

import static com.codeborne.selenide.Selenide.$;

public class Diagram {
    public static void verifyState(String state) {
        $("#select2-field_trnOrdState-container").shouldHave(Condition.text(state));
    }

    public static void switchProcessingState(String state) {
        Component.clickOnDlgmodal("button[data-state=\"" + state + "\"]");
        verifyState(state);
    }
}
