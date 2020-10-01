package com.ordolabs.collect.util.struct

import kotlin.math.max

/**
 * Tree node with mutable [value].
 */
class TreeNode<V : Any>(var value: V) {

    constructor(value: V, children: List<V>) : this(value) {
        addChildren(children)
    }

    /**
     * Nodes, which [parent] is `this` one.
     * Must never be edited by casting to `MutableList`.
     * Edit by using corresponding methods of this class.
     */
    val children: List<TreeNode<V>>
        get() = _children

    /**
     * Node, which has `this` one as one of a children.
     */
    val parent: TreeNode<V>?
        get() = _parent

    /**
     * Computes height of tree, which root is `this` node.
     */
    val height: Int
        get() {
            return if (_children.isEmpty())
                1
            else {
                var h = 0
                for (child in _children)
                    h = max(h, child.height)
                h + 1
            }
        }

    /**
     * Computes, is `this` node a leaf (has no children).
     */
    val isLeaf: Boolean
        get() = _children.isEmpty()

    private val _children: MutableList<TreeNode<V>> = mutableListOf()
    private var _parent: TreeNode<V>? = null

    /**
     * Add specified node as child of `this` one.
     * Sets `this` as a [parent] of [child] node.
     */
    fun addChild(child: TreeNode<V>) {
        _children.add(child)
        child._parent = this
    }

    /**
     * Creates nodes from specified list of items
     * and adds them as children of `this` one.
     */
    fun addChildren(children: List<V>) {
        val nodes = children.map { TreeNode(it) }
        nodes.forEach { addChild(it) }
    }

    /**
     * @return true if [children] contains [child],
     *  false if not or if [child] is `null`.
     */
    fun hasChild(child: TreeNode<V>?): Boolean {
        if (child == null) return false
        return _children.contains(child)
    }
}