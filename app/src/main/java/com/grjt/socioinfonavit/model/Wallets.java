package com.grjt.socioinfonavit.model;

import java.util.ArrayList;

public class Wallets {

    private ArrayList<Wallet> wallets;

    public Wallets() { }

    public Wallets(ArrayList<Wallet> wallets) {
        this.wallets = wallets;
    }

    public ArrayList<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(ArrayList<Wallet> wallets) {
        this.wallets = wallets;
    }
}
