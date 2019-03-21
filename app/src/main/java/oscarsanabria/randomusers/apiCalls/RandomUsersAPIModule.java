package oscarsanabria.randomusers.apiCalls;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import oscarsanabria.randomusers.app.Const;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomUsersAPIModule {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Const.RANDOM_USERS_BASE_URL)
                    .build();
        }
        return retrofit;
    }

}
