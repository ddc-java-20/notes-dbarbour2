package edu.cnm.deepdive.notes.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.notes.R;
import edu.cnm.deepdive.notes.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.notes.viewmodel.NoteViewModel;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;
  private NoteViewModel viewModel;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);

    // Inflate the layout for this fragment
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    viewModel = new ViewModelProvider(this.requireActivity()).get(NoteViewModel.class);
    viewModel
        .getNotes()
        .observe(lifecycleOwner, (notes) -> {
          // TODO: 2/13/2025 if createing a new adapter each time the data changes, create one no;
          // otherwise, we need to create on earlier, and it will exit by this time 
          // TODO: 2/13/2025 pass data to adapter 
          // TODO: 2/13/2025 notify adapter of change
        });
  }
}