package com.bitohur.androidclient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,
                false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewNama.setText(user.getNama());
        holder.textViewNim.setText(user.getNim());
        holder.textViewJk.setText(user.getJk());
        holder.textViewFakultas.setText(user.getFakultas());
        holder.textViewKelas.setText(user.getKelas());
        holder.textViewAgama.setText(user.getAgama());
        holder.textViewAlamat.setText(user.getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).showUpdateDialog(user);
                }
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).showDeleteDialog(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        Button buttonDelete;
        TextView textViewNama;
        TextView textViewNim;
        TextView textViewJk;
        TextView textViewFakultas;
        TextView textViewKelas;
        TextView textViewAgama;
        TextView textViewAlamat;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewNim = itemView.findViewById(R.id.textViewNim);
            textViewJk = itemView.findViewById(R.id.textViewJk);
            textViewFakultas = itemView.findViewById(R.id.textViewFakultas);
            textViewKelas = itemView.findViewById(R.id.textViewKelas);
            textViewAgama= itemView.findViewById(R.id.textViewAgama);
            textViewAlamat = itemView.findViewById(R.id.textViewAlamat);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

        }

    }

}
