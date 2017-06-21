package com.algo_ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<KeyType extends Comparable<KeyType>> extends Tree {

    /**
     * 中序遍历, 亦为有序遍历，中根遍历
     */
    protected void inOrder(BinaryTreeNode node, Consumer c) {
        if (node != null) {
            inOrder(node.left, c);
            c.accept(node);
            inOrder(node.right, c);
        }
    }

    public void inOrder(Consumer<BinaryTreeNode> c) {
        inOrder((BinaryTreeNode) mRoot, c);
    }


    /**
     * 前序遍历， 先根遍历
     */
    protected void preOrder(BinaryTreeNode node, Consumer c) {
        if (node != null) {
            c.accept(node);
            preOrder(node.left, c);
            preOrder(node.right, c);
        }
    }

    public void preOrder(Consumer c) {
        preOrder((BinaryTreeNode) mRoot, c);
    }


    /**
     * 后序遍历"Binary树"， 后根遍历, DFS
     */
    protected void postOrder(BinaryTreeNode node, Consumer c) {
        if (node != null) {
            postOrder(node.left, c);
            postOrder(node.right, c);
            c.accept(node);
        }
    }

    public void postOrder(Consumer c) {
        postOrder((BinaryTreeNode) mRoot, c);
    }

    /**
     * 层次遍历，BFS
     */
    public void levelorder(Consumer c) {
        Queue queue = new LinkedList();
        queue.add(mRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = (BinaryTreeNode) queue.poll();
            c.accept(node);
            if (node.hasLeftChild())
                queue.add(node.left);
            if (node.hasRightChild())
                queue.add(node.right);
        }
    }


    /**
     * (递归实现)查找键值为key的节点
     *
     * @return null if not found, then return the found node.
     */
    protected BinaryTreeNode<KeyType> search(BinaryTreeNode<KeyType> node, KeyType key) {
        if (node == null)
            return null;

        int cmp = key.compareTo((KeyType) node.key);
        if (cmp < 0)
            return search(node.left, key);
        else if (cmp > 0)
            return search(node.right, key);
        else
            return node;
    }

    public BinaryTreeNode<KeyType> search(KeyType key) {
        return search((BinaryTreeNode) mRoot, key);
    }


    /**
     * (非递归实现)查找"Binary树x"中键值为key的节点
     */
    protected BinaryTreeNode<KeyType> iterativeSearch(BinaryTreeNode<KeyType> node, KeyType key) {
        while (node != null) {
            int cmp = key.compareTo((KeyType) node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    public BinaryTreeNode<KeyType> iterativeSearch(KeyType key) {
        return iterativeSearch((BinaryTreeNode) mRoot, key);
    }


    /**
     * 查找最小结点：返回tree为根结点的Binary树的最小结点。
     */
    protected BinaryTreeNode<KeyType> minimum(BinaryTreeNode<KeyType> node) {
        if (node == null)
            return null;
        while (node.left != null)
            node = node.left;
        return node;
    }

    public KeyType minimum() {
        BinaryTreeNode<KeyType> p = minimum((BinaryTreeNode) mRoot);
        return p != null ? (KeyType) (p.key) : null;
    }

    /**
     * 查找最大结点：返回tree为根结点的Binary树的最大结点。
     */
    protected BinaryTreeNode<KeyType> maximum(BinaryTreeNode<KeyType> node) {
        if (node == null)
            return null;

        while (node.right != null)
            node = node.right;
        return node;
    }

    public KeyType maximum() {
        BinaryTreeNode<KeyType> p = maximum((BinaryTreeNode) mRoot);
        return p != null ? (KeyType) (p.key) : null;
    }


    protected BinaryTreeNode<KeyType> createBinaryTreeNode(KeyType key, BinaryTreeNode<KeyType> left, BinaryTreeNode<KeyType> right, BinaryTreeNode<KeyType> parent) {
        return new BinaryTreeNode<KeyType>(key, left, right, parent);
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     * <p>
     * 参数说明：
     * tree AVL树的根结点
     * key 插入的结点的键值
     * 返回值：
     * 根节点
     */
    protected BinaryTreeNode<KeyType> insert(BinaryTreeNode<KeyType> node, KeyType key) {
        if (node == null) {
            node = createBinaryTreeNode(key, null, null, null);
//            node = new BinaryTreeNode<KeyType>(key, null, null, null);
            this.size++;
        } else {
            int cmp = key.compareTo((KeyType) (node.key));
            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.left = insert(node.left, key);
                node.left.parent = node;
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.right = insert(node.right, key);
                node.right.parent = node;
            } else {    // cmp==0
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }
        return node;
    }

    public void insert(KeyType key) {
        mRoot = insert((BinaryTreeNode) mRoot, key);
    }

    /**
     * delete node
     *
     * @param key - the key to delete, do nothing when not found.
     */
    public void remove(KeyType key) {
        if (key == null) return;
        mRoot = this.remove((BinaryTreeNode) mRoot, key);
    }

    protected BinaryTreeNode<KeyType> removeNode(BinaryTreeNode<KeyType> node_del) {
        if (node_del.isLeaf()) {
            return null;
        } else if (!node_del.hasRightChild()) {
            return node_del.left;
        } else if (!node_del.hasLeftChild()) {
            return node_del.right;
        } else {
            //has both
            BinaryTreeNode father = node_del;
            BinaryTreeNode child = father.left;
            while (child.right != null) { //转左，然后向右到尽头
                father = child;
                child = child.right;
            }
            node_del.key = child.key;
            if (father != node_del) {
                father.right = child.left;    //重接*father的右子树
                if (father.right != null) father.right.parent = father;
            } else {
                father.left = child.left; //重接*father的右子树的左子树
                if (father.left != null) father.left.parent = father;
            }
            return node_del;
        }
    }

    /**
     * remove key under the node
     *
     * @param node
     * @param key
     * @return the new root
     */
    protected BinaryTreeNode<KeyType> remove(BinaryTreeNode<KeyType> node, KeyType key) {
        if (node == null) {
            return null;
        } else {
            int cmp = key.compareTo((KeyType) (node.key));
            if (cmp < 0) {
                node.left = remove(node.left, key);
                if (node.left != null) node.left.parent = node;
                return node;
            } else if (cmp > 0) {
                node.right = remove(node.right, key);
                if (node.right != null) node.right.parent = node;
                return node;
            } else {
                this.size--;
                return removeNode(node);
            }
        }
    }

    /**
     * 另一种删除节点的方法
     *
     * @param node
     * @param key
     * @return
     */
    protected BinaryTreeNode delete(BinaryTreeNode node, Comparable key) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            node.left = delete(node.left, key);
            if (node.left != null) node.left.parent = node;
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            node.right = delete(node.right, key);
            if (node.right != null) node.right.parent = node;
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((node.left != null) && (node.right != null)) {
                //find the prer.
                BinaryTreeNode max = maximum(node.left);
                node.key = max.key;
                node.left = delete(node.left, max.key);
                if (node.left != null) node.left.parent = node;
                //or find the successor
//                AVLTreeNode min = (AVLTreeNode) minimum(node.right);
//                node.key = min.key;
//                node.right = remove(node.right, min.key);
//                if (node.right != null) ((AVLTreeNode) node.right).parent = node;
            } else {
                size--;
                return (node.left != null) ? node.left : node.right;
            }
        }
        return node;
    }

    public void delete(KeyType key) {
        if (key == null) return;
        mRoot = delete((BinaryTreeNode) mRoot, key);
    }


    /**
     * 打印"二叉查找树"
     * key        -- 节点的键值
     * direction  --
     * 0，表示该节点是根节点;
     * -1，表示该节点是它的父结点的左孩子;
     * 1，表示该节点是它的父结点的右孩子。
     */
    protected void print(BinaryTreeNode<KeyType> node, KeyType key, int direction) {
        if (node != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%s is root\n", node.key);
            else                // tree是分支节点
                System.out.printf("%s is %s's %6s child\n", node.key, key, direction == 1 ? "right" : "left");

            print(node.left, (KeyType) node.key, -1);
            print(node.right, (KeyType) node.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print((BinaryTreeNode) mRoot, (KeyType) mRoot.key, 0);
    }

    /**
     * 辅助方法，合并两个双链表
     *
     * @param a
     * @param b
     * @return
     */
    protected BinaryTreeNode append(BinaryTreeNode a, BinaryTreeNode b) {
        if (a == null) return (b);
        if (b == null) return (a);

        // 分别得到两个链表的最后一个元素
        BinaryTreeNode aLast = a.left;
        BinaryTreeNode bLast = b.left;

        // 将两个链表 头尾相连
        aLast.right = b;
        b.left = aLast;

        bLast.right = a;
        a.left = bLast;

        return (a);
    }

    /**
     * 二分查找树转化为有序的循环双链表
     * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只调整指针的指向
     * 如果不限制空间，则可以在中序遍历的过程中使用回调函数形成一个新的LinkedList即可
     * <p>
     * 递归的解决二叉树转换为双链表
     * 假定每个递归调用都会返回构建好的双链表，可把问题分解为左右两个子树。
     * 由于左右子树都已经是有序的，当前节点作为中间的一个节点，把左右子树得到的链表连接起来即可。
     * O(n),
     * n, numbers of the nodes in the bst.
     *
     * @param root
     * @return
     */
    protected BinaryTreeNode convert2CycledDoubleLL(BinaryTreeNode root) {
        if (root == null) return null;

        // 递归解决子树
        BinaryTreeNode aList = convert2CycledDoubleLL(root.left);
        BinaryTreeNode bList = convert2CycledDoubleLL(root.right);

        // 把根节点转换为一个节点的双链表。方便后面的链表合并
        root.left = root;
        root.right = root;

        //合并之后即为升序排列，顺序为 (aList, root, bList)
        aList = append(aList, root);
        aList = append(aList, bList);

        return (aList);
    }


    public BinaryTreeNode convert2CycledDoubleLL() {
        return convert2CycledDoubleLL((BinaryTreeNode) mRoot);
    }
}
