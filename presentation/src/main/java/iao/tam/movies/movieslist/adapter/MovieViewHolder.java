package iao.tam.movies.movieslist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import iao.tam.movies.databinding.ItemMovieBinding;
import org.app.metier.domain.dto.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private OnMovieClickListener onMovieClickListener;

    private ItemMovieBinding binding;

    private MovieViewHolder(ItemMovieBinding binding, OnMovieClickListener onMovieClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.onMovieClickListener = onMovieClickListener;
    }

    public void bind(Movie movie) {
        binding.textViewMovieTitle.setText(movie.getTitle());

        // TODO: deal with null images
        Picasso.with(binding.getRoot().getContext()).load(movie.getPosterPath()).into(binding.imageViewMoviePoster);

        binding.textViewMovieReleaseDate.setText(movie.getReleaseDate());

        binding.getRoot().setOnClickListener(v -> {
            if (onMovieClickListener != null) {
                onMovieClickListener.onMovieClick(movie);
            }
        });
    }

    public static MovieViewHolder create(ViewGroup parent, OnMovieClickListener onMovieClickListener) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);

        return new MovieViewHolder(binding, onMovieClickListener);
    }
}
