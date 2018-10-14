package com.example.aura.submission4_basisdata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aura.submission4_basisdata.BuildConfig;
import com.example.aura.submission4_basisdata.DetailActivity;
import com.example.aura.submission4_basisdata.R;
import com.example.aura.submission4_basisdata.helper.Config;
import com.example.aura.submission4_basisdata.model.FavModel;
import com.example.aura.submission4_basisdata.model.ResultItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<ResultItem> listMovie;
    private List<ResultItem> search;

    public MovieAdapter(Context context, ArrayList<ResultItem> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle;
        TextView tvOverview;
        TextView tvDate;
        TextView tvRating;
        CardView klik_detail;
        Button btn_share;
        Button btn_detail;

        MyViewHolder(View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.poster);
            tvTitle = itemView.findViewById(R.id.tittle);
            tvOverview = itemView.findViewById(R.id.deskripsi);
            tvDate = itemView.findViewById(R.id.date);
            tvRating = itemView.findViewById(R.id.rating);
            klik_detail = itemView.findViewById(R.id.detailKlik);
            btn_share = itemView.findViewById(R.id.btnShare);
            btn_detail = itemView.findViewById(R.id.btnDetail);
        }
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder holder, final int position) {
        holder.tvTitle.setText(listMovie.get(position).getTitle());
        holder.tvOverview.setText(listMovie.get(position).getOverview() + " ...");
        holder.tvRating.setText(listMovie.get(position).getVoteAverage());
        DateFormat inputFormat = new SimpleDateFormat(context.getString(R.string.FORMAT_YYY_MM_DD));
        DateFormat outputFormat = new SimpleDateFormat(context.getString(R.string.FORMAT_DAY_DD_MM_YYYY));
        String inputDateStr = listMovie.get(position).getReleaseDate();
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        holder.tvDate.setText(outputDateStr);
        Glide.with(context)
                .load(BuildConfig.IMAGE + listMovie.get(position).getPosterPath())
                .into(holder.imgPoster);

        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id_movie", listMovie.get(position).getId());
                intent.putExtra("poster", listMovie.get(position).getPosterPath());
                intent.putExtra("backdrop", listMovie.get(position).getBackdropPath());
                intent.putExtra("title", holder.tvTitle.getText().toString().trim());
                intent.putExtra("overview", listMovie.get(position).getOverview());
                intent.putExtra("relase_date", holder.tvDate.getText().toString().trim());
                intent.putExtra("vote", listMovie.get(position).getVoteAverage());
                intent.putExtra("language", listMovie.get(position).getOriginalLanguage());

//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra(Config.BUNDLE_ID, listMovie.get(position).getId());
//                intent.putExtra(Config.BUNDLE_POSTER_IMAGE, BuildConfig.IMAGE + listMovie.get(position).getPosterPath());
//                intent.putExtra(Config.BUNDLE_TITTLE, holder.tvTitle.getText().toString().trim());
//                intent.putExtra(Config.BUNDLE_OVERVIEW, listMovie.get(position).getOverview());
//                intent.putExtra(Config.BUNDLE_RELEASE_DATE, holder.tvDate.getText().toString().trim());
//                intent.putExtra(Config.BUNDLE_VOTE_AVERAGE, listMovie.get(position).getVoteAverage());
//                intent.putExtra(Config.BUNDLE_ORIGINAL_LANGUAGE, listMovie.get(position).getOriginalLanguage());
//                intent.putExtra(Config.BUNDLE_BACKDROP_IMAGE, BuildConfig.IMAGE + listMovie.get(position).getBackdropPath());
                context.startActivity(intent);
            }
        });

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = holder.tvTitle.getText().toString().trim();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Link by TMDB");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults returnMovie = new FilterResults();
                final ArrayList<ResultItem> hasilPencarian = new ArrayList<>();

                if (search == null)
                    search = listMovie;
                if (constraint != null) {
                    if (listMovie != null & search.size() > 0) {
                        for (final ResultItem result : search) {
                            if (result.getTitle().toLowerCase().contains(constraint.toString()))
                                hasilPencarian.add(result);
                        }
                    }
                    returnMovie.values = hasilPencarian;
                }

                return returnMovie;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listMovie = (ArrayList<ResultItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}
