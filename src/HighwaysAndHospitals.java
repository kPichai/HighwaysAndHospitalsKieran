import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static ArrayList<Integer> determineSubtrees(int n, int cities[][]) {
        boolean[] isVisited = new boolean[n+1];
        ArrayList<Integer> subtrees = new ArrayList<Integer>();
        Queue<Integer> toSearch = new LinkedList<Integer>();
        toSearch.add(1);

        int count = 0;

        while (!toSearch.isEmpty()) {
            int curNode = toSearch.remove();
            isVisited[curNode] = true;
            for (int[] path : cities) {
                if (!isVisited[path[1]] && path[0] == curNode) {
                    toSearch.add(path[1]);
                    count++;
                }
            }

            if (toSearch.isEmpty()) {
                subtrees.add(count);
                count = 0;
                for (int[] path: cities) {
                    if (!isVisited[path[0]]) {
                        toSearch.add(path[0]);
                        break;
                    }
                }
            }
        }

        return subtrees;
    }

    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        if (hospitalCost < highwayCost) {
            return (long)n * hospitalCost;
        }

        ArrayList<Integer> subtrees = determineSubtrees(n, cities);

        System.out.println(subtrees);

        long highwayTotalCost = 0;
        for (Integer numCities: subtrees) {
            highwayTotalCost += (long)highwayCost*(numCities - 1);
        }

        return (long)hospitalCost*subtrees.size() + highwayTotalCost;
    }
}
