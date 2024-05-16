package com.enset.books_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.enset.books_android.models.Book;

import ma.enset.booksConsultingApp.R;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_book_item, parent, false);
        }
        Book book = getItem(position);

        ImageView imageViewBook = convertView.findViewById(R.id.imageViewBook);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthors);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);

        textViewTitle.setText(book.getVolumeInfo().getTitle());
        textViewAuthor.setText(book.getVolumeInfo().getAuthors().toString());
        // textViewDescription.setText(book.getVolumeInfo().getDescription().substring(0, 10) + "...");

        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail().replace("http://", "https://")).into(imageViewBook);

        return convertView;
    }
}
