package a.m.mad314_pd_1_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    MovieAdapter adapter;
    ArrayList<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    public void initRecyclerView(View v){

        movies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Movie movie = new Movie();

            movie.dutation = "2h 30m";
            movie.name = "Movie 1";
            movie.rating= "4.5";
            movie.image = "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_.jpg";

            movies.add(movie);
        }

        adapter = new MovieAdapter(movies,getActivity().getApplication());
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity().getApplicationContext(),1);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView_movies);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(onItemClickpoke);
    }

    public View.OnClickListener onItemClickpoke = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            RecyclerView.ViewHolder viewHolder= (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Toast.makeText(getActivity().getApplicationContext(),movies.get(position).name,Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.overflow_menu,menu);
        menu.findItem(R.id.logout_menu).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:


                FirebaseAuth.getInstance().signOut();
                moveToNewActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void moveToNewActivity () {

        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}
