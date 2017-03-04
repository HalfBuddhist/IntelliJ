package com.algo_ds.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<KeyType extends Comparable<KeyType>> extends Tree {

    /**
     * 中序遍历, 亦为有序遍历，中根遍历
     */
    private void inOrder(BinaryTreeNode node) {
        if (node != null) {
            inOrder(node.leftChild);
            System.out.print(node.key + " ");
            inOrder(node.rightChild);
        }
    }

    public void inOrder() {
        inOrder((BinaryTreeNode) mRoot);
    }


    /**
     * 前序遍历， 先根遍历
     */
    private void preOrder(BinaryTreeNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    public void preOrder() {
        preOrder((BinaryTreeNode) mRoot);
    }


    /**
     * 后序遍历"Binary树"， 后根遍历, DFS
     */
    private void postOrder(BinaryTreeNode node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.key + " ");
        }
    }

    public void postOrder() {
        postOrder((BinaryTreeNode) mRoot);
    }

    /**
     * 层次遍历，BFS
     */
    public void levelorder() {
        Queue queue = new LinkedList();
        queue.add(mRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = (BinaryTreeNode) queue.poll();
            System.out.print(node.key + " ");
            if (node.hasLeftChild())
                queue.add(node.leftChild);
            if (node.hasRightChild())
                queue.add(node.rightChild);
        }
    }


    /**
     * (递归实现)查找键值为key的节点
     * @return null if not found, then return the found node.
     */
    private BinaryTreeNode<KeyType> search(BinaryTreeNode<KeyType> node, KeyType key) {
        if (node == null)
            return null;

        int cmp = key.compareTo((KeyType) node.key);
        if (cmp < 0)
            return search(node.leftChild, key);
        else if (cmp > 0)
            return search(node.rightChild, key);
        else
            return node;
    }

    public BinaryTreeNode<KeyType> search(KeyType key) {
        return search((BinaryTreeNode) mRoot, key);
    }


    /**
     * (非递归实现)查找"Binary树x"中键值为key的节点
     */
    private BinaryTreeNode<KeyType> iterativeSearch(BinaryTreeNode<KeyType> node, KeyType key) {
        while (node != null) {
            int cmp = key.compareTo((KeyType) node.key);
            if (cmp < 0)
                node = node.leftChild;
            else if (cmp > 0)
                node = node.rightChild;
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
        while (node.leftChild != null)
            node = node.leftChild;
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

        while (node.rightChild != null)
            node = node.rightChild;
        return node;
    }

    public KeyType maximum() {
        BinaryTreeNode<KeyType> p = maximum((BinaryTreeNode) mRoot);
        return p != null ? (KeyType) (p.key) : null;
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     * <p/>
     * 参数说明：
     * tree AVL树的根结点
     * key 插入的结点的键值
     * 返回值：
     * 根节点
     */
    protected BinaryTreeNode<KeyType> insert(BinaryTreeNode<KeyType> node, KeyType key) {
        if (node == null) {
            node = new BinaryTreeNode<KeyType>(key, null, null, null);
            this.size++;
        } else {
            int cmp = key.compareTo((KeyType) (node.key));
            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.leftChild = insert(node.leftChild, key);
                node.leftChild.parent = node;
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.rightChild = insert(node.rightChild, key);
                node.rightChild.parent = node;
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
            return node_del.leftChild;
        } else if (!node_del.hasLeftChild()) {
            return node_del.rightChild;
        } else {
            //has both
            BinaryTreeNode father = node_del;
            BinaryTreeNode child = father.leftChild;
            while (child.rightChild != null) { //转左，然后向右到尽头
                father = child;
                child = child.rightChild;
            }
            node_del.key = child.key;
            if (father != node_del) {
                father.rightChild = child.leftChild;    //重接*father的右子树
                if (father.rightChild != null) father.rightChild.parent = father;
            } else {
                father.leftChild = child.leftChild; //重接*father的右子树的左子树
                if (father.leftChild != null) father.leftChild.parent = father;
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
                node.leftChild = remove(node.leftChild, key);
                if (node.leftChild != null) node.leftChild.parent = node;
                return node;
            } else if (cmp > 0) {
                node.rightChild = remove(node.rightChild, key);
                if (node.rightChild != null) node.rightChild.parent = node;
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
            node.leftChild = delete(node.leftChild, key);
            if (node.leftChild != null) node.leftChild.parent = node;
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            node.rightChild = delete(node.rightChild, key);
            if (node.rightChild != null) node.rightChild.parent = node;
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((node.leftChild != null) && (node.rightChild != null)) {
                //find the prer.
                BinaryTreeNode max = maximum(node.leftChild);
                node.key = max.key;
                node.leftChild = delete(node.leftChild, max.key);
                if (node.leftChild != null) node.leftChild.parent = node;
                //or find the successor
//                AVLTreeNode min = (AVLTreeNode) minimum(node.rightChild);
//                node.key = min.key;
//                node.rightChild = remove(node.rightChild, min.key);
//                if (node.rightChild != null) ((AVLTreeNode) node.rightChild).parent = node;
            } else {
                size--;
                return (node.leftChild != null) ? node.leftChild : node.rightChild;
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
    private void print(BinaryTreeNode<KeyType> node, KeyType key, int direction) {
        if (node != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%s is root\n", node.key);
            else                // tree是分支节点
                System.out.printf("%s is %s's %6s child\n", node.key, key, direction == 1 ? "right" : "left");

            print(node.leftChild, (KeyType) node.key, -1);
            print(node.rightChild, (KeyType) node.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print((BinaryTreeNode) mRoot, (KeyType) mRoot.key, 0);
    }


    public static void main(String[] argv) {
        int arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
//        int arr[] = {3, 4, 6, 2, 5, 1};

        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        System.out.printf("== 依次添加: ");
        for (int i : arr) {
            System.out.printf("insert %d ", i);
            tree.insert(i);
        }

        System.out.printf("\n== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();
        System.out.printf("\n");

        System.out.printf("\n== 层次遍历: ");
        tree.levelorder();
        System.out.printf("\n");

        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 数量值: %d\n", tree.getSize());
        System.out.printf("== 树的详细信息: \n");
        tree.print();

        //test the find func
        System.out.println(tree.search(5));
        System.out.println(tree.iterativeSearch(5));

        int i = 7;
        System.out.printf("\n== 删除节点: %d", i);
        tree.delete(i);

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
        System.out.printf("\n== 树的详细信息: \n");
        tree.print();
        System.out.printf("== 数量值: %d\n", tree.getSize());
        System.out.println("hello world!");
    }

}
