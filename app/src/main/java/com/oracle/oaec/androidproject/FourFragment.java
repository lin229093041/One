package com.oracle.oaec.androidproject;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.oracle.oaec.androidproject.View.LoginActivity;
import com.oracle.oaec.androidproject.po.MySetting;
import com.oracle.oaec.androidproject.publicmethod.ToUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FourFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourFragment extends Fragment {

    private TextView denglu;

    private FourBaseAdapter adapter;
    private ArrayList<MySetting> list;
    private ListView listView;
    private Intent intent;

    private String userNumber;

    private TextView tv_item_text;

    private int[] lv_iamge = new int[]{R.drawable.name_image, R.drawable.sex_image, R.drawable.birthday_image, R.drawable.region_image, R.drawable.record_image};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView iv;
    private OnFragmentInteractionListener mListener;
    private OnColorChangedListener listener;

    public FourFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourFragment newInstance(String param1, String param2) {
        FourFragment fragment = new FourFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onResume() {
        if(LoginActivity.Urlusername!=null){
            StringBuffer sb=new StringBuffer(ToUrl.Url);
            sb.append("UserServlet?mode=4&name=");
            sb.append(LoginActivity.Urlusername);
            Log.i("123123",sb.toString());
            FinalHttp http2=new FinalHttp();
                    http2.get(sb.toString(), new AjaxCallBack<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            if(o==null){
                                return;
                            }
                            DisplayImageOptions displayImageOptions=new DisplayImageOptions.Builder(

                            ).cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher).build();
                            ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getContext()).diskCacheSize(1024*1024 * 10).memoryCacheSize(1024 * 1024 * 10).build();
                            ImageLoader.getInstance().init(config);

                            String picUrl = o.toString();
                            Log.i("1212",picUrl+"!2123");
                            ImageLoader.getInstance().displayImage(picUrl,iv,displayImageOptions,new SimpleImageLoadingListener(){
                                @Override
                                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                    super.onLoadingFailed(imageUri, view, failReason);
                                    //图片加载失败后处理


                                }
                            });
                            super.onSuccess(o);
                        }
                    });
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        iv= (ImageView) view.findViewById(R.id.user_img);

        listView = (ListView) view.findViewById(R.id.lv_four);
        //填充数据
        fillData();
        adapter = new FourBaseAdapter();
        listView.setAdapter(adapter);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginActivity.Urlusername!=null) {
                    Toast.makeText(getContext(),"已经登陆，进入选择头像界面",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), TouXiangActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
        denglu = (TextView) view.findViewById(R.id.denglu);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();

                //跳转到登录页面
                intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 0);

            }
        });
//        SharedPreferences s = getActivity().getSharedPreferences("user", MODE_PRIVATE);
//        String username = s.getString("Username", null);
//        if(username!=null){
//            denglu.setText(username);
//        }
//        String username = getActivity().getIntent().getStringExtra("Username");
//        if (username != null) {
//            denglu.setText(username);
//        }

        tv_item_text = (TextView) getActivity().findViewById(R.id.tv_item_text);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        //跳转到选择性别界面
                        final String[] items = new String[]{"男", "女"};
                        final Builder mBuilder = new Builder(getActivity());
                        mBuilder.setTitle("请选择你的性别");
                        mBuilder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.get(1).setContent(items[which]);
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });
                        mBuilder.show();


                        break;
                    case 2:
                        //跳转到选择日期界面
                        intent = new Intent();
                        intent.setClass(getActivity(), DateSelection.class);
                        startActivity(intent);
                        break;
                    case 3:

                        break;
                    case 4:
                        Intent intent3=new Intent(getContext(),DianGeActivity.class);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String s = data.getStringExtra("Username");
            if (s != null) {
                denglu.setText(s);
                Log.d("FourFragment", s);
                // getActivity();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void onResume() {
//        String username = getActivity().getIntent().getStringExtra("Username");
//        if(username!=null){
//            denglu.setText(username);
//        }
//        super.onResume();
//    }

    //数据的填充
    private void fillData() {
        list = new ArrayList<MySetting>();
        list.add(new MySetting("昵称", ""));
        list.add(new MySetting("性别", ""));
        list.add(new MySetting("生日", ""));
        list.add(new MySetting("地区", ""));
        list.add(new MySetting("点播历史", ""));
    }

    public interface OnColorChangedListener {

        /**
         * 用来传递数据的方法，实现需要交给使用者来实现
         * 在颜色发生改变（RadioButton选中发生改变）的时候来调用
         *
         * @param Color
         */
        public void onColorChanged(int Color);
    }

    public void setOnColorChangeListener(OnColorChangedListener l) {
        this.listener = l;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class FourBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getActivity().getLayoutInflater().inflate(R.layout.fouritem, null);
            ImageView iv_item = (ImageView) view.findViewById(R.id.iv_item);
            TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
            TextView tv_item_text = (TextView) view.findViewById(R.id.tv_item_text);

            MySetting mySetting = list.get(position);
            iv_item.setImageResource(lv_iamge[position]);
            tv_item.setText(mySetting.getName());
            tv_item_text.setText(mySetting.getContent());


            return view;
        }
    }
}
