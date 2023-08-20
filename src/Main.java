public class Main {
    public static void main(String[] args) {
        Tree avlTree = new Tree();

        for (int i = 0; i < 1000000; i++) {
            avlTree.add(i);
            avlTree.add(-i);
        }

        System.out.println("Height of the tree: " + avlTree.getHeight());
        System.out.println("Contains 10: " + avlTree.contains(10));
    }
}
