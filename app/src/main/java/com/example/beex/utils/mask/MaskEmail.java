package com.example.beex.utils.mask;

public class MaskEmail {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String emailHidden(){
        StringBuilder newEmail = new StringBuilder(email);
        int position = email.indexOf('@');

        if (position>2) {
            newEmail = new StringBuilder(Character.toString(email.charAt(0)));
            for (int i = 2; i < position; i++)
                newEmail.append('*');
            newEmail.append(email.substring(position - 1));
        }

        return newEmail.toString();
    }
}
