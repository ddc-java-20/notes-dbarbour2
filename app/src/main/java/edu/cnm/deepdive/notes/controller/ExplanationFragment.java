package edu.cnm.deepdive.notes.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.notes.R;

public class ExplanationFragment extends DialogFragment {

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    return new Builder( this.requireContext())
        .setTitle(R.string.camera_permission_title)
        .setIcon(android.R.drawable.ic_dialog_info)
        .setMessage(R.string.camera_permission_explanation)
        .setNeutralButton(android.R.string.ok, (dialog, which) -> {
          ((OnDismissListener) requireActivity()).onDismiss();
        })
        .create();
  }

  public interface OnDismissListener {
    void onDismiss();
  }

}
