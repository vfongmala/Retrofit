package project.vichita.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Vichita Fongmala on 2/4/15.
 */
public interface SimpleRetrofit {

    @GET("/shots/21603")
    Shot getShot();

    @GET("/shots/{id}")
    Shot getShotById(@Path("id") int id);

    @GET("/shots/{id}")
    void getShotByIdWithCallback(@Path("id") int id, Callback<Shot> callback);

    @GET("/shots/popular")
    void getShotsByPopular(Callback<ShotList> callback);
}
