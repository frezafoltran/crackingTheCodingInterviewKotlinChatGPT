Class with algorithm:

class OCheckSubtree {
    
    fun isSubtree(t1: TreeNode?, t2: TreeNode?): Boolean {
        if (t1 == null || t2 == null) {
            return false
        }
        
        if (t1.value == t2.value && treesAreSame(t1, t2)) {
            return true
        }
        
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2)
    }
    
    private fun treesAreSame(t1: TreeNode?, t2: TreeNode?): Boolean {
        if (t1 == null && t2 == null) {
            return true
        }
        
        if (t1 == null || t2 == null || t1.value != t2.value) {
            return false
        }
        
        return treesAreSame(t1.left, t2.left) && treesAreSame(t1.right, t2.right)
    }
}

class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

Test class:

import org.junit.Test
import kotlin.test.assertEquals

class OCheckSubtreeTest {
    
    @Test
    fun `tree T2 is subtree of tree T1`() {
        val t1 = createTree1()
        val t2 = createTree2()
        val oCheckSubtree = OCheckSubtree()
        assertEquals(true, oCheckSubtree.isSubtree(t1, t2))
    }
    
    @Test
    fun `tree T2 is not subtree of tree T1`() {
        val t1 = createTree1()
        val t2 = createTree3()
        val oCheckSubtree = OCheckSubtree()
        assertEquals(false, oCheckSubtree.isSubtree(t1, t2))
    }
    
    private fun createTree1(): TreeNode {
        val node3 = TreeNode(3, TreeNode(4), TreeNode(5))
        val node2 = TreeNode(2, TreeNode(6), node3)
        return TreeNode(1, node2, TreeNode(7))
    }
    
    private fun createTree2(): TreeNode {
        val node3 = TreeNode(3, TreeNode(4), TreeNode(5))
        return TreeNode(2, TreeNode(6), node3)
    }
    
    private fun createTree3(): TreeNode {
        val node3 = TreeNode(3, TreeNode(4), TreeNode(8))
        return TreeNode(2, TreeNode(6), node3)
    }
}