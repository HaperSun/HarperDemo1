package com.sun.base.net.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import com.sun.common.Const;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author: Harper
 * @date: 2021/11/12
 * @note: http 请求头拦截器
 */
public class HeaderInterceptor implements Interceptor {

    private final Context mContext;

    public HeaderInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

//        String authorization = UserInfoManager.getInstance(mContext) != null
//                && UserInfoManager.getInstance(mContext).getCurrentLoginUser() != null ?
//                "Bearer " + UserInfoManager.getInstance(mContext).getCurrentLoginUser().getAccess_token() : "";
        // Request customization: add request headers
        SharedPreferences sp = mContext.getSharedPreferences("um_push", MODE_PRIVATE);
        String deviceToken = sp.getString("deviceToken", "");
        assert deviceToken != null;
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader("authorization", "authorization")
                .addHeader("deviceToken", deviceToken)
                .addHeader("client", "android")
                .addHeader("deviceType", "android")
                .addHeader("appVersion", Const.APP_VERSION + "");
        ;

        return chain.proceed(requestBuilder.build());

    }
}
