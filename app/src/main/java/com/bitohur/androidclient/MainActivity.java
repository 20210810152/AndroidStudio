package com.bitohur.androidclient;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import okhttp3.ResponseBody;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserDialog();
            }
        });

        fetchUsers();
    }

    private void showAddUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add User");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
        final EditText editTextNama = view.findViewById(R.id.editTextNama);
        final EditText editTextNim = view.findViewById(R.id.editTextNim);
        final EditText editTextJk = view.findViewById(R.id.editTextJk);
        final EditText editTextFakultas = view.findViewById(R.id.editTextFakultas);
        final EditText editTextKelas = view.findViewById(R.id.editTextKelas);
        final EditText editTextAgama = view.findViewById(R.id.editTextAgama);
        final EditText editTextAlamat = view.findViewById(R.id.editTextAlamat);

        builder.setView(view);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editTextNama.getText().toString();
                String nim = editTextNim.getText().toString();
                String jk = editTextJk.getText().toString();
                String fakultas = editTextFakultas.getText().toString();
                String kelas = editTextKelas.getText().toString();
                String agama = editTextAgama.getText().toString();
                String alamat = editTextAlamat.getText().toString();
                addUser(nama, nim, jk, fakultas, kelas, agama, alamat);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void addUser(String nama, String nim, String jk, String fakultas, String kelas, String agama, String alamat) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        User user = new User(nama, nim, jk, fakultas, kelas, agama, alamat);
        Call<Void> call = apiService.insertUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                    fetchUsers();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to add user", Toast.LENGTH_SHORT).show();
            }
        });
    }


// ...

    private void fetchUsers() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList.clear();
                    userList.addAll(response.body());
                    userAdapter.notifyDataSetChanged();
                } else {
                    Log.e("MainActivity", "Response error: " + response.errorBody().toString());
                    Toast.makeText(MainActivity.this, "Failed to fetch users: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Kontol", "Fetch error: ", t);
                Toast.makeText(MainActivity.this, "Failed to fetch users: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(int id, String nama, String nim, String jk, String fakultas, String kelas, String agama, String alamat) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        User user = new User(id, nama, nim, jk, fakultas, kelas, agama, alamat);
        Call<Void> call = apiService.updateUser(user);

        Log.d("Check update", "Updating user: " + id + ", " + nama + ", " + nim +", " + jk+ ", " + fakultas+ ", " + kelas+", " + agama+ ", " + alamat);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "User updated successfully");
                    Toast.makeText(MainActivity.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                    fetchUsers();
                } else {
                    Log.e("MainActivity", "Response error: " + response.errorBody().toString());
                    Toast.makeText(MainActivity.this, "Failed to update user: " + response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("MainActivity", "Fetch error: ", t);
                Toast.makeText(MainActivity.this, "Failed to update user: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void showUpdateDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update User");
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_update_user, (ViewGroup)
                findViewById(android.R.id.content), false);
        final EditText inputNama = viewInflated.findViewById(R.id.editTextNama);
        final EditText inputNim = viewInflated.findViewById(R.id.editTextNim);
        final EditText inputJk = viewInflated.findViewById(R.id.editTextJk);
        final EditText inputFakultas = viewInflated.findViewById(R.id.editTextFakultas);
        final EditText inputKelas = viewInflated.findViewById(R.id.editTextKelas);
        final EditText inputAgama = viewInflated.findViewById(R.id.editTextAgama);
        final EditText inputAlamat = viewInflated.findViewById(R.id.editTextAlamat);
        inputNama.setText(user.getNama());
        inputNim.setText(user.getNim());
        inputJk.setText(user.getJk());
        inputFakultas.setText(user.getFakultas());
        inputKelas.setText(user.getKelas());
        inputAgama.setText(user.getAgama());
        inputAlamat.setText(user.getAlamat());
        builder.setView(viewInflated);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String nama = inputNama.getText().toString();
                String nim = inputNim.getText().toString();
                String jk = inputJk.getText().toString();
                String fakultas = inputFakultas.getText().toString();
                String kelas = inputKelas.getText().toString();
                String agama = inputAgama.getText().toString();
                String alamat = inputAlamat.getText().toString();
                updateUser(user.getId(), nama, nim, jk, fakultas, kelas, agama, alamat);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void deleteUser(int userId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.deleteUser(userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User deleted successfully",
                            Toast.LENGTH_SHORT).show();
                    fetchUsers();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to delete user",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to delete user",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDeleteDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");
        builder.setMessage("Apakah anda yakin ingin menghapus user ini?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser(user.getId());
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
