package ru.iteco.fmhandroid;

import android.app.Activity;
import android.view.View;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.util.TreeIterables;

public class SplashScreenIdlingResource implements IdlingResource {
    private ResourceCallback resourceCallback;
    private final Activity activity;
    private final int splashScreenViewId;

    public SplashScreenIdlingResource(Activity activity, int splashScreenViewId) {
        this.activity = activity;
        this.splashScreenViewId = splashScreenViewId;
    }

    @Override
    public String getName() {
        return SplashScreenIdlingResource.class.getName() + ":" + splashScreenViewId;
    }

    @Override
    public boolean isIdleNow() {
        // Выполняем проверку на главном потоке
        final boolean[] isIdle = {false};
        activity.runOnUiThread(() -> {
            View splashView = activity.findViewById(splashScreenViewId);
            isIdle[0] = splashView == null || splashView.getVisibility() != View.VISIBLE;
            if (isIdle[0] && resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
        });
        return isIdle[0];
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}