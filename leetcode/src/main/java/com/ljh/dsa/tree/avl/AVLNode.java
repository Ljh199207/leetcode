package com.ljh.dsa.tree.avl;

/**
 * @author ljh
 * @date 2020-04-16 15:58
 */
public class AVLNode<T extends Comparable<T>> {
    //节点值
    T data;
    //左子树
    AVLNode left;
    //右子树
    AVLNode right;
    //深度
    int dept;

    public AVLNode(T data, AVLNode left, AVLNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.dept = 0;
    }

}
