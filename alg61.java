import java.util.*;
public class alg61 extends Node
{
     static int n = 4;
     static int W = 16;
     static int maxprofit;
     static int nodeCount = 0;
     static int[] p = new int[] {40,30,50,10};
     static int[] w = new int[]{2,5,10,5}; 
     public static void main(String[] args)
    {
        knapsack2();
        System.out.println("There was a total profit of " + maxprofit);
		System.out.println("The items selected were: ");
		for(int l = 0; l < p.length; l++) {
			if (p[l] != 0) {
			System.out.println(l+1 + " ");
			}
		}
		System.out.println("Number of nodes visited was " + nodeCount);
    }
    
    public static void knapsack2()
    {
        Node u = new Node();
        Node v = new Node();

        Queue<Node> Q = new LinkedList<Node>();
        
        v.level = -1;
        v.profit = 0;
        v.weight = 0;
        maxprofit = 0;
        v.bound =bound(v);
        Q.add(v);
        
        while(!Q.isEmpty())
        {
            Q.remove(v);
            u.level = v.level+1;
            u.weight = v.weight + w[u.level];
            u.profit = v.profit + p[u.level];

            if (u.weight < W && u.profit > maxprofit)
                maxprofit = u.profit;
            if(bound(u) > maxprofit)
            {
                Q.add(u);
                nodeCount++;
            }
            u.weight = v.weight;
            u.profit = v.profit;
            if(bound(u) > maxprofit)
                {
                    Q.add(u);
                    nodeCount++;
                }
            }
    }
    public static float bound (Node u) 
    {
        int totweight;
		int j; int k;
		float result; 
		if(u.weight >= W) {
			return 0;
		}
		else {
			result = u.profit;
			j = u.level + 1;
			totweight = u.weight;
			while(j < n && totweight + w[j] < W) 
            {
				totweight = totweight + w[j];
				result = result + p[j];
				j++;
			}
			k = j;
			if(k < n) {
				result = result + (W - totweight) + p[k]/w[k];
		    }
			return result;
		}
	}
}

