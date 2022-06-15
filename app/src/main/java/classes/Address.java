public class Address {
      String canton;
      String distrito;
      String direccion;


     public Address(String canton, String distrito, String direccion){
         this.canton= canton;
         this.distrito = distrito;
         this.direccion = direccion;
     }

     public getDistrito(){
         return this.canton;
     }

     public getCanton(){
         return this.canton;
     }

     public getDireccion(){
         return this.direccion;
     }

     public setDireccion(String direccion){
         this.direccion= direccion;
     }

     public setCanton(String canton){
        this.canton = canton;
    }

    public setDistrito(String distrito){
        this.direccion= distrito;
    }

    
    
}
