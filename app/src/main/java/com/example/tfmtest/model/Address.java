package com.example.tfmtest.model;

public class Address {
      String canton;
      String distrito;
      String provincia;


     public Address(String canton, String distrito, String direccion){
         this.canton= canton;
         this.distrito = distrito;
         this.provincia = direccion;
     }

     public  String getDistrito(){
         return this.canton;
     }

     public String  getCanton(){
         return this.canton;
     }

     public String getProvincia(){
         return this.provincia;
     }

     public void setProvincia(String provincia){
         this.provincia = provincia;
     }

     public void setCanton(String canton){
        this.canton = canton;
    }

    public void setDistrito(String distrito){
        this.provincia = distrito;
    }

    
    
}
