package net.kwmt27.supportdesignsample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomSheetModalActivity extends AppCompatActivity {

    private static final String FRAGMENT_MODAL = "modal";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_modal);
        findViewById(R.id.show_short).setOnClickListener(mOnClickListener);
        findViewById(R.id.show_long).setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.show_short:
                    ModalFragment.newInstance(5)
                            .show(getSupportFragmentManager(), FRAGMENT_MODAL);
                    break;
                case R.id.show_long:
                    ModalFragment.newInstance(ModalFragment.LENGTH_ALL)
                            .show(getSupportFragmentManager(), FRAGMENT_MODAL);
                    break;
            }
        }
    };

    /**
     * This is the bottom sheet.
     */
    public static class ModalFragment extends BottomSheetDialogFragment {

        private static final String ARG_LENGTH = "length";

        public static final int LENGTH_ALL = Cheeses.sCheeseStrings.length;

        public static ModalFragment newInstance(int length) {
            ModalFragment fragment = new ModalFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_LENGTH, Math.min(LENGTH_ALL, length));
            fragment.setArguments(args);
//            fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.design_bottom_sheet_recyclerview, container, false);
        }



        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            // For the scrolling content, you can use RecyclerView, NestedScrollView or any other
            // View that inherits NestedScrollingChild
            RecyclerView recyclerView =
                    (RecyclerView) view.findViewById(R.id.bottom_sheet_recyclerview);
            Context context = recyclerView.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            int length = getArguments().getInt(ARG_LENGTH);
            String[] array = new String[length];
            System.arraycopy(Cheeses.sCheeseStrings, 0, array, 0, length);
            recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(context, array));
        }

    }
}