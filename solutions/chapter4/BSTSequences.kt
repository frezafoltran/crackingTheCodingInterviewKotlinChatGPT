class Node(val value: Int, var left: Node?=null, var right: Node?=null)


class BSTSequences(private val root: Node){
    private val sequences = mutableListOf < List < Int >> ()

    fun getSequences(): List < List < Int >> {
        val prefix = mutableListOf(root.value)
        val left = root.left
        val right = root.right
        val leftSequences = if (left != null) getSequences(left) else listOf()
        val rightSequences = if (right != null) getSequences(right) else listOf()
        sequences.addAll(combineSequences(prefix, leftSequences, rightSequences))
        return sequences
    }

    private fun getSequences(node: Node): List < List < Int >> {
        val prefix = mutableListOf(node.value)
        val left = node.left
        val right = node.right
        val leftSequences = if (left != null) getSequences(left) else listOf()
        val rightSequences = if (right != null) getSequences(right) else listOf()
        return combineSequences(prefix, leftSequences, rightSequences)
    }

    private fun combineSequences(
        prefix: MutableList < Int >,
        leftSequences: List < List < Int >>,
        rightSequences: List < List < Int >>
    ): List < List < Int >> {
        if (leftSequences.isEmpty() & & rightSequences.isEmpty()) {
            return listOf(prefix.toList())
        } else if (leftSequences.isEmpty()){
            return rightSequences.map {prefix + it}
        } else if (rightSequences.isEmpty()){
            return leftSequences.map {prefix + it}
        }
        val result = mutableListOf < List < Int >> ()
        leftSequences.forEach{left ->
                              rightSequences.forEach {right ->
                                                      permutations(prefix, left, right)?.let {result.add(it)}
                                                      permutations(prefix, right, left)?.let {result.add(it)}
                                                      }
                              }
        return result
    }

    private fun permutations(
        prefix: MutableList < Int >,
        list1: List < Int >,
        list2: List < Int >
    ): List < Int >?{
        if (list1.isEmpty() | | list2.isEmpty()){
            val remaining = if (list1.isEmpty()) list2 else list1
            return prefix + remaining
        }

        val result = mutableListOf < List < Int >> ()
        val newPrefix = prefix.toMutableList()
        newPrefix.add(list1[0])
        val list1Rest = list1.drop(1)
        val list2Rest = list2.drop(1)

        permutations(newPrefix, list1Rest, list2)?.let {result.add(it)}
        permutations(newPrefix, list2Rest, list1)?.let {result.add(it)}

        return if (result.isEmpty()) null else result.flatMap {prefix + it}
    }
}


class BSTSequencesTest{
    private lateinit var root: Node

    @ BeforeEach
    fun setup(){
//        / *
        2
        /
//        1   3
//        * /
        root = Node(2, Node(1), Node(3))
    }

    @ Test
    fun `test BSTSequences`(){
        val expected = listOf(
            listOf(2, 1, 3),
            listOf(2, 3, 1)
        )
        val actual = BSTSequences(root).getSequences()
        assertEquals(expected.size, actual.size)
        expected.forEach {
            assertTrue(it in actual)
        }
    }
}
