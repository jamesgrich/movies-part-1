package ch.jamesgri.moviesnew;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ch.jamesgri.moviesnew.data.remote.ApiUtils;
import ch.jamesgri.moviesnew.data.remote.MovieService;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MovieService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getMovieService();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new MoviesAdapter(this, new ArrayList<ClipData.Item>(0), new MoviesAdapter().PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        }));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadMovies();
    }
}

