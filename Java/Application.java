package Java;

public class Application {
    public static void main(String[] args) {
        Adherent adherent = new Adherent(
                "El Amrani",
                "Sara",
                "sara@email.com",
                "0612345678",
                21,
                1001
        );

            Auteur auteur = new Auteur(
                    "Hugo",
                    "Victor",
                    "victor.hugo@email.com",
                    "0600000000",
                    46,
                    501
            );

            Livre livre = new Livre(
                    12345,
                    "Les Misérables",
                    auteur
            );

            System.out.println(adherent);
            System.out.println(livre);
        }
    }