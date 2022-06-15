package classes;

public class Address {
      String canton;
      String distrito;
      String direccion;


     public Address(String canton, String distrito, String direccion){
         this.canton= canton;
         this.distrito = distrito;
         this.direccion = direccion;
     }

     public  String getDistrito(){
         return this.canton;
     }

     public String  getCanton(){
         return this.canton;
     }

     public String getDireccion(){
         return this.direccion;
     }

     public void setDireccion(String direccion){
         this.direccion= direccion;
     }

     public void setCanton(String canton){
        this.canton = canton;
    }

    public void setDistrito(String distrito){
        this.direccion= distrito;
    }

    
    
}
