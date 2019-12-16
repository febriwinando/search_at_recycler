package com.example.searcrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searcrecycler.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {
    private List<UserModel> userModelList;
    private List<UserModel> userModelListFull;

    public UserAdapter(List<UserModel> userModelList) {
        this.userModelList = userModelList;
        userModelListFull = new ArrayList<>(userModelList);
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserModel userModel = userModelList.get(position);

        holder.imageView.setImageResource(userModel.getPic());
        holder.textView1.setText(userModel.getNama());
        holder.textView2.setText(userModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    @Override
    public Filter getFilter() {
        return userModelFilter;
    }

    private Filter userModelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<UserModel> filteredList =new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(userModelListFull);
            }else{
                String filterpattern = constraint.toString().toLowerCase().trim();

                for (UserModel item : userModelListFull){
                    if (item.getNama().toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            userModelList.clear();
            userModelList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgView);
            textView1 = itemView.findViewById(R.id.txtView1);
            textView2 = itemView.findViewById(R.id.txtView2);
        }
    }
}
