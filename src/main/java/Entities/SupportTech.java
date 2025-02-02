package Entities;

import java.util.Date;

public class SupportTech extends Visiteur{
    public SupportTech() {}

    public SupportTech(String cin, String nom, String prenom, Date dateNaissance, String email, String telephone, String login, String motDePasse) {
        super(cin, nom, prenom, dateNaissance, email, telephone, login, motDePasse);
    }
}

