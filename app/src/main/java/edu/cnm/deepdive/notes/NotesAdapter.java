package edu.cnm.deepdive.notes;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.notes.NotesAdapter.Holder;

public class NotesAdapter extends RecyclerView.Adapter<Holder>{


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return null; // TODO: 2/13/2025 create and return instance of holder
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int i) {
    // TODO: 2/13/2025 INvoke holder.bind withthe object
  }

  @Override
  public int getItemCount() {
    return 0; //return the number of note instances that can be display in thes list
  }

  static class Holder  extends RecyclerView.ViewHolder {

    public Holder(@NonNull View itemView) {
      super(itemView);
      // TODO: 2/13/2025 intialize any fields
    }

  }

  public void bind(Object item){
    // TODO: 2/13/2025 use data from item to pupulate widgets in itemview
  }
}
