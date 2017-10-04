package org.belosoft.mejorarencasa.Models;

import org.belosoft.mejorarencasa.App.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by OscarPC on 04/10/2017.
 */

public class Historical extends RealmObject {

    @PrimaryKey
    public int id;
    @Required
    String historical_column_user;
    @Required
    String historical_column_type_series;
    @Required
    String historical_column_calories;
    @Required
    String historical_column_series_number;
    @Required
    String historical_column_repetition_series_one;
    @Required
    String historical_column_repetition_series_two;
    @Required
    String historical_column_repetition_series_three;
    @Required
    String historical_column_repetition_series_four;
    @Required
    String historical_column_repetition_series_five;

    public Historical(){}

    public Historical(int id
            ,String historical_column_user
            ,String historical_column_type_series
            ,String historical_column_calories
            ,String historical_column_series_number
            ,String historical_column_repetition_series_one
            ,String historical_column_repetition_series_two
            ,String historical_column_repetition_series_three
            ,String historical_column_repetition_series_four
            ,String historical_column_repetition_series_five){
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.historical_column_user = historical_column_user;
        this.historical_column_type_series = historical_column_type_series;
        this.historical_column_calories = historical_column_calories;
        this.historical_column_series_number = historical_column_series_number;
        this.historical_column_repetition_series_one = historical_column_repetition_series_one;
        this.historical_column_repetition_series_two = historical_column_repetition_series_two;
        this.historical_column_repetition_series_three = historical_column_repetition_series_three;
        this.historical_column_repetition_series_four = historical_column_repetition_series_four;
        this.historical_column_repetition_series_five = historical_column_repetition_series_five;
    }

    public int getId() {
        return id;
    }

    public String getHistorical_column_user() {
        return historical_column_user;
    }

    public void setHistorical_column_user(String historical_column_user) {
        this.historical_column_user = historical_column_user;
    }

    public String getHistorical_column_type_series() {
        return historical_column_type_series;
    }

    public void setHistorical_column_type_series(String historical_column_type_series) {
        this.historical_column_type_series = historical_column_type_series;
    }

    public String getHistorical_column_calories() {
        return historical_column_calories;
    }

    public void setHistorical_column_calories(String historical_column_calories) {
        this.historical_column_calories = historical_column_calories;
    }

    public String getHistorical_column_series_number() {
        return historical_column_series_number;
    }

    public void setHistorical_column_series_number(String historical_column_series_number) {
        this.historical_column_series_number = historical_column_series_number;
    }

    public String getHistorical_column_repetition_series_one() {
        return historical_column_repetition_series_one;
    }

    public void setHistorical_column_repetition_series_one(String historical_column_repetition_series_one) {
        this.historical_column_repetition_series_one = historical_column_repetition_series_one;
    }

    public String getHistorical_column_repetition_series_two() {
        return historical_column_repetition_series_two;
    }

    public void setHistorical_column_repetition_series_two(String historical_column_repetition_series_two) {
        this.historical_column_repetition_series_two = historical_column_repetition_series_two;
    }

    public String getHistorical_column_repetition_series_three() {
        return historical_column_repetition_series_three;
    }

    public void setHistorical_column_repetition_series_three(String historical_column_repetition_series_three) {
        this.historical_column_repetition_series_three = historical_column_repetition_series_three;
    }

    public String getHistorical_column_repetition_series_four() {
        return historical_column_repetition_series_four;
    }

    public void setHistorical_column_repetition_series_four(String historical_column_repetition_series_four) {
        this.historical_column_repetition_series_four = historical_column_repetition_series_four;
    }

    public String getHistorical_column_repetition_series_five() {
        return historical_column_repetition_series_five;
    }

    public void setHistorical_column_repetition_series_five(String historical_column_repetition_series_five) {
        this.historical_column_repetition_series_five = historical_column_repetition_series_five;
    }
}
