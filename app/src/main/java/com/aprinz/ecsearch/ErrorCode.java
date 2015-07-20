package com.aprinz.ecsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexander on 15.07.2015.
 */
public class ErrorCode {
    public final String id;
    public final String pName;
    public final String driverEventDesc;
    public final String shortAdvice;
    public final String extendedAdvice;
    public final String maintenanceEventDesc;
    public final String repairText;

    public ErrorCode(String id, String pName, String driverEventDesc, String shortAdvice, String extendedAdvice, String maintenanceEventDesc, String repairText) {
        this.id = id;
        this.pName = pName;
        this.driverEventDesc = driverEventDesc;
        this.shortAdvice = shortAdvice;
        this.extendedAdvice = extendedAdvice;
        this.maintenanceEventDesc = maintenanceEventDesc;
        this.repairText = repairText;
    }

    public List<Item> getAsList() {
        List<Item> list = new ArrayList<Item>();

        list.add(new Item("id", id));
        list.add(new Item("pName", pName));
        list.add(new Item("driverEventDesc", driverEventDesc));
        list.add(new Item("shortAdvice", shortAdvice));
        list.add(new Item("extendedAdvice", extendedAdvice));
        list.add(new Item("maintenanceEventDesc", maintenanceEventDesc));
        list.add(new Item("repairText", repairText));

        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorCode errorCode = (ErrorCode) o;
        return Objects.equals(id, errorCode.id) &&
                Objects.equals(pName, errorCode.pName) &&
                Objects.equals(driverEventDesc, errorCode.driverEventDesc) &&
                Objects.equals(shortAdvice, errorCode.shortAdvice) &&
                Objects.equals(extendedAdvice, errorCode.extendedAdvice) &&
                Objects.equals(maintenanceEventDesc, errorCode.maintenanceEventDesc) &&
                Objects.equals(repairText, errorCode.repairText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pName, driverEventDesc, shortAdvice, extendedAdvice, maintenanceEventDesc, repairText);
    }
}
