package com.mine.app.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mine.app.MusicApplication;
import com.mine.app.R;
import com.mine.app.activity.PlayActivity;
import com.mine.app.adapter.PagerBaseAdapter;

public class PlayFragment extends BaseFragment implements View.OnClickListener{

    private ViewPager mPager;
    private RadioGroup mGroup;
    private ImageView mIndicator;
    private RadioButton[] mButtons;

    private PagerBaseAdapter mAdapter;
    private List<Fragment> mFragments;
    private int mScreenWidth;
    private int mCurrIndex;

    private View view;
    private LayoutInflater mInflater;

    private TextView mMusicName, mArtistName;
    private ImageView mIvArtist, mIvPlay, mIvPause, mIvNext,mIvMenu;

    private int mCurrentPlayIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = getActivity().getLayoutInflater();
        view = mInflater.inflate(R.layout.start_play_layout, null);

        mCurrentPlayIndex = MusicApplication.getApplication().getSharedUtils().getPosition();


        mMusicName = (TextView) view.findViewById(R.id.music_name_tv);
        mArtistName = (TextView) view.findViewById(R.id.music_artist_tv);
        mIvArtist = (ImageView)view.findViewById(R.id.artist_iv);
        mIvPause = (ImageView)view.findViewById(R.id.pause_iv);
        mIvPlay = (ImageView)view.findViewById(R.id.play_iv);
        mIvNext = (ImageView)view.findViewById(R.id.next_iv);
        mIvMenu = (ImageView)view.findViewById(R.id.menu_iv);

        return view;
    }



    @Override
    public int setId() {
        return 0;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.artist_iv:
                Intent intent = new Intent();
                intent.setClass(getActivity(), PlayActivity.class);
                intent.putExtra("music_position",mCurrentPlayIndex);
                getActivity().startActivity(intent);
                break;
            case R.id.play_iv:
                mIvPlay.setVisibility(View.GONE);
                mIvPause.setVisibility(View.VISIBLE);
                break;
            case R.id.pause_iv:
                mIvPlay.setVisibility(View.VISIBLE);
                mIvPause.setVisibility(View.GONE);
                break;
            case R.id.next_iv:
                break;
            case R.id.menu_iv:
                break;
        }
    }
}
