package com.ljh.dsa.tree.avl;

/**
 * @author ljh
 * @date 2020-04-21 11:52
 */
public class App {

    public static void main(String[] args) {
        AVLTree binaryTree = new AVLTree();
        binaryTree.insert(39);
        binaryTree.insert(24);
        binaryTree.insert(64);
        binaryTree.insert(23);
        binaryTree.insert(30);
        binaryTree.insert(53);
        binaryTree.insert(60);
        binaryTree.insert(25);
        binaryTree.preOrderStack(binaryTree.root);
    }
}
