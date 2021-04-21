package com.example.hiotcloud.test.fragmenttest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.hiotcloud.R;

/**
 */
public class TestFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private static int mParam1;

    public TestFragment() {

    }

    public static TestFragment newInstance(int param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, mParam1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ImageView ivFragmentTest = view.findViewById(R.id.iv_fragment_test);
        ivFragmentTest.setImageResource(mParam1);
        return view;
    }
}
