package com.example.beex.repository;

public class FieldVerification {
    String email  = "aaa@gmail.com";
    String cpf    = "111.111.111-11";
    String pass   = "abc45678";

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
        if (!cpf.isEmpty() &&
                cpf.length() == 14)
            return true;
        return false;
    }

    public boolean emailVerification(){
        if (!email.isEmpty() &&
                email.indexOf('.') != -1 &&
                email.indexOf('@') != -1)
            return true;
        return false;
    }
    public boolean passVerification(){
        if (!pass.isEmpty())
            return true;
        return false;
    }
}