package com.algo_ds.tree;


/**
 * SizeAvLTree 在avl的基础上，可以计算左右孩子的结点数。
 * 目前对于leftcnt, rightcnt, inverse cnt的维护，只支持插入操作的维护，并没有维护删除操作时这两个结点的信息。
 *
 * @param <KeyType>
 */
public class SizeAVLTree<KeyType extends Comparable<KeyType>> extends AVLTree {

    /**
     * tree variables
     */
    private int greatInverseCnt = 0; //the inversions counter, is the ele who large that it.
    private int lessInverseCnt = 0; //the inversions counter, is the ele who less than it.
    private static boolean inserted = true; //flag of if insert or this is a repeated node.

    /**
     * getters.
     */
    public int getGreatInverseCnt() {
        return greatInverseCnt;
    }

    public int getLessInverseCnt() {
        return lessInverseCnt;
    }

    /**
     * LL：左左对应的情况(左单旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode leftLeftRotation(AVLTreeNode oldroot1) {
        //call the super method
        SizeAVLTreeNode newroot = (SizeAVLTreeNode) super.leftLeftRotation(oldroot1);
        SizeAVLTreeNode oldroot = (SizeAVLTreeNode) oldroot1;

        //updathe the leftcnt and end cnt;
        (oldroot).leftCnt = newroot.rightCnt;
        newroot.rightCnt += oldroot.rightCnt + 1;

        return newroot;
    }

    /**
     * RR：右右对应的情况(右单旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected AVLTreeNode rightRightRotation(AVLTreeNode oldroot1) {
        SizeAVLTreeNode newroot = (SizeAVLTreeNode) super.rightRightRotation(oldroot1);
        SizeAVLTreeNode oldroot = (SizeAVLTreeNode) oldroot1;

        //updatet the leftcnt and end cnt;
        oldroot.rightCnt = newroot.leftCnt;
        newroot.leftCnt += oldroot.leftCnt + 1;

        return newroot;
    }

    @Override
    protected BinaryTreeNode insert(BinaryTreeNode node_btn, Comparable key) {
        SizeAVLTreeNode node = (SizeAVLTreeNode) node_btn;
        if (node == null) { //recursive end;
            node = new SizeAVLTreeNode(key, null, null, null);
            size++;
            inserted = true; //set default
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.left = insert(node.left, key);
                node.left.parent = node;
                if (inserted) {
                    node.leftCnt++;
                    this.greatInverseCnt += node.rightCnt + 1;
                }
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((SizeAVLTreeNode) node.left) - height((SizeAVLTreeNode) node.right) == 2) {
                    if (key.compareTo(node.left.key) < 0)
                        node = (SizeAVLTreeNode) leftLeftRotation(node);
                    else
                        node = (SizeAVLTreeNode) leftRightRotation(node);
                }
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.right = insert(node.right, key);
                node.right.parent = node;
                if (inserted) {
                    node.rightCnt++;
                    this.lessInverseCnt += node.leftCnt + 1;
                }
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((AVLTreeNode) node.right) - height((AVLTreeNode) node.left) == 2) {
                    if (key.compareTo(node.right.key) > 0)
                        node = (SizeAVLTreeNode) rightRightRotation(node);
                    else
                        node = (SizeAVLTreeNode) rightLeftRotation(node);
                }
            } else {    // cmp==0, recursive end;
                inserted = false;
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }

        node.height = Math.max(height((AVLTreeNode) node.left), height((AVLTreeNode) node.right)) + 1;

        return node;
    }
}
