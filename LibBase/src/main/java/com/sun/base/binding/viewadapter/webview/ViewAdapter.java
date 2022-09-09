package com.sun.base.binding.viewadapter.webview;

import android.text.TextUtils;
import android.webkit.WebView;

import androidx.databinding.BindingAdapter;

/**
 * @author: Harper
 * @date: 2022/9/8
 * @note:
 */
public class ViewAdapter {
    @BindingAdapter({"render"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }
}
