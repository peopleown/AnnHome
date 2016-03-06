package com.example.mrpan.annhome;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import DB.DBAdapter;
import entity.Dialog;
import utils.MySharePreference;

/**
 * Created by mrpan on 16/2/16.
 */
public class FavoriteFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "FavoriteFragment";

    private View currentView=null;

    private RecyclerView mRecyclerView;

    FragmentTransaction transaction;

    private ImageButton m_toggle;

    private Context context=null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_favorite_layout,
                container, false);
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        context=getActivity();
        ((MainActivity)getActivity()).setSlidingPaneLayout(currentView);
        return currentView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeTheme();
        initView();
    }

    void initView(){

        mRecyclerView=(RecyclerView)currentView.findViewById(R.id.favorites_list);
        m_toggle=(ImageButton)currentView.findViewById(R.id.m_toggle);

        m_toggle.setOnClickListener(this);

        //data
//        ArrayList<HashMap<String,Object>> datas=new ArrayList<HashMap<String, Object>>();
//        HashMap<String,Object> hashMap=new HashMap<String,Object>();
//        hashMap.put("title","喜欢的标题");
//        hashMap.put("total","喜欢的摘要");
//        hashMap.put("pic","");
//        datas.add(hashMap);
        List<Dialog> datas= DBAdapter.getDBAdapter(context).getArticles("游客",10);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        FavoriteListAdapter myAdapter = new FavoriteListAdapter(context,datas);
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);
    }

    public void changeTheme(){
        MySharePreference mySharePreference=new MySharePreference(getActivity());
        int theme=mySharePreference.getInt("theme",0);
        LinearLayout linearLayout= (LinearLayout) currentView.findViewById(R.id.main_bg);
        linearLayout.setBackgroundResource(theme);
    }

    @Override
    public void onClick(View v) {
        transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.push_left_in,
                R.anim.push_left_out);
        switch (v.getId()){
            case R.id.m_toggle:
                ((MainActivity) getActivity()).getSlidingPaneLayout().openPane();
                break;
            default:
                break;
        }
    }
}
