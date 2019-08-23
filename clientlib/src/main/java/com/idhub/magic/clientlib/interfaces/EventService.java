package com.idhub.magic.clientlib.interfaces;

import java.util.List;

import com.idhub.magic.center.event.ChainEvent;
import com.idhub.magic.center.parameter.MagicResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventService {
	@GET("event/query_events")
	Call<MagicResponse<List<ChainEvent>>> queryEvents(@Query("identity") String identity);
}
