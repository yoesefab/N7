package Java;

public class Ingénieur extends Employe {

    private String specialité ;

    public Ingénieur(String nom, String prenom, String email, String telephone , double salaire , String specialité) {
        super(nom, prenom, email, telephone , salaire);
        this.specialité = specialité ;
    }

    @Override
    public double calculerSalire() {
        return getSalaire() + getSalaire() * 0.15;
    }
}
