package edu.cnm.deepdive.notes.service;

import android.accounts.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;
import javax.inject.Singleton;

/** @noinspection deprecation*/
@Singleton
public class UserRepository {

  private final GoogleSignInService signInService;

  @Inject
  UserRepository(GoogleSignInService signInService) {
    this.signInService = signInService;
  }

  // TODO: 2/26/2025 add operations methods for reading/writing users to database

  public Single<GoogleSignInAccount> getCurrentAccount(){
    return signInService
        .refresh();

  }
}
