package com.example.returnplus.fragment.consult;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.returnplus.R;


/**
 * 视频页面
 */
public class fragment_video extends Fragment {

    private LinearLayout container;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        this.container = view.findViewById(R.id.container);
        this.webView = new WebView(getActivity());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.setWebViewClient(new WebViewClient()); // 设置WebViewClient以处理URL请求

        for (int i = 1; i <= 4; i++) {
            int imageViewId = getResources().getIdentifier("image" + i, "id", getActivity().getPackageName());
            ImageView imageView = view.findViewById(imageViewId);
            int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playVideo(finalI);
                }
            });
        }
        return view;
    }

    private void playVideo(int index) {
        String videoUrl;
        switch (index) {
            case 1:
                videoUrl = "https://www.baidu.com/";
                break;
            case 2:
                videoUrl = "http://www.example.com/video2.mp4";
                break;
            // 将其余的视频 URL 省略
            case 3:
                videoUrl = "http://www.example.com/video3.mp4";
                break;
            case 4:
                videoUrl = "http://www.example.com/video4.mp4";
                break;
            default:
                return;
        }
        if (!videoUrl.isEmpty()) {
            webView.loadUrl(videoUrl);
            container.addView(webView);
            webView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

}