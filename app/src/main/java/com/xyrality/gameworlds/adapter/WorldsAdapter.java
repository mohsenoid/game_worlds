package com.xyrality.gameworlds.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyrality.gameworlds.R;
import com.xyrality.gameworlds.network.model.World;

/**
 * Created by Mohsen on 1/20/16.
 */
public class WorldsAdapter extends RecyclerView.Adapter {
    private World[] worlds;

    public WorldsAdapter(World[] worlds) {
        this.worlds = worlds;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_world, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        World world = worlds[position];

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(world.getName());
        viewHolder.status.setText(world.getWorldStatus().getDescription());
        viewHolder.country.setText(world.getCountry());
    }

    @Override
    public int getItemCount() {
        return worlds.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView status;
        private final TextView country;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.world_name);
            country = (TextView) v.findViewById(R.id.world_country);
            status = (TextView) v.findViewById(R.id.world_status);

        }
    }
}
