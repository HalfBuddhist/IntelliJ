package com.algo_ds.tree.no_inherit;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {

    protected AVLTreeNode<T> mRoot = null;   // 根结点
    protected int size = 0;

    /**
     * 获取树的高度
     */
    protected int height(AVLTreeNode node) {
        return node != null ? node.height : 0;
    }

    public int height() {
        return height((AVLTreeNode) mRoot);
    }

    public AVLTreeNode<T> search(T key) {
        return search((AVLTreeNode) mRoot, key);
    }

    /**
     * (递归实现)查找键值为key的节点
     *
     * @return null if not found, then return the found node.
     */
    private AVLTreeNode<T> search(AVLTreeNode<T> node, T key) {
        if (node == null)
            return null;

        int cmp = key.compareTo((T) node.key);
        if (cmp < 0)
            return search(node.leftChild, key);
        else if (cmp > 0)
            return search(node.rightChild, key);
        else
            return node;
    }

    /**
     * LL：左左对应的情况(左单旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode leftLeftRotation(AVLTreeNode oldroot) {
        AVLTreeNode newroot = (AVLTreeNode) oldroot.leftChild;
        oldroot.leftChild = newroot.rightChild;
        if (oldroot.leftChild != null) ((AVLTreeNode) oldroot.leftChild).parent = oldroot;

        newroot.parent = oldroot.parent;
        if (oldroot.isLeftChild()) ((AVLTreeNode) newroot.parent).leftChild = newroot;
        else if (oldroot.isRightChild()) ((AVLTreeNode) newroot.parent).rightChild = newroot;

        newroot.rightChild = oldroot;
        oldroot.parent = newroot;

        //update the height
        oldroot.height = Math.max(height((AVLTreeNode) oldroot.leftChild), height((AVLTreeNode) oldroot.rightChild)) + 1;
        newroot.height = Math.max(height((AVLTreeNode) newroot.leftChild), oldroot.height) + 1;

        return newroot;
    }

    /**
     * RR：右右对应的情况(右单旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode rightRightRotation(AVLTreeNode oldroot) {
        AVLTreeNode newroot = (AVLTreeNode) oldroot.rightChild;
        oldroot.rightChild = newroot.leftChild;
        if (oldroot.rightChild != null) ((AVLTreeNode) oldroot.rightChild).parent = oldroot;

        newroot.parent = oldroot.parent;
        if (oldroot.isLeftChild()) ((AVLTreeNode) newroot.parent).leftChild = newroot;
        else if (oldroot.isRightChild()) ((AVLTreeNode) newroot.parent).rightChild = newroot;

        newroot.leftChild = oldroot;
        oldroot.parent = newroot;

        oldroot.height = Math.max(height((AVLTreeNode) oldroot.leftChild), height((AVLTreeNode) oldroot.rightChild)) + 1;
        newroot.height = Math.max(height((AVLTreeNode) newroot.rightChild), oldroot.height) + 1;

        return newroot;
    }

    /**
     * LR：左右对应的情况(左双旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode leftRightRotation(AVLTreeNode oldroot) {
        rightRightRotation((AVLTreeNode) oldroot.leftChild);
        return leftLeftRotation(oldroot);
    }

    /**
     * RL：右左对应的情况(右双旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode rightLeftRotation(AVLTreeNode oldroot) {
        leftLeftRotation((AVLTreeNode) oldroot.rightChild);
        return rightRightRotation(oldroot);
    }

    protected AVLTreeNode createBinaryTreeNode(Comparable key, AVLTreeNode left, AVLTreeNode right, AVLTreeNode parent) {
        return new AVLTreeNode(key, (AVLTreeNode) left, (AVLTreeNode) right, (AVLTreeNode) parent);
    }

    public void insert(T key) {
        mRoot = insert((AVLTreeNode) mRoot, key);
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
    protected AVLTreeNode insert(AVLTreeNode node_btn, Comparable key) {
        AVLTreeNode node = (AVLTreeNode) node_btn;
        if (node == null) {
            node = (AVLTreeNode) createBinaryTreeNode(key, null, null, null);
            this.size++;
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.leftChild = insert(node.leftChild, key);
                node.leftChild.parent = node;
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((AVLTreeNode) node.leftChild) - height((AVLTreeNode) node.rightChild) == 2) {
                    if (key.compareTo(node.leftChild.key) < 0)
                        node = leftLeftRotation(node);
                    else
                        node = leftRightRotation(node);
                }
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.rightChild = insert(node.rightChild, key);
                node.rightChild.parent = node;
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((AVLTreeNode) node.rightChild) - height((AVLTreeNode) node.leftChild) == 2) {
                    if (key.compareTo(node.rightChild.key) > 0)
                        node = rightRightRotation(node);
                    else
                        node = rightLeftRotation(node);
                }
            } else {    // cmp==0
                repeatedFoundWhenInsert(node);
            }
        }

        node.height = Math.max(height((AVLTreeNode) node.leftChild), height((AVLTreeNode) node.rightChild)) + 1;

        return node;
    }

    /**
     * 删除结点(z)，返回根节点
     * <p/>
     * 参数说明：
     * tree AVL树的根结点
     * z 待删除的结点
     * 返回值：
     * 根节点
     */
    protected AVLTreeNode remove(AVLTreeNode node, Comparable key) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            node.leftChild = remove(node.leftChild, key);
            if (node.leftChild != null) node.leftChild.parent = node;

            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((AVLTreeNode) node.rightChild) - height((AVLTreeNode) node.leftChild) == 2) {
                AVLTreeNode r = (AVLTreeNode) node.rightChild;
                if (height((AVLTreeNode) r.leftChild) > height((AVLTreeNode) r.rightChild))
                    node = rightLeftRotation(node);
                else
                    node = rightRightRotation(node);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            node.rightChild = remove(node.rightChild, key);
            if (node.rightChild != null) node.rightChild.parent = node;

            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((AVLTreeNode) node.leftChild) - height((AVLTreeNode) node.rightChild) == 2) {
                AVLTreeNode l = (AVLTreeNode) node.leftChild;
                if (height((AVLTreeNode) l.rightChild) > height((AVLTreeNode) l.leftChild))
                    node = leftRightRotation(node);
                else
                    node = leftLeftRotation(node);
            }
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((node.leftChild != null) && (node.rightChild != null)) {
                if (height((AVLTreeNode) node.leftChild) > height((AVLTreeNode) node.rightChild)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，node以下是平衡的，整个AVL树则未必是平衡的。
                    // 采用这种方式的好处是：可以减少一些旋转的次数，因为删除存在多次旋转的可能性，理论上应该有logn次。
                    // 具体来讲就是调用remove((AVLTreeNode) node.left, max.key);后仍是平衡的，因为左子树
                    // 虽可能经过多次旋转，但其高度最大减1，所以在删除后，可以保证node平衡。
                    AVLTreeNode max = (AVLTreeNode) maximum(node.leftChild);
                    node.replaceNodeData(max);
                    node.leftChild = remove(node.leftChild, max.key);
                    if (node.leftChild != null) node.leftChild.parent = node;
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：同上。
                    AVLTreeNode min = (AVLTreeNode) minimum(node.rightChild);
                    node.replaceNodeData(min);
                    node.rightChild = remove(node.rightChild, min.key);
                    if (node.rightChild != null) ((AVLTreeNode) node.rightChild).parent = node;
                }
            } else {
                this.size--;
                return (node.leftChild != null) ? node.leftChild : node.rightChild;
            }
        }
        node.height = Math.max(height((AVLTreeNode) node.leftChild), height((AVLTreeNode) node.rightChild)) + 1;
        return node;
    }

    /**
     * delete node
     *
     * @param key - the key to delete, do nothing when not found.
     */
    public void remove(T key) {
        if (key == null) return;
        mRoot = this.remove((AVLTreeNode) mRoot, key);
    }


    /**
     * 查找最小结点：返回tree为根结点的Binary树的最小结点。
     */
    protected AVLTreeNode<T> minimum(AVLTreeNode<T> node) {
        if (node == null)
            return null;
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
    }

    public T minimum() {
        AVLTreeNode<T> p = minimum((AVLTreeNode) mRoot);
        return p != null ? (p.key) : null;
    }

    /**
     * 查找最大结点：返回tree为根结点的Binary树的最大结点。
     */
    protected AVLTreeNode<T> maximum(AVLTreeNode<T> node) {
        if (node == null)
            return null;

        while (node.rightChild != null)
            node = node.rightChild;
        return node;
    }

    public T maximum() {
        AVLTreeNode<T> p = maximum((AVLTreeNode) mRoot);
        return p != null ? (p.key) : null;
    }


    /**
     * function to call when found repeated key.
     */
    protected void repeatedFoundWhenInsert(AVLTreeNode node) {
        System.out.println("添加失败：不允许添加相同的节点！");
    }


    /**
     * 中序遍历, 亦为有序遍历，中根遍历
     */
    private void inOrder(AVLTreeNode node) {
        if (node != null) {
            inOrder(node.leftChild);
            System.out.print(node.key + " ");
            inOrder(node.rightChild);
        }
    }

    public void inOrder() {
        inOrder((AVLTreeNode) mRoot);
    }


    /**
     * 前序遍历， 先根遍历
     */
    private void preOrder(AVLTreeNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    public void preOrder() {
        preOrder((AVLTreeNode) mRoot);
    }


    /**
     * 后序遍历"Binary树"， 后根遍历, DFS
     */
    private void postOrder(AVLTreeNode node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.key + " ");
        }
    }

    public void postOrder() {
        postOrder((AVLTreeNode) mRoot);
    }

    /**
     * 层次遍历，BFS
     */
    public void levelorder() {
        Queue queue = new LinkedList();
        queue.add(mRoot);
        while (!queue.isEmpty()) {
            AVLTreeNode node = (AVLTreeNode) queue.poll();
            System.out.print(node.key + " ");
            if (node.hasLeftChild())
                queue.add(node.leftChild);
            if (node.hasRightChild())
                queue.add(node.rightChild);
        }
    }

    /**
     * 打印"二叉查找树"
     * key        -- 节点的键值
     * direction  --
     * 0，表示该节点是根节点;
     * -1，表示该节点是它的父结点的左孩子;
     * 1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLTreeNode<T> node, T key, int direction) {
        if (node != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%s is root\n", node.key);
            else                // tree是分支节点
                System.out.printf("%s is %s's %6s child\n", node.key, key, direction == 1 ? "end" : "start");

            print(node.leftChild, (T) node.key, -1);
            print(node.rightChild, (T) node.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print((AVLTreeNode) mRoot, (T) mRoot.key, 0);
    }

    public int getSize() {
        return size;
    }

}
