package a.m.mad314_pd_1_project.service;

import java.util.ArrayList;

import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IDataService {


    @GET("movies")
    Call<ResponseModel<ArrayList<MovieResponse>>> getMovies();


}
