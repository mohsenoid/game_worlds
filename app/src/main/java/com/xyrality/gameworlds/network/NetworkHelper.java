package com.xyrality.gameworlds.network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.xyrality.gameworlds.network.event.LoginEvent;
import com.xyrality.gameworlds.network.model.LoginResponse;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mohsen on 1/20/16.
 */
public class NetworkHelper {
    final static String TAG = NetworkHelper.class.getSimpleName();
    final static String SERVER_URL = "http://backend1.lordsandknights.com";
    static NetworkHelper instance = null;
    static Retrofit retrofit;

    static Context context;
    static String deviceType;
    static String deviceId;
    static Api api;

    private NetworkHelper(Context context) {
        this.context = context;

        deviceType = deviceTypeGenerator();
        deviceId = deviceUniqueIdGenerator();
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static NetworkHelper getInstance(Context context) {
        if (instance == null)
            instance = new NetworkHelper(context);

        return instance;
    }

    public void login(String login, String password) {
        Call<LoginResponse> loginResponseCall = null;
        try {
            loginResponseCall = api.login(login, password, deviceType, deviceId);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Response<LoginResponse> response) {
                    EventBus.getDefault().post(new LoginEvent(response.isSuccess(), response.body()));
                }

                @Override
                public void onFailure(Throwable t) {
                    EventBus.getDefault().post(new LoginEvent(t));
                }
            });

        } catch (Throwable e) {
            e.printStackTrace();
            EventBus.getDefault().post(new LoginEvent(e));
        }

    }

    private String deviceTypeGenerator() {
        String result = String.format("%s %s", android.os.Build.MODEL, android.os.Build.VERSION.RELEASE);
        return result;
    }

    private String deviceUniqueIdGenerator() {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String result = info.getMacAddress();

        return result;
    }

}
