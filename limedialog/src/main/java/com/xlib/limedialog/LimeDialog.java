package com.xlib.limedialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * Copyright 2019 (C) xplo
 * <p>
 * Created  : 2019-10-19
 * Updated  :
 * Author   : xplo
 * Desc     :
 * Comment  :
 */
public class LimeDialog extends DialogFragment {

    private static final String TAG = "LimeDialog";

    //private Activity activity;
    private FragmentManager fragmentManager;
    private int layoutId;
    private String title;
    private String message;
    private String posButtonText;
    private String negButtonText;
    private boolean isCancelable;
    private DialogListener listener;

    private TextView tvDialogTitle;
    private TextView tvDialogMessage;
    private Button btDialogPositiveButton;
    private Button btDialogNegativeButton;


    public LimeDialog(Builder builder) {
        //this.activity = builder.activity;
        this.fragmentManager = builder.fragmentManager;
        this.layoutId = builder.layoutId;
        this.title = builder.title;
        this.message = builder.message;
        this.posButtonText = builder.posButtonText;
        this.negButtonText = builder.negButtonText;
        this.isCancelable = builder.isCancelable;
        this.listener = builder.listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(layoutId, container, false);
        rootView.setMinimumHeight(dpToPixel(150));
        rootView.setMinimumWidth(dpToPixel(200));
        return rootView;

    }

    private int dpToPixel(int dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);
        return pixels;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tvDialogTitle = view.findViewById(R.id.tvDialogTitle);
        tvDialogMessage = view.findViewById(R.id.tvDialogMessage);
        btDialogPositiveButton = view.findViewById(R.id.btDialogPositiveButton);
        btDialogNegativeButton = view.findViewById(R.id.btDialogNegativeButton);

        setView();


    }

    private void setView() {

        setCancelable(isCancelable);

        if (title == null) tvDialogTitle.setVisibility(View.GONE);
        if (message == null) tvDialogMessage.setVisibility(View.GONE);
        if (posButtonText == null) btDialogPositiveButton.setVisibility(View.GONE);
        if (negButtonText == null) btDialogNegativeButton.setVisibility(View.GONE);


        if (title != null) tvDialogTitle.setText(title);
        if (message != null) tvDialogMessage.setText(message);
        if (posButtonText != null) btDialogPositiveButton.setText(posButtonText);
        if (negButtonText != null) btDialogNegativeButton.setText(negButtonText);


        btDialogPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.onClickPositiveButton();
                dismiss();
            }
        });

        btDialogNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.onClickNegativeButton();
                dismiss();
            }
        });




    }

    public void show(){
        super.show(fragmentManager,TAG);
    }

    public static class Builder {

        //private Activity activity;
        private FragmentManager fragmentManager;
        private int layoutId = R.layout.custom_dialog;
        private String title = null;
        private String message = null;
        private String posButtonText = null;
        private String negButtonText = null;
        private boolean isCancelable = true;
        private DialogListener listener;

//        public Builder(Activity activity) {
//            this.activity = activity;
//        }

        public Builder(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }


        public Builder setLayoutId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPosButtonText(String posButtonText) {
            this.posButtonText = posButtonText;
            return this;
        }

        public Builder setNegButtonText(String negButtonText) {
            this.negButtonText = negButtonText;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public Builder setListener(DialogListener listener) {
            this.listener = listener;
            return this;
        }

        public LimeDialog build() {
            return new LimeDialog(this);
        }
    }

    public interface DialogListener {

        void onClickPositiveButton();

        void onClickNegativeButton();


    }


}
