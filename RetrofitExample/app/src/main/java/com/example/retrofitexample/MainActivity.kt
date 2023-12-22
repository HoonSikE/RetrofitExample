package com.example.retrofitexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitexample.Retrofit.JsonPlaceholderService
import com.example.retrofitexample.Retrofit.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: JsonPlaceholderService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createRetrofit()
        getPost()
        getPostList()
    }
    private fun createRetrofit() {
        // Retrofit 객체 생성
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(JsonPlaceholderService::class.java)
    }
    private fun getPost(){
        // API 호출
        val call = service.getPost(1)

        // 비동기 API 응답 처리
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                // 성공적
                if (response.isSuccessful) {
                    // 데이터
                    val post = response.body()

                    // 전체 데이터 출력 TextView
                    val postTextView: TextView = findViewById(R.id.postTextView)
                    postTextView.text = post.toString() // 또는 원하는 데이터를 표시할 방식으로 수정

                    // 각각의 정보를 출력 TextView
                    val userIdTextView: TextView = findViewById(R.id.userIdTextView)
                    val titleTextView: TextView = findViewById(R.id.titleTextView)
                    val bodyTextView: TextView = findViewById(R.id.bodyTextView)
                    // 각각의 정보를 TextView에 설정
                    post?.let {
                        userIdTextView.text = "UserID: ${it.userId}"
                        titleTextView.text = "Title: ${it.title}"
                        bodyTextView.text = "Body: ${it.body}"
                    }
                } else {
                    // 오류 처리
                }
            }

            // 실패
            override fun onFailure(call: Call<Post>, t: Throwable) {
            }
        })
    }
    private fun getPostList(){
        // API 호출
        val call = service.getPostList()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                // 성공
                if (response.isSuccessful) {
                    // 전체 게시물 목록
                    val posts = response.body()
                    posts?.let {
                        for (post in posts) {
                            println(post)
                        }
                    }
                } else {
                    // 오류 처리
                }
            }

            // 실패
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }
        })
    }
}