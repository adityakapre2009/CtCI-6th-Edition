/*
Q. Check if a tree is symmetric.  
For example, this binary tree is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
   1
   / \
  2   2
   \   \
   3    3


Recursive
*/

public boolean isSymmetric(Node root) {
  if (root == null)  return true;    //base case true
  return isSymmetric(root.left, root.right);
}
public boolean isSymmetric(Node l, Node r) {
  if (l == null && r == null) 
      return true;
  else if (l == null || r == null) 
      return false;
  if (l.val != r.val) // main logic
        return false;
  if (!isSymmetric(l.left, r.right)) 	
        return false;
  if (!isSymmetric(l.right, r.left)) 	
        return false;
  return true;
}

/*
Iterative
aditya added {poll similarTo remove} & {offer similarTo add} 
trick : use Queue to store left & right nodes to be compared
*/

private Boolean isSymmetricIterative(Node n) {
	boolean result = false;
	if (n == null) 
      		return true;
	Queue<Node> queue = new LinkedList<Node>();
	queue.add(n.left);
	queue.add(n.right);
	while (!queue.isEmpty()) {
		Node l = queue.remove();
		Node r = queue.remove();
		if (l == null && r == null) {
			result = true;
		} else if (l == null || r == null || l.data != r.data) {
			// It is required to set result = false here
			result = false;
			break;
		} else if (l != null && r != null) {
			queue.add(l.left);
			queue.add(r.right);
			queue.add(l.right);
			queue.add(r.left);
`		}
	}
	return result;
}

