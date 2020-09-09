package com.grjt.socioinfonavit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.grjt.socioinfonavit.R;
import com.grjt.socioinfonavit.model.Benevit;
import com.grjt.socioinfonavit.model.Benevits;
import com.grjt.socioinfonavit.model.Landing;
import com.grjt.socioinfonavit.model.Locked;
import com.grjt.socioinfonavit.model.Unlocked;
import com.grjt.socioinfonavit.model.Wallet;
import com.grjt.socioinfonavit.ui.adapter.WalletAdapter;
import com.grjt.socioinfonavit.ws.ApiCall;
import com.grjt.socioinfonavit.ws.BenevitsServices;
import com.grjt.socioinfonavit.ws.Callback;
import com.grjt.socioinfonavit.ws.GetWallets;
import com.grjt.socioinfonavit.ws.OnTaskCompletedArray;

import org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.grjt.socioinfonavit.control.Global.Wallet1;
import static com.grjt.socioinfonavit.control.Global.Wallet2;
import static com.grjt.socioinfonavit.control.Global.Wallet3;
import static com.grjt.socioinfonavit.control.Global.Wallet4;
import static com.grjt.socioinfonavit.control.Global.Wallet5;
import static com.grjt.socioinfonavit.control.Global.Wallet6;
import static com.grjt.socioinfonavit.control.Global.Wallet7;
import static com.grjt.socioinfonavit.control.Global.Wallet8;
import static com.grjt.socioinfonavit.control.Global.Wallet9;
import static com.grjt.socioinfonavit.control.Global.landingBenevits;
import static com.grjt.socioinfonavit.control.Global.signature;
import static com.grjt.socioinfonavit.control.Global.system_WServices;
import static com.grjt.socioinfonavit.control.Global.wallets;

public class BenevitActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    private BenevitActivity context;
    private Callback callBack = new Callback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benevit);

        context = this;
        initActions();
    }

    private void initActions(){
        URL[] urls;
        final GetWallets getWallets = new GetWallets();
        urls = getWallets.getWallets("wallets");

        ApiCall asyncT = new ApiCall(new OnTaskCompletedArray() {
            @Override
            public void onTaskCompleted(JSONArray result, String type) {
                if (type.equals("OK")) {
                    callBack.processingResult(result, "wallet");
                    //creamos la lista de carteras
                    setWalletsService();
                    getLandingBenevits();
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        asyncT.execute(urls);
    }

    private void setWalletsService(){
        Wallet1 = new Benevits();
        Wallet2 = new Benevits();
        Wallet3 = new Benevits();
        Wallet4 = new Benevits();
        Wallet5 = new Benevits();
        Wallet6 = new Benevits();
        Wallet7 = new Benevits();
        Wallet8 = new Benevits();
        Wallet9 = new Benevits();
        for (Wallet wallet: wallets) {
            switch (wallet.getId()){
                case 1:
                    Wallet1.setId(wallet.getId());
                    Wallet1.setDescription(wallet.getName());
                    break;
                case 2:
                    Wallet2.setId(wallet.getId());
                    Wallet2.setDescription(wallet.getName());
                    break;
                case 3:
                    Wallet3.setId(wallet.getId());
                    Wallet3.setDescription(wallet.getName());
                    break;
                case 4:
                    Wallet4.setId(wallet.getId());
                    Wallet4.setDescription(wallet.getName());
                    break;
                case 5:
                    Wallet5.setId(wallet.getId());
                    Wallet5.setDescription(wallet.getName());
                    break;
                case 6:
                    Wallet6.setId(wallet.getId());
                    Wallet6.setDescription(wallet.getName());
                    break;
                case 7:
                    Wallet7.setId(wallet.getId());
                    Wallet7.setDescription(wallet.getName());
                    break;
                case 8:
                    Wallet8.setId(wallet.getId());
                    Wallet8.setDescription(wallet.getName());
                    break;
                case 9:
                    Wallet9.setId(wallet.getId());
                    Wallet9.setDescription(wallet.getName());
                    break;
                default:
                    break;
            }
        }

    }

    private void getLandingBenevits(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(system_WServices)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BenevitsServices benevitsServices = retrofit.create(BenevitsServices.class);
        Call<Landing> call = benevitsServices.getWallets(signature);
        call.enqueue(new retrofit2.Callback<Landing>() {
            @Override
            public void onResponse(Call<Landing> call, Response<Landing> response) {
                if(!response.isSuccessful()){
                    return;
                }
                landingBenevits = response.body();
                selectBenevit();
                loadWallets();
                Log.d("onResponse", "OK");
            }

            @Override
            public void onFailure(Call<Landing> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }

    private void selectBenevit(){
        ArrayList<Benevit> benevit1 = new ArrayList<>();
        ArrayList<Benevit> benevit2 = new ArrayList<>();
        ArrayList<Benevit> benevit3 = new ArrayList<>();
        ArrayList<Benevit> benevit4 = new ArrayList<>();
        ArrayList<Benevit> benevit5 = new ArrayList<>();
        ArrayList<Benevit> benevit6 = new ArrayList<>();
        ArrayList<Benevit> benevit7 = new ArrayList<>();
        ArrayList<Benevit> benevit8 = new ArrayList<>();
        ArrayList<Benevit> benevit9 = new ArrayList<>();
        for (Locked locked: landingBenevits.getLocked()) {
            switch (locked.getWallet().getId()){
                case 1:
                    benevit1.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 2:
                    benevit2.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 3:
                    benevit3.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 4:
                    benevit4.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 5:
                    benevit5.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 6:
                    benevit6.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 7:
                    benevit7.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 8:
                    benevit8.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                case 9:
                    benevit9.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), true));
                    break;
                default:
                    break;
            }
        }
        for (Unlocked locked: landingBenevits.getUnlocked()) {
            switch (locked.getWallet().getId()){
                case 1:
                    benevit1.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 2:
                    benevit2.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 3:
                    benevit3.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 4:
                    benevit4.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 5:
                    benevit5.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 6:
                    benevit6.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 7:
                    benevit7.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 8:
                    benevit8.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                case 9:
                    benevit9.add(new Benevit(locked.getId(), locked.getName(), locked.getDescription(), locked.getTitle(),
                            locked.getInstructions(), locked.getExpirationDate(), locked.isActive(),
                            locked.getPrimaryColor(), locked.isHasCoupons(), locked.getVectorFileName(),
                            locked.getVectorFullPath(), locked.getSlug(), locked.getWallet(),
                            locked.getTerritories(), locked.getAlly(), false));
                    break;
                default:
                    break;
            }
        }
        Wallet1.setBenevit(benevit1);
        Wallet2.setBenevit(benevit2);
        Wallet3.setBenevit(benevit3);
        Wallet4.setBenevit(benevit4);
        Wallet5.setBenevit(benevit5);
        Wallet6.setBenevit(benevit6);
        Wallet7.setBenevit(benevit7);
        Wallet8.setBenevit(benevit8);
        Wallet9.setBenevit(benevit9);
    }

    private void loadWallets(){
        lLayout = new GridLayoutManager(BenevitActivity.this, 2);

        RecyclerView walletsView = (RecyclerView) findViewById(R.id.walletsGridView);
        walletsView.setHasFixedSize(true);
        walletsView.setLayoutManager(lLayout);

        WalletAdapter walletAdapter = new WalletAdapter(Wallet1.benevit, context);
        walletsView.setAdapter(walletAdapter);
        walletsView.setLayoutManager(new LinearLayoutManager(this));
    }
}