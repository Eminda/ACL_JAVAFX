/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class ResignedEmployee {
    final private SimpleStringProperty name;
    final private SimpleStringProperty prefName;
    final private SimpleStringProperty nic;
    final private SimpleStringProperty epfno;
    final private SimpleStringProperty resigneddate;
    final private String did;
     public ResignedEmployee(String name, String prefName, String nic, String epfno, String resigneddate,String did) {
        this.name =new SimpleStringProperty(name);
        this.prefName = new SimpleStringProperty(prefName);
        this.nic = new SimpleStringProperty(nic);
        this.epfno = new SimpleStringProperty(epfno);
        this.resigneddate = new SimpleStringProperty(resigneddate);
        this.did = did;
     }
     
      public String getName() {
        return name.get();
    }

    public String getPrefName() {
        return prefName.get();
    }

    public String getNic() {
        return nic.get();
    }

    public String getEpfno() {
        return epfno.get();
    }

    public String getResigneddate() {
        return resigneddate.get();
    }
     public String getDid(){
         return this.did;
     }
}
