package a.m.mad314_pd_1_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieResponse> movies;
    private View.OnClickListener movieListener;


    public MovieAdapter(ArrayList<MovieResponse> movies, Context c) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);

        return new ViewHolder(view);
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListner)
    {
        movieListener = itemClickListner;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(movies.get(position).getImage()).into(holder.movie_image);
        holder.movie_name.setText(movies.get(position).getMovieName());
        holder.movie_duration.setText(movies.get(position).getDuration());
        holder.movie_category.setText(movies.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movie_image;
        TextView movie_name;
        TextView movie_category;
        TextView movie_duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_image = itemView.findViewById(R.id.movie_image);
            movie_name = itemView.findViewById(R.id.movie_name);
            movie_duration = itemView.findViewById(R.id.movie_duration);
            movie_category = itemView.findViewById(R.id.movie_category);

            itemView.setTag(this);
            itemView.setOnClickListener(movieListener);



        }
    }

}
