package Java;

public class GestionBibliotheque {
    public static void main(String[] args) {

        Livre livre1 = new Livre("Java Basics", "Ali");
        DVD dvd1 = new DVD("Inception", "Nolan");

        Utilisateur user = new Utilisateur("Mohammed");

        user.emprunterObjet(livre1);
        user.emprunterObjet(dvd1);

        livre1.retourner();
        dvd1.retourner();
    }
}