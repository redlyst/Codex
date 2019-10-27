package id.redlyst.codex.`interface`

import id.redlyst.codex.model.CommentsModel
import id.redlyst.codex.model.StoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("v0/topstories.json?print=pretty")
    fun getTopStories(): Call<List<Int>>

    @GET("v0/item/{storyid}.json?print=pretty")
    fun getAStory(@Path("storyid") id: Int): Call<StoryModel>

    @GET("v0/item/{commentid}.json?print=pretty")
    fun getComments(@Path("commentid") id: Int): Call<CommentsModel>

}
