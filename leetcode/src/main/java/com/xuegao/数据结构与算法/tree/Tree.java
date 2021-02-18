package com.xuegao.数据结构与算法.tree;

import java.util.ArrayDeque;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.tree
 * <br/> @ClassName：Tree
 * <br/> @Description：普通的tree
 * <br/> @author：xuegao
 * <br/> @date：2020/9/29 19:10
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

class MyTree {
    // 根节点
    private Node root;

    public MyTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 递归的先序
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + ", ");
        preorderTraversal(node.getLeftChild());
        preorderTraversal(node.getRightChild());
    }

    // 非递归的先序
    // TODO

    // 递归的中序
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.getLeftChild());
        System.out.print(node.getData() + ", ");
        inorderTraversal(node.getRightChild());
    }

    // 非递归的中序
    // TODO

    // 递归的后序
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.getLeftChild());
        postorderTraversal(node.getRightChild());
        System.out.print(node.getData() + ", ");
    }

    // 非递归的后序
    // TODO

    // 添加数据
    public void insertNode(int item) {
        Node newNode = new Node(item);

        if (this.root == null) {
            this.root = newNode;
        } else {
            Node tempRoot = this.root;
            while (true) {
                if (item > tempRoot.getData()) {
                    if (tempRoot.getRightChild() == null) {
                        tempRoot.setRightChild(newNode);
                        newNode.setParentNode(tempRoot);
                        break;
                    } else {
                        // 更改临时的根节点
                        tempRoot = tempRoot.getRightChild();
                    }
                } else if (item < tempRoot.getData()) {
                    if (tempRoot.getLeftChild() == null) {
                        tempRoot.setLeftChild(newNode);
                        newNode.setParentNode(tempRoot);
                        break;
                    } else {
                        // 更改临时的根节点
                        tempRoot = tempRoot.getLeftChild();
                    }
                } else {
                    break;
                }
            }
        }
    }

    // 找到最小的值
    public int findMin() {
        Node temp = this.root;
        while (temp.getLeftChild() != null) {
            temp = temp.getLeftChild();
        }
        return temp.getData();
    }

    // 找到最大的值
    public int findMax() {
        Node temp = this.root;
        while (temp.getRightChild() != null) {
            temp = temp.getRightChild();
        }
        return temp.getData();
    }

    // 删除一个数据
    public void delete(int item) {
        Node temp = this.root;
        while (true) {
            if (temp == null) {
                return;
            }
            if (temp.getData() == item) {
                delete(temp);
                return;
            }
            if (item > temp.getData()) {
                temp = temp.getRightChild();
            } else {
                temp = temp.getLeftChild();
            }
        }
    }

    // 删除一个节点
    public void delete(Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.getParentNode() == null) {
                this.root = null;
            } else if (node.getParentNode().getLeftChild() == node) {
                node.getParentNode().setLeftChild(null);
            } else if (node.getParentNode().getRightChild() == node) {
                node.getParentNode().setRightChild(null);
            }
        } else if (node.getLeftChild() == null && node.getRightChild() != null) {
            node.setData(node.getRightChild().getData());
            node.setRightChild(null);
        } else if (node.getLeftChild() != null && node.getRightChild() == null) {
            node.setData(node.getLeftChild().getData());
            node.setLeftChild(null);
        }
    }

    // 树形打印一棵树
    public void printTree(Node tree) {
        if (this.root == null) {
            System.out.println("null");
            return;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(tree);
        int level = this.getHeight(this.root);

        Node temp;
        // 本层的最后一个
        Node nowLast = this.root;
        // 下一层的最后一个
        Node nextLast;
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
                queue.addLast(new Node("*"));
            } else {
                if (temp.getLeftChild() != null) {
                    queue.addLast(temp.getLeftChild());
                }
            }

            if (temp.getRightChild() == null && level > 1) {
                queue.addLast(new Node("*"));
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

    public int getHeight(Node node) {
        Node temp = node;
        if (temp == null) {
            return 0;
        }
        if (temp.getLeftChild() == null && temp.getRightChild() == null) {
            return 1;
        }
        int leftHeight = getHeight(temp.getLeftChild()) + 1;
        int rightHeight = getHeight(temp.getRightChild()) + 1;
        int i;
        if (leftHeight > rightHeight) {
            i = leftHeight;
        } else {
            i = rightHeight;
        }
        return i;
    }

    public void printSpace(double spaceData) {
        for (int i = 0; i < spaceData; i++) {
            System.out.print(" ");
        }
    }

    public double backFirst(int level) {
        double n = 1;

        n = Math.pow(2, level) - 1;
        return n;  //返回第一个数字  空格的数量
    }

    public double backAfter(int level) {
        double n, m;
        n = m = 1;

        n = Math.pow(2, level) - 1;
        m = (2 * n) + 1;
        return m;//返回第二个数字及以后的  空格的数量
    }

}

class Node {
    // 节点的值
    private int data;
    // 节点的左孩子
    private Node leftChild;
    // 节点的右孩子
    private Node rightChild;
    // 节点的父节点
    private Node parentNode;
    // 树形打印用到
    private String strData;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(String strData) {
        this.strData = strData;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }
}