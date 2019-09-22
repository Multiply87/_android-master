package com.example.privatchat.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.privatchat.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GmailLogin {
    Context context;
    View view;

    public GmailLogin(Context context, View view) {
        this.context = context;
        this.view = view;
    }


    public String[] getGoogleAccounts() {
        String[] accs = {};
        AccountManager am = AccountManager.get(context);
        Account[] accounts = am.getAccounts();
        String[] accs_arr = new String[accounts.length];

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].type.equalsIgnoreCase("com.google"))//&&accounts[i].name.indexOf("@privatbank.ua")>-1)
            {
                accs_arr[i] = accounts[i].name;
            }
        }

        ArrayList<String> list = new ArrayList<String>();
        for (String s : accs_arr)
            if (s != null && !s.equals(""))
                list.add(s);
        accs_arr = list.toArray(new String[list.size()]);

        return accs_arr;

    }

    public void showPopUpChangeAccount(View view, String[] accounts) {

        RadioGroup radioGroup = new RadioGroup(context);

        for (int i = 0; i < accounts.length; i++) {
            String account = accounts[i];
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText(account);
            radioGroup.addView(radioButton);
        }


        Button insidePopupButton = new Button(context);
        LinearLayout layoutOfPopup = new LinearLayout(context);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(4, context.getResources().getColor(R.color.colorPrimaryDark));
        gradientDrawable.setColor(context.getResources().getColor(R.color.white));

        layoutOfPopup.setBackground(gradientDrawable);
        layoutOfPopup.setPadding(10, 5, 5, 10);

        insidePopupButton.setText("Выбрать");


        layoutOfPopup.setOrientation(LinearLayout.VERTICAL);
        layoutOfPopup.addView(radioGroup);
        layoutOfPopup.addView(insidePopupButton);

        PopupWindow popupMessage = new PopupWindow(layoutOfPopup, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupMessage.setContentView(layoutOfPopup);
        popupMessage.showAtLocation(view, Gravity.CENTER, 0, -250);

        insidePopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account =
                        ((RadioButton) layoutOfPopup.findViewById(radioGroup.getCheckedRadioButtonId()))
                                .getText().toString();
                //Log.d("myLogs",account);
                popupMessage.dismiss();
                postData("https://script.google.com/macros/s/AKfycbxNlAJztAcQbvOLBDZQwlIHWF8KyFa9yvJXcyjMZqOJm1SYwhI/exec", "createOtp", account, popupMessage);
                showPopUpInputPassword(view, account);
            }
        });

    }

    public void showPopUpInputPassword(View view, String account) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_gmail, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView accountName = (TextView) popupView.findViewById(R.id.tv_account);
        accountName.setText(account);

        Button button_cancel = (Button) popupView.findViewById(R.id.b_cancel);

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button button_ok = (Button) popupView.findViewById(R.id.b_ok);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = null;
                EditText et_gmail_otp = (EditText) popupView.findViewById(R.id.et_gmail_otp);
                Log.d("myLogs", et_gmail_otp.getText().toString());
                postData("https://script.google.com/macros/s/AKfycbxNlAJztAcQbvOLBDZQwlIHWF8KyFa9yvJXcyjMZqOJm1SYwhI/exec", "checkAccess", et_gmail_otp.getText().toString(), popupWindow);
            }
        });

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, -250);


    }


    public void postData(String mURL, String oper, String email, PopupWindow popupWindow) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());

        StringRequest postRequest = new StringRequest( com.android.volley.Request.Method.POST, mURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                        String access = jsonObject.get("access").getAsString();
                        if (oper=="checkAccess"&&access=="true"){
                            Log.d("myLogs", "Поздравляшки!");
                            popupWindow.dismiss();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                //add your parameters here as key-value pairs
                params.put("oper", oper);
                params.put("email", email);

                return params;
            }
        };
        queue.add(postRequest);
    }

}
