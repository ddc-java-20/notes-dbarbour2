package edu.cnm.deepdive.notes.controller;

import static android.Manifest.permission.CAMERA;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.notes.R;
import edu.cnm.deepdive.notes.databinding.ActivityMainBinding;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private NavController navController;
  private AppBarConfiguration appBarConfig;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setupNavigation();
  }

  @Override
  public boolean onSupportNavigateUp() {
    return NavigationUI.navigateUp(navController, appBarConfig);
  }

  private void setupNavigation() {
    setSupportActionBar(binding.toolbar);
    appBarConfig = new AppBarConfiguration.Builder(R.id.home_fragment).build();
    navController = ((NavHostFragment) binding.navHostFragmentContainer.getFragment()).getNavController();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
  }
  
  private void setupPermissions() {
    if (shouldRequestCameraPermission()) {
      if(shouldExplainCameraPermission()) {
        navController.navigate(HomeFragmentDirections.openExplanationFragment());
      } else {
        // TODO: 2/19/2025 invoke onDismiss call back directly 
      }
    } else {
      // TODO: 2/19/2025 store result if appropriate 
    }
  }

  private boolean shouldRequestCameraPermission() {
    return ContextCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED;
  }

  private boolean shouldExplainCameraPermission() {
    return ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA);
  }
  
}