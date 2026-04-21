package Java;

public class Compte {
    private String numero ;
    private double solde ;
    private static int nbCompte = 0 ;

    public Compte() {
        this.numero = "" ;
        this.solde = 0 ;
        nbCompte++;
    }

    // * Getters

    public String getNumero() {
        return this.numero;
    }

    public double getSolde() {
        return this.solde;
    }

    // * Setters

    public void setNumero(String numero) {
        this.numero = numero ;
    }

    public void setSolde(double solde) {
        this.solde = solde ;
    }

    // * Methods

    public void deposer(double montant) {
        if (montant >= 0) {
            setSolde(solde + montant);
        }
        else System.out.printf("Montant Invalid");
    }

    public void retirer(double montant) {
        if (getSolde() >= montant) {
            setSolde(solde - montant) ;
        }
        else System.out.println("Votre Solde Insufissant");
    }

    public void afficherCompteInfo() {
        System.out.println(" ---- Compte Information --- ");
        System.out.println(" | Numero : " + this.numero);
        System.out.println(" | Solde  : " + this.solde);
        System.out.println(" --------------------------- ");
    }

    public static void afficherNbComptes() {
        System.out.println("Nombres de Comptes : " + nbCompte);
    }

    // * main

    public static void main() {
        Compte compte = new Compte();
        compte.deposer(1000);
        compte.retirer(500);
        compte.afficherCompteInfo();
    }
}
