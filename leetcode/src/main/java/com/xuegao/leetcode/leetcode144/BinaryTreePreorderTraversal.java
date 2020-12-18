// package com.xuegao.leetcode.leetcode144;
//
// import com.xuegao.tree.MyTree;
//
// /**
//  * <br/> @PackageName：com.xuegao.leetcode.leetcode144
//  * <br/> @ClassName：BinaryTreePreorderTraversal
//  * <br/> @Description：二叉树的前序遍历  非递归的先序
//  * <br/> @author：xuegao
//  * <br/> @date：2020/12/18 17:28
//  */
// public class BinaryTreePreorderTraversal {
//
//     public static void main(String[] args) {
//         MyTree myTree = new MyTree();
//         // myTree.insertNode(1);
//         // myTree.insertNode(2);
//         // myTree.insertNode(3);
//
//         myTree.insertNode(5);
//         myTree.insertNode(2);
//         myTree.insertNode(6);
//         myTree.insertNode(1);
//         myTree.insertNode(3);
//         myTree.insertNode(5);
//         myTree.insertNode(11);
//         myTree.insertNode(2);
//         myTree.insertNode(8);
//         myTree.insertNode(4);
//         myTree.insertNode(5);
//
//         int height = myTree.getHeight(myTree.getRoot());
//         System.out.println(height);
//         System.out.println("=================================");
//
//         myTree.printTreeToRoot();
//         System.out.println();
//         System.out.println("================================");
//         myTree.preorderTraversal();
//         System.out.println();
//         System.out.println("================================");
//         myTree.preorderTraversalNew();
//         System.out.println();
//         System.out.println("================================");
//         myTree.preorderTraversalNew2();
//         System.out.println();
//         System.out.println("================================");
//         myTree.inorderTraversal();
//         System.out.println();
//     }
// }