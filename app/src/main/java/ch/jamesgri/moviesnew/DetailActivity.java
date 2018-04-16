package ch.jamesgri.moviesnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import ch.jamesgri.moviesnew.data.model.Movies;
import ch.jamesgri.moviesnew.data.model.Movies$$Parcelable;

/**
 * Created by jamesrichardson on 15/04/2018.
 */

public class DetailActivity extends AppCompatActivity {

    private ImageView mImageHeader;
    private ImageView mMoviesArtwork;
    private TextView mMovieTitle;
    private TextView mMovieReleaseDate;
    private TextView mMovieVoteAverage;
    private TextView mMoviePlotSynopsis;
    private static Movies moviesObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        Movies moviesObject = (Movies) Parcels.unwrap(i.getParcelableExtra("parcel_data"));
        mImageHeader = findViewById(R.id.movies_header);
        mMoviesArtwork = findViewById(R.id.movies_artwork);
        mMovieTitle = findViewById(R.id.movies_title);
        mMovieReleaseDate = findViewById(R.id.movies_release_date);
        mMovieVoteAverage = findViewById(R.id.movies_vote_average);
        mMoviePlotSynopsis = findViewById(R.id.movies_plot_synopsis);

    }

    public void displayDetailMovies(Movies moviesObject) {

        mMovieTitle.setText(moviesObject.getOriginalTitle());
        mMovieReleaseDate.setText(moviesObject.getReleaseDate());
        mMovieVoteAverage.setText(String.valueOf(moviesObject.getVoteAverage()));
        mMoviePlotSynopsis.setText(moviesObject.getOverview());

        Picasso.with(this)
                .load(moviesObject.getBackdropPath())
                .into(mImageHeader);

        Picasso.with(this)
                .load(moviesObject.getPosterPath())
                .into(mMoviesArtwork);
    }
}

