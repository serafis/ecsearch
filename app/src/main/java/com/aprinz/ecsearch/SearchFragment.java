package com.aprinz.ecsearch;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alexander on 27.07.2015.
 */
public class SearchFragment extends DialogFragment implements View.OnClickListener {
    private OnSearchedListener callback;
    private TextView textView;
    private Button searchButton;
    View.OnClickListener onOK;

    public interface OnSearchedListener {
        public void onErrorCodeSearched(String id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            callback = (OnSearchedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnSearchedListener");
        }

        Dialog myDialog = getDialog();
        myDialog.setTitle(R.string.search);

        View view = inflater.inflate(R.layout.search_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.searchterm);
        searchButton = (Button) view.findViewById(R.id.fragment_searchbutton);
        searchButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_searchbutton:
                callback.onErrorCodeSearched(textView.getText().toString());
                dismiss();
                break;
        }
    }

}
