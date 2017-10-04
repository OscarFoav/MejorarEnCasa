package org.belosoft.mejorarencasa.Models;

import org.belosoft.mejorarencasa.App.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by OscarPC on 04/10/2017.
 */

public class Users extends RealmObject {

    @PrimaryKey
    private int id ;
    @Required
    String users_column_user ;
    @Required
    String users_column_series_number ;
    @Required
    String users_column_repetition_series_one ;
    @Required
    String users_column_repetition_series_two ;
    @Required
    String users_column_repetition_series_three ;
    @Required
    String users_column_repetition_series_four ;
    @Required
    String users_column_repetition_series_five ;

    public Users(){}

    public Users(
            int id
            ,String users_column_user
            ,String users_column_series_number
            ,String users_column_repetition_series_one
            ,String users_column_repetition_series_two
            ,String users_column_repetition_series_three
            ,String users_column_repetition_series_four
            ,String users_column_repetition_series_five    ) {
        this.id = MyApplication.DefaultValuesID.incrementAndGet();   // conseguir new ID
        this.users_column_user = users_column_user;
        this.users_column_series_number = users_column_series_number;
        this.users_column_repetition_series_one = users_column_repetition_series_one;
        this.users_column_repetition_series_two = users_column_repetition_series_two;
        this.users_column_repetition_series_three = users_column_repetition_series_three;
        this.users_column_repetition_series_four = users_column_repetition_series_four;
        this.users_column_repetition_series_five = users_column_repetition_series_five;
    }

    public int getId() {
        return id;
    }

    public String getUsers_column_user() {
        return users_column_user;
    }

    public void setUsers_column_user(String users_column_user) {
        this.users_column_user = users_column_user;
    }

    public String getUsers_column_series_number() {
        return users_column_series_number;
    }

    public void setUsers_column_series_number(String users_column_series_number) {
        this.users_column_series_number = users_column_series_number;
    }

    public String getUsers_column_repetition_series_one() {
        return users_column_repetition_series_one;
    }

    public void setUsers_column_repetition_series_one(String users_column_repetition_series_one) {
        this.users_column_repetition_series_one = users_column_repetition_series_one;
    }

    public String getUsers_column_repetition_series_two() {
        return users_column_repetition_series_two;
    }

    public void setUsers_column_repetition_series_two(String users_column_repetition_series_two) {
        this.users_column_repetition_series_two = users_column_repetition_series_two;
    }

    public String getUsers_column_repetition_series_three() {
        return users_column_repetition_series_three;
    }

    public void setUsers_column_repetition_series_three(String users_column_repetition_series_three) {
        this.users_column_repetition_series_three = users_column_repetition_series_three;
    }

    public String getUsers_column_repetition_series_four() {
        return users_column_repetition_series_four;
    }

    public void setUsers_column_repetition_series_four(String users_column_repetition_series_four) {
        this.users_column_repetition_series_four = users_column_repetition_series_four;
    }

    public String getUsers_column_repetition_series_five() {
        return users_column_repetition_series_five;
    }

    public void setUsers_column_repetition_series_five(String users_column_repetition_series_five) {
        this.users_column_repetition_series_five = users_column_repetition_series_five;
    }
}
