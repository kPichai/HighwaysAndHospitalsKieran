/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 * Completed by: Kieran Pichai
 */

public class HighwaysAndHospitals {

    public static int determineSubtrees(int n, int cities[][]) {
        int[] tree = new int[n + 1];
        int numTrees = n;
        for (int[] path : cities) {

            int startingNode = path[1];
            while (tree[startingNode] != 0) {
                startingNode = tree[startingNode];
            }
            while (tree[startingNode] != 0) {
                int temp = tree[startingNode];
                tree[startingNode] = startingNode;
                startingNode = temp;
            }

            int checkFor = path[0];
            while (tree[checkFor] != 0) {
                checkFor = tree[checkFor];
            }
            while (tree[checkFor] != 0) {
                int temp = tree[checkFor];
                tree[checkFor] = checkFor;
                checkFor = temp;
            }

            if (startingNode != checkFor) {
                numTrees--;
                tree[checkFor] = startingNode;
            }
        }

        return numTrees;
    }

    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        if (hospitalCost <= highwayCost) {
            return (long)n * hospitalCost;
        }
        long numGraphs = determineSubtrees(n, cities);
        return numGraphs * hospitalCost + (n - numGraphs) * highwayCost;
    }
}