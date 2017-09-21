package com.slesh.willread;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ActiveFragment extends Fragment {

    private ViewingCounter mCallback;

    public interface ViewingCounter {

        int count();

        int inc();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof ViewingCounter) {
            Log.d("counter", "test-test-test");
        }

    }
}
