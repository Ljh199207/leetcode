package com.ljh.dsa.tree.serach;

/**
 * @author ljh
 * @date 2020-04-17 16:29
 */
public class App {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(39);
        binaryTree.insert(24);
        binaryTree.insert(64);
        binaryTree.insert(23);
        binaryTree.insert(30);
        binaryTree.insert(53);
        binaryTree.insert(60);
        binaryTree.insert(25);
        binaryTree.preOrder(binaryTree.root);
        System.out.println();
        binaryTree.preOrderStack(binaryTree.root);
        System.out.println();
        /*binaryTree.inOrder(binaryTree.root);
        System.out.println();
        binaryTree.inOrderStack(binaryTree.root);*/
      /*  binaryTree.postOrder(binaryTree.root);
        System.out.println();
        binaryTree.postOrderStack(binaryTree.root);*/
        /*binaryTree.levelOrder2(binaryTree.root);*/
    }
}
