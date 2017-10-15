package org.belosoft.SeflTrainer.Models;

import org.belosoft.SeflTrainer.App.MyApplication;

import java.util.Date;

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
    String historical_user;
    @Required
    String historical_type_series;
    float historical_calories;
    int historical_series_number;
    int seconds_leaps;
    int historical_repetition_series_one;
    int historical_repetition_series_two;
    int historical_repetition_series_three;
    int historical_repetition_series_four;
    int historical_repetition_series_five;
    @Required
    Date createAt;

    public Historical() {
    }

    public Historical(String historical_user
            , String historical_type_series
            , float historical_calories
            , int historical_series_number
            , int seconds_leaps
            , int historical_repetition_series_one
            , int historical_repetition_series_two
            , int historical_repetition_series_three
            , int historical_repetition_series_four
            , int historical_repetition_series_five
            , Date createAt) {
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.historical_user = historical_user;
        this.historical_type_series = historical_type_series;
        this.historical_calories = historical_calories;
        this.historical_series_number = historical_series_number;
        this.seconds_leaps = seconds_leaps;

        this.historical_repetition_series_one = historical_repetition_series_one;
        this.historical_repetition_series_two = historical_repetition_series_two;
        this.historical_repetition_series_three = historical_repetition_series_three;
        this.historical_repetition_series_four = historical_repetition_series_four;
        this.historical_repetition_series_five = historical_repetition_series_five;
        this.createAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String gethistorical_user() {
        return historical_user;
    }

    public void sethistorical_user(String historical_user) {
        this.historical_user = historical_user;
    }

    public String gethistorical_type_series() {
        return historical_type_series;
    }

    public void sethistorical_type_series(String historical_type_series) {
        this.historical_type_series = historical_type_series;
    }

    public float gethistorical_calories() {
        return historical_calories;
    }

    public void sethistorical_calories(float historical_calories) {
        this.historical_calories = historical_calories;
    }

    public int gethistorical_series_number() {
        return historical_series_number;
    }

    public void sethistorical_series_number(int historical_series_number) {
        this.historical_series_number = historical_series_number;
    }


    public int getSeconds_leaps() {
        return seconds_leaps;
    }

    public void setSeconds_leaps(int seconds_leaps) {
        this.seconds_leaps = seconds_leaps;
    }

    public int gethistorical_repetition_series_one() {
        return historical_repetition_series_one;
    }

    public void sethistorical_repetition_series_one(int historical_repetition_series_one) {
        this.historical_repetition_series_one = historical_repetition_series_one;
    }

    public int gethistorical_repetition_series_two() {
        return historical_repetition_series_two;
    }

    public void sethistorical_repetition_series_two(int historical_repetition_series_two) {
        this.historical_repetition_series_two = historical_repetition_series_two;
    }

    public int gethistorical_repetition_series_three() {
        return historical_repetition_series_three;
    }

    public void sethistorical_repetition_series_three(int historical_repetition_series_three) {
        this.historical_repetition_series_three = historical_repetition_series_three;
    }

    public int gethistorical_repetition_series_four() {
        return historical_repetition_series_four;
    }

    public void sethistorical_repetition_series_four(int historical_repetition_series_four) {
        this.historical_repetition_series_four = historical_repetition_series_four;
    }

    public int gethistorical_repetition_series_five() {
        return historical_repetition_series_five;
    }

    public void sethistorical_repetition_series_five(int historical_repetition_series_five) {
        this.historical_repetition_series_five = historical_repetition_series_five;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date date) {
        this.createAt = date;
    }

    ;
}
