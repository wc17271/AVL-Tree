public class Tree {

  private Node root;

  public void add(int key) {
    root = insert(key, root);
  }

  public Node insert(int key, Node root) {
    if (root == null) {
      return new Node(key);
    }

    if (key < root.getKey()) {
      root.setLeft(insert(key, root.getLeft()));
    } else if (key > root.getKey()) {
      root.setRight(insert(key, root.getRight()));
    } else {
      return root;
    }

    root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));

    int balance = balanceFactor(root);

    // CASE 1A: Left subtree > right subtree, new node is the left child node of the left subtree:
    if (balance > 1 && balanceFactor(root.getLeft()) > 0) {
      return rightRotate(root);
    }

    // CASE 1B: Left subtree > right subtree, new node is the right child node of the left subtree:
    if (balance > 1 && balanceFactor(root.getLeft()) < 0) {
      // Left rotation: (Update the root's left-child to be the new rotated subtree)
      root.setLeft(leftRotate(root.getLeft()));

      // Right rotation around the root:
      return rightRotate(root);
    }

    // CASE 2A: Right subtree > left subtree, new node is the right child node of the right subtree:
    if (balance < -1 && balanceFactor(root.getRight()) < 0) {
      return leftRotate(root);
    }

    // CASE 2B: Right subtree > left subtree, new node is the left child node of the right subtree:
    if (balance < -1 && balanceFactor(root.getRight()) > 0) {
      // Right rotation: (Update the root's right-child to be the new rotated subtree)
      root.setRight(rightRotate(root.getRight()));

      // Left rotation around the root:
      return leftRotate(root);
    }

    return root;
  }

  // Left rotation around the root:
  private Node leftRotate(Node node) {
    // Get the right child of the root:
    Node newRoot = node.getRight();

    // Get the left child of the new root:
    Node leftChild = newRoot.getLeft();

    // Store left child of the new root as the right child of the current/old root:
    node.setRight(leftChild);

    // Store the current/old root as the left child of the new root:
    newRoot.setLeft(node);

    // Update the heights of the nodes:
    node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    newRoot.setHeight(1 + Math.max(height(newRoot.getLeft()), height(newRoot.getRight())));

    return newRoot;
  }

  // Right rotation around the root:
  private Node rightRotate(Node node) {
    // Get the left child of the root:
    Node newRoot = node.getLeft();

    // Get the right child of the new root:
    Node rightChild = newRoot.getRight();

    // Store right child of the new root as the left child of the current/old root:
    node.setLeft(rightChild);

    // Store the current/old root as the right child of the new root:
    newRoot.setRight(node);

    // Update the heights of the nodes:
    node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    newRoot.setHeight(1 + Math.max(height(newRoot.getLeft()), height(newRoot.getRight())));

    return newRoot;
  }

  // Returns the balance factor of the tree:
  int balanceFactor(Node node) {
    if (node == null) {
      return 0;
    } else {
      // If balance factor = 0, left subtree = right subtree
      // If balance factor > 1, left subtree > right subtree
      // If balance factor < -1, left subtree < right subtree
      return height(node.getLeft()) - height(node.getRight());
    }
  }

  // Return the height of the inputted node:
  private int height(Node node) {
    if (node == null) {
      return 0;
    }

    return node.getHeight();
  }

  // Returns the hieght of the root.
  public int getHeight() {
    return root.getHeight();
  }

  // Check to see if tree contains a certain key:
  boolean contains(int key) {
    Node node = root;

    while (node != null) {
        if (node.getKey() == key) {
            return true;
        }

        if (key > node.getKey()) {
            node = node.getRight();
        } else {
            node = node.getLeft();
        }
    }

    return false;
  }
}
