package com.example.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.provider.Contacts;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.model.People;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Neha Rathore on 7/12/2018.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ServicesViewHolder> {

    private List<People> mServiceList;
    private RecyclerClickListener listener;
    private RecyclerView recyclerView;
    private Typeface tf;
    private static final String TAG = "PeopleAdpater";
    private String clientId;
    private String clientType;
    private String clientMobile;
    private SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    View mView;
    View holderView;
    Context context;


    PeopleAdapter(Context context) {
        this.context = context;
    }

    public PeopleAdapter(Context context, List<People> mServiceList, RecyclerView recyclerView) {
        this.mServiceList = mServiceList;
        this.recyclerView = recyclerView;

        this.context = context;
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.listener = recyclerClickListener;
    }

    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        ServicesViewHolder servicesViewHolder = new ServicesViewHolder(mView);

        return servicesViewHolder;
    }

    @Override
    public void onBindViewHolder(ServicesViewHolder holder, final int position) {
        // animation= AnimationUtils.loadAnimation(context, R.anim.push_left_in);

        Log.d("peoplelist","peoplelist"+ mServiceList.size());
        final People services = mServiceList.get(position);
        holder.serviceName.setText(services.getName());

        holder.cardViewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "hii", Toast.LENGTH_SHORT).show();
                try {

                    listener.servicenumber(services.getId(), services.getName(), services.getMass(),services.getHeight(),services.getCreated());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mServiceList.size();

    }



    public class ServicesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt)
        TextView serviceName;
        //        @Bind(R.id.side_strip)
//        LinearLayout relate;
        @Bind(R.id.card_view_services_default)
        CardView cardViewService;
//        @Bind(R.id.starts_from)
//        TextView startsfrom;
//        @Bind(R.id.service_price_default)
//        TextView monthlyPrice;
//        @Bind(R.id.service_currency_default)
//        TextView currency;


        public ServicesViewHolder(View itemView) {
            super(itemView);


            ButterKnife.bind(this, itemView);}
    }


    public interface RecyclerClickListener {
        void servicenumber(String serviceId, String Name, String mass, String height, String date);
    }

    public void setData(List<People> data) {
        if (mServiceList != null)
            mServiceList.clear();
        else
            mServiceList = new ArrayList<>();
        if (data != null)
            mServiceList.addAll(data);
        notifyDataSetChanged();
    }
}


