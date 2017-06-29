package es.smt.appfrigo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

import java.io.Serializable;

/**
 * Created by Galder on 14/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UseInfo implements Serializable {

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String lastAccess;
    private String name ;
    private String equipment;
    private String operatorName ;
    private int sales ;

    public UseInfo() {
    }

    public UseInfo(String lastAccess, String name, String equipment, String operatorName, int sales) {
        this.lastAccess = lastAccess;
        this.name = name;
        this.equipment = equipment;
        this.operatorName = operatorName;
        this.sales = sales;
    }

    public String getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
