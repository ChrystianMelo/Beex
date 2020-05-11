package com.example.beex.utils;

public class FieldVerification {
    private String email;
    private String cpf;
    private String pass;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /*public FieldVerification(String email,String cpf, String pass) {
        setEmail(email);
        setCpf(cpf);
        setPass(pass);
    }*/

    public boolean cpfVerification(){
        return cpf.length() == 14;
    }

    public boolean emailVerification(){
        return !email.isEmpty() &&
                email.indexOf('.') != -1 &&
                email.indexOf('@') != -1;
    }
    public boolean passVerification(){
        return !pass.isEmpty();
    }
}