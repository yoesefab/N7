import java.util.*;

class City {
    int id;

    City(int id) {
        this.id = id;
    }
}

public class Main {
    static int[][] weights;

    static int totalDistance(List<City> path) {
        int total = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            total += weights[path.get(i).id][path.get(i + 1).id];
        }

        total += weights[path.get(path.size() - 1).id][path.get(0).id];

        return total;
    }

    // * Generation de voisin
    static List<City> getNeighbor(List<City> path) {
        List<City> newPath = new ArrayList<>(path);

        int i = (int)(Math.random() * newPath.size());
        int j = (int)(Math.random() * newPath.size());

        Collections.swap(newPath, i, j);

        return newPath;
    }

    static double acceptanceProbability(int currentCost, int newCost, double T) {
        if (newCost < currentCost) return 1.0;
        return Math.exp((currentCost - newCost) / T);
    }

    public static void main(String[] args) {

        int n = 5;
        weights = new int[n][n];

        weights[0][1] = weights[1][0] = 5;
        weights[0][2] = weights[2][0] = 5;
        weights[0][3] = weights[3][0] = 8;
        weights[0][4] = weights[4][0] = 9;

        weights[1][2] = weights[2][1] = 5;
        weights[1][3] = weights[3][1] = 5;
        weights[1][4] = weights[4][1] = 7;

        weights[2][3] = weights[3][2] = 4;
        weights[2][4] = weights[4][2] = 3;

        weights[3][4] = weights[4][3] = 4;

        // * Diagonal is 0
        for (int i = 0; i < n; i++) {
            weights[i][i] = 0;
        }

        List<City> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(new City(i));
        }

        // * Solution initial
        List<City> currentSolution = new ArrayList<>(cities);
        Collections.shuffle(currentSolution);

        int currentCost = totalDistance(currentSolution);

        // * Parameters
        double T = 1000;
        double coolingRate = 0.995;

        List<City> bestSolution = new ArrayList<>(currentSolution);
        int bestCost = currentCost;

        while (T > 1) {

            List<City> neighbor = getNeighbor(currentSolution);
            int neighborCost = totalDistance(neighbor);

            double prob = acceptanceProbability(currentCost, neighborCost, T);

            if (prob > Math.random()) {
                currentSolution = neighbor;
                currentCost = neighborCost;
            }

            if (currentCost < bestCost) {
                bestSolution = new ArrayList<>(currentSolution);
                bestCost = currentCost;
            }

            T *= coolingRate;
        }

        // * Result
        System.out.println("Best distance: " + bestCost);
        System.out.println("Best path (city IDs):");
        for (City c : bestSolution) {
            System.out.print(c.id + " -> ");
        }
        System.out.println(bestSolution.get(0).id);
    }
}