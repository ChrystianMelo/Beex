package com.example.beex.repository.Mask;

public class MaskEmail {
    String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public MaskEmail(String email) {
        setEmail(email);
    }

    public String emailHidden(){
        String newEmail = email;
        int position = email.indexOf('@');

        if (position>2) {
            newEmail = Character.toString(email.charAt(0));
            for (int i = 2; i < position; i++)
                newEmail += '*';
            newEmail += email.substring(position - 1);
        }
        return newEmail;
    }
}
