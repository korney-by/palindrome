package core.intefaces

import entity.UserRating

interface RatingRepositoryInterface {
    var topCount: Int
    val leader: UserRating?
    fun addRating(userName: String, rating: Int)
    fun getUserRating(userName: String): Int
    fun getTopList(): List<UserRating>
}
