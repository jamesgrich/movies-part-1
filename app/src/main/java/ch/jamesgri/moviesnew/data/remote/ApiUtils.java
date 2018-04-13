package ch.jamesgri.moviesnew.data.remote;

import android.net.Uri;

import java.net.URI;
import java.net.URL;

import ch.jamesgri.moviesnew.data.model.Movies;

/**
 * Created by jamesrichardson on 02/04/2018.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://api.themoviedb.org/3/w185/";

    public static final String API_KEY = "";

    public static MovieService getMovieService() {
        return RetrofitClient.getClient(BASE_URL).create(MovieService.class);
    }
}
