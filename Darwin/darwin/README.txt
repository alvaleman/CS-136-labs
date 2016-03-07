12.1//

pre: U T I M S D A D

post: M I T A D D S U

in-order: M I T U S A D D

12.20//


public static boolean isAVL(BinaryTree <E> tree) {
       if (tree == null) {
return true;
}
       return !Math.abs(tree.left().height() -  tree.right().height())) > 1 && isAVL(tree.left()) && isAVL(tree.right());
}
