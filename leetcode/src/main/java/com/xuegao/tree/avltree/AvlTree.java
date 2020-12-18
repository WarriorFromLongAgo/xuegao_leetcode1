package com.xuegao.tree.avltree;

/**
 * <br/> @PackageName：com.xuegao.tree.avltree
 * <br/> @ClassName：AvlTree
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 17:26
 */
public class AvlTree {
    public static void main(String[] args) {
        // https://www.cnblogs.com/kjcc/p/13217578.html

        MyAvlTree myTree = new MyAvlTree();
        // myTree.insertNode(5);
        // myTree.insertNode(2);
        // myTree.insertNode(6);
        // myTree.insertNode(1);
        // myTree.insertNode(3);
        // myTree.insertNode(5);
        // myTree.insertNode(11);
        // myTree.insertNode(2);
        // myTree.insertNode(8);
        // myTree.insertNode(4);

        myTree.insertNode(6);
        myTree.insertNode(8);
        myTree.insertNode(7);
        myTree.insertNode(11);
        myTree.insertNode(12);

        myTree.inorderTraversal();
        System.out.println();
        System.out.println("-------------------------------------");
        myTree.printTree(myTree.getRoot());

        // myTree.delete(6);
        // myTree.delete(7);
        // myTree.delete(8);
        // System.out.println();
        // System.out.println("-------------------------------------");
        // myTree.printTree(myTree.getRoot());

        myTree.clearTree();
        System.out.println();
        System.out.println("-------------------------------------");
        myTree.printTree(myTree.getRoot());
    }
}