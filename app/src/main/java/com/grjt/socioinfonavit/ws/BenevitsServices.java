package com.grjt.socioinfonavit.ws;

import com.grjt.socioinfonavit.model.Landing;
import com.grjt.socioinfonavit.model.Wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BenevitsServices {

    @GET("member/landing_benevits")
    Call<Landing> getWallets(@Header("Authorization") String authorization);

}
