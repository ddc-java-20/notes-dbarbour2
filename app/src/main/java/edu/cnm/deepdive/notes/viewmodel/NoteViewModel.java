package edu.cnm.deepdive.notes.viewmodel;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.notes.model.entity.Note;
import edu.cnm.deepdive.notes.service.NoteRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class NoteViewModel extends ViewModel implements DefaultLifecycleObserver {

  private final NoteRepository noteRepository;
  private final MutableLiveData<Long> noteId;
  private final LiveData<Note> note;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  @Inject
  NoteViewModel(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
    noteId = new MutableLiveData<>();
    note = Transformations.switchMap(noteId, noteRepository::get);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();

  }

  public void save (Note note) {
    //reset thowable livedata
    // invoke subscribe method on the machine, to start it attach consumers
    // there will be two consumbers, one successful (note) and one for unceusseful(thowable)
    // the first puts note into livedata bucket, second invokes a helper method

    throwable.setValue(null); //will be invoked for controller on ui thread
    noteRepository
        .save(note)
        .ignoreElement()
        .subscribe(
            () -> {},
            this::postThowable,
            pending
        );
  }
public void fetch(long noteId) {
    throwable.setValue(null);
    this.noteId.setValue(noteId);
}

public void delete(Note note) {
    throwable.setValue(null);
    noteRepository
        .remove(note)
        .subscribe(
            () -> {},
            this::postThowable,
            pending
        );
}

  public MutableLiveData<Long> getNoteId() {
    return noteId;
  }

  public LiveData<Note> getNote() {
    return note;
  }

  public LiveData<List<Note>> getNotes(){
    return noteRepository.getAll();
  }

  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    pending.clear();
    DefaultLifecycleObserver.super.onStop(owner);
  }

  private void postThowable(Throwable throwable) {
    Log.e(NoteViewModel.class.getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.setValue(throwable);
  }

}
