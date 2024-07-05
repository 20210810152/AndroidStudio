package com.bitohur.androidclient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
        @FormUrlEncoded
        @POST("add_user.php")
        Call<ResponseBody> addUser(
                @Field("nama") String nama,
                @Field("nim") String nim,
                @Field("jk") String jk,
                @Field("fakultas") String fakultas,
                @Field("kelas") String kelas,
                @Field("agama") String agama,
                @Field("alamat") String alamat
        );
        @POST("add_user.php")
        Call<Void> insertUser(@Body User user);
        @GET("get_users.php")
        Call<List<User>> getUsers();

        @PUT("update_user_process.php")
        Call<Void> updateUser(@Body User user);

        @DELETE("delete_user.php")
        Call<Void> deleteUser(@Query("id") int userId);
    }
