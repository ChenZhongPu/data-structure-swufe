# General Trees
> In computer science, a tree is a widely used ADT that represents a hierarchical tree structure with a set of connected nodes.

Like a linked list, a tree can be defined in a recursive way such that a tree *T* is either empty or consists of a node *r*, called the **root** of *T*, and a (possibly empty) set of subtrees whose roots are the children of *r*.

This definition reveals that **parent-children** relationships are very important in trees.

## Terminology
When studying trees, you will be immersed with a ton of terminologies. Please calm down and take it easy, because most of them are instinct and easy-to-memorize.

<img src="https://miro.medium.com/max/975/0*reevDbBrtxor3Nyw.png" width="80%">

- The connections between nodes are sometimes called **edges** or **links**.
- The value stored in a node is often called a **key**.
- Each node in a tree has zero or more **child nodes**, which are below it in the tree.
- All nodes have exactly one **parent**, except the topmost **root node**.
- A node might have many **ancestor nodes**[^descendant], such as the parent's parent.
- Child nodes with the same parent are **sibling nodes**.
- An **internal node** (also known as an **inner node**, **inode** for short, or **branch node**) is any node of a tree that has child nodes.
- An **external node** (also known as an **outer node**, **leaf node**, or **terminal node**) is any node that does not have child nodes.
- The **height** of a node is the length of the longest downward path to a leaf from that node. The height of the root is the height of the tree. Conventionally, an empty tree has height −1.
- The **depth** of a node is the length of the path to its root.
- The **level** of a node is the same as depth when using zero-based counting.
- Each non-root node can be treated as the root node of its own **subtree**, which includes that node and all its descendants.
- Number of nodes in the tree is called **size of a tree**.
- A **path** is a sequence of nodes with the property that each node in the sequence is adjacent to the node linked to it. A path that does not repeat nodes is called a **simple path**.

---
[^descendant] The counterpart of an ancestor is called **descendant**, meaning a node reachable by repeated proceeding from parent to child. Also known as *subchild*. 