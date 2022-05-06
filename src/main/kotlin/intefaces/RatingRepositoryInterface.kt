package intefaces

import entity.UserRating

interface RatingRepositoryInterface {
    val leader: UserRating?
    fun addRating(userName: String, rating: Int)
    fun getTopList(): List<UserRating>
}