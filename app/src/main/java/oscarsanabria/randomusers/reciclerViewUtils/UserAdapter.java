package oscarsanabria.randomusers.reciclerViewUtils;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import oscarsanabria.randomusers.R;
import oscarsanabria.randomusers.app.Const;
import oscarsanabria.randomusers.data.Result;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context fContext;
    private List<Result> fUsers;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fGender, fName;

        public MyViewHolder(View view) {
            super(view);
            this.fGender = view.findViewById(R.id.gender);
            this.fName = view.findViewById(R.id.name);
        }
    }

    public UserAdapter(Context context, List<Result> users){
        this.fContext = context;

        this.fUsers = users;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        Result user = fUsers.get(position);
        holder.fGender.setText(Html.fromHtml("&#8226;"));
        holder.fGender.setTextColor(getGenderColor(user.getGender()));
        holder.fName.setText(fContext.getString(R.string.user_name,user.getName().getFirst(),user.getName().getLast()));
    }

    @Override
    public int getItemCount() {
        return fUsers.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private int getGenderColor(String gender) {
        Log.d("gender",":"+gender);
        if(gender.equals(Const.MALE)){
            return fContext.getColor(R.color.colorBlue);
        }
        return fContext.getColor(R.color.colorPink);
    }
}
