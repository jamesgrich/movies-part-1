package ch.jamesgri.moviesnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import ch.jamesgri.moviesnew.data.model.MovieReview;
import ch.jamesgri.moviesnew.data.model.MovieVideo;
import ch.jamesgri.moviesnew.data.model.Movies;
import ch.jamesgri.moviesnew.data.model.MoviesResponse;
import ch.jamesgri.moviesnew.data.remote.ApiUtils;
import ch.jamesgri.moviesnew.data.remote.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ch.jamesgri.moviesnew.MainActivity.mListofMovies;

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
    private MoviesAdapter mAdapter;
    private MovieService mService;
    private String movieId;
    private String movieTrailerTitle;


    private static Movies moviesObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();

        mService = ApiUtils.getMovieService();
        mImageHeader = findViewById(R.id.movies_header);
        mMoviesArtwork = findViewById(R.id.movies_artwork);
        mMovieTitle = findViewById(R.id.movies_title);
        mMovieReleaseDate = findViewById(R.id.movies_release_date);
        mMovieVoteAverage = findViewById(R.id.movies_vote_average);
        mMoviePlotSynopsis = findViewById(R.id.movies_plot_synopsis);

        if (i != null) {
            if (i.hasExtra("parcel_data")) {
                moviesObject = i.getParcelableExtra("parcel_data");
                displayDetailMovies(moviesObject);
            }
        }

        getReviews(movieId);
        getVideos(movieId);

    }

    public void getReviews(String idOfMovie) {

        mService.getReviews(idOfMovie, ApiUtils.API_KEY).enqueue(new Callback<MovieReview>() {

            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {
                if (response.isSuccessful()) {
                    mListofMovies = response.body().getResults();
                    Log.d("DetailActivity", "Number of movies received: " + mListofMovies.size());
                    mAdapter.updateMovies(mListofMovies);
                    mAdapter.notifyDataSetChanged();

                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<MovieReview> call, Throwable t) {
                Log.d("DetailActivity", "error loading reviews from API");
            }
        });
    }

    public void getVideos(String idOfMovie) {

        mService.getVideos(idOfMovie, ApiUtils.API_KEY).enqueue(new Callback<MovieVideo>() {

            @Override
            public void onResponse(Call<MovieVideo> call, Response<MovieVideo> response) {
                if (response.isSuccessful()) {
                    VideoTextView.setText(movieTrailer1.getTitle());
//                    mListofMovies = response.body().getResults();
//                    Log.d("DetailActivity", "Number of movies received: " + mListofMovies.size());
//                    mAdapter.updateMovies(mListofMovies);
                    mAdapter.notifyDataSetChanged();

                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<MovieVideo> call, Throwable t) {
                Log.d("DetailActivity", "error loading videos from API");
            }
        });
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

