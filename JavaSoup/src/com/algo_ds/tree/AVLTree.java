package com.algo_ds.tree;

public class AVLTree<KeyType extends Comparable<KeyType>> extends BinaryTree {
    /**
     * 获取树的高度
     */
    protected int height(AVLTreeNode node) {
        return node != null ? node.height : 0;
    }

    public int height() {
        return height((AVLTreeNode) mRoot);
    }

    /**
     * 比较两个值的大小
     */
    protected static int max(int a, int b) {
        return a > b ? a : b;
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
        oldroot.height = max(height((AVLTreeNode) oldroot.leftChild), height((AVLTreeNode) oldroot.rightChild)) + 1;
        newroot.height = max(height((AVLTreeNode) newroot.leftChild), oldroot.height) + 1;

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

        oldroot.height = max(height((AVLTreeNode) oldroot.leftChild), height((AVLTreeNode) oldroot.rightChild)) + 1;
        newroot.height = max(height((AVLTreeNode) newroot.rightChild), oldroot.height) + 1;

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

    /**
     * 将结点插入到AVL树中，并返回根节点
     * <p/>
     * 参数说明：
     * tree AVL树的根结点
     * key 插入的结点的键值
     * 返回值：
     * 根节点
     */
    @Override
    protected BinaryTreeNode insert(BinaryTreeNode node_btn, Comparable key) {
        AVLTreeNode node = (AVLTreeNode) node_btn;
        if (node == null) {
            node = new AVLTreeNode(key, null, null, null);
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
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }

        node.height = max(height((AVLTreeNode) node.leftChild), height((AVLTreeNode) node.rightChild)) + 1;

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
    @Override
    protected BinaryTreeNode remove(BinaryTreeNode node1, Comparable key) {
        AVLTreeNode node = (AVLTreeNode) node1;
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
                    // 具体来讲就是调用remove((AVLTreeNode) node.leftChild, max.key);后仍是平衡的，因为左子树
                    // 虽可能经过多次旋转，但其高度最大减1，所以在删除后，可以保证node平衡。
                    AVLTreeNode max = (AVLTreeNode) maximum(node.leftChild);
                    node.key = max.key;
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
                    node.key = min.key;
                    node.rightChild = remove(node.rightChild, min.key);
                    if (node.rightChild != null) ((AVLTreeNode) node.rightChild).parent = node;
                }
            } else {
                return (node.leftChild != null) ? node.leftChild : node.rightChild;
            }
        }
        node.height = max(height((AVLTreeNode) node.leftChild), height((AVLTreeNode) node.rightChild)) + 1;
        return node;
    }


    public static void main(String[] args) {
//        int arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9}; //very unbalanced tree.
//        int arr[] = {3, 4, 6, 2, 5, 1}; //simple example.
//        int arr[] = {7, 4, 8, 3, 5, 9, 6}; //test for delete 9, one rotation.
        int arr[] = {10, 6, 13, 4, 8, 12, 14, 3, 5, 7, 9, 11, 2}; // delete 14,会有两次旋转

        AVLTree<Integer> tree = new AVLTree<Integer>();

        System.out.printf("== 依次添加: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            tree.insert(arr[i]);
        }

        System.out.printf("\n== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();

        System.out.printf("\n== 层次遍历: ");
        tree.levelorder();
        System.out.printf("\n");

        System.out.printf("== 高度: %d\n", tree.height());
        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 大小： %d\n", tree.getSize());
        System.out.printf("== 树的详细信息: \n");
        tree.print();

        int del = 14;
        System.out.printf("\n== 删除根节点: %d", del);
        tree.remove(del);

        System.out.printf("\n== 高度: %d", tree.height());
        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
        System.out.printf("\n== 树的详细信息: \n");
        tree.print();
    }
}
