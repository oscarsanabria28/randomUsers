package oscarsanabria.randomusers.apiCalls;

import java.util.List;

import io.reactivex.Single;
import oscarsanabria.randomusers.data.APIResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RandomUserService {
    // Fetch all users
    @GET("api?seed=oscar&results=20&nat=us")
    Single<APIResponse> fetchAllUsers();
    //Single<APIResponse> fetchAllUsers(@Query("results") int count);
}
