package com.slesh.willread;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static com.slesh.willread.MainActivity.HISTORY;


public class ActiveFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateHistory(view);
    }

    public void updateHistory() {
        updateHistory(getView());
    }

    private void updateHistory(View view) {
        SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
        Set<String> historyItems = preferences.getStringSet(HISTORY, new HashSet<>());
        StringBuilder historyBuilder = new StringBuilder();
        for (String historyItem : historyItems) {
            historyBuilder.append(historyItem).append('\n');
        }
        TextView history = view.findViewById(R.id.history);
        history.setText(historyBuilder.toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
