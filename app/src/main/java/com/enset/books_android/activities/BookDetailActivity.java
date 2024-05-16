package com.enset.books_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import ma.enset.booksConsultingApp.R;
import com.enset.books_android.models.Book;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        TextView textViewTitle = findViewById(R.id.textViewTitle1);
        TextView textViewAuthors = findViewById(R.id.textViewAuthors1);
        TextView textViewDescription = findViewById(R.id.textViewDescription1);
        ImageView imageViewBook = findViewById(R.id.imageViewBook1);

        Button buttonShare = findViewById(R.id.buttonShare);

        textViewTitle.setText(book.getVolumeInfo().getTitle());
        textViewAuthors.setText(book.getVolumeInfo().getAuthors().toString());
        textViewDescription.setText(book.getVolumeInfo().getDescription());
        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail().replace("http://", "https://")).into(imageViewBook);

        buttonShare.setOnClickListener(view -> {
            String shareMsg = "Check out this book : \nTitle : " + book.getVolumeInfo().getTitle() + "\n"
                                + "Authors : " + book.getVolumeInfo().getAuthors().toString() + "\n"
                                + "Description : " + book.getVolumeInfo().getDescription();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}
