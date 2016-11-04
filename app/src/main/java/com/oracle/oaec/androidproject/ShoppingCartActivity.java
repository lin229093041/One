package com.oracle.oaec.androidproject;

import android.app.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oracle.oaec.androidproject.po.CaiPin;
import com.oracle.oaec.androidproject.po.CaipinMsg;

import java.util.ArrayList;

public class ShoppingCartActivity extends FragmentActivity {
    private Fragment shoppingEvaluaterFragment;
    private Fragment shoppingcartFragment;
    private Fragment shoppingMsgFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        shoppingcartFragment = new ShoppingCartFragment();
        transaction.replace(R.id.shopping_ry, shoppingcartFragment).commit();


    }


}
