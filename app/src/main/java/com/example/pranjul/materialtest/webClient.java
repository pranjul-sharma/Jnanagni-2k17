package com.example.pranjul.materialtest;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by LENOVO on 3/2/17.
 */

public class webClient extends WebViewClient {
    //static String url;
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }
}