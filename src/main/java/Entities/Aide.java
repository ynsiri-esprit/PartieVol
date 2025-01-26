package Entities;

public class Aide {
    private int id;
    private String question;
    private String reponse;

    // Constructeurs
    public Aide() {
    }

    public Aide(int id, String question, String reponse) {
        this.id = id;
        this.question = question;
        this.reponse = reponse;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    // toString
    @Override
    public String toString() {
        return "Aide{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", reponse='" + reponse + '\'' +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aide aide = (Aide) o;
        return id == aide.id;
    }
}

