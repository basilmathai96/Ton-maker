package com.diyandroid.startuptimes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diyandroid.startuptimes.Adapter.PageAdapter;
import com.diyandroid.startuptimes.Class.ListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ReportingFragment extends Fragment {

    View view;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    List<ListItem> movieList = new ArrayList<>();

    // Get a reference to the Firebase NewsFragment, and portions of that NewsFragment.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference newsRef = database.getReference("News/Pending");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_database, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Reporting News");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //attaching value event listener
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous news list
                movieList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting news & adding news to the list
                    ListItem news3 = postSnapshot.getValue(ListItem.class);
                    movieList.add(news3);
                }

                if (getActivity() != null) {
                    //creating adapter
                    mAdapter = new PageAdapter(movieList);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(view.getContext(), "Error: " + databaseError, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
