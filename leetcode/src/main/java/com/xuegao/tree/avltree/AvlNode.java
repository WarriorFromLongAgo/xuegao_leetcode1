package com.xuegao.tree.avltree;

/**
 * <br/> @PackageName：com.xuegao.tree.avltree
 * <br/> @ClassName：AvlNode
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 17:26
 */
public class AvlNode {
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