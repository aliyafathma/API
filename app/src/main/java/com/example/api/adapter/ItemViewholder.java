package com.example.api.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.api.databinding.ItemUserBinding;
import com.example.api.model.Name;

import java.util.ArrayList;

public class ItemViewholder extends RecyclerView.ViewHolder {
    //define file java binding kita dulu. Binding = untuk menampilkan view
    private ItemUserBinding binding;

    public ItemViewholder(@NonNull ItemUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Name nama) {
        binding.setName(nama);
    }
}
