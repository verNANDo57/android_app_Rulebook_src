package com.verNANDo57.rulebook_educational;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.verNANDo57.rulebook_educational.customthemeengine.app.CustomThemeEngineBottomSheetDialogFragment;
import com.verNANDo57.rulebook_educational.customthemeengine.utils.ColorUtils;
import com.verNANDo57.rulebook_educational.extradata.R;
import com.verNANDo57.rulebook_educational.preferences.AppSettingsActivity;
import com.verNANDo57.rulebook_educational.rules.dictionaries.AppDictionaries;

public class BottomNavBetweenActivitiesFragment extends CustomThemeEngineBottomSheetDialogFragment {

    public BottomNavBetweenActivitiesFragment(){
        //Required empty public constructor
    }

    @SuppressLint("NonConstantResourceId")
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_bottomsheet_between_activities, container, false);

        NavigationView navigationbetweenlessons = viewFragment.findViewById(R.id.navigation_view_between_activities);
        navigationbetweenlessons.setBackgroundColor(getResources().getColor(R.color.bottomsheetdialog_background));
        navigationbetweenlessons.setItemTextColor(ColorStateList.valueOf(ColorUtils.lighter(getResources().getColor(R.color.bottomsheetdialog_primary), 0.01f)));
        navigationbetweenlessons.setItemIconTintList(ColorStateList.valueOf(ColorUtils.lighter(getResources().getColor(R.color.bottomsheetdialog_accent), 0.01f)));
        navigationbetweenlessons.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            switch (id)
            {
                case R.id.bottomsheetdialog_betweenactivities_mainrules:
                    requireActivity().finish();
                    startActivity(new Intent(getActivity(), AppChooseYourDestiny.class));
                    break;

                case R.id.bottomsheetdialog_betweenactivities_dictionaries:
                    requireActivity().finish();
                    startActivity(new Intent(getActivity(), AppDictionaries.class));
                    break;

                case R.id.bottomsheetdialog_betweenactivities_about:
                    requireActivity().finish();
                    startActivity(new Intent(getActivity(), AppAboutApplicationActivity.class));
                    break;

                case R.id.bottomsheetdialog_betweenactivities_settings:
                    requireActivity().finish();
                    startActivity(new Intent(getActivity(), AppSettingsActivity.class));
                    break;

                case R.id.bottomsheetdialog_betweenactivities_exit:
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireActivity());
                    builder.setTitle(getString(R.string.app_exit));
                    builder.setMessage(getString(R.string.are_you_sure));
                    builder.setIcon(R.drawable.ic_warning_outline);
                    builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requireActivity().finishAffinity();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    androidx.appcompat.app.AlertDialog alert = builder.create();
                    alert.show();
                    break;
            }

            return false;
        });
        return viewFragment;
    }
}
