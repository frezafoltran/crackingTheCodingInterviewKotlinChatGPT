class BuildOrder(val projects: List < String > , val dependencies: List < Pair < String, String>>) {

    fun findBuildOrder(): List < String >? {

        val adjList = mutableMapOf < String, MutableList < String >> ()
        val inDegree = mutableMapOf < String, Int > ()

        // initialize adjacency list and in -degree map
        projects.forEach {
            adjList[it] = mutableListOf()
            inDegree[it] = 0
        }

        // fill the adjacency list and in -degree map based on the dependencies
        dependencies.forEach {
            adjList[it.first]?.add(it.second)
            inDegree[it.second] = inDegree[it.second]!! + 1
        }

        // initialize queue and result list
        val queue = ArrayDeque < String > ()
        val result = mutableListOf < String > ()

        // add all projects with in -degree zero to the queue
        inDegree.filter {it.value == 0}.forEach {queue.add(it.key)}

        // perform topological sorting
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            result.add(current)
            adjList[current]?.forEach {
                inDegree[it] = inDegree[it]!! - 1
                if (inDegree[it] == 0) queue.add(it)
            }
        }

        // check if a valid build order was found
        return if (result.size == projects.size) {
            result
        } else {
            null
        }
    }
}


class BuildOrderTest {

    @ Test
    fun `find build order`() {
        val buildOrder = BuildOrder(
            listOf("a", "b", "c", "d", "e", "f"),
            listOf("a" to "d", "f" to "b", "b" to "d", "f" to "a", "d" to "c")
        ).findBuildOrder()

        assertEquals(listOf("e", "f", "a", "b", "d", "c"), buildOrder)
    }

    @ Test
    fun `invalid build order`() {
        val buildOrder = BuildOrder(
            listOf("a", "b", "c", "d", "e", "f"),
            listOf("a" to "d", "f" to "b", "b" to "d", "f" to "a", "d" to "c", "c" to "f")
        ).findBuildOrder()

        assertNull(buildOrder)
    }
}
