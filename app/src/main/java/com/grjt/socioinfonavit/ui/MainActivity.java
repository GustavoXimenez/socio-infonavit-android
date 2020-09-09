package com.grjt.socioinfonavit.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.grjt.socioinfonavit.R;
import com.grjt.socioinfonavit.control.CustomTypefaceSpan;
import com.grjt.socioinfonavit.control.FontTypeface;
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
import com.grjt.socioinfonavit.ws.DeleteLogin;
import com.grjt.socioinfonavit.ws.DeleteRequest;
import com.grjt.socioinfonavit.ws.GetLandingBenevits;
import com.grjt.socioinfonavit.ws.GetWallets;
import com.grjt.socioinfonavit.ws.OnTaskCompleted;
import com.grjt.socioinfonavit.ws.OnTaskCompletedArray;
import com.grjt.socioinfonavit.ws.PostRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
import static com.grjt.socioinfonavit.control.Global.typeOfPostRequest;
import static com.grjt.socioinfonavit.control.Global.wallets;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MainActivity context;
    private Callback callBack = new Callback();

    private GridLayoutManager lLayout;

    private ShimmerRecyclerView shimmerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initMenu();
        initActions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerRecyclerView.showShimmerAdapter();
    }

    private void initView(){
        shimmerRecyclerView = (ShimmerRecyclerView) findViewById(R.id.shimmer_recycler_view);
    }

    private void initMenu(){
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setSystemUiVisibility(0);
        Menu menu = navigationView.getMenu();

        FontTypeface fontTypeface = new FontTypeface(this);
        Typeface typeface = fontTypeface.getTypefaceAndroid();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem != null) {
                SpannableString spannableString = new SpannableString(menuItem.getTitle());
                spannableString.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                menuItem.setTitle(spannableString);
                applyFontToItem(menuItem, typeface);
            }
        }

        navigationView.setNavigationItemSelectedListener(this);

        Button buttonButtonMenu = (Button) context.findViewById(R.id.title_bar_left_menu);
        buttonButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    private void applyFontToItem(MenuItem item, Typeface font) {
        SpannableString mNewTitle = new SpannableString(item.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font, 16), 0,
                mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        item.setTitle(mNewTitle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_benevits) {

        } else if (id == R.id.nav_logout) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(R.string.main_alert);
            alertDialog.setMessage(R.string.main_logout);
            alertDialog.setPositiveButton(context.getString(R.string.login_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    typeOfPostRequest = "logout";
                    DeleteRequest asyncT = new DeleteRequest(new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(JSONObject result, String type) {
                            switch (type){
                                case "OK":
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    startActivity(intent);
                                    break;
                                case "errorLogout":
                                    Toast.makeText(context, R.string.main_error_logout, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(context, R.string.main_error_service, Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                    asyncT.execute();
                }
            });
            alertDialog.setNegativeButton(context.getString(R.string.login_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                shimmerRecyclerView.hideShimmerAdapter();
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
                shimmerRecyclerView.hideShimmerAdapter();
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

    private void loadWallets() {
        lLayout = new GridLayoutManager(MainActivity.this, 2);

        try {
            if (Wallet1.getBenevit().size() > 0) {
                TextView textView1 = (TextView) findViewById(R.id.tvWallet1);
                textView1.setText(Wallet1.description);
                RecyclerView walletsView1 = (RecyclerView) findViewById(R.id.walletsGridView1);
                walletsView1.setHasFixedSize(true);
                walletsView1.setLayoutManager(lLayout);

                WalletAdapter walletAdapter1 = new WalletAdapter(Wallet1.benevit, context);
                walletsView1.setAdapter(walletAdapter1);
            }

            if (Wallet2.getBenevit().size() > 0) {
                TextView textView2 = (TextView) findViewById(R.id.tvWallet2);
                textView2.setText(Wallet2.description);
                RecyclerView walletsView2 = (RecyclerView) findViewById(R.id.walletsGridView2);
                walletsView2.setHasFixedSize(true);
                walletsView2.setLayoutManager(lLayout);

                WalletAdapter walletAdapter2 = new WalletAdapter(Wallet2.benevit, context);
                walletsView2.setAdapter(walletAdapter2);
            }

            if (Wallet3.getBenevit().size() > 0) {
                TextView textView3 = (TextView) findViewById(R.id.tvWallet3);
                textView3.setText(Wallet3.description);
                RecyclerView walletsView3 = (RecyclerView) findViewById(R.id.walletsGridView3);
                walletsView3.setHasFixedSize(true);
                walletsView3.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter3 = new WalletAdapter(Wallet3.benevit, context);
                walletsView3.setAdapter(walletAdapter3);
            }

            if (Wallet4.getBenevit().size() > 0) {
                TextView textView4 = (TextView) findViewById(R.id.tvWallet4);
                textView4.setText(Wallet4.description);
                RecyclerView walletsView4 = (RecyclerView) findViewById(R.id.walletsGridView4);
                walletsView4.setHasFixedSize(true);
                walletsView4.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter4 = new WalletAdapter(Wallet4.benevit, context);
                walletsView4.setAdapter(walletAdapter4);
            }

            if (Wallet5.getBenevit().size() > 0) {
                TextView textView5 = (TextView) findViewById(R.id.tvWallet5);
                textView5.setText(Wallet5.description);
                RecyclerView walletsView5 = (RecyclerView) findViewById(R.id.walletsGridView5);
                walletsView5.setHasFixedSize(true);
                walletsView5.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter5 = new WalletAdapter(Wallet5.benevit, context);
                walletsView5.setAdapter(walletAdapter5);
            }

            if (Wallet6.getBenevit().size() > 0) {
                TextView textView6 = (TextView) findViewById(R.id.tvWallet6);
                textView6.setText(Wallet6.description);
                RecyclerView walletsView6 = (RecyclerView) findViewById(R.id.walletsGridView6);
                walletsView6.setHasFixedSize(true);
                walletsView6.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter6 = new WalletAdapter(Wallet6.benevit, context);
                walletsView6.setAdapter(walletAdapter6);
            }

            if (Wallet7.getBenevit().size() > 0) {
                TextView textView7 = (TextView) findViewById(R.id.tvWallet7);
                textView7.setText(Wallet7.description);
                RecyclerView walletsView7 = (RecyclerView) findViewById(R.id.walletsGridView7);
                walletsView7.setHasFixedSize(true);
                walletsView7.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter7 = new WalletAdapter(Wallet7.benevit, context);
                walletsView7.setAdapter(walletAdapter7);
            }

            if (Wallet8.getBenevit().size() > 0) {
                TextView textView8 = (TextView) findViewById(R.id.tvWallet8);
                textView8.setText(Wallet8.description);
                RecyclerView walletsView8 = (RecyclerView) findViewById(R.id.walletsGridView8);
                walletsView8.setHasFixedSize(true);
                walletsView8.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter8 = new WalletAdapter(Wallet8.benevit, context);
                walletsView8.setAdapter(walletAdapter8);
            }

            if (Wallet9.getBenevit().size() > 0) {
                TextView textView7 = (TextView) findViewById(R.id.tvWallet7);
                textView7.setText(Wallet7.description);
                RecyclerView walletsView9 = (RecyclerView) findViewById(R.id.walletsGridView9);
                walletsView9.setHasFixedSize(true);
                walletsView9.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                WalletAdapter walletAdapter9 = new WalletAdapter(Wallet9.benevit, context);
                walletsView9.setAdapter(walletAdapter9);
            }
        } catch (Exception e) {
            Log.d("loadWallets", e.getMessage());
        }
    }
}