package com.xuegao.数据结构与算法.tree.tree;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.tree.avltree
 * <br/> @ClassName：Tree
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 17:23
 */
public class Tree {
    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        myTree.insertNode(5);
        myTree.insertNode(2);
        myTree.insertNode(6);
        myTree.insertNode(1);
        myTree.insertNode(3);
        myTree.insertNode(5);
        myTree.insertNode(11);
        myTree.insertNode(2);
        myTree.insertNode(8);
        myTree.insertNode(4);
        myTree.preorderTraversal();
        System.out.println();
        System.out.println("-------------------------------------");
        myTree.inorderTraversal();
        System.out.println();
        System.out.println("-------------------------------------");
        myTree.postorderTraversal();
        System.out.println();
        System.out.println("-------------------------------------");
        myTree.printTree(myTree.getRoot());

        myTree.delete(3);
        System.out.println();
        System.out.println("-------------------------------------");
        myTree.printTree(myTree.getRoot());
    }
}