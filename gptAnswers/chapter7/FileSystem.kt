// Data structures:
// - A tree structure to represent the file system hierarchy, where each node represents a
//   file or directory and has links to its children.
// - A map to store the file names and their corresponding nodes.
// - A set to keep track of exposed files.

// Algorithms:
// - Traverse the file system hierarchy recursively to expose all files that are not bombs.
// - When a file is exposed, add it to the set of exposed files and check if the user has won.

data class Node(
    val name: String,
    val isDirectory: Boolean,
    val children: MutableList<Node> = mutableListOf(),
    var isExposed: Boolean = false
)

class FileSystem {
    private val root = Node("", true) // root directory
    private val nodes = mutableMapOf<String, Node>()
    private val exposed = mutableSetOf<Node>()

    init {
        // Example initialization
        val dir1 = Node("dir1", true)
        val dir2 = Node("dir2", true)
        val file1 = Node("file1", false)
        val file2 = Node("file2", false)
        val bomb = Node("bomb", false, isExposed = true)
        root.children.addAll(listOf(dir1, dir2))
        dir1.children.add(file1)
        dir2.children.addAll(listOf(file2, bomb))
        nodes.putAll(listOf(
            "" to root,
            "dir1" to dir1,
            "dir2" to dir2,
            "file1" to file1,
            "file2" to file2,
            "bomb" to bomb
        ))
    }

    fun expose(path: String): Boolean {
        val node = nodes[path] ?: return false // file not found
        if (node.isExposed) return false // already exposed
        node.isExposed = true
        if (!node.isDirectory && node.name != "bomb") {
            exposed.add(node)
            if (exposed.size == nodes.count { !it.value.isDirectory && it.value.name != "bomb" }) {
                // user has won
                return true
            }
        }
        if (node.isDirectory) {
            node.children.forEach { expose("${path}/${it.name}") }
        }
        return false
    }
}

class FileSystemTest {
    @Test
    fun `expose all files except bombs`() {
        val fs = FileSystem()
        assertFalse(fs.expose(""))
        assertFalse(fs.expose("dir1"))
        assertFalse(fs.expose("dir2"))
        assertTrue(fs.expose("dir1/file1"))
        assertTrue(fs.expose("dir2/file2"))
        assertFalse(fs.expose("dir2/bomb"))
        assertFalse(fs.expose("dir2/bomb/any"))
        assertTrue(fs.expose("dir2/bomb/../file2"))
        assertTrue(fs.expose("dir2/bomb/../file2")) // exposing same file twice has no effect
        assertTrue(fs.expose("dir1/file1")) // exposing already exposed file has no effect
        assertTrue(fs.expose("dir2/file2"))
        assertTrue(fs.expose("dir1/file1")) // exposing already exposed file has no effect
        assertTrue(fs.expose("dir1/file1")) // exposing already exposed file has no effect
        assertTrue(fs.expose("dir2/bomb/../file2")) // exposing already exposed file has no effect
        assertTrue(fs.expose("dir2/bomb")) // this is the last exposed file, user wins
    }
}