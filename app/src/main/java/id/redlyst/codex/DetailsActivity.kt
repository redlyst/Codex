package id.redlyst.codex

import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.redlyst.codex.`interface`.API
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val id = intent.getStringExtra(x_id)
        val title = intent.getStringExtra(x_title)
        val comment = intent.getStringExtra(x_comment)
        val score = intent.getStringExtra(x_score)
        val by = intent.getStringExtra(x_by)
        val descendants = intent.getStringExtra(x_descendants)
        val kids = intent.getStringExtra(x_kids)
        val time = intent.getStringExtra(x_time)
        val type = intent.getStringExtra(x_type)
        val url = intent.getStringExtra(x_url)

        tv_by.text = by
        tv_title.text = title
        tv_type.text = type
        tv_time.text = time
        tv_url.text = url



        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Story Detail"
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun getComments(){


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    companion object {
        const val x_id = "x_id"
        const val x_title = "x_title"
        const val x_comment = "x_comment"
        const val x_score = "x_score"
        const val x_by = "x_by"
        const val x_descendants = "x_descendants"
        const val x_kids = "x_kids"
        const val x_time = "x_time"
        const val x_type = "x_type"
        const val x_url = "x_url"
    }

}