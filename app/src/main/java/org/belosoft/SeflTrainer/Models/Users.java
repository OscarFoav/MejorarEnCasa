package org.belosoft.SeflTrainer.Models;

import org.belosoft.SeflTrainer.App.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by OscarPC on 04/10/2017.
 */

public class Users extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    String user_serie;
    int user_age;
    int user_weight;
    @Required
    String series_name;
    int seconds_leaps;
    int repetition_series_one;
    int repetition_series_two;
    int repetition_series_three;
    int repetition_series_four;
    int repetition_series_five;
    Date createAt;

    public Users() {
    }

    public Users(String user_serie
            , int user_age
            , int user_weight
            , String series_name
            , int seconds_leaps
            , int repetition_series_one
            , int repetition_series_two
            , int repetition_series_three
            , int repetition_series_four
            , int repetition_series_five
            , Date createAt) {
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.user_serie = user_serie;
        this.user_age = user_age;
        this.user_weight = user_weight;
        this.series_name = series_name;
        this.seconds_leaps = seconds_leaps;
        this.repetition_series_one = repetition_series_one;
        this.repetition_series_two = repetition_series_two;
        this.repetition_series_three = repetition_series_three;
        this.repetition_series_four = repetition_series_four;
        this.repetition_series_five = repetition_series_five;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public String getUser_serie() {
        return user_serie;
    }

    public void setUser_serie(String user_serie) {
        this.user_serie = user_serie;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public int getUser_weight() {
        return user_weight;
    }

    public void setUser_weight(int user_weight) {
        this.user_weight = user_weight;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public int getSeconds_leaps() {
        return seconds_leaps;
    }

    public void setSeconds_leaps(int seconds_leaps) {
        this.seconds_leaps = seconds_leaps;
    }

    public int getRepetition_series_one() {
        return repetition_series_one;
    }

    public void setRepetition_series_one(int repetition_series_one) {
        this.repetition_series_one = repetition_series_one;
    }

    public int getRepetition_series_two() {
        return repetition_series_two;
    }

    public void setRepetition_series_two(int repetition_series_two) {
        this.repetition_series_two = repetition_series_two;
    }

    public int getRepetition_series_three() {
        return repetition_series_three;
    }

    public void setRepetition_series_three(int repetition_series_three) {
        this.repetition_series_three = repetition_series_three;
    }

    public int getRepetition_series_four() {
        return repetition_series_four;
    }

    public void setRepetition_series_four(int repetition_series_four) {
        this.repetition_series_four = repetition_series_four;
    }

    public int getRepetition_series_five() {
        return repetition_series_five;
    }

    public void setRepetition_series_five(int repetition_series_five) {
        this.repetition_series_five = repetition_series_five;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date date) {
        this.createAt = date;
    }

    ;

}
