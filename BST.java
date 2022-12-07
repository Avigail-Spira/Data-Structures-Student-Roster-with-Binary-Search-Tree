public class BST {
    private BNode root; 
    
  
    public BST () {
    	root = null;
    }
	
	public static class BNode{  
		Student data;  
		BNode left;  
		BNode right;  
		
		public BNode(Student data){  
	            this.data = data;  
	            this.left = null;  
	            this.right = null;  
	     }  
	}  
	
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	public void Insert(Student s) {
		root = Insert(s, root);
	}
	
	public BNode Insert(Student s, BNode t) {
		if (t != null) {
			if (s.compareTo(t.data) < 0) 
				t.left = Insert(s, t.left);
			else 
				t.right = Insert(s, t.right);
		}
		return new BNode(s);
	}
	
	
	public void PreOrder() {
		PreOrder(root);
	}
	
	private void PreOrder (BNode t) {
		if (t != null) {
			System.out.println(t.data); 
			PreOrder(t.left);
			PreOrder(t.right);
		}
	}
	
	
	
	public void InOrder() {
		InOrder(root);
	}
	
	private void InOrder(BNode t) {
		if (t != null) {
			InOrder(t.left);
			System.out.println(t.data); 	
			InOrder(t.right);
		}
	}
	
	public void InOrderToArray(Student[] array) {
		InOrderToArray(array, root, 0);
	}
	
	private void InOrderToArray(Student[] array, BNode t, int i) {
		if (t != null) {
		InOrderToArray(array, t.left, i++); 
		array[i] = t.data;
		InOrderToArray(array, t.right, i++);
		}
	}
	
	
	public void PostOrder() {
		PostOrder(root);
	}
	
	private void PostOrder(BNode t) {
		if (t != null) {
			PostOrder(t.left);
			PostOrder(t.right);
			System.out.println(t.data);
		}
	}
	
	public void Delete(Student s) {
		if (isEmpty()) throw new EmptyTreeException();
		Delete(s,root);
	}
	
	private BNode Delete(Student s, BNode t) {
		if (t == null) throw new EmptyTreeException();
		if (s.compareTo(t.data) < 0)
			t.left = Delete(s, t.left);
		else if (s.compareTo(t.data) > 0)
			t.right = Delete(s, t.right);
		else {
			if (t.right == null)
				return t.left;
			if (t.left == null)
				return t.right;
			t.data = FindMax(t.left);
			t.left = Delete(t.data, t.right);
		}
		return t;
	}




	private Student FindMax(BNode root) {
		if (root == null) throw new EmptyTreeException();
		BNode t = root;
		while (t.right != null)
			t = t.right;
		
		return t.data;
	}
	
	
	public Student Search(Student s) {
		if (isEmpty()) throw new EmptyTreeException();
		return Search(s, root);
	}
	
	private Student Search(Student s, BNode r) {
		if (r == null) throw new EmptyTreeException();
		if (s.compareTo(r.data) < 0)
			return Search(s, r.left);
		if (s.compareTo(r.data) > 0)
			return Search(s, r.right);
		return r.data;
	}
	
	
	public Student SearchByID(Student s) {
		if (! isEmpty()) throw new EmptyTreeException();
		return SearchByID(s, root);
	}
	
	private Student SearchByID(Student s, BNode r) {
		if (r == null) throw new EmptyTreeException();
		if (s.getIDNo().compareTo(r.data.getIDNo()) < 0)
			return Search(s, r.left);
		if (s.getIDNo().compareTo(r.data.getIDNo()) > 0)
			return Search(s, r.right);
		return r.data;
	} 
}