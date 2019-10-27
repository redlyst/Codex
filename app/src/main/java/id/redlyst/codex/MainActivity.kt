package id.redlyst.codex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.redlyst.codex.`interface`.API
import id.redlyst.codex.adapter.TopStoriesAdapter
import id.redlyst.codex.model.StoryModel
import id.redlyst.codex.model.TopStoriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var rvStory: RecyclerView
    private var storylist = ArrayList<TopStoriesModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvStory = findViewById(R.id.rv_codex)
        rvStory.setHasFixedSize(true)
        rvStory.layoutManager = LinearLayoutManager(this)
//        val topStoriesAdapter = TopStoriesAdapter(storylist)
        getData()
//        showRecyclerList()
    }

    private fun getData(){

        val retrofit = Retrofit.Builder()
           .baseUrl("https://hacker-news.firebaseio.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
       val api = retrofit.create(API::class.java)
       val call = api.getTopStories()
       call.enqueue(object : Callback<List<Int>> {

           override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
               if (response.isSuccessful) {
                   Log.d("Success", "Berhasil : ${response.body().toString()}")
                   val topStories = response.body()

                   for (i in 0..14) {
                       if (topStories != null) {
                           api.getAStory(topStories[i]).enqueue(object : Callback<StoryModel> {
                               override fun onResponse(call: Call<StoryModel>, response: Response<StoryModel>) {
                                   assert(response.body() != null)
                                   val id = response.body()!!.id
                                   val title = response.body()!!.title
                                   val comment = if(response.body()?.kids?.size == null) "No" else response.body()?.kids?.size
                                   val score = response.body()!!.score
                                   val by = response.body()!!.by
                                   val descendants = response.body()!!.descendants
                                   val kids = response.body()!!.kids
                                   val time = response.body()!!.time
                                   val type = response.body()!!.type
                                   val url = response.body()!!.url

                                   storylist.add(TopStoriesModel(id.toString(),
                                       title.toString(),
                                       comment.toString(),
                                       score.toString(),
                                       by.toString(),
                                       descendants.toString(),
                                       kids.toString(),
                                       time.toString(),
                                       type.toString(),
                                       url.toString()
                                       ))

                                   val adapter = TopStoriesAdapter(storylist)

                                   rvStory.adapter = adapter
                                   adapter.notifyDataSetChanged()

                                   adapter.setOnItemClickCallback(object : TopStoriesAdapter.OnItemClickCallback {
                                       override fun onItemClicked(data: TopStoriesModel) {
                                           showSelected(data)
                                       }

                                   })
                               }

                               override fun onFailure(call: Call<StoryModel>, t: Throwable) {

                               }
                           })
                       }
                   }

               } else {
                   // error response, no access to resource?
               }
           }

           override fun onFailure(call: Call<List<Int>>, error: Throwable) {
               Log.e("Error", "Error Broo : ${error.message}")
           }
       })

    }

    private fun showSelected(story: TopStoriesModel) {
        val moveWithDataIntent = Intent(this@MainActivity, DetailsActivity::class.java)
        moveWithDataIntent.putExtra(DetailsActivity.x_id, story.id)
        moveWithDataIntent.putExtra(DetailsActivity.x_title, story.title)
        moveWithDataIntent.putExtra(DetailsActivity.x_comment, story.comments)
        moveWithDataIntent.putExtra(DetailsActivity.x_score, story.score)
        moveWithDataIntent.putExtra(DetailsActivity.x_by, story.by)
        moveWithDataIntent.putExtra(DetailsActivity.x_descendants, story.descendants)
        moveWithDataIntent.putExtra(DetailsActivity.x_kids, story.kids)
        moveWithDataIntent.putExtra(DetailsActivity.x_time, story.time)
        moveWithDataIntent.putExtra(DetailsActivity.x_type, story.type)
        moveWithDataIntent.putExtra(DetailsActivity.x_url, story.url)
        startActivity(moveWithDataIntent)
    }

//    private fun showRecyclerList() {
//        rvStory.layoutManager = LinearLayoutManager(this)
//        val topStoriesAdapter = TopStoriesAdapter(storylist)
//        rvStory.adapter = topStoriesAdapter
//
//        topStoriesAdapter.setOnItemClickCallback(object : TopStoriesAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: TopStoriesModel) {
//                showSelected(data)
//            }
//
//        })
//
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favorites, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_fav -> {
                val toast = Toast.makeText(applicationContext,"Sorry, not available right now",Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

}