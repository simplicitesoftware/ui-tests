package com.simplicite.test.data;

import com.simplicite.menu.administration.businessobject.SimpliciteBusinessObjectAssistant;
import com.simplicite.menu.administration.module.SimpliciteModule;

import java.util.ArrayList;

public class ModelCreation {

    public ArrayList<SimpliciteBusinessObjectAssistant> createModel(SimpliciteModule module)
    {
        ArrayList<SimpliciteBusinessObjectAssistant> supplier = new ArrayList<>();
        supplier.add(new SimpliciteBusinessObjectAssistant("Supplier", "trn_supplier", module, "sup"));
        return supplier;
    }
}
