package repository

import entity.UserRating
import intefaces.RatingRepositoryInterface
import java.util.*

object RatingRepository : RatingRepositoryInterface {
    private const val TOP_COUNT = 5
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
        val newRating = rating + getUserRating(userName)
        ratingMap[userName] = newRating
        toTopList(userName)
    }


    private fun toTopList(userName: String) {
        if (topList.size < TOP_COUNT) {
            if (!topList.contains(User(userName))) topList.add(User(userName))
        } else {
            val lastUser = topList.last()
            if (getUserRating(lastUser.userName) < getUserRating(userName)) {
                topList.remove(lastUser)
                topList.add(User(userName))
            }
        }
    }

    private fun getUserRating(userName: String): Int =
        ratingMap[userName] ?: 0


    data class User(val userName: String) : Comparable<User> {
        override fun compareTo(other: User): Int =
            getUserRating(other.userName)-getUserRating(this.userName)
    }

}