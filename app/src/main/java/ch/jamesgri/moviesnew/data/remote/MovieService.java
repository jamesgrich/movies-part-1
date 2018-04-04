package ch.jamesgri.moviesnew.data.remote;

/**
 * Created by jamesrichardson on 21/03/2018.
 */

import java.util.List;

import ch.jamesgri.moviesnew.data.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {


    // Calling the GET request for the two API end points

    @GET("movie/{sort_by}")
    Call<Result> getMovies(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

}
