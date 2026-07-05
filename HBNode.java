/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

public class HBNode { 
    int key;       // The value of the node (must be unique)
    int height;    // Height of the subtree rooted at this node
    int weight;    // Number of nodes in the subtree rooted at this node (including itself)
    HBNode left;   // Left child
    HBNode right;  // Right child

    // Constructor - creates a node with a given key
    public HBNode(int key) {
        this.key = key;
        this.height = 1; // A single node has height 1
        this.weight = 1; // A single node has weight 1
        this.left = null;
        this.right = null;
    }

    // Returns the height of a node, or 0 if the node is null
    public static int getHeight(HBNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    // Returns the weight of a node, or 0 if the node is null
    public static int getWeight(HBNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.weight;
        }
    }

    // Updates this node's height and weight based on its children
    public void updateHeightAndWeight() {
        int leftHeight = getHeight(this.left);
        int rightHeight = getHeight(this.right);
        this.height = 1 + Math.max(leftHeight, rightHeight);

        int leftWeight = getWeight(this.left);
        int rightWeight = getWeight(this.right);
        this.weight = 1 + leftWeight + rightWeight;
    }
}
    

