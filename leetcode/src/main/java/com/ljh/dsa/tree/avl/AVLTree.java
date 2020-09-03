package com.ljh.dsa.tree.avl;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author ljh
 * @date 2020-04-20 11:44
 */
public class AVLTree<T extends Comparable<T>> {

    public AVLNode root;


    private int height(AVLNode<T> avlNode) {
        if (avlNode != null)
            return avlNode.dept;
        return 0;
    }

    public int height() {
        return height(root);
    }

    /*
     * 比较两个值的大小
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    //LL的旋转代码   左节点左子树上插入
    private AVLNode leftLeftRotation(AVLNode<T> k2) {
        AVLNode<T> k1;
        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.dept = max(height(k2.right), height(k2.left)) + 1;
        k1.dept = max(height(k1.left), k2.dept) + 1;
        return k1;
    }

    //RR的旋转代码  右节点右子树
    private AVLNode rightRightRotation(AVLNode<T> k1) {
        AVLNode<T> k2;
        k2 = k1.right;
        k1.right = k1.left;
        k2.left = k1;
        k2.dept = max(height(k2.right), k1.dept) + 1;
        k1.dept = max(height(k1.left), height(k2.right)) + 1;
        return k2;
    }

    //LR的旋转代码  左节点右子树
    private AVLNode leftRightRotation(AVLNode<T> k3) {
        k3.left = rightRightRotation(k3.left);
        return leftLeftRotation(k3);
    }

    //RL的旋转代码  右节点左子树
    private AVLNode rightLeftRotation(AVLNode<T> k3) {
        k3.right = leftLeftRotation(k3.right);
        return rightRightRotation(k3);
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    private AVLNode insert(AVLNode<T> node, T data) {
        if (node == null) {
            node = new AVLNode<>(data, null, null);
            if (node == null) {
                System.out.println("ERROR: create avltree node failed!");
                return null;
            }
        } else {
            int cmp = data.compareTo(node.data);
            // 应该将data插入到"tree的左子树"的情况
            if (cmp < 0) {
                node.left = insert(node.left, data);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(node.left) - height(node.right) == 2) {
                    if (data.compareTo((T) node.left.data) < 0) {
                        node = leftLeftRotation(node);
                    } else {
                        node = leftRightRotation(node);
                    }
                }
            } else if (cmp > 0) {
                node.right = insert(node.right, data);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(node.right) - height(node.left) == 2) {
                    if (data.compareTo((T) node.right.data) > 0) {
                        node = rightRightRotation(node);
                    } else {
                        node = rightLeftRotation(node);
                    }
                }
            } else {
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }
        node.dept = max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private AVLNode<T> remove(AVLNode<T> tree, AVLNode<T> z) {
        if (tree == null || z == null) {
            return null;
        }
        int cmp = z.data.compareTo(tree.data);
        if (cmp < 0) {   // 待删除的节点在"tree的左子树"中
            tree.left = remove(tree.left, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.right) - height(tree.left) == 2) {
                AVLNode<T> r = tree.right;
                if (height(r.left) < height(r.right)) {
                    tree = rightRightRotation(tree);
                } else {
                    tree = rightLeftRotation(tree);
                }
            }
        } else if (cmp > 0) {
            tree.right = remove(tree.right, z);
            if (height(tree.left) - height(tree.right) == 2) {
                AVLNode<T> r = tree.left;
                if (height(r.right) > height(r.left)) {
                    tree = leftRightRotation(tree);
                } else {
                    tree = leftLeftRotation(tree);
                }
            }
        } else {
            // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if (tree.right != null && tree.left != null) {
                if (height(tree.left) > height(tree.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> max = maximum(tree.left);
                    tree.data = max.data;
                    tree.left = remove(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> min = minimum(tree.right);
                    tree.data = min.data;
                    tree.right = remove(tree.right, min);
                }
            } else {
                AVLNode<T> tmp = tree;
                tree = (tree.left != null) ? tree.left : tree.right;
                tmp = null;
            }
        }
        return tree;
    }

    public void remove(T key) {
        AVLNode<T> z;
        if ((z = search(root, key)) != null)
            root = remove(root, z);
    }

    public AVLNode search(AVLNode<T> root, T data) {
        if (root == null) {
            return null;
        }
        int cmp = root.data.compareTo(data);
        if (cmp < 0) {
            return search(root.left, data);
        } else if (cmp > 0) {
            return search(root.right, data);
        } else {
            return root;
        }
    }

    private AVLNode maximum(AVLNode root) {
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public T maximum() {
        AVLNode<T> p = maximum(root);
        if (p != null) {
            return p.data;
        }
        return null;
    }

    private AVLNode minimum(AVLNode root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public T minimum() {
        AVLNode<T> p = minimum(root);
        if (p != null) {
            return p.data;
        }
        return null;
    }

    public void preOrder(AVLNode<T> localNode) {
        if (localNode != null) {
            System.out.print(localNode.data+ " ");
            preOrder(localNode.left);
            preOrder(localNode.right);
        }
    }

    public void preOrderStack(AVLNode node) {
        List<T> list = new LinkedList<>();
        Stack<AVLNode> stack = new Stack<>();
        stack.push(node);
        while (node != null && !stack.isEmpty()) {
            node = stack.pop();
            list.add((T) node.data);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        list.forEach(e -> System.out.print(e + " "));
    }

}
