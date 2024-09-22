/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 * Completed by: Kieran Pichai
 */

public class HighwaysAndHospitals {

    // Union-find algorithm to find the number of disconnected components in a highway-hospital graph
    public static int determineSubtrees(int n, int cities[][]) {

        // Initializing maps
        int[] tree = new int[n + 1];
        int[] subtreeSize = new int[n + 1];

        int numTrees = n;

        for (int[] path : cities) {

            // Finds the root of the starting node in path
            int startingNode = path[1];
            while (tree[startingNode] != 0) {
                startingNode = tree[startingNode];
            }

            // Path compression of the starting node
            while (tree[startingNode] != 0) {
                // Store the parent of the startingNode
                int temp = tree[startingNode];
                tree[startingNode] = startingNode;
                // Move up to the parent node, path compression
                startingNode = temp;
            }

            // Finds the root of the checkFor node, ending node in path
            int checkFor = path[0];
            while (tree[checkFor] != 0) {
                checkFor = tree[checkFor];
            }

            while (tree[checkFor] != 0) {
                int temp = tree[checkFor];
                tree[checkFor] = checkFor;
                checkFor = temp;
            }

            // Checks if we are connecting two new subgraphs
            if (startingNode != checkFor) {

                // Decreases num of disconnected components as we are now connecting two of them
                numTrees--;

                // Weight balancing occurs here by comparing size of the startingNode tree and checkFor tree
                if (subtreeSize[startingNode] <= subtreeSize[checkFor]) {
                    tree[startingNode] = checkFor;
                    // Combines sizes of trees
                    subtreeSize[checkFor] += subtreeSize[startingNode] + 1;
                } else {
                    tree[checkFor] = startingNode;
                    subtreeSize[startingNode] += subtreeSize[checkFor] + 1;
                }
            }
        }

        return numTrees;
    }

    // Main cost calculating function based off cost parameters
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {

        // Checks condition where hospitals are cheaper than highways because it's the easiest situation to calculate
        if (hospitalCost <= highwayCost) {
            return (long)n * hospitalCost;
        }

        // Stores the number of disconnected components from helper function and returns the final cost calculation
        long numGraphs = determineSubtrees(n, cities);
        return numGraphs * hospitalCost + (n - numGraphs) * highwayCost;
    }
}