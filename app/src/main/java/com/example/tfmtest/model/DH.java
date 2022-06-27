package com.example.tfmtest.model;


import java.util.Date;

public class DH {
    Date creationDate;
    Severity severity;
    boolean state;
    String nombre;

    public DH(Date creationDate,
            Severity severity,
            boolean state,
            String nombre) {
        this.creationDate = creationDate;
        this.severity = severity;
        this.state = state;
        this.nombre = nombre;
    }




    public  void setCreationDate(Date creationDate ){
          this.creationDate =creationDate;
     }

    public void setSeverity(Severity severity ){
        this.severity = severity;
   }

   public void setEstate(boolean state ){
    this.state = state;
}

public void setNombre(String nombre){
    this.nombre =nombre;
}

public Date getCreationDate(){
    return this.creationDate;
}

public Severity getSeverity(){
    return this.severity;
}

public boolean getEstate(){
    return this.state;
}

public String getNombre(){
    return this.nombre;
}

}
