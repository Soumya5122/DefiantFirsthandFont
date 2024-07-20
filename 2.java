class Node {
  int data;
  Node left, right;
  Node(int d) {
      data = d;
      left = right = null;
  }
}

class Solution {
  // Return the root of the modified tree after removing all the half nodes.
  public Node RemoveHalfNodes(Node root) {
      if (root == null) {
          return null;
      }

      root.left = RemoveHalfNodes(root.left);
      root.right = RemoveHalfNodes(root.right);

      if (root.left == null && root.right == null) {
          return root;
      }

      if (root.left == null) {
          return root.right;
      }

      if (root.right == null) {
          return root.left;
      }

      return root;
  }
}

public class Main {
  public static void main(String[] args) {
      Solution solution = new Solution();

      Node root = new Node(2);
      root.left = new Node(7);
      root.right = new Node(5);
      root.left.right = new Node(4);
      root.left.right.left = new Node(6);
      root.left.right.right = new Node(3);
      root.right.right = new Node(11);
      root.right.right.left = new Node(9);
      root.right.right.right = new Node(8);

      Node newRoot = solution.RemoveHalfNodes(root);

      printInOrder(newRoot);
  }

  public static void printInOrder(Node node) {
      if (node == null) {
          return;
      }

      printInOrder(node.left);
      System.out.print(node.data + " ");
      printInOrder(node.right);
  }
}