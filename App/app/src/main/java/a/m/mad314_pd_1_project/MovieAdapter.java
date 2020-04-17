package a.m.mad314_pd_1_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private View.OnClickListener movieListener;


    public MovieAdapter(ArrayList<Movie> movies, Context c) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);

        return new ViewHolder(view);
    }

    public void setOnItemClickListner(View.OnClickListener itemClickListner)
    {
        movieListener = itemClickListner;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(movies.get(position).image).into(holder.movie_image);
        holder.movie_name.setText(movies.get(position).name);
        holder.movie_duration.setText(movies.get(position).dutation);
        holder.movie_rating.setText(movies.get(position).rating);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movie_image;
        TextView movie_name;
        TextView movie_duration;
        TextView movie_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_image = itemView.findViewById(R.id.movie_image);
            movie_name = itemView.findViewById(R.id.movie_name);
            movie_duration = itemView.findViewById(R.id.movie_duration);
            movie_rating = itemView.findViewById(R.id.movie_rating);

            itemView.setTag(this);
            itemView.setOnClickListener(movieListener);



        }
    }

}
