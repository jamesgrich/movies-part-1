package ch.jamesgri.moviesnew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ch.jamesgri.moviesnew.data.model.Movies;

import static ch.jamesgri.moviesnew.data.remote.ApiUtils.BASE_URL;
import static ch.jamesgri.moviesnew.data.remote.ApiUtils.SIZE;

/**
 * Created by jamesrichardson on 21/03/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movies> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView movieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movies_artwork);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mItemListener.onPostClick(getItem(clickedPosition));
        }
    }

    public MoviesAdapter(Context context, List<Movies> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.movies_list_item, parent, false);
        postView.setFocusable(true);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movies item = mItems.get(position);

        Picasso.with(mContext)
                .load(item.getPosterPath())
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateMovies(List<Movies> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Movies getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(Movies movies);
    }
}