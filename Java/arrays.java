package Java;

import java.util.Scanner;
import java.util.Arrays;

class Exercice1 {

    final static int ARR_SIZE = 6;

    static int[] notes ;

    private static void readNotes() {

        notes = new int[ARR_SIZE];

        Scanner input = new Scanner(System.in);

        System.out.println("--- Read Notes ---");
        for (int i = 0 ; i < ARR_SIZE ; i++) {
            System.out.printf("Note [%d] : " , i + 1);
            notes[i] = input.nextInt();
            System.out.println();
        }
        input.close();
    }

    private static void printNotes() {
        Arrays.sort(notes);
        System.out.println("--- Print Notes ---");
        System.out.print("[ ");
        for (int note : notes) {
            System.out.print(note + " ");
        }
        System.out.print(" ]");
    }

    private static int average() {
        int size = notes.length;
        int sum = 0 ;
        for (int note : notes) {
            sum += note;
        }
        return (sum/size);
    }

    private static int max() {
        int max = notes[0] ; 
        for (int note : notes) {
            if (note > max) max = note ; 
        }
        return max;
    }

    private static int min() {
        int min = notes[0] ; 
        for (int note : notes) {
            if (note < min) min = note ; 
        }
        return min;
    }
    public static void main(String[] args) {
        readNotes();
        printNotes();
        System.out.println();
        System.out.println("Average = " + average());
        System.out.println("Max = " + max());
        System.out.println("Min = " + min());
    }
}

class Exercice2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Verbe du 1ere Groupe : ");  
        String verbe = input.nextLine().trim().toLowerCase();
        input.close();
        if (verbe.length() < 3 || !verbe.endsWith("er")) {
            System.out.println("Le verbe n'est pas du 1er groupe");
        } else {
            String radical = verbe.substring(0 , verbe.length() - 2);

            System.out.println("Je" + radical + "e");
            System.out.println("Tu " + radical + "es");
            System.out.println("Il/Elle " + radical + "e");
            System.out.println("Nous " + radical + "ons");
            System.out.println("Vous " + radical + "ez");
            System.out.println("Ils/Elles " + radical + "ent");
        }
    }
}

class Exercice3 {
    static String str = "";
    static Scanner input = new Scanner(System.in);

    public static void readString() {
        System.out.print("Entrer une chaine : ");
        str = input.nextLine().trim();
    }

    public static void printString() {
        if (str.isEmpty()) {
            System.out.println("Aucune chaine saisie.");
        } else {
            System.out.println("Chaine : " + str);
        }
    }

    public static String inverseString() {
        String str_inverse = "";
        for (int i = str.length() - 1 ; i >= 0 ; i-- ) {
            str_inverse += str.charAt(i);
        }
        return str_inverse;
    }

    public static int wordsCount() {
        if (str.isEmpty()) {
            return 0;
        }
        return str.split("\\s+").length;
    }

    private static void printMenu() {
        System.out.println("----------- Strings Manager ------------");
        System.out.println("1. Saisir une chaine");
        System.out.println("2. Afficher la chaine");
        System.out.println("3. Inverser la chaine");
        System.out.println("4. Compter les mots");
        System.out.println("0. Quitter");
    }

    private static void waitForMenu() {
        System.out.println();
        System.out.println("Frappez une touche pour revenir au menu");
        input.nextLine();
    }

    public static void main(String[] args) {
        String choice;

        do {
            printMenu();
            System.out.print("Votre choix : ");
            choice = input.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    readString();
                    break;
                case "2":
                    printString();
                    break;
                case "3":
                    if (str.isEmpty()) {
                        System.out.println("Aucune chaine saisie.");
                    } else {
                        System.out.println("Chaine inversee : " + inverseString());
                    }
                    break;
                case "4":
                    System.out.println("Nombre de mots : " + wordsCount());
                    break;
                case "0":
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }

            if (!choice.equals("0")) {
                waitForMenu();
                System.out.println();
            }
        } while (!choice.equals("0"));

        input.close();
    }
    
}

class Exercice4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        System.out.print("Entrer une chaine : ");
        String str = input.nextLine().trim();
        String lowerStr = str.toLowerCase();
        int[] nb_occurences = new int[26];

        for (int i = 0 ; i < lowerStr.length() ; i++) {
            char c = lowerStr.charAt(i); 
            int index = c - 'a';
            if (index >= 0 && index < 26) {
                nb_occurences[index] += 1 ;
            }
        }

        System.out.println("La chaine \"" + str + "\" contient :");
        for (int i = 0 ; i < nb_occurences.length ; i++) {
            if (nb_occurences[i] > 0) {
                char letter = (char) ('A' + i);
                System.out.println(nb_occurences[i] + " fois la lettre '" + letter + "'");
            }
        }

        input.close();
    }
}

public class arrays {
    public static void main(String[] args) {
        Exercice1.main(args);
        Exercice2.main(args);
        Exercice3.main(args);
        Exercice4.main(args);
    }
}
