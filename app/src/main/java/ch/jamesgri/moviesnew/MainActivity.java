package ch.jamesgri.moviesnew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.jamesgri.moviesnew.data.model.Movies;
import ch.jamesgri.moviesnew.data.model.MoviesResponse;
import ch.jamesgri.moviesnew.data.remote.ApiUtils;
import ch.jamesgri.moviesnew.data.remote.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

import static ch.jamesgri.moviesnew.data.remote.ApiUtils.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MovieService mService;
    public static List<Movies> mListofMovies;

    // Code for the menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
            getMovies("popular");
                return true;
            default:
            case R.id.action_highest_rated:
            getMovies("top_rated");
                return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = ApiUtils.getMovieService();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this, new ArrayList<Movies>(0), new MoviesAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        getMovies("popular");
    }

    public void getMovies(final String preference) {

        mService.getMovies(preference, ApiUtils.API_KEY).enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    mListofMovies = response.body().getResults();
                    Log.d("MainActivity", "Number of movies received: " + mListofMovies.size());
                    mAdapter.updateMovies(mListofMovies);
                    mAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
}