package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.gms.ads.*;

/**
 * Created by ludwang on 14-1-12.
 */
public class AdsBanner extends Activity {
    private AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("myAdUnitId");

        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        // Optionally populate the ad request builder.
        adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

        // Add the AdView to the view hierarchy.
        layout.addView(adView);

        // Start loading the ad.
        adView.loadAd(adRequestBuilder.build());

        setContentView(layout);
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adView.resume();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

}
