public class User {
    String fullName;
    String email;
    String profilePictureUrl;
    int idRol;

    public User(String fullName,String email,String profilePictureUrl,int idRol) {
         this.fullName=  fullName;
         this.email= email;
         this.profilePictureUrl=profilePictureUrl;
         this.idRol =idRol;
    }

    public getFullName (){
        return this.fullName;
    }
    public getEmail (){
        return this.email;
    }
    public getProfilePictureUrl (){
        return this.profilePictureUrl;
    }
    public getIdRol (){
        return this.idRol;
    }

    public setFullName(String fullName){
        this.fullName = fullName; 
    }

    public setEmail(String email){
        this.email = email;
    }

    public setProfile(String profileUrl){
        this.profileUrl = profileUrl;
    }

    public setIdRol(int rol){
        this.rol = rol;
    }

}
