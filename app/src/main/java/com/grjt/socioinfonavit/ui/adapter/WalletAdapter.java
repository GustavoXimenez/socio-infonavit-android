package com.grjt.socioinfonavit.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grjt.socioinfonavit.R;
import com.grjt.socioinfonavit.model.Benevit;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    private ArrayList<Benevit> benevits;
    private Context context;

    public WalletAdapter(ArrayList<Benevit> benevits, Context context){
        this.benevits = benevits;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.holder_wallet, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Benevit benevit = benevits.get(position);

            ImageView imgWallet = holder.imageWallet;
            ImageView imgWalletLoveIt = holder.imageWalletLoveIt;
            TextView tvDescription = holder.description;
            TextView tvCountry = holder.textCountry;
            TextView tvExpired = holder.textExpired;
            Button btnLoveIt = holder.btnLoveIt;

            if(benevit.getType()){
                if(benevit.getAlly().getMiniLogoFullPath() != null) {
                    Picasso.get()
                            .load(benevit.getAlly().getMiniLogoFullPath())
                            .into(imgWalletLoveIt);
                }

                imgWallet.setVisibility(View.GONE);
                tvDescription.setVisibility(View.GONE);
                tvCountry.setVisibility(View.GONE);
                tvExpired.setVisibility(View.GONE);

            } else {
                if(benevit.getVectorFullPath() != null) {
                    Picasso.get()
                            .load(benevit.getVectorFullPath())
                            .into(imgWallet);
                }

                imgWallet.setBackgroundColor(Color.parseColor(benevit.getPrimaryColor()));

                if(benevit.getDescription() != null) tvDescription.setText(benevit.getDescription());

                String strDays = String.format(context.getString(R.string.main_wallet_expire) + " ", calculateDays(benevit.getExpirationDate()));
                if(benevit.getExpirationDate() != null) tvExpired.setText(strDays);

                if(benevit.getTerritories().size()>0){
                    tvCountry.setText(benevit.getTerritories().get(0).getName());
                }

                imgWalletLoveIt.setVisibility(View.GONE);
                btnLoveIt.setVisibility(View.GONE);
            }
        } catch (Exception e){
            Log.d("onBindViewHolder", e.getMessage());
        }
    }

    private long calculateDays(String date){
        int day = Integer.parseInt("31");
        int month = Integer.parseInt("12");
        int year = Integer.parseInt("2020");
        long noOfDaysBetween = 0;

        try {
            String[] arrayDate = date.split("-");
            day = Integer.parseInt(arrayDate[0]);
            month = Integer.parseInt(arrayDate[1]);
            year = Integer.parseInt(arrayDate[2]);
        } catch (Exception e){

        }
        LocalDate dateBefore = LocalDate.now();
        LocalDate dateAfter = LocalDate.of(day, month, year);
        noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        return noOfDaysBetween;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemCount(){
        if(benevits.isEmpty()){
            return 0;
        } else {
            return benevits.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageWallet;
            private ImageView imageWalletLoveIt;
            private TextView description;
            private TextView textCountry;
            private TextView textExpired;
            private Button btnLoveIt;

            public ViewHolder(@NonNull View itemView){
                super(itemView);

                imageWallet = itemView.findViewById(R.id.imgWallet);
                imageWalletLoveIt = itemView.findViewById(R.id.imgWalletLoveIt);
                description = itemView.findViewById(R.id.tvDescription);
                textCountry = itemView.findViewById(R.id.tvCountry);
                textExpired = itemView.findViewById(R.id.tvExpired);
                btnLoveIt = itemView.findViewById(R.id.btnWantIt);
            }
    }

}
