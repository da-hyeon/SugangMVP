package com.dutch_pay.hdh.sugangmvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.util.Base64;

import com.dutch_pay.hdh.sugangmvp.data.model.ParentAuth;
import com.dutch_pay.hdh.sugangmvp.data.manager.GsonPConverterFactory;
import com.dutch_pay.hdh.sugangmvp.service.RestInterFace;
import com.dutch_pay.hdh.sugangmvp.util.SimpleStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class MyApplication extends Application {

    private static MyApplication appInstance;
    //타임아웃
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 15;
    private static OkHttpClient client;
    private static RestInterFace Interface;
    private ParentAuth mParentAuth;
    private String BASE_URL ="";
    private Activity mActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public static MyApplication getInstance() {
        if (appInstance == null) {
            appInstance = new MyApplication();
        }
        return appInstance;
    }

    public ParentAuth getParentAuth() {
        if (mParentAuth == null) mParentAuth = new ParentAuth();
        try {
            mParentAuth.parseFromJson(new JSONObject(SimpleStore.get(Constants.KEY_PARENT_AUTH)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mParentAuth;
    }

    public void setParentAuth(ParentAuth mParentAuth) {
        if (mParentAuth != null) {
            SimpleStore.put(Constants.KEY_PARENT_AUTH, mParentAuth.toJsonObject().toString());
        }
    }

    public String getBASE_URL() {
        return SimpleStore.get(Constants.BASE_URL);
    }

    public void setBASE_URL(String BASE_URL) {
        if (BASE_URL != null) {
            SimpleStore.put(Constants.BASE_URL, BASE_URL);
        }
    }

    public synchronized static RestInterFace getRestAdapter(String baseUrl, boolean isCheck) {
        if (Interface == null || isCheck == true) {
            //통신로그를 확인하기 위한 부분
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //쿠키 메니저의 cookie policy를 변경 합니다.
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            //OkHttpClient를 생성합니다.
            client = configureClient(new OkHttpClient().newBuilder()) //인증서 무시
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                    .cookieJar(new JavaNetCookieJar(cookieManager)) //쿠키메니져 설정
                    .addInterceptor(httpLoggingInterceptor) //http 로그 확인
                    .build();

            //Retrofit 설정
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Interface = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(new GsonPConverterFactory(gson))
                    .build().create(RestInterFace.class); //인터페이스 연결
        }
        return Interface;
    }

    /**
     * UnCertificated 허용
     */
    public static OkHttpClient.Builder configureClient(final OkHttpClient.Builder builder) {
        final TrustManager[] certs = new TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) {
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) {
            }
        }};

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        } catch (final java.security.GeneralSecurityException ex) {
            ex.printStackTrace();
        }

        try {
            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(final String hostname, final SSLSession session) {
                    return true;
                }
            };

            builder.sslSocketFactory(ctx.getSocketFactory()).hostnameVerifier(hostnameVerifier);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return builder;
    }

    /**
     * 디버깅 해쉬키 가져오기
     */
    @Nullable
    public static String getPackageHashKey(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo("com.itmain.sclass2V2", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
