package com.sample.todaymygold.POJO;

public class GoldRatePojo
{
    private String date;
    private String state;
    private String city;
    private int _22k_gold_rate;
    private int _24k_gold_rate;
    private int yesterday22K_gold;
    private int yesterday24K_gold;

    public GoldRatePojo(String date, String state, String city, int _22k_gold_rate, int _24k_gold_rate, int yesterday22K_gold, int yesterday24K_gold) {
        this.date = date;
        this.state = state;
        this.city = city;
        this._22k_gold_rate = _22k_gold_rate;
        this._24k_gold_rate = _24k_gold_rate;
        this.yesterday22K_gold = yesterday22K_gold;
        this.yesterday24K_gold = yesterday24K_gold;
    }

    public GoldRatePojo() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int get_22k_gold_rate() {
        return _22k_gold_rate;
    }

    public void set_22k_gold_rate(int _22k_gold_rate) {
        this._22k_gold_rate = _22k_gold_rate;
    }

    public int get_24k_gold_rate() {
        return _24k_gold_rate;
    }

    public void set_24k_gold_rate(int _24k_gold_rate) {
        this._24k_gold_rate = _24k_gold_rate;
    }

    public int getYesterday22K_gold() {
        return yesterday22K_gold;
    }

    public void setYesterday22K_gold(int yesterday22K_gold) {
        this.yesterday22K_gold = yesterday22K_gold;
    }

    public int getYesterday24K_gold() {
        return yesterday24K_gold;
    }

    public void setYesterday24K_gold(int yesterday24K_gold) {
        this.yesterday24K_gold = yesterday24K_gold;
    }
}
