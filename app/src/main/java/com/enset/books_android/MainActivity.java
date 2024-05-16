package com.enset.books_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import com.enset.books_android.activities.BookDetailActivity;
import com.enset.books_android.adapters.BookAdapter;
import com.enset.books_android.models.Book;
import com.enset.books_android.models.GoogleBooksResponse;
import com.enset.books_android.apis.GoogleBooksApi;

import ma.enset.booksConsultingApp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listViewBooks;
    Button buttonSearch;
    EditText editTextQuery;
    List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        books = new ArrayList<>();

        BookAdapter bookAdapter = new BookAdapter(this, R.layout.list_book_item, books);
        listViewBooks = findViewById(R.id.listViewBooks);
        buttonSearch = findViewById(R.id.buttonSearch);
        editTextQuery = findViewById(R.id.editTextQuery);
        listViewBooks.setAdapter(bookAdapter);

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GoogleBooksApi booksApi = retrofit.create(GoogleBooksApi.class);

        buttonSearch.setOnClickListener(view -> {
            String query = editTextQuery.getText().toString();
            Call<GoogleBooksResponse> call = booksApi.searchBooks(query);
            call.enqueue(new Callback<GoogleBooksResponse>() {
                @Override
                public void onResponse(Call<GoogleBooksResponse> call, Response<GoogleBooksResponse> response) {
                    GoogleBooksResponse booksResponse = response.body();
                    // Log.i("Info", booksResponse.getKind() + " " + booksResponse.getTotalItems());
                    books.clear();
                    books.addAll(booksResponse.getItems());
                    bookAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<GoogleBooksResponse> call, Throwable throwable) {
                    throwable.printStackTrace();
                    Log.i("Info", "nop");
                    Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_SHORT);
                }
            });
        });
        listViewBooks.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), BookDetailActivity.class);
            intent.putExtra("book", books.get(i));
            startActivity(intent);
        });
    }
}