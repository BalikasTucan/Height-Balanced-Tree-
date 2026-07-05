/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

public class HBTree {
     HBNode root;

    public HBTree() {
        this.root = null;
    }

    public boolean find(int key) {
        return findNode(root, key) != null;
    }

    private HBNode findNode(HBNode node, int key) {
        if (node == null) return null;
        if (key == node.key) return node;
        if (key < node.key) return findNode(node.left, key);
        else return findNode(node.right, key);
    }

    public boolean insert(int key) {
        if (find(key)) return false;
        root = insertNode(root, key);
        return true;
    }

    private HBNode insertNode(HBNode node, int key) {
        if (node == null) return new HBNode(key);

        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else {
            node.right = insertNode(node.right, key);
        }

        node.updateHeightAndWeight();
        return balance(node);
    }

    public boolean delete(int key) {
        if (!find(key)) return false;
        root = deleteNode(root, key);
        return true;
    }

    private HBNode deleteNode(HBNode node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            HBNode successor = getMin(node.right);
            node.key = successor.key;
            node.right = deleteNode(node.right, successor.key);
        }

        node.updateHeightAndWeight();
        return balance(node);
    }

    private HBNode getMin(HBNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void print() {
        int size = HBNode.getWeight(root);
        int maxHeight = HBNode.getHeight(root);
        int minHeight = getMinHeight(root);
        System.out.println("size: " + size + ", max_height: " + maxHeight + ", min_height: " + minHeight);
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(HBNode node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    private int getMinHeight(HBNode node) {
        if (node == null) return 0;
        return 1 + Math.min(getMinHeight(node.left), getMinHeight(node.right));
    }

    private HBNode balance(HBNode node) {
        int leftHeight = HBNode.getHeight(node.left);
        int rightHeight = HBNode.getHeight(node.right);
        int diff = Math.abs(leftHeight - rightHeight);
        int maxAllowed = Math.max(1, (int) Math.floor(Math.log(node.weight) / Math.log(2)));
        
        if (diff <= maxAllowed) return node;

        // Determine type of rotation
        if (leftHeight > rightHeight) {
            if (HBNode.getHeight(node.left.left) >= HBNode.getHeight(node.left.right)) {
                node = rotateRight(node); // LL
            } else {
                node.left = rotateLeft(node.left); // LR
                node = rotateRight(node);
            }
        } else {
            if (HBNode.getHeight(node.right.right) >= HBNode.getHeight(node.right.left)) {
                node = rotateLeft(node); // RR
            } else {
                node.right = rotateRight(node.right); // RL
                node = rotateLeft(node);
            }
        }
        
        if (node.left != null && node.right != null) {
                node.left = balance(node.left);
                node.right = balance(node.right);
                node.updateHeightAndWeight();
            }

        return node;
    }

    private HBNode rotateLeft(HBNode node) {
        HBNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        node.updateHeightAndWeight();
        newRoot.updateHeightAndWeight();
        return newRoot;
    }

    private HBNode rotateRight(HBNode node) {
        HBNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        node.updateHeightAndWeight();
        newRoot.updateHeightAndWeight();
        return newRoot;
    }
}

    

