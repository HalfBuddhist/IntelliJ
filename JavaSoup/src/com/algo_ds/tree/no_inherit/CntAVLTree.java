package com.algo_ds.tree.no_inherit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * CntAVLTree 在avl的基础上，可以统计下面的结点数。
 * 支持增，删（按指定key删，按顺序号删），查（按key查，按顺序号查，最大最小）
 * 改（可转化为删与增）
 */
public class CntAVLTree<T extends Comparable<T>> {

    protected CntAVLTreeNode<T> mRoot = null;   // 根结点
    protected int size = 0;
    public int greatInverseCnt = 0; //the inversions counter, is the ele who large that it.
    public int lessInverseCnt = 0; //the inversions counter, is the ele who less than it.
    private boolean inserted = true; //flag of if insert or this is a repeated node.
    private boolean removed = true; //flag of if remove or this is no this node.
    private T removed_key; // used in k-remove

    /**
     * 获取树的高度
     */
    protected int height(CntAVLTreeNode node) {
        return node != null ? node.height : 0;
    }

    public int height() {
        return height((CntAVLTreeNode) mRoot);
    }

    public CntAVLTreeNode<T> search(T key) {
        return search((CntAVLTreeNode) mRoot, key);
    }

    /**
     * (递归实现)查找键值为key的节点
     *
     * @return null if not found, then return the found node.
     */
    private CntAVLTreeNode<T> search(CntAVLTreeNode<T> node, T key) {
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
    protected CntAVLTreeNode leftLeftRotation(CntAVLTreeNode oldroot) {
        CntAVLTreeNode newroot = oldroot.leftChild;
        oldroot.leftChild = newroot.rightChild;
        if (oldroot.leftChild != null) (oldroot.leftChild).parent = oldroot;

        newroot.parent = oldroot.parent;
        if (oldroot.isLeftChild()) ((CntAVLTreeNode) newroot.parent).leftChild = newroot;
        else if (oldroot.isRightChild()) ((CntAVLTreeNode) newroot.parent).rightChild = newroot;

        newroot.rightChild = oldroot;
        oldroot.parent = newroot;

        //update the height
        oldroot.height = Math.max(height((CntAVLTreeNode) oldroot.leftChild), height((CntAVLTreeNode) oldroot.rightChild)) + 1;
        newroot.height = Math.max(height((CntAVLTreeNode) newroot.leftChild), oldroot.height) + 1;

        //update the cnt
        newroot.cnt += (1 + (oldroot.hasRightChild() ? oldroot.rightChild.cnt : 0));
        oldroot.cnt -= (1 + (newroot.hasLeftChild() ? newroot.leftChild.cnt : 0));

        return newroot;
    }

    /**
     * RR：右右对应的情况(右单旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected CntAVLTreeNode rightRightRotation(CntAVLTreeNode oldroot) {
        CntAVLTreeNode newroot = (CntAVLTreeNode) oldroot.rightChild;
        oldroot.rightChild = newroot.leftChild;
        if (oldroot.rightChild != null) ((CntAVLTreeNode) oldroot.rightChild).parent = oldroot;

        newroot.parent = oldroot.parent;
        if (oldroot.isLeftChild()) ((CntAVLTreeNode) newroot.parent).leftChild = newroot;
        else if (oldroot.isRightChild()) ((CntAVLTreeNode) newroot.parent).rightChild = newroot;

        newroot.leftChild = oldroot;
        oldroot.parent = newroot;

        //upate height
        oldroot.height = Math.max(height((CntAVLTreeNode) oldroot.leftChild), height((CntAVLTreeNode) oldroot.rightChild)) + 1;
        newroot.height = Math.max(height((CntAVLTreeNode) newroot.rightChild), oldroot.height) + 1;

        //update cnt;
        newroot.cnt += (1 + (oldroot.hasLeftChild() ? oldroot.leftChild.cnt : 0));
        oldroot.cnt -= (1 + (newroot.hasRightChild() ? newroot.rightChild.cnt : 0));

        return newroot;
    }

    /**
     * LR：左右对应的情况(左双旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected CntAVLTreeNode leftRightRotation(CntAVLTreeNode oldroot) {
        rightRightRotation((CntAVLTreeNode) oldroot.leftChild);
        return leftLeftRotation(oldroot);
    }

    /**
     * RL：右左对应的情况(右双旋转)。
     * <p/>
     * 返回值：旋转后的根节点
     */
    protected CntAVLTreeNode rightLeftRotation(CntAVLTreeNode oldroot) {
        leftLeftRotation((CntAVLTreeNode) oldroot.rightChild);
        return rightRightRotation(oldroot);
    }

    protected CntAVLTreeNode createBinaryTreeNode(Comparable key, CntAVLTreeNode left, CntAVLTreeNode right, CntAVLTreeNode parent) {
        return new CntAVLTreeNode(key, (CntAVLTreeNode) left, (CntAVLTreeNode) right, (CntAVLTreeNode) parent);
    }

    public void insert(T key) {
        mRoot = insert((CntAVLTreeNode) mRoot, key);
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
    protected CntAVLTreeNode insert(CntAVLTreeNode node_btn, Comparable key) {
        CntAVLTreeNode node = (CntAVLTreeNode) node_btn;
        if (node == null) {
            node = (CntAVLTreeNode) createBinaryTreeNode(key, null, null, null);
            this.size++;
            this.inserted = true;
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.leftChild = insert(node.leftChild, key);
                node.leftChild.parent = node;
                if (inserted) {
                    node.cnt++;
                }
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((CntAVLTreeNode) node.leftChild) - height((CntAVLTreeNode) node.rightChild) == 2) {
                    if (key.compareTo(node.leftChild.key) < 0)
                        node = leftLeftRotation(node);
                    else
                        node = leftRightRotation(node);
                }
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.rightChild = insert(node.rightChild, key);
                node.rightChild.parent = node;
                if (inserted) {
                    node.cnt++;
                }
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height((CntAVLTreeNode) node.rightChild) - height((CntAVLTreeNode) node.leftChild) == 2) {
                    if (key.compareTo(node.rightChild.key) > 0)
                        node = rightRightRotation(node);
                    else
                        node = rightLeftRotation(node);
                }
            } else {    // cmp==0
                inserted = false;
                repeatedFoundWhenInsert(node);
            }
        }

