package Entities;

import java.util.Objects;

public class SupportTech {
    private String login;
    private String motDePasse;
    private int codeAppli;

    // Constructeurs
    public SupportTech() {
    }

    public SupportTech(String login, String motDePasse, int codeAppli) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.codeAppli = codeAppli;
    }

    // Getters et Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCodeAppli() {
        return codeAppli;
    }

    public void setCodeAppli(int codeAppli) {
        this.codeAppli = codeAppli;
    }

    // toString
    @Override
    public String toString() {
        return "SupportTech{" +
                "login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", codeAppli=" + codeAppli +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTech that = (SupportTech) o;
        return codeAppli == that.codeAppli &&
                Objects.equals(login, that.login) &&
                Objects.equals(motDePasse, that.motDePasse);
    }
}

