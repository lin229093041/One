package com.oracle.oaec.androidproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment {
    private GridView gv_one;
    private GridView gv_two;
    private GridView gv_three;

    private ArrayList<String> list_one;
    private ArrayList<String> list_two;
    private ArrayList<String> list_three;

    private TwoBaseAdapter_one adapter_one;
    private TwoBaseAdapter_two adapter_two;
    private TwoBaseAdapter_three adapter_three;

    private TextView chakan;//查看更多

    private Intent intent;//跳转页面

    private int[] lv_iamge1 = new int[]{R.drawable.lv_image1, R.drawable.activity_two_2, R.drawable.lv_image2};
    private int[] lv_iamge2 = new int[]{R.drawable.activity_four_2, R.drawable.activity_five_image2};
    private int[] lv_iamge3 = new int[]{R.drawable.activity_six_2, R.drawable.activity_seven_1};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        gv_one = (GridView) view.findViewById(R.id.gv_one);
        gv_two = (GridView) view.findViewById(R.id.gv_two);
        gv_three = (GridView) view.findViewById(R.id.gv_three);

        //填充数据
        fillData_one();
        fillData_two();
        fillData_three();

        adapter_one = new TwoBaseAdapter_one();
        gv_one.setAdapter(adapter_one);

        adapter_two = new TwoBaseAdapter_two();
        gv_two.setAdapter(adapter_two);

        adapter_three = new TwoBaseAdapter_three();
        gv_three.setAdapter(adapter_three);


        //查看更多的点击事件
        chakan = (TextView) view.findViewById(R.id.kankan);
        chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "查看更多", Toast.LENGTH_SHORT).show();

                //跳转到详情的界面
                intent = new Intent();
                intent.setClass(getActivity(), SeeMore.class);
                getActivity().startActivity(intent);
            }
        });

        //最上面的GridView的item点击事件，也就是第一个
        gv_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "上面的第一个", Toast.LENGTH_SHORT).show();

                        //跳转到第一个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_One.class);
                        getActivity().startActivity(intent);

                        break;
                    case 1:
                        Toast.makeText(getActivity(), "上面的第二个", Toast.LENGTH_SHORT).show();

                        //跳转到第二个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_Two.class);
                        getActivity().startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "上面的第三个", Toast.LENGTH_SHORT).show();

                        //跳转到第三个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_Three.class);
                        getActivity().startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        //第二个
        gv_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "中间的第一个", Toast.LENGTH_SHORT).show();

                        //跳转到第四个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_Four.class);
                        getActivity().startActivity(intent);

                        break;
                    case 1:
                        Toast.makeText(getActivity(), "中间的第二个", Toast.LENGTH_SHORT).show();

                        //跳转到第五个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_Five.class);
                        getActivity().startActivity(intent);

                        break;
                    default:
                        break;
                }
            }
        });
        gv_three.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "下面的第一个", Toast.LENGTH_SHORT).show();

                        //跳转到第六个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_Six.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "下面的第二个", Toast.LENGTH_SHORT).show();

                        //跳转到第七个页面
                        intent = new Intent();
                        intent.setClass(getActivity(), PageContent_Seven.class);
                        getActivity().startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });


        return view;


    }

    private void fillData_one() {
        list_one = new ArrayList<String>();
        list_one.add(new String("浪漫七夕感恩回馈活动"));
        list_one.add(new String("2016第三季新品发布"));
        list_one.add(new String("端午节活动海报"));
    }

    private void fillData_two() {
        list_two = new ArrayList<String>();
        list_two.add(new String("一眼就爱上这种新格调"));
        list_two.add(new String("伴在当下，许在未来——巡店感悟"));
    }

    private void fillData_three() {
        list_three = new ArrayList<String>();
        list_three.add(new String("我与咖啡有个约会"));
        list_three.add(new String("感受咖啡，感受典雅生活"));
    }


    //第一个
    private class TwoBaseAdapter_one extends BaseAdapter {

        @Override
        public int getCount() {
            return list_one.size();
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

            View view = getActivity().getLayoutInflater().inflate(R.layout.two_items, null);
            ImageView iv_item = (ImageView) view.findViewById(R.id.iv_two_item);
            TextView tv_item = (TextView) view.findViewById(R.id.tv_two_item);

            iv_item.setImageResource(lv_iamge1[position]);
            tv_item.setText(list_one.get(position));


            return view;
        }
    }

    //第二个
    private class TwoBaseAdapter_two extends BaseAdapter {

        @Override
        public int getCount() {
            return list_two.size();
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

            View view = getActivity().getLayoutInflater().inflate(R.layout.two_items2, null);
            ImageView iv_item = (ImageView) view.findViewById(R.id.iv_two_item2);
            TextView tv_item = (TextView) view.findViewById(R.id.tv_two_item2);

            iv_item.setImageResource(lv_iamge2[position]);
            tv_item.setText(list_two.get(position));

            return view;
        }
    }

    //第三个
    private class TwoBaseAdapter_three extends BaseAdapter {

        @Override
        public int getCount() {
            return list_three.size();
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

            View view = getActivity().getLayoutInflater().inflate(R.layout.two_items2, null);
            ImageView iv_item = (ImageView) view.findViewById(R.id.iv_two_item2);
            TextView tv_item = (TextView) view.findViewById(R.id.tv_two_item2);

            iv_item.setImageResource(lv_iamge3[position]);
            tv_item.setText(list_three.get(position));


            return view;
        }
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
}
