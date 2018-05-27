package com.example.illya.android_lab_2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link ItemListDialogFragment.Listener}.</p>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment {

    private static final String ARG_MODAL_TEXT = "modal_text";
    private static final String ARG_MODAL_FONT = "modal_font";

    public static ItemListDialogFragment newInstance(String modalText, String modalFont) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_MODAL_TEXT, modalText);
        args.putString(ARG_MODAL_FONT, modalFont);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView outputText = (TextView) getView().findViewById(R.id.text);
        outputText.setText((String) this.getArguments().get(ARG_MODAL_TEXT));
        switch ((String) this.getArguments().get(ARG_MODAL_FONT)) {
            case "sans-serif-light": outputText.setTextAppearance(view.getContext(), R.style.sansSerifLight); break;
            case "sans-serif-medium": outputText.setTextAppearance(view.getContext(), R.style.sansSerifMedium); break;
            case "sans-serif": outputText.setTextAppearance(view.getContext(), R.style.sansSerif); break;
            case "sans-serif-condensed": outputText.setTextAppearance(view.getContext(), R.style.sansSerifCondensed); break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
    }
}
