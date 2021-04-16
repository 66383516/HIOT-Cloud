package com.example.hiotcloud.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hiotcloud.utils.Constans;


/**
 * viewpager适配器
 */
class MainViewPagerAdapter extends FragmentPagerAdapter{

    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case Constans.MAIN_VIEWPAGER_INDEX_MESSAGE:
                //创建信息Frament todo
                break;
                //创建设备Frament
            case Constans.MAIN_VIEWPAGER_INDEX_EQUIPMENT:
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_SCENE:
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_MINE:
                break;
            default:
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Constans.MAIN_FRAGMENT_COUNT;
    }
}
