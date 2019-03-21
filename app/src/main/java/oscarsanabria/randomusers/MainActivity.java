package oscarsanabria.randomusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import oscarsanabria.randomusers.reciclerViewUtils.UserAdapter;
import oscarsanabria.randomusers.apiCalls.RandomUserService;
import oscarsanabria.randomusers.apiCalls.RandomUsersAPIModule;
import oscarsanabria.randomusers.data.APIResponse;
import oscarsanabria.randomusers.data.Result;
import oscarsanabria.randomusers.reciclerViewUtils.UserTouchListener;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {
    private RecyclerView fRecyclerView;
    private UserAdapter fUserAdapter;
    private List<Result> fUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fRecyclerView = findViewById(R.id.recycler_view);
        fUserAdapter = new UserAdapter(this,fUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        fRecyclerView.setLayoutManager(mLayoutManager);
        fRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        fRecyclerView.addOnItemTouchListener(new UserTouchListener((view, position) -> {
            Result user = fUsers.get(position);
            Toast.makeText(getApplicationContext(), user.getEmail() + " is selected!", Toast.LENGTH_SHORT).show();
        }));
        fRecyclerView.setAdapter(fUserAdapter);

        getUsers();
    }

    private void getUsers(){

        RandomUserService apiResponse = RandomUsersAPIModule.getClient(getApplicationContext()).create(RandomUserService.class);

        apiResponse.fetchAllUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<APIResponse>() {
            @Override
            public void onSuccess(APIResponse result) {
                try {
                    //Gson gson = new Gson();
                    Log.d("success_retro", "result " + result);

                    if (result != null && result.getResults() != null) {
                        Log.d("success_retro", "result size" + result.getResults().size());
                        fUsers.clear();
                        fUsers.addAll(result.getResults());
                        fUserAdapter.notifyDataSetChanged();
                    }
                    //Log.d("success_retro", "result is null " + gson.toJson(result));
                }catch(Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
