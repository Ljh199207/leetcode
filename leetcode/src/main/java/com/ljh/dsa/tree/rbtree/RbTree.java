package com.ljh.dsa.tree.rbtree;

/**
 * @author ljh
 * @date 2020-04-22 09:07
 */
public class RbTree<T extends Comparable<T>> extends RBNode {

    RBNode root;

    public RbTree(boolean color, Comparable key, RBNode parent, RBNode left, RBNode right) {
        super(color, key, parent, left, right);
    }

    /*************对红黑树节点x进行左旋操作 ******************/
    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x) {
        //  1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y;
        y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
      /*  int cmp = x.key.compareTo((T) x.parent.key);
        if (cmp > 0) {
            y = x.parent.right;
        } else {
            y = x.parent.left;
        }*/
        if (x.parent == null) {
            this.root = y;
        }
        if (x == x.parent.left) {
            x.parent.left = y;//则也将y设为左子节点
        } else {
            x.parent.right = y;
        }
        //3. 将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }


    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y) {
        //1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        //2将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        }
        if (y.parent.left == y) {
            y.parent.left = x;
        }
        if (y.parent.right == y) {
            y.parent.right = x;
        }
        // 3. 将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }


    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(RED, key, null, null, null);
        if (node != null) {
            insert(node);
        }
    }

    public void insert(RBNode<T> node) {
        RBNode<T> current = null; //表示最后node的父节点
        RBNode<T> x = this.root; //用来向下搜索

        //1.找到插入位置
        while (x != null) {
            current = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else {
                x = x.left;
            }
        }
        node.parent = current;
        //2.接下来判断node是左子节点还是右子节点
        if (current != null) {
            int cmp = node.key.compareTo(current.key);
            if (cmp > 0) {
                current.right = node;
            } else {
                current.left = node;
            }
        } else {
            this.root = node;
        }
        //3.利用旋转操作将其修正为一颗红黑树
        insertFixUp(node);
    }

    private RBNode<T> parentOf(RBNode<T> node) {
        return node != null ? node.parent : null;
    }

    private boolean colorOf(RBNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBNode<T> node) {
        return ((node != null) && (node.color == RED)) ? true : false;
    }

    private boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBNode<T> node) {
        if (node != null)
            node.color = BLACK;
    }

    private void setRed(RBNode<T> node) {
        if (node != null)
            node.color = RED;
    }

    private void setParent(RBNode<T> node, RBNode<T> parent) {
        if (node != null)
            node.parent = parent;
    }

    private void setColor(RBNode<T> node, boolean color) {
        if (node != null)
            node.color = color;
    }

    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent;//定义父节点和祖父节点
        //需要修正的条件：父节点存在，且父节点的颜色是红色
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);
            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBNode<T> uncle = gparent.right;
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RBNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    setBlack(parent);
                    setRed(gparent);
                    rightRotate(gparent);
                }
            } else {
                // Case 1条件：叔叔节点是红色
                RBNode<T> uncle = gparent.left;
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    RBNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        // 将根节点设为黑色
        setBlack(this.root);
    }





}
