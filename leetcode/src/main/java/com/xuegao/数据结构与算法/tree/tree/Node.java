package com.xuegao.数据结构与算法.tree.tree;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.tree.avltree
 * <br/> @ClassName：Node
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 17:23
 */
public class Node {
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