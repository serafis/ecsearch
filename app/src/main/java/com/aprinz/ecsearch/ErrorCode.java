package com.aprinz.ecsearch;

import java.util.ArrayList;
import java.util.List;

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

    public ErrorCode() {
        this.id = "";
        this.pName = "";
        this.driverEventDesc = "";
        this.shortAdvice = "";
        this.extendedAdvice = "";
        this.maintenanceEventDesc = "";
        this.repairText = "";
    }

    public List<Item> getAsList() {
        List<Item> list = new ArrayList<Item>();

        list.add(new Item("Fehlercode", id));
        list.add(new Item("Priorität", pName));
        list.add(new Item("Fehler Lokführer", driverEventDesc));
        list.add(new Item("Abhilfetext v>0", shortAdvice));
        list.add(new Item("Abhilfetext v=0", extendedAdvice));
        list.add(new Item("Fehler Wekstatt", maintenanceEventDesc));
        list.add(new Item("Abhilfetext Werkstatt", repairText));

        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorCode errorCode = (ErrorCode) o;

        if (id != null ? !id.equals(errorCode.id) : errorCode.id != null) return false;
        if (pName != null ? !pName.equals(errorCode.pName) : errorCode.pName != null) return false;
        if (driverEventDesc != null ? !driverEventDesc.equals(errorCode.driverEventDesc) : errorCode.driverEventDesc != null)
            return false;
        if (shortAdvice != null ? !shortAdvice.equals(errorCode.shortAdvice) : errorCode.shortAdvice != null)
            return false;
        if (extendedAdvice != null ? !extendedAdvice.equals(errorCode.extendedAdvice) : errorCode.extendedAdvice != null)
            return false;
        if (maintenanceEventDesc != null ? !maintenanceEventDesc.equals(errorCode.maintenanceEventDesc) : errorCode.maintenanceEventDesc != null)
            return false;
        return !(repairText != null ? !repairText.equals(errorCode.repairText) : errorCode.repairText != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (driverEventDesc != null ? driverEventDesc.hashCode() : 0);
        result = 31 * result + (shortAdvice != null ? shortAdvice.hashCode() : 0);
        result = 31 * result + (extendedAdvice != null ? extendedAdvice.hashCode() : 0);
        result = 31 * result + (maintenanceEventDesc != null ? maintenanceEventDesc.hashCode() : 0);
        result = 31 * result + (repairText != null ? repairText.hashCode() : 0);
        return result;
    }
}
