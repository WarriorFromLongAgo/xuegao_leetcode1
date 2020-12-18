package com.xuegao.tree;

import java.util.ArrayDeque;

/**
 * <br/> @PackageName：com.xuegao.tree
 * <br/> @ClassName：AvlTree2
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/29 19:11
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

class MyAvlTree {
    private AvlNode root;

    public MyAvlTree() {
        this.root = null;
    }

    public AvlNode getRoot() {
        return root;
    }

    public void setRoot(AvlNode root) {
        this.root = root;
    }

    //私有的方法  返回实时更新的 Height
    private int Height(AvlNode tree) {
        if (tree != null) {
            return tree.getHeight();
        }
        return 0;
    }

    //公开的方法  返回整棵树的Height
    public int Height() {
        return this.Height(this.root);
    }

    //
    //            a                b
    //          /   \            /   \
    //         b     c    ==    d     a
    //       /   \             /     /  \
    //      d     e           f     e    c
    //     /
    //    f
    //
    // 左左结构，向右旋转
    private AvlNode LeftLeftRotation(AvlNode k2) {
        AvlNode k1;

        k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        k2.setParentNode(k1);

        k2.setHeight(Math.max(Height(k2.getLeftChild()), Height(k2.getRightChild())) + 1);
        k1.setHeight(Math.max(Height(k1.getLeftChild()), k2.getHeight()) + 1);
        return k1;
    }

    //
    //        a                         c
    //      /   \                     /   \
    //     b     c       ==          a     e
    //          /  \                / \     \
    //         d    e              b   d     f
    //               \
    //                f
    //
    // 右右结构，向左旋转
    private AvlNode RightRightRotation(AvlNode k1) {
        AvlNode k2;

        k2 = k1.getRightChild();
        k1.setRightChild(k2.getLeftChild());
        k2.setLeftChild(k1);
        k1.setParentNode(k2);

        k1.setHeight(Math.max(Height(k1.getLeftChild()), Height(k1.getRightChild())) + 1);
        k2.setHeight(Math.max(Height(k2.getRightChild()), k1.getHeight()) + 1);

        return k2;
    }

    //
    //           8                  8
    //         /   \              /   \                  6
    //        5     12   ==      6     12    ==        /   \
    //      /   \              /   \                  5      8
    //     3     6            5     7                /      /  \
    //            \          /                      3      7   12
    //             7        3
    //
    // 左右双结构，当左边太高时
    private AvlNode LeftRightRotation(AvlNode node) {
        AvlNode rightRightRotation = RightRightRotation(node.getLeftChild());
        node.setLeftChild(rightRightRotation);
        rightRightRotation.setParentNode(node);
        AvlNode leftLeftRotation = LeftLeftRotation(node);
        return leftLeftRotation;
    }

    //
    //      4                  4                       6
    //    /   \              /  \                    /   \
    //   2      7      ==   2    6       ==         4      7
    //        /   \            /   \              /   \     \
    //       6     8          5      7           2     5     8
    //      /                         \
    //     5                           8
    //
    // 右左双结构
    private AvlNode RightLeftRotation(AvlNode node) {
        AvlNode leftLeftRotation = LeftLeftRotation(node.getRightChild());
        node.setRightChild(leftLeftRotation);
        leftLeftRotation.setParentNode(node);
        AvlNode rightRightRotation = RightRightRotation(node);

        return rightRightRotation;
    }


    public AvlNode insertNode(AvlNode tree, int item) {
        if (tree == null) {
            tree = new AvlNode(item);
        } else {
            boolean isFindSameNode = this.lookSameNode(this.root, item);
            if (isFindSameNode) {
                return tree;
            } else {
                if (item < tree.getData()) {
                    AvlNode avlNode = insertNode(tree.getLeftChild(), item);
                    tree.setLeftChild(avlNode);
                    avlNode.setParentNode(tree);
                    if (Height(tree.getLeftChild()) - Height(tree.getRightChild()) > 1) {
                        if ((item > tree.getLeftChild().getData() ? 1 : -1) < 0) {
                            tree = LeftLeftRotation(tree);
                        } else {
                            tree = LeftRightRotation(tree);
                        }
                    }
                } else if (item > tree.getData()) {
                    AvlNode avlNode = insertNode(tree.getRightChild(), item);
                    tree.setRightChild(avlNode);
                    avlNode.setParentNode(tree);
                    if (Height(tree.getRightChild()) - Height(tree.getLeftChild()) > 1) {
                        if ((item > tree.getRightChild().getData() ? 1 : -1) > 0) {
                            tree = RightRightRotation(tree);
                        } else {
                            tree = RightLeftRotation(tree);
                        }
                    }
                } else {
                    System.out.println();
                    System.out.println("插入失败");
                }
            }
        }

        tree.setHeight(Math.max(Height(tree.getLeftChild()), Height(tree.getRightChild())) + 1);
        return tree;
    }

    public void insertNode(int item) {
        setRoot(insertNode(this.root, item));
    }

    /// 查找插入的节点的数据，是否和树中的重复，重复则返回
    private boolean lookSameNode(AvlNode node, int item) {
        if (node == null) {
            return false;
        }
        if (node.getData() == item) {
            return true;
        } else if (item < node.getData()) {
            return this.lookSameNode(node.getLeftChild(), item);
        } else {
            return this.lookSameNode(node.getRightChild(), item);
        }
    }

    /// 中序遍历
    public void inorderTraversal() {
        this.inorderTraversal(this.root);
    }

    private void inorderTraversal(AvlNode node) {
        if (node == null) {
            return;
        }
        this.inorderTraversal(node.getLeftChild());
        System.out.print(node.getData() + ", ");
        this.inorderTraversal(node.getRightChild());
    }


    // 树形打印一棵树
    public void printTree(AvlNode tree) {
        if (this.root == null) {
            System.out.println("null");
            return;
        }
        ArrayDeque<AvlNode> queue = new ArrayDeque<>();
        queue.add(tree);
        int level = this.nodeDepth(this.root);

        AvlNode temp;
        // 本层的最后一个
        AvlNode nowLast = this.root;
        // 下一层的最后一个
        AvlNode nextLast;
        // 本层的第一个
        boolean layerFirst = true;

        while (queue.size() > 0) {
            temp = queue.removeFirst();
            double before = this.backFirst(level);
            double after = this.backAfter(level);
            if (layerFirst) {
                this.printSpace(before);
            }

            if (temp.getData() == 0) {
                System.out.print("*");
                layerFirst = false;
            } else {
                System.out.print(temp.getData());
                layerFirst = false;
            }
            if (!layerFirst) {
                this.printSpace(after);
            }

            if (temp.getLeftChild() == null && level > 1) {
                queue.addLast(new AvlNode("*"));
            } else {
                if (temp.getLeftChild() != null) {
                    queue.addLast(temp.getLeftChild());
                }
            }

            if (temp.getRightChild() == null && level > 1) {
                queue.addLast(new AvlNode("*"));
            } else {
                if (temp.getRightChild() != null) {
                    queue.addLast(temp.getRightChild());
                }
            }
            nextLast = temp.getRightChild();

            if (temp == nowLast) {
                System.out.println();
                layerFirst = true;
                level--;
                nowLast = nextLast;
            }
            if (level == 0 && queue.isEmpty()) {
                return;
            }
        }
    }

    // 打印空格
    public void printSpace(double spaceData) {
        for (int i = 0; i < spaceData; i++) {
            System.out.print(" ");
        }
    }

    // 返回第一个数字  空格的数量
    public double backFirst(int level) {
        double n = 1;

        n = Math.pow(2, level) - 1;
        return n;
    }

    // 返回第二个数字及以后的  空格的数量
    public double backAfter(int level) {
        double n, m;
        n = m = 1;

        n = Math.pow(2, level) - 1;
        m = (2 * n) + 1;
        return m;
    }

    // 获得当前node的高度
    private int nodeDepth(AvlNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = nodeDepth(node.getLeftChild());
        int rightDepth = nodeDepth(node.getRightChild());
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /// 树的清空操作
    public void clearTree() {
        this.clearTree(this.root);
        this.root = null;
    }

    private void clearTree(AvlNode tree) {
        if (tree != null) {
            this.clearTree(tree.getLeftChild());
            tree.setLeftChild(null);
            this.clearTree(tree.getRightChild());
            tree.setRightChild(null);
        }
    }

    /*
     * 获取当前节点中的最大值，前驱节点
     */
    public AvlNode getPreNode(AvlNode avlNode) {
        AvlNode tempNode = avlNode;
        if (tempNode.getRightChild() != null) {
            tempNode = tempNode.getRightChild();
        }
        return tempNode;
    }


    public void deleteNode(int t) {
        if (t != 0) {
            root = deleteNode(root, t);
        }
    }

    /*
     * 删除节点
     *
     *
     */
    public AvlNode deleteNode(AvlNode avlNode, int t) {
        // 主要返回删除节点的替代节点，每次递归时，刷新节点是否处于平衡
        AvlNode curParentNode = null;
        // 没找到节点
        if (avlNode == null) {
            return null;
        }

        if (t < avlNode.getData()) {
            // avlNode.left = deleteNode(avlNode.left, t);
            avlNode.setLeftChild(deleteNode(avlNode.getLeftChild(), t));
            /*
             * 当值在树中找不到时，返回最后遍历的节点,如下，删除0时候，树中不存在，最后遍历到1返回，递归的时候使其2.left=1
             *        2
             *
             *     1
             */
            curParentNode = avlNode;
        } else if (t > avlNode.getData()) {
            // avlNode.right = deleteNode(avlNode.right, t);
            avlNode.setRightChild(deleteNode(avlNode.getRightChild(), t));
            // 同上
            curParentNode = avlNode;
        } else {
            /*
             * (1)删除节点是叶子节点
             */
            if (avlNode.getLeftChild() == null && avlNode.getRightChild() == null) {
                curParentNode = null;
            }

            /*
             * (2)删除节点有一个孩子  左孩子或者右孩子
             */
            // if (avlNode.left != null && avlNode.right == null) {
            //     curParentNode = avlNode.left;
            // } else if (avlNode.left == null && avlNode.right != null) {
            //     curParentNode = avlNode.right;
            // }
            if (avlNode.getLeftChild() != null && avlNode.getRightChild() == null) {
                curParentNode = avlNode.getLeftChild();
            } else if (avlNode.getLeftChild() == null && avlNode.getRightChild() != null) {
                curParentNode = avlNode.getRightChild();
            }


            /*
             * (3)删除节点的两个孩子都存在时,找到前驱节点
             *
             */
            // if (avlNode.left != null && avlNode.right != null) {
            //     Node preNode = getPreNode(avlNode.left);//前驱节点
            //     preNode.left = deleteNode(avlNode.left, preNode.t);//删除前驱节点位置，因为前驱节点要替换到待删除的节点
            //     preNode.right = avlNode.right;
            //     curParentNode = preNode;
            // }
            if (avlNode.getLeftChild() != null && avlNode.getRightChild() != null) {
                // 前驱节点
                AvlNode preNode = getPreNode(avlNode.getLeftChild());
                // 删除前驱节点位置，因为前驱节点要替换到待删除的节点
                preNode.setLeftChild(deleteNode(avlNode.getLeftChild(), preNode.getData()));
                preNode.setRightChild(avlNode.getRightChild());
                curParentNode = preNode;
            }
        }


        if (curParentNode == null) {
            return null;
        }
        /*
         * 维护平衡
         * 更新height
         */
        // curParentNode.height = 1 + Math.max(Height(curParentNode.getLeftChild()), Height(curParentNode.getRightChild()));
        // //平衡因子
        // int balanceFactor = getBalanceFactor(curParentNode);
        // if (balanceFactor > 1 && getBalanceFactor(curParentNode.left) >= 0) {
        //     //右旋LL
        //     return rightRotate(curParentNode);
        // }
        // if (balanceFactor < -1 && getBalanceFactor(curParentNode.right) <= 0) {
        //     //左旋RR
        //     return leftRotate(curParentNode);
        // }
        // //LR  先左旋再右旋
        // if (balanceFactor > 1 && getBalanceFactor(curParentNode.left) < 0) {
        //     avlNode.left = leftRotate(curParentNode.left);
        //     return rightRotate(curParentNode);
        // }
        // //RL  先右旋再左旋
        // if (balanceFactor < -1 && getBalanceFactor(curParentNode.right) > 0) {
        //     avlNode.right = rightRotate(curParentNode.right);
        //     return leftRotate(curParentNode);
        // }

        return curParentNode;
    }

}

class AvlNode {
    // 节点的值
    private int data;
    // 节点的左孩子
    private AvlNode leftChild;
    // 节点的右孩子
    private AvlNode rightChild;
    // 节点的父节点
    private AvlNode parentNode;
    // 树形打印用到
    private String strData;
    // 节点高度，需要时常刷新
    private int height;

    public AvlNode() {
    }

    public AvlNode(int data) {
        this.data = data;
    }

    public AvlNode(String strData) {
        this.strData = strData;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AvlNode leftChild) {
        this.leftChild = leftChild;
    }

    public AvlNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(AvlNode rightChild) {
        this.rightChild = rightChild;
    }

    public AvlNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(AvlNode parentNode) {
        this.parentNode = parentNode;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }
}
