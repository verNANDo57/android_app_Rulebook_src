package com.verNANDo57.rulebook_educational.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.verNANDo57.rulebook_educational.customthemeengine.utils.ColorUtils;
import com.verNANDo57.rulebook_educational.extradata.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    static class SearchHolder extends RecyclerView.ViewHolder {

        private final CardView search_item_card;
        private final ImageView search_item_imageview;
        public final TextView search_item_title;
        public final TextView search_item_summary;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            search_item_card = itemView.findViewById(R.id.recycler_view_item_card);
            search_item_imageview = itemView.findViewById(R.id.search_item_imageview);
            search_item_title = itemView.findViewById(R.id.search_item_title);
            search_item_summary= itemView.findViewById(R.id.search_item_summary);
        }
    }

    public static ArrayList<SearchItemData> listdata;
    public static SearchItemData mSearchItemData;

    public SearchAdapter(ArrayList<SearchItemData> listdata) {
        SearchAdapter.listdata = listdata;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SearchHolder(listItem);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        Context context = holder.search_item_card.getContext();
        mSearchItemData = listdata.get(position);
        holder.search_item_title.setText(listdata.get(position).getItemTitle());
        holder.search_item_summary.setText(listdata.get(position).getItemDescription());
        holder.search_item_imageview.setImageResource(listdata.get(position).getItemImgId());
        holder.search_item_imageview.setColorFilter(ColorUtils.lighter(context.getResources().getColor(R.color.coloraccent), 0.01f));
        //Set CardView background value to TRANSPARENT (equals 0) to make it look better
        holder.search_item_card.setCardBackgroundColor(0);
        //Remove shadow around cardview to make it look better
        holder.search_item_card.setCardElevation(0);
        holder.search_item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: HANDLE CLICK
                //Firstly animate view, then start activity
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.app_fade_out);
                animation.setDuration(125);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //Generated Method
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //Generated Method
                        //Get context from any VIEW object instead of passing CONTEXT as an argument to SearchAdapter and keeping it as class field
                        SearchReferences.SearchDataOnClickReferences(context, holder);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //Generated Method
                    }
                });
                view.startAnimation(animation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public void filterList(ArrayList<SearchItemData> filteredList) {
        listdata = filteredList;
        notifyDataSetChanged();
    }
}
