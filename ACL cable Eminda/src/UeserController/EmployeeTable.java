/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class EmployeeTable {
    final private SimpleStringProperty name;
    final private SimpleStringProperty prefName;
    final private SimpleStringProperty nic;
    final private SimpleStringProperty epfno;
    final private SimpleStringProperty photo;
    final private String did;
    final private String pos;

    public EmployeeTable(String name, String prefName, String nic, String epfno, String photo,String did,String pos) {
        this.name =new SimpleStringProperty(name);
        this.prefName = new SimpleStringProperty(prefName);
        this.nic = new SimpleStringProperty(nic);
        this.epfno = new SimpleStringProperty(epfno);
        this.photo = new SimpleStringProperty(photo);
        this.did = did;
        this.pos = pos;
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

    public String getPhoto() {
        return photo.get();
    }
     public String getDid(){
         return this.did;
     }

    public String getPos() {
        return pos;
    }
}
