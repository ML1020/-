import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practise {
    //两棵树是否相同
    public static boolean isSameTree(Node p,Node q){
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null) {
            return  false;
        }return p.value == q.value
                && isSameTree(p.left,q.left)
                && isSameTree(p.right,q.right);
    }

    //两棵树是否镜像
    public static boolean isMirror(Node p,Node q){
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null) {
            return  false;
        }return p.value == q.value
                && isMirror(p.left,q.right)
                && isMirror(p.right,q.left);
    }

    //一棵树是否为对称的
    //子树互为镜像的
    public static boolean isSymmetric(Node root){
        if (root == null){return true;}
        return isMirror(root.left,root.right);
    }

    //一棵树是否为另一棵树的子树
    //搜索子树
   public static boolean search2(Node root,Node t){
        if (root == null){return false;}
        if (root.value == t.value){
            if (isSameTree(root,t)){
                return true;
            }
        }
        if (search2(root.left,t)){
            return true;
        }
        return search2(root.right,t);
    }
    public static boolean isSubtree(Node s,Node t){
        return search2(s,t);
    }

    //还原二叉树,前序和中序
    //用两个list保存前序和中序
    public static Node buildTree1(List<Character> preorder,List<Character> inorder){
        if (preorder.size() == 0){
            return null;
        }
        char rootValue = preorder.get(0);
        //找到root值在中序的位置，位置的数刚好是左子树的个数
        int leftCount = inorder.indexOf(rootValue);
        Node root = new Node(rootValue);
        //左子树的前序和中序
        //subList 切
        List<Character> leftPreorder = preorder.subList(1,1+leftCount);
        List<Character> leftInorder = inorder.subList(0,leftCount);
        Node left = buildTree1(leftPreorder,leftInorder);//递归
        root.left = left;
        //左闭右开区间，size不用-1；
        List<Character> rightPreorder = preorder.subList(1+leftCount,preorder.size());
        List<Character> rightInorder = inorder.subList(leftCount+1,inorder.size());
        Node right = buildTree1(rightPreorder,rightInorder);
        root.right = right;
        return root;
    }

    //还原二叉树,中序和后序
    public static Node buildTree2(List<Character> inorder,List<Character> postorder){
        if (postorder.size() == 0){
            return null;
        }
        char rootValue = postorder.get(postorder.size()-1);
        int leftCount = inorder.indexOf(rootValue);
        Node root = new Node(rootValue);
        List<Character> leftPostorder = postorder.subList(0,leftCount);
        List<Character> leftInorder = inorder.subList(0,leftCount);
        Node left = buildTree2(leftInorder,leftPostorder);
        root.left = left;
        List<Character> rightPostorder = postorder.subList(leftCount,postorder.size()-1);
        List<Character> rightInorder = inorder.subList(leftCount+1,inorder.size());
        Node right = buildTree2(rightInorder,rightPostorder);
        root.right = right;
        return root;
    }

    public static void main(String[] args){
        List<Character> preorder = Arrays.asList('A','B','D','E','G','C','F','H');
        List<Character> inorder = Arrays.asList('D','B','G','E','A','C','F','H');
        List<Character> postorder = Arrays.asList('D','G','E','B','H','F','C','A');
        Node root1 = buildTree1(preorder,inorder);
        System.out.println("成功");
        Node root2 = buildTree2(inorder,postorder);
        System.out.println("又成功了");
    }

}
