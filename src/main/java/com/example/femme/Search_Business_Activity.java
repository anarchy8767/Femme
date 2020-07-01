package com.example.femme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.femme.Model.Products;
import com.example.femme.ViewHolder.BusinessViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Search_Business_Activity extends AppCompatActivity
{

    private Button Searchbtn;
    //private EditText inputText;
    private Spinner  inputText;
    private RecyclerView searchList;
    private String SearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__business_);



        inputText = findViewById(R.id.search_product_name);
        Searchbtn = findViewById(R.id.search_btn1);
        searchList = findViewById(R.id.search_list);

        searchList.setLayoutManager(new LinearLayoutManager(Search_Business_Activity.this));

        Searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              SearchInput = inputText.getSelectedItem().toString();

              onStart();

            }
        });
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Business");

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(reference.orderByChild("category").startAt(SearchInput).endAt(SearchInput + "\uf8ff"), Products.class)
                .build();

        FirebaseRecyclerAdapter<Products, BusinessViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, BusinessViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusinessViewHolder holder, int i, @NonNull final Products products)
                    {

                     holder.txtBusinessName.setText(products.getBusiness_name());
                     holder.txtCategory.setText("Category: " + products.getCategory());
                     holder.txtAddress.setText("Address: " + products.getAddress());

                     holder.itemView.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v)
                         {
                             Intent intent = new Intent(Search_Business_Activity.this, Business_Detail_Activity.class);
                             intent.putExtra("bid", products.getBid());
                             startActivity(intent);


                         }
                     });

                    }

                    @NonNull
                    @Override
                    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_layout,parent, false);
                       BusinessViewHolder holder = new BusinessViewHolder(view);
                       return holder;

                    }
                };

        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}