        node.height = Math.max(height((CntAVLTreeNode) node.leftChild), height((CntAVLTreeNode) node.rightChild)) + 1;

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
    protected CntAVLTreeNode remove(CntAVLTreeNode node, Comparable key) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (node == null) {
            removed = false;
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            node.leftChild = remove(node.leftChild, key);
            if (node.leftChild != null) node.leftChild.parent = node;
            if (removed) {
                node.cnt--;
                greatInverseCnt += node.hasRightChild() ? node.rightChild.cnt + 1 : 1;
            }
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((CntAVLTreeNode) node.rightChild) - height((CntAVLTreeNode) node.leftChild) == 2) {
                CntAVLTreeNode r = (CntAVLTreeNode) node.rightChild;
                if (height((CntAVLTreeNode) r.leftChild) > height((CntAVLTreeNode) r.rightChild))
                    node = rightLeftRotation(node);
                else
                    node = rightRightRotation(node);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            node.rightChild = remove(node.rightChild, key);
            if (node.rightChild != null) node.rightChild.parent = node;
            if (removed) {
                node.cnt--;
                lessInverseCnt += node.hasLeftChild() ? node.leftChild.cnt + 1 : 1;
            }
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((CntAVLTreeNode) node.leftChild) - height((CntAVLTreeNode) node.rightChild) == 2) {
                CntAVLTreeNode l = (CntAVLTreeNode) node.leftChild;
                if (height((CntAVLTreeNode) l.rightChild) > height((CntAVLTreeNode) l.leftChild))
                    node = leftRightRotation(node);
                else
                    node = leftLeftRotation(node);
            }
        } else {
            // tree是对应要删除的节点。
            removed = true;
            // tree的左右孩子都非空
            if ((node.leftChild != null) && (node.rightChild != null)) {
                if (height((CntAVLTreeNode) node.leftChild) > height((CntAVLTreeNode) node.rightChild)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，node以下是平衡的，整个AVL树则未必是平衡的。
                    // 采用这种方式的好处是：可以减少一些旋转的次数，因为删除存在多次旋转的可能性，理论上应该有logn次。
                    // 具体来讲就是调用remove((CntAVLTreeNode) node.left, max.key);后仍是平衡的，因为左子树
                    // 虽可能经过多次旋转，但其高度最大减1，所以在删除后，可以保证node平衡。
                    CntAVLTreeNode max = (CntAVLTreeNode) maximum(node.leftChild);
                    node.replaceNodeData(max);
                    node.leftChild = remove(node.leftChild, max.key);
                    if (node.leftChild != null) node.leftChild.parent = node;
                    node.cnt--;
                    lessInverseCnt++;//add the max itself.
                    greatInverseCnt += node.rightChild.cnt;
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：同上。
                    CntAVLTreeNode min = (CntAVLTreeNode) minimum(node.rightChild);
                    node.replaceNodeData(min);
                    node.rightChild = remove(node.rightChild, min.key);
                    if (node.rightChild != null) (node.rightChild).parent = node;
                    node.cnt--;
                    greatInverseCnt++; //add the min itself
                    lessInverseCnt += node.leftChild.cnt;
                }
            } else {
                this.size--;
                if (node.leftChild != null) {
                    lessInverseCnt += node.leftChild.cnt;
                    return node.leftChild;
                } else {
                    greatInverseCnt += node.hasRightChild() ? node.rightChild.cnt : 0;
                    return node.rightChild;
                }
            }
        }
        node.height = Math.max(height((CntAVLTreeNode) node.leftChild), height((CntAVLTreeNode) node.rightChild)) + 1;
        return node;
    }

    /**
     * delete node
     *
     * @param key - the key to delete, do nothing when not found.
     */
    public void remove(T key) {
        if (key == null) return;
        mRoot = this.remove((CntAVLTreeNode) mRoot, key);
    }

    private CntAVLTreeNode k_remove(CntAVLTreeNode node, int k) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (node == null) {
            removed = false;
            return null;
        }

        int lcnt = node.hasLeftChild() ? node.leftChild.cnt : 0;
        if (k > lcnt + 1) {
            node.rightChild = k_remove(node.rightChild, k - 1 - lcnt);
            if (node.rightChild != null) node.rightChild.parent = node;
            if (removed) node.cnt--;
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((CntAVLTreeNode) node.leftChild) - height((CntAVLTreeNode) node.rightChild) == 2) {
                CntAVLTreeNode l = (CntAVLTreeNode) node.leftChild;
                if (height((CntAVLTreeNode) l.rightChild) > height((CntAVLTreeNode) l.leftChild))
                    node = leftRightRotation(node);
                else
                    node = leftLeftRotation(node);
            }
        } else if (k < lcnt + 1) {
            node.leftChild = k_remove(node.leftChild, k);
            if (node.leftChild != null) node.leftChild.parent = node;
            if (removed) node.cnt--;
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height((CntAVLTreeNode) node.rightChild) - height((CntAVLTreeNode) node.leftChild) == 2) {
                CntAVLTreeNode r = (CntAVLTreeNode) node.rightChild;
                if (height((CntAVLTreeNode) r.leftChild) > height((CntAVLTreeNode) r.rightChild))
                    node = rightLeftRotation(node);
                else
                    node = rightRightRotation(node);
            }
        } else {
            removed = true;
            removed_key = (T) node.key;
            // tree的左右孩子都非空
            if ((node.leftChild != null) && (node.rightChild != null)) {
                if (height((CntAVLTreeNode) node.leftChild) > height((CntAVLTreeNode) node.rightChild)) {
                    CntAVLTreeNode max = (CntAVLTreeNode) maximum(node.leftChild);
                    node.replaceNodeData(max);
                    max.key = removed_key;//avoid bo be override
                    node.leftChild = k_remove(node.leftChild, node.leftChild.cnt);
                    if (node.leftChild != null) node.leftChild.parent = node;
                    node.cnt--;
                } else {
                    CntAVLTreeNode min = (CntAVLTreeNode) minimum(node.rightChild);
                    node.replaceNodeData(min);
                    min.key = removed_key;//avoid bo be override
                    node.rightChild = k_remove(node.rightChild, 1);
                    if (node.rightChild != null) (node.rightChild).parent = node;
                    node.cnt--;
                }
            } else {
                this.size--;
                return (node.leftChild != null) ? node.leftChild : node.rightChild;
            }
        }

        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
        return node;
    }

    public T k_remove(int k) {
        mRoot = this.k_remove((CntAVLTreeNode) mRoot, k);
        return removed ? removed_key : null;
    }


    /**
     * 查找最小结点：返回tree为根结点的Binary树的最小结点。
     */
    protected CntAVLTreeNode<T> minimum(CntAVLTreeNode<T> node) {
        if (node == null)
            return null;
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
    }

    public T minimum() {
        CntAVLTreeNode<T> p = minimum((CntAVLTreeNode) mRoot);
        return p != null ? (p.key) : null;
    }

    /**
     * get the kth in the asending order
     *
     * @param k
     * @return
     */
    public T k_minimum(int k) {
        CntAVLTreeNode<T> p = k_minimum(mRoot, k);
        return p != null ? (p.key) : null;
    }

    private CntAVLTreeNode<T> k_minimum(CntAVLTreeNode node, int k) {
        int lcnt = node.hasLeftChild() ? node.leftChild.cnt : 0;
        if (k > lcnt + 1) {
            return k_minimum(node.rightChild, k - 1 - lcnt);
        } else if (k < lcnt + 1) {
            return k_minimum(node.leftChild, k);
        } else {
            return node;
        }
    }

    /**
     * 查找最大结点：返回tree为根结点的Binary树的最大结点。
     */
    protected CntAVLTreeNode<T> maximum(CntAVLTreeNode<T> node) {
        if (node == null)
            return null;

        while (node.rightChild != null)
            node = node.rightChild;
        return node;
    }

    public T maximum() {
        CntAVLTreeNode<T> p = maximum((CntAVLTreeNode) mRoot);
        return p != null ? (p.key) : null;
    }


    /**
     * function to call when found repeated key.
     */
    protected void repeatedFoundWhenInsert(CntAVLTreeNode node) {
        System.out.println("添加失败：不允许添加相同的节点！");
    }


    /**
     * 中序遍历, 亦为有序遍历，中根遍历
     */
    private void inOrder(CntAVLTreeNode node) {
        if (node != null) {
            inOrder(node.leftChild);
            System.out.print(node.key + " ");
            inOrder(node.rightChild);
        }
    }

    public void inOrder() {
        inOrder((CntAVLTreeNode) mRoot);
    }


    /**
     * 前序遍历， 先根遍历
     */
    private void preOrder(CntAVLTreeNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    public void preOrder() {
        preOrder((CntAVLTreeNode) mRoot);
    }


    /**
     * 后序遍历"Binary树"， 后根遍历, DFS
     */
    private void postOrder(CntAVLTreeNode node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.key + " ");
        }
    }

    public void postOrder() {
        postOrder((CntAVLTreeNode) mRoot);
    }

    /**
     * 层次遍历，BFS
     */
    public void levelorder() {
        Queue queue = new LinkedList();
        queue.add(mRoot);
        while (!queue.isEmpty()) {
            CntAVLTreeNode node = (CntAVLTreeNode) queue.poll();
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
    private void print(CntAVLTreeNode<T> node, T key, int direction) {
        if (node != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%s is root, cnt is %d.\n", node.key, node.cnt);
            else                // tree是分支节点
                System.out.printf("%s is %s's %6s child；cnt is %d.\n", node.key, key, direction == 1 ? "end" : "start", node.cnt);

            print(node.leftChild, (T) node.key, -1);
            print(node.rightChild, (T) node.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print((CntAVLTreeNode) mRoot, (T) mRoot.key, 0);
    }

    public int getSize() {
        return size;
    }

}
