package com.example.weight_watcher.Model.Toasts.App_Toasts;

import android.content.Context;
import android.widget.Toast;

public class App_Toast {
    private Context context;
    public Integer duration;
    public CharSequence message;
    public Toast toast;

    public App_Toast(CharSequence Message, int Duration, Context Contexts){
        this.message = Message;
        this.duration = Duration;
        this.context = Contexts;
        this.createToast();

    }

    private void createToast(){
        this.toast = Toast.makeText(this.context,this.message,this.duration);
    }

    public void showToast(){
        this.toast.show();

    }

}
