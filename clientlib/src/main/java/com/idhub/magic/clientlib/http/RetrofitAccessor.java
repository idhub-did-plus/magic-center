package com.idhub.magic.clientlib.http;

import retrofit2.Call;
import retrofit2.http.GET;

public class RetrofitAccessor {


	public interface UserService {
	    @GET("not_restful/user/{name}.json")
	    Call<User> loadUser(@Path("name") String name);

	}

	//Bean和Service准备好，接下来就是调用Retrofit了：
	OkHttpClient client = new OkHttpClient.Builder().build();
	Retrofit retrofit = new Retrofit.Builder().baseUrl("http://***.b0.upaiyun.com/")
	                                          .addConverterFactory(GsonConverterFactory.create())
	                                          .client(client)
	                                          .build();

	UserService userService = retrofit.create(UserService.class);

	User user = userService.loadUser("kkmike999")
	                   .execute()
	                   .body();
}
