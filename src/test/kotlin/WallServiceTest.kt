import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService(ownerId = 1)
        val post = Post(ownerId = 1, text = "first post", date = "1 pm")
        service.add(post)
        val result = post.id

        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {
        val service = WallService(ownerId = 1)
        service.add(Post(ownerId = 1, text = "first post", date = "1 pm"))
        service.add(Post(ownerId = 1, text = "second post", date = "2 pm"))
        service.add(Post(ownerId = 1, text = "third post", date = "3 pm"))

        val update = Post(id = 1, ownerId = 1, text = "corrected post", date = "4 pm")

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        val service = WallService(ownerId = 1)
        service.add(Post(ownerId = 1, text = "first post", date = "1 pm"))
        service.add(Post(ownerId = 1, text = "second post", date = "2 pm"))
        service.add(Post(ownerId = 1, text = "third post", date = "3 pm"))

        val update = Post(id = 5, ownerId = 1, text = "corrected post", date = "4 pm")

        val result = service.update(update)

        assertFalse(result)
    }
}