fun main() {
    val post1 = Post(ownerId = 1,text = "111", likes = 1, date =  "1 pm")
    val post2 = Post(ownerId = 2,text = "222", likes = 2, date = "2 pm")
    println("Создали посты:")
    post1.printPost()
    post2.printPost()

    println("Создаем стену автора 1 и 2")
    val wall1 = WallService(ownerId = 1)
    val wall2 = WallService(ownerId = 2)

    println("Добавляем посты на стену")
    wall1.add(post1)
    wall2.add(post2)
    wall1.add(Post(ownerId = 1,text = "222", likes = 2, date =  "2 pm"))
    wall2.add(Post(ownerId = 2,text = "222", likes = 2, date =  "2 pm"))

    println("Печатаем стену1:")
    println(wall1.toString())

    println("Печатаем стену1:")
    println(wall2.toString())

    println("Делаем редакцию поста 1 при помощи другого поста:")
    val correctedPost1 = post1.correctPostByPost(post2)
    correctedPost1.printPost()
    println("Делаем редакцию поста 2, указывая новый текст")
    val correctedPost2 = post2.correctPostText("new text, correct2")
    correctedPost2.printPost()

    println("Обновляем отредактированный пост на стене")
    println(wall1.update(correctedPost1))
    println(wall2.update(correctedPost2))

    println("Печатаем обновленную стену 1:")
    println(wall1.toString())

    println("Печатаем обновленную стену 2:")
    println(wall2.toString())

}

class WallService(ownerId: Long) {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += post
        post.id = (posts.indexOf(posts.last())).toLong() +1
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index,post1) in posts.withIndex()){
            if (post1.id == post.id) {
                posts[index] = post.copy(id = post.id, text = post.text, likes = post.likes)
                return true
            }
        }
        return false
    }

    fun likeById(id: Long) {
        for ((index,post) in posts.withIndex()){
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes + 1)
            }
        }
    }

    override fun toString(): String {
        var string: String = ""
        for (post in posts){
            string += ("id: " + post.id +", owner id: " + post.ownerId +
                    ", text: " + post.text + ", likes: " + post.likes +
                    ", date: " + post.date + "\n")

        }
        return string
    }
}