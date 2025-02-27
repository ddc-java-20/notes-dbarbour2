package edu.cnm.deepdive.notes.viewmodel;

import android.content.Intent;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.notes.service.GoogleSignInService;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * @noinspection deprecation
 */
@HiltViewModel
public class LoginViewModel extends ViewModel implements DefaultLifecycleObserver {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final GoogleSignInService signInService;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<Throwable> refreshThowable;
  private final MutableLiveData<Throwable> signInThowable;
  private final CompositeDisposable pending;

  public LoginViewModel(GoogleSignInService signInService) {
    this.signInService = signInService;
    account = new MutableLiveData<>();
    refreshThowable = new MutableLiveData<>();
    signInThowable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<GoogleSignInAccount> getAccount() {
    return account;
  }

  public LiveData<Throwable> getRefreshThowable() {
    return refreshThowable;
  }

  public LiveData<Throwable> getSignInThowable() {
    return signInThowable;
  }

  public void refresh() {
    refreshThowable.setValue(null);
    signInThowable.setValue(null);
    signInService
        .refresh()
        .subscribe(
            this.account::postValue,
            (throwable) -> postThrowable(throwable, refreshThowable),
            pending
        );
  }

  public void startSignIn(ActivityResultLauncher<Intent> launcher) {
    refreshThowable.setValue(null);
    signInThowable.setValue(null);
    signInService.startSignIn(launcher);
  }

  public void completeSignIn(ActivityResult result) {
    refreshThowable.setValue(null);
    signInThowable.setValue(null);
    signInService
        .completeSignIn(result)
        .subscribe(
            this.account::postValue,
            (throwable) -> postThrowable(throwable, signInThowable),
            pending
        );
  }

  public void signOut() {
    refreshThowable.setValue(null);
    signInThowable.setValue(null);
    signInService
        .signOut()
        .doFinally(() -> account.postValue(null))
        .subscribe();
  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    pending.clear();
    DefaultLifecycleObserver.super.onStop(owner);
  }

  private void postThrowable(Throwable throwable, MutableLiveData<Throwable> throwableLiveData) {
    Log.e(TAG, throwable.getMessage(), throwable);
    throwableLiveData.postValue(throwable);
  }
}
