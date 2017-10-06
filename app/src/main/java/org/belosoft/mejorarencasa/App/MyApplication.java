package org.belosoft.mejorarencasa.App;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import org.belosoft.mejorarencasa.Models.DefaultValues;
import org.belosoft.mejorarencasa.Models.Historical;
import org.belosoft.mejorarencasa.Models.Users;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by OscarPC on 04/10/2017.
 */

public class MyApplication extends Application {
    public static AtomicInteger DefaultValuesID = new AtomicInteger();
    public static AtomicInteger UsersID = new AtomicInteger();
    public static AtomicInteger HistoricalID = new AtomicInteger();

    @Override
    public void onCreate() {

        setUpRealmConfig();

        // lectura del primer registro. detectar si la tabla esta vacia
        Realm realm = Realm.getDefaultInstance();
        DefaultValuesID = getIdByTable(realm, DefaultValues.class);
        UsersID = getIdByTable(realm, Users.class);
        HistoricalID = getIdByTable(realm, Historical.class);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        realm.close();

    }

    private void setUpRealmConfig() {

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();

    }
}
