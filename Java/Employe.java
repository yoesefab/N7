package Java;

public abstract class Employe {
    private String nom ;
    private String prenom ;
    private String email ;
    private String telephone ;
    private double salaire ;

    public Employe(String nom, String prenom, String email, String telephone , double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone ;
        this.salaire = salaire ;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public double getSalaire() {
        return salaire;
    }

    public String getTelephone() {
        return telephone;
    }

    public abstract double calculerSalire() ;
}
