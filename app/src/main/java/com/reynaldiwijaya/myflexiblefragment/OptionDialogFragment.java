package com.reynaldiwijaya.myflexiblefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {
    Button btnChoose, btnClose;
    RadioGroup rgOptions;
    RadioButton rbSaf, rbMou, rbLvg, rbMoyes;
    OnOptionDialogListener optionDialogListener;

    public OptionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnChoose = view.findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);
        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        rgOptions = view.findViewById(R.id.rg_options);
        rbLvg = view.findViewById(R.id.rb_lvg);
        rbMou = view.findViewById(R.id.rb_mou);
        rbMoyes = view.findViewById(R.id.rb_moyes);
        rbSaf = view.findViewById(R.id.rb_saf);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Fragment fragment = getParentFragment();

        if (fragment instanceof DetailCategoryFragment) {
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                getDialog().cancel();
                break;

            case R.id.btn_choose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1){
                    String coach = null;
                    switch (checkedRadioButtonId) {
                        case R.id.rb_moyes:
                            coach = rbMoyes.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach = rbLvg.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach = rbMou.getText().toString().trim();
                            break;
                        case R.id.rb_saf:
                            coach = rbSaf.getText().toString().trim();
                            break;
                    }

                    if (optionDialogListener != null) {
                        optionDialogListener.onOptionChoosen(coach);
                    }
                    getDialog().dismiss();
                }
                break;
        }
    }
    public interface OnOptionDialogListener {
        void onOptionChoosen(String text);

    }

}
