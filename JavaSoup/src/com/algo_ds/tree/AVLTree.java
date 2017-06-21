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
     * LL：左左对应的情况(左单旋转)。
     * <p>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode leftLeftRotation(AVLTreeNode oldroot) {
        AVLTreeNode newroot = (AVLTreeNode) oldroot.left;
        oldroot.left = newroot.right;
        if (oldroot.left != null) ((AVLTreeNode) oldroot.left).parent = oldroot;

        newroot.parent = oldroot.parent;
        if (oldroot.getLeft()) ((AVLTreeNode) newroot.parent).left = newroot;
        else if (oldroot.getRight()) ((AVLTreeNode) newroot.parent).right = newroot;

        newroot.right = oldroot;
        oldroot.parent = newroot;

        //update the height
        oldroot.height = Math.max(height((AVLTreeNode) oldroot.left), height((AVLTreeNode) oldroot.right)) + 1;
        newroot.height = Math.max(height((AVLTreeNode) newroot.left), oldroot.height) + 1;

        return newroot;
    }

    /**
     * RR：右右对应的情况(右单旋转)。
     * <p>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode rightRightRotation(AVLTreeNode oldroot) {
        AVLTreeNode newroot = (AVLTreeNode) oldroot.right;
        oldroot.right = newroot.left;
        if (oldroot.right != null) ((AVLTreeNode) oldroot.right).parent = oldroot;

        newroot.parent = oldroot.parent;
        if (oldroot.getLeft()) ((AVLTreeNode) newroot.parent).left = newroot;
        else if (oldroot.getRight()) ((AVLTreeNode) newroot.parent).right = newroot;

        newroot.left = oldroot;
        oldroot.parent = newroot;

        oldroot.height = Math.max(height((AVLTreeNode) oldroot.left), height((AVLTreeNode) oldroot.right)) + 1;
        newroot.height = Math.max(height((AVLTreeNode) newroot.right), oldroot.height) + 1;

        return newroot;
    }

    /**
     * LR：左右对应的情况(左双旋转)。
     * <p>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode leftRightRotation(AVLTreeNode oldroot) {
        rightRightRotation((AVLTreeNode) oldroot.left);
        return leftLeftRotation(oldroot);
    }

    /**
     * RL：右左对应的情况(右双旋转)。
     * <p>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode rightLeftRotation(AVLTreeNode oldroot) {
        leftLeftRotation((AVLTreeNode) oldroot.right);
        return rightRightRotation(oldroot);
    }

    @Override
    protected BinaryTreeNode createBinaryTreeNode(Comparable key, BinaryTreeNode left, BinaryTreeNode right, BinaryTreeNode parent) {
        return new AVLTreeNode(key, (AVLTreeNode) left, (AVLTreeNode) right, (AVLTreeNode) parent);
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
    @Override
    protected BinaryTreeNode insert(BinaryTreeNode node_btn, Comparable key) {
        AVLTreeNode node = (AVLTreeNode) node_btn;
//        BinaryTreeNode node = node_btn;
        if (node == null) {
            node = (AVLTreeNode) createBinaryTreeNode(key, null, null, null);
//            node = new AVLTreeNode(key, null, null, null);
            this.size++;
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.left = insert(node.left, key);
                node.left.parent = node;
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((AVLTreeNode) node.left) - height((AVLTreeNode) node.right) == 2) {
                    if (key.compareTo(node.left.key) < 0)
                        node = leftLeftRotation(node);
                    else
                        node = leftRightRotation(node);
                }
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.right = insert(node.right, key);
                node.right.parent = node;
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((AVLTreeNode) node.right) - height((AVLTreeNode) node.left) == 2) {
                    if (key.compareTo(node.right.key) > 0)
                        node = rightRightRotation(node);
                    else
                        node = rightLeftRotation(node);
                }
            } else {    // cmp==0
                repeatedFoundWhenInsert(node);
            }
        }

        node.height = Math.max(height((AVLTreeNode) node.left), height((AVLTreeNode) node.right)) + 1;

        return node;
    }

    /**
     * 删除结点(z)，返回根节点
     * <p>
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
            node.left = remove(node.left, key);
            if (node.left != null) node.left.parent = node;

            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((AVLTreeNode) node.right) - height((AVLTreeNode) node.left) == 2) {
                AVLTreeNode r = (AVLTreeNode) node.right;
                if (height((AVLTreeNode) r.left) > height((AVLTreeNode) r.right))
                    node = rightLeftRotation(node);
                else
                    node = rightRightRotation(node);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            node.right = remove(node.right, key);
            if (node.right != null) node.right.parent = node;

            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((AVLTreeNode) node.left) - height((AVLTreeNode) node.right) == 2) {
                AVLTreeNode l = (AVLTreeNode) node.left;
                if (height((AVLTreeNode) l.right) > height((AVLTreeNode) l.left))
                    node = leftRightRotation(node);
                else
                    node = leftLeftRotation(node);
            }
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((node.left != null) && (node.right != null)) {
                if (height((AVLTreeNode) node.left) > height((AVLTreeNode) node.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，node以下是平衡的，整个AVL树则未必是平衡的。
                    // 采用这种方式的好处是：可以减少一些旋转的次数，因为删除存在多次旋转的可能性，理论上应该有logn次。
                    // 具体来讲就是调用remove((AVLTreeNode) node.left, max.key);后仍是平衡的，因为左子树
                    // 虽可能经过多次旋转，但其高度最大减1，所以在删除后，可以保证node平衡。
                    AVLTreeNode max = (AVLTreeNode) maximum(node.left);
                    node.replaceNodeData(max);
                    node.left = remove(node.left, max.key);
                    if (node.left != null) node.left.parent = node;
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：同上。
                    AVLTreeNode min = (AVLTreeNode) minimum(node.right);
                    node.replaceNodeData(min);
                    node.right = remove(node.right, min.key);
                    if (node.right != null) ((AVLTreeNode) node.right).parent = node;
                }
            } else {
                this.size--;
                return (node.left != null) ? node.left : node.right;
            }
        }
        node.height = Math.max(height((AVLTreeNode) node.left), height((AVLTreeNode) node.right)) + 1;
        return node;
    }


    /**
     * function to call when found repeated key.
     */
    protected void repeatedFoundWhenInsert(AVLTreeNode node) {
        System.out.println("添加失败：不允许添加相同的节点！");
    }
}
