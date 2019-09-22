package com.example.privatchat.model.internet;

import com.example.privatchat.mainclass.Dialog;
import com.example.privatchat.mainclass.Message;
import com.example.privatchat.mainclass.User;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface API {

    @GET("/getUser")
    Observable<User> hello(@Query(value = "ldap") String LDAP);

    @POST("/getDialog")
    Observable<List<Dialog>> getDialog(@Body User user );

    @POST("/getMessage")
    Observable<List<Message>> getMessage(@Body User user );
}
