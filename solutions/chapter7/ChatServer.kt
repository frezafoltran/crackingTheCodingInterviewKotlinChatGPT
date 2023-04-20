//Class ChatServer:


class ChatServer {
    // time complexity: O(1) for most operations, O(n) for fetching chat history
    // space complexity: O(n), where n is the number of active chats

    // data structures to keep track of active chats, users, and chat history
    val activeChats = mutableMapOf < User, Chat > ()
    val users = mutableMapOf < String, User > ()
    val chatHistory = mutableListOf < String > ()

    // method to create a new chat and add users to it
    fun createChat(users: List < String >) {
        val chat = Chat()
        users.forEach {username ->
                       val user = users[username] ?: createUser(username)
                       activeChats[user] = chat
                       chat.users.add(user)
                       }
    }

    // method to fetch chat history
    fun getChatHistory(): List < String > {
        return chatHistory
    }

    // method to send a message in a chat
    fun sendMessage(user: User, message: String) {
        val chat = activeChats[user] ?: throw IllegalArgumentException("User is not in an active chat")
        chat.sendMessage(user, message)
        chatHistory.add("$user: $message")
    }

    // method to create a new user if they don't exist already
    private fun createUser(username: String): User {
        val user = User(username)
        users[username] = user
        return user
    }
}


class ChatServerTest {

    @ Test
    fun `create chat`() {
        val server = ChatServer()
        server.createChat(listOf("Alice", "Bob"))
        val alice = server.users["Alice"]!!
        val bob = server.users["Bob"]!!
        assertEquals(server.activeChats[alice], server.activeChats[bob])
    }

    @ Test
    fun `send message`() {
        val server = ChatServer()
        server.createChat(listOf("Alice", "Bob"))
        server.sendMessage(server.users["Alice"]!!, "Hello Bob!")
        assertEquals(server.chatHistory.last(), "Alice: Hello Bob!")
    }
}
