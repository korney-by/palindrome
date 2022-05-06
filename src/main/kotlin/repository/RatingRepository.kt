package repository

import core.intefaces.RatingRepositoryInterface
import entity.UserRating
import java.util.TreeSet

object RatingRepository : RatingRepositoryInterface {
    override var topCount: Int = 3
    private val ratingMap = mutableMapOf<String, Int>()
    private val topList = TreeSet<User>()

    override val leader: UserRating?
        get() = ratingMap[topList.first()?.userName]?.let {
            UserRating(
                userName = topList.first().userName,
                rating = it
            )
        }

    override fun getTopList(): List<UserRating> =
        topList.toList().map {
            UserRating(
                userName = it.userName,
                rating = getUserRating(it.userName)
            )
        }

    override fun addRating(userName: String, rating: Int) {
        delFromTopList(userName)
        val newRating = rating + getUserRating(userName)
        ratingMap[userName] = newRating
        addToTopList(userName)
    }

    private fun delFromTopList(userName: String) {
        topList.remove(User(userName))
    }

    private fun addToTopList(userName: String) {
        topList.add(User(userName))
        if (topList.size > topCount) {
            topList.remove(topList.last())
        }
    }

    override fun getUserRating(userName: String): Int =
        ratingMap[userName] ?: 0

    data class User(val userName: String) : Comparable<User> {

        override fun compareTo(other: User): Int {
            val result = getUserRating(other.userName) - getUserRating(this.userName)
            return if (result == 0) {
                this.userName.compareTo(other.userName)
            } else result
        }
    }
}
