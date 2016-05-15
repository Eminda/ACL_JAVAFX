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

    public EmployeeTable(String name, String prefName, String nic, String epfno, String photo,String did) {
        this.name =new SimpleStringProperty(name);
        this.prefName = new SimpleStringProperty(prefName);
        this.nic = new SimpleStringProperty(nic);
        this.epfno = new SimpleStringProperty(epfno);
        this.photo = new SimpleStringProperty(photo);
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

    public String getPhoto() {
        return photo.get();
    }
     public String getDid(){
         return this.did;
     }
}
