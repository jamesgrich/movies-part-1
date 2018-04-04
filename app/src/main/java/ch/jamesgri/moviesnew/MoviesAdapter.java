package ch.jamesgri.moviesnew;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ch.jamesgri.moviesnew.data.model.Result;

/**
 * Created by jamesrichardson on 21/03/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<ClipData.Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView titleTv;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, ImageView movieImage, PostItemListener postItemListener) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movies_artwork);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ClipData.Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.R.id.getId());

            notifyDataSetChanged();
        }
    }

    public MoviesAdapter(Context context, List<ClipData.Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClipData.Item item = mItems.get(position);
        ImageView moviesImage = holder.titleTv;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateMovies(List<ClipData.Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private ClipData.Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}