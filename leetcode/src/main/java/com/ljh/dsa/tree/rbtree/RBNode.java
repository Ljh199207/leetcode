package com.ljh.dsa.tree.rbtree;

/**
 * @author ljh
 * @date 2020-04-22 08:58
 */
public class RBNode<T extends Comparable<T>> {

    public final static boolean RED = true;

    public final static boolean BLACK = false;

    boolean color;  //颜色
    T key;  //值
    RBNode left; //左子树
    RBNode right; //右子树
    RBNode parent; //父节点

    public RBNode(boolean color, T key, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.color = color;
        this.key = key;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return key;
    }

    //打印节点的关键值和颜色信息
    public String toString() {
        return "" + key + (this.color == RED ? "R" : "B");
    }
}
