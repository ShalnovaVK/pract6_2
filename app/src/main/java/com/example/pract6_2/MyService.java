package com.example.pract6_2;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
public class MyService extends Service {
    private WindowManager windowManager;
    private View view;
    private Button button;
    private TextView textView;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        view = LayoutInflater.from(this).inflate(R.layout.service_layout, null);

        button = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.TextView);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        windowManager.addView(view, params);
        button.setOnClickListener(view -> {
            windowManager.removeView(view);
            stopSelf();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        textView.setText("Саша не бухает, Саша отдыхает");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(view);
    }
}