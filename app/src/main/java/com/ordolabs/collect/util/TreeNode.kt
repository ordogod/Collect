package com.ordolabs.collect.util

/**
 * Tree node with mutable [item], list of [children] and [parent] node.
 */
class TreeNode<T : Any>(var item: T) {

    constructor(item: T, children: List<T>) : this(item) {
        addChildren(children)
    }

    /**
     * Nodes, which [parent] is `this` one.
     * Must never be edited by casting to `MutableList`.
     * Edit by using corresponding methods of this class.
     */
    val children: List<TreeNode<T>>
        get() = _children

    /**
     * Node, which has `this` one as one of a children.
     */
    val parent: TreeNode<T>?
        get() = _parent

    private val _children: MutableList<TreeNode<T>> = mutableListOf()
    private var _parent: TreeNode<T>? = null

    /**
     * Creates nodes from specified list of items.
     * Adds them into [children] list.
     * Sets `this` as a parent of each node.
     */
    fun addChildren(children: List<T>) {
        val nodes = children.map { TreeNode(it) }
        _children.addAll(nodes)
        nodes.forEach { it._parent = this }
    }

    /**
     * @return true if [children] contains [child],
     *  false if not or if [child] is `null`.
     */
    fun hasChild(child: TreeNode<T>?): Boolean {
        if (child == null) return false
        return _children.contains(child)
    }
}