import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Kieran Pichai
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
        isVisited[1] = true;

        ArrayList<Integer> cur = new ArrayList<Integer>();
        cur.add(1);

        int count = 1;

        while (!toSearch.isEmpty()) {
            int curNode = toSearch.remove();
            isVisited[curNode] = true;
            for (int[] path : cities) {
                if (path[0] == curNode && !isVisited[path[1]]) {
                    toSearch.add(path[1]);
                    cur.add(path[1]);
                    isVisited[path[1]] = true;
                }
            }

            if (toSearch.isEmpty()) {
                count = cur.size();
                cur = new ArrayList<Integer>();
                subtrees.add(count);
                for (int[] path: cities) {
                    if (!isVisited[path[0]]) {
                        toSearch.add(path[0]);
                        cur.add(path[0]);
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
