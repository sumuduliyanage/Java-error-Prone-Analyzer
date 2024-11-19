Handler handler = new Handler();
Runnable task = new Runnable() {
@Override
public void run() {
    if (App.connection_status == 2) {
        ConnectionTimer.cancel();
        SharedPreferences SharedAppDetails = getSharedPreferences("settings_data", 0);
        SharedPreferences.Editor Editor = SharedAppDetails.edit();
        Editor.putString("connection_time", String.valueOf(App.CountDown));
        Editor.apply();

        if (App.CountDown >= 20) {
            SharedPreferences settings = getSharedPreferences("settings_data", 0);
            String Rate = settings.getString("rate", "false");
            if (Rate.equals("false")) {
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent Servers = new Intent(MainActivity.this, ReviewActivity.class);
                        startActivity(Servers);
                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                    }
                }, 1000); // Delay of 1 second before starting ReviewActivity
            }
        }

        startAnimation(MainActivity.this, R.id.tv_main_count_down, R.anim.fade_out_1000, false);
        startAnimation(MainActivity.this, R.id.iv_progress_bar, R.anim.fade_out_1000, false);
        startAnimation(MainActivity.this, R.id.la_animation, R.anim.fade_out_1000, false);
    }

    // Schedule this task to run again in 60 seconds (60000 milliseconds)
    handler.postDelayed(this, 60000); // 60000ms = 1 minute
}
};

// Start the first execution after 0 milliseconds (immediately)
handler.postDelayed(task, 0);
