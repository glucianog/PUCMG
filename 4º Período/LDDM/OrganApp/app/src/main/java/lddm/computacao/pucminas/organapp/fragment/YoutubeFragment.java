package lddm.computacao.pucminas.organapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import lddm.computacao.pucminas.organapp.R;
import lddm.computacao.pucminas.organapp.video.YouTubeConfig;

/**
 * Created by gabri on 28/10/2017.
 */

public class YoutubeFragment extends Fragment{

    private static final String API_KEY= YouTubeConfig.getApiKey();
    private String mvideoId;

    public static YoutubeFragment newInstance(String videoId) {
        YoutubeFragment fragment = new YoutubeFragment();
        Bundle args = new Bundle();
        args.putString("videoId",videoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mvideoId = getArguments().getString("videoId");

            if (mvideoId.contains("/")) {

                mvideoId = mvideoId.substring(mvideoId.lastIndexOf("/") + 1);
                Log.d("videoid", mvideoId);
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_video, container, false);
        YouTubePlayerSupportFragment youtubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager()
                .findFragmentById(R.id.youtubeFragment);


        youtubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.loadVideo(mvideoId);
                    youTubePlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult error) {
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}
