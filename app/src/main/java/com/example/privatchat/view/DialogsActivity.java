package com.example.privatchat.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.privatchat.R;
import com.example.privatchat.mainclass.Dialog;
import com.example.privatchat.viewmodel.MyViewModel;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

public class DialogsActivity extends AppCompatActivity implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog>{


    public static void open(Context context) {
        context.startActivity(new Intent(context, DialogsActivity.class));
    }

    DialogsList dialogsList;
    private DialogsListAdapter<Dialog> dialogsAdapter;
    MyViewModel myViewModel;
    ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        dialogsList = findViewById(R.id.DL);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);//переходим во вьюмодел
        getLifecycle().addObserver(myViewModel);

        imageLoader = (imageView, url, payload) -> Picasso.with(DialogsActivity.this).load(url).into(imageView);


        dialogsAdapter  = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogLongClickListener(this);
        dialogsList.setAdapter(dialogsAdapter);
        myViewModel.getDialog().observe(this,DialogsList -> {
            dialogsAdapter.setItems(DialogsList);
        });
    }


    @Override
    public void onDialogClick(Dialog dialog) {
        //переход в диалог
        MessagesActivity.open(this);
    }

    @Override
    public void onDialogLongClick(Dialog dialog) {

    }
}
