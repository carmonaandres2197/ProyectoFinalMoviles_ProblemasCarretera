package com.example.tfmtest.model;

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

    public String getFullName (){
        return this.fullName;
    }
    public String getEmail (){
        return this.email;
    }
    public String getProfilePictureUrl (){
        return this.profilePictureUrl;
    }
    public int getIdRol (){
        return this.idRol;
    }

    public void setFullName(String fullName){
        this.fullName = fullName; 
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setProfile(String profileUrl){
        this.profilePictureUrl = profileUrl;
    }
    public void setIdRol(int rol){
        this.idRol = rol;
    }

}
