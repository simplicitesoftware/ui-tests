package com.simplicite.utils;

import static com.simplicite.menu.MainMenuProperties.work;

public enum Traduction {

    SUPPLIER("Supplier", "EN"),
    FORMATION("Formation", "FR"),
    TRAINING("Training", "EN");

    private final String traduction;
    private final String language;

    /**
     * Traduction for Simplicite framework
     *
     * @param traduction Word to add to the dictionnary
     * @param lang       Language of the traduction
     */
    Traduction(String traduction, String lang) {

        this.traduction = traduction;
        this.language = lang;
    }

    /**
     * @return the word
     */
    public String getTraduction() {
        return traduction;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    public void doTraduction(Traduction traduction) {
        work.find("input[name*=\"EN\"]").sendKeys(getTraduction());
        work.find("input[name*=\"FR\"]").sendKeys(traduction.getTraduction());
    }
}

