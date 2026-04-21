package Java;

public class Manager extends Employe {

    private String service;

    public Manager(String nom, String prenom, String email, String telephone, double salaire , String service) {
        super(nom, prenom, email, telephone, salaire);
        this.service = service;
    }

    @Override
    public double calculerSalire() {
        return getSalaire() + getSalaire() * 0.20;
    }
}
