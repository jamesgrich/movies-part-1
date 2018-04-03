package ch.jamesgri.moviesnew.data.remote;

/**
 * Created by jamesrichardson on 21/03/2018.
 */

import java.util.List;

import ch.jamesgri.moviespart1.data.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {


    // Calling the GET request for the two API end points

    @GET("/movie/popular?api_key=")
    Call<List<Result>> getPopular();

    @GET("movie/top_rated?api_key=")
    Call<Result> getTopRated(@Path("toprated") String topRated);

}
