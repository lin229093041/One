package com.oracle.oaec.androidproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private ImageView left_top;
    private ImageView right_top;

    private SlideShowView center_one;
    private ImageView center_two;

    private ImageView left_below;
    private ImageView right_below;

    private TwoFragment addressFragment;//要跳转页面的fragment

    private Intent intent;//跳转页面


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //左上角的图片点击事件
        left_top = (ImageView) view.findViewById(R.id.left_top);
        left_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到第二个页面的fragment
                Toast.makeText(getActivity(), "图片", Toast.LENGTH_SHORT).show();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).ll_address.performClick();
                }
//                addressFragment = new TwoFragment();
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
////                ft.add(R.id.fl_content, addressFragment);
////                ft.commit();
//
//                if (addressFragment == null) {
//                    addressFragment = new TwoFragment();
//                    ft.add(R.id.fl_content, addressFragment);
//                } else {
//                    ft.show(addressFragment);
//                }
//                ft.commit();
            }
        });
        //右上角的图片点击事件
        right_top = (ImageView) view.findViewById(R.id.right_top);
        right_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "右上角图片的点击事件", Toast.LENGTH_SHORT).show();

                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).ll_friend.performClick();
                }
//                //跳转到商品
//                intent = new Intent();
//                intent.setClass(getActivity(), ShoppingCartFragment.class);
//                getActivity().startActivity(intent);
            }
        });

        //中间第一个图片的点击事件
        center_one = (SlideShowView) view.findViewById(R.id.center_one);

        //中间第二个图片的点击事件
        center_two = (ImageView) view.findViewById(R.id.center_two);
        center_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "中间图片第二个的点击事件", Toast.LENGTH_SHORT).show();
                intent=new Intent(getActivity(),Introduce_Activity.class);
                getActivity().startActivity(intent);
            }
        });
        //左下角图片的点击事件
        left_below = (ImageView) view.findViewById(R.id.left_below);
        left_below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "左下角图片的点击事件", Toast.LENGTH_SHORT).show();
                //跳转到点歌
                intent = new Intent();
                intent.setClass(getActivity(), OnDemand.class);
                getActivity().startActivity(intent);

            }
        });
        //右下角图片的点击事件
        right_below = (ImageView) view.findViewById(R.id.right_below);
        right_below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "右下角图片的点击事件", Toast.LENGTH_SHORT).show();


                //跳转到我的
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).ll_setting.performClick();
                }

            }
        });


        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
