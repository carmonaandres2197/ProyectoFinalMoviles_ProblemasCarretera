import javax.print.attribute.standard.Severity;

public class DH {
    Date creationDate;
    Severity severity;
    boolean state;
    String nombre;

    public DH(Date creationDate,
            Severity severity,
            boolean state,
            String Nombre) {
        this.creationDate = creationDate;
        this.severity = severity;
        this.state = state;
        this.nombre = nombre;
    }

    public setCreationDate(String creationDate ){
          this.creationDate =creationDate;
     }

    public setSeverity(Severity severity ){
        this.severity =creationDate;
   }

   public setEstate(boolean state ){
    this.state = state;
}

public setNombre(String nombre ){
    this.nombre =nombre;
}

public getCreationDate(){
    return this.creationDate;
}

public getSeverity(){
    return this.severity;
}

public getEstate(){
    return this.state;
}

public getNombre(){
    return this.nombre;
}

}
