package edu.cnm.deepdive.notes.model.entity;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

@Entity(
    tableName="note",
    indices = {
        @Index(value = {"title"},unique=true)
    }
)
public class Note {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name="note_id")
  private long id;

  @ColumnInfo(collate = ColumnInfo.NOCASE, index = true)
  @NonNull
  private String title = "";

  @NonNull
  private String content = "";

  private Uri image;

  @ColumnInfo(name="created_on", index = true)
  @NonNull
  private Instant createdOn = Instant.now();

  @ColumnInfo(name="modified_on", index = true)
  @NonNull
  private Instant modifiedOn = Instant.now();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getTitle() {
    return title;
  }

  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  @NonNull
  public String getContent() {
    return content;
  }

  public void setContent(@NonNull String content) {
    this.content = content;
  }

  public Uri getImage() {
    return image;
  }

  public void setImage(Uri image) {
    this.image = image;
  }

  @NonNull
  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(@NonNull Instant createdOn) {
    this.createdOn = createdOn;
  }

  @NonNull
  public Instant getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(@NonNull Instant modifiedOn) {
    this.modifiedOn = modifiedOn;
  }
}
