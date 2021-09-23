package com.simplicite.utils.datastore;

import com.simplicite.menu.administration.Field;
import com.simplicite.menu.administration.businessobject.BusinessObject;
import com.simplicite.menu.administration.module.Module;

import java.util.ArrayList;

public class ModelCreation {
    
    public static ArrayList<BusinessObject> createModel(Module module)
    {
        ArrayList<BusinessObject> objects = new ArrayList<>();
        //objects.add(new BusinessObject("Supplier", "supplier", module, "sup"));
        objects.add(new BusinessObject("Product", "product", module, "pro"));
        objects.add(new BusinessObject("Client", "client", module, "cli"));
        objects.add(new BusinessObject("Command", "command", module, "cmd"));
        return objects;
    }

    public ArrayList<Field> getFieldList(Field module)
    {
        return null;
    }
}
