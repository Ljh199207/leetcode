package com.ljh.dsa.tree.serach;

import java.util.*;

/**
 * @author ljh 二叉搜索树
 * @date 2020-04-17 14:27
 */
public class BinaryTree {

    Node root;


    public void insert(int data) {
        Node newNode = new Node();
        newNode.val = data;
        if (root == null) {
            //如果是第一个节点，也就是根节点为null,直接创建一个新的节点即可　
            root = newNode;
        } else {
            Node current = root;
            //current节点的父节点
            Node parent;
            while (true) {
                parent = current;
                //如果插入的值小于当前节点的值，从左子树查找
                if (data < current.val) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node findNode(int val) {
        Node current = root;
        while (val != current.val) {
            if (val > current.val) {
                current = current.right;
            } else {
                current = current.left;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    //删除节点
    public boolean remove(int val) {
        Node current = root;
        Node parent = root;
        boolean isLeft = false;
        boolean isRight = false;
        //查找所要删除的节点的左子节点还是右子节点
        while (val != current.val) {
            parent = current;
            isLeft = false;
            isRight = false;
            if (val < current.val) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isRight = true;
            }
        }
        //不存在该值
        if (current == null) {
            return false;
        }
        //是叶子节点，不存在子节点
        if (current.right == null && current.left == null) {
            System.out.println("是叶子节点");
            if (isLeft) {
                parent.left = null;
            }
            if (isRight) {
                parent.right = null;
            }
            return true;
        }
        //存在左子节点
        else if (current.left != null && current.right == null) {
            System.out.println("存在左子节点");
            if (isLeft) {
                parent.left = current.left;
            } else if (isRight) {
                parent.right = current.left;
            }
            current = null;
            return true;
        }
        //存在右子节点
        else if (current.right != null && current.left == null) {
            System.out.println("存在右子节点");
            if (isLeft) {
                parent.left = current.right;
            } else if (isRight) {
                parent.right = current.left;
            }
            current = null;
            return true;
        } else {
            System.out.println("存在左右子节点");
            if (isLeft) {
                parent.left = current.right;
                Node currentLeft = current.right;
                Node parentLeft = currentLeft;
                while (currentLeft != null) {
                    parentLeft = currentLeft;
                    currentLeft = currentLeft.left;
                }
                parentLeft.left = current.left;
                current = null;
            } else if (isRight) {
                parent.right = current.right;
                Node currentLeft = current.right;
                Node parentLeft = currentLeft;
                while (current != null) {
                    parentLeft = currentLeft;
                    currentLeft = currentLeft.left;
                }
                parentLeft.left = current.left;
                current = null;
            }
            return true;
        }
    }

    public void preOrder(Node localNode) {
        if (localNode != null) {
            System.out.print(localNode.val + " ");
            preOrder(localNode.left);
            preOrder(localNode.right);
        }
    }

    public void preOrderStack(Node node) {
        List<Integer> list = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (node != null && !stack.isEmpty()) {
            node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        list.forEach(e -> System.out.print(e + " "));
    }


    public void inOrder(Node localNode) {
        if (localNode != null) {
            inOrder(localNode.left);
            System.out.print(localNode.val + " ");
            inOrder(localNode.right);
        }
    }

    public void inOrderStack(Node node) {
        List<Integer> list = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        list.forEach(e -> System.out.print(e + " "));
    }

    public void postOrder(Node localNode) {
        if (localNode != null) {
            postOrder(localNode.left);
            postOrder(localNode.right);
            System.out.print(localNode.val + " ");
        }
    }

    public void postOrderStack(Node node) {
        List<Integer> list = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node last = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == last) {
                list.add(node.val);
                stack.pop();
                last = node;
                node = null;
            } else {
                node = node.right;
            }
        }
        list.forEach(e -> System.out.print(e + " "));
    }

    public void levelOrder(Node node) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            //创建临时数据存放每一层数据
            List<Integer> tmp = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node n = queue.poll();
                tmp.add(n.val);
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            list.add(tmp);
        }
        list.forEach(e -> e.forEach(r -> System.out.println(r)));
    }

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public void levelOrder2(Node node) {
        if (node != null) {
            List<List<Integer>> lists = helper(node, 0);
            lists.forEach(e -> e.forEach(r -> System.out.print(r + " ")));
        }
    }

    public List<List<Integer>> helper(Node node, Integer level) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        if (node.left != null) {
            helper(node.left, level + 1);
        }
        if (node.right != null) {
            helper(node.right, level + 1);
        }
        return res;
    }

}
