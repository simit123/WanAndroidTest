package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wanandroid.com.wanandroidtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeHierarchyFragment extends Fragment {


    public static KnowledgeHierarchyFragment getInstance(String title) {
        KnowledgeHierarchyFragment mKnowledgeHierarchyFragment = new KnowledgeHierarchyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mKnowledgeHierarchyFragment.setArguments(bundle);
        return mKnowledgeHierarchyFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_knowledge_hierarchy, container, false);
    }

}
