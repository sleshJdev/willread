package com.slesh.willread;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("app", "onViewCreated");
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(getActivity().getCacheDir(), "history"));
            byte[] buffer = new byte[1024];
            int read;
            StringBuilder history = new StringBuilder();
            if ((read = in.read(buffer)) != -1) {
                history.append(new String(buffer, 0, read));
            }
            TextView historyDetails = getActivity().findViewById(R.id.historyDetails);
            historyDetails.setText(history.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("app", "onAttach");
    }

}
