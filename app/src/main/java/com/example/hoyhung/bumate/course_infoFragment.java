package com.example.hoyhung.bumate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class course_infoFragment extends Fragment {

    public course_infoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_info, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        rv.setLayoutManager(llm);
        //int page = getArguments().getInt(ARG_SECTION_NUMBER);
        //RVAdapter adapter = new RVAdapter(pokemons.subList(3 * ( page - 1 ), 3 * page));
        //rv.setAdapter(adapter);
        return rootView;
        //return inflater.inflate(R.layout.fragment_course_info, container, false);
    }
}
