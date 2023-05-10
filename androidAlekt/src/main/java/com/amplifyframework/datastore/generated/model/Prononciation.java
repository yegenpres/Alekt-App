package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Prononciation type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Prononciations", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Prononciation implements Model {
  public static final QueryField ID = field("Prononciation", "id");
  public static final QueryField TITLE = field("Prononciation", "title");
  public static final QueryField VIDEO_PATH = field("Prononciation", "videoPath");
  public static final QueryField DESCRIPTION = field("Prononciation", "description");
  public static final QueryField TASK = field("Prononciation", "task");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String videoPath;
  private final @ModelField(targetType="String", isRequired = true) String description;
  private final @ModelField(targetType="PrononcTask", isRequired = true) List<PrononcTask> task;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getVideoPath() {
      return videoPath;
  }
  
  public String getDescription() {
      return description;
  }
  
  public List<PrononcTask> getTask() {
      return task;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Prononciation(String id, String title, String videoPath, String description, List<PrononcTask> task) {
    this.id = id;
    this.title = title;
    this.videoPath = videoPath;
    this.description = description;
    this.task = task;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Prononciation prononciation = (Prononciation) obj;
      return ObjectsCompat.equals(getId(), prononciation.getId()) &&
              ObjectsCompat.equals(getTitle(), prononciation.getTitle()) &&
              ObjectsCompat.equals(getVideoPath(), prononciation.getVideoPath()) &&
              ObjectsCompat.equals(getDescription(), prononciation.getDescription()) &&
              ObjectsCompat.equals(getTask(), prononciation.getTask()) &&
              ObjectsCompat.equals(getCreatedAt(), prononciation.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), prononciation.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getVideoPath())
      .append(getDescription())
      .append(getTask())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Prononciation {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("videoPath=" + String.valueOf(getVideoPath()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("task=" + String.valueOf(getTask()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Prononciation justId(String id) {
    return new Prononciation(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      videoPath,
      description,
      task);
  }
  public interface TitleStep {
    VideoPathStep title(String title);
  }
  

  public interface VideoPathStep {
    DescriptionStep videoPath(String videoPath);
  }
  

  public interface DescriptionStep {
    TaskStep description(String description);
  }
  

  public interface TaskStep {
    BuildStep task(List<PrononcTask> task);
  }
  

  public interface BuildStep {
    Prononciation build();
    BuildStep id(String id);
  }
  

  public static class Builder implements TitleStep, VideoPathStep, DescriptionStep, TaskStep, BuildStep {
    private String id;
    private String title;
    private String videoPath;
    private String description;
    private List<PrononcTask> task;
    @Override
     public Prononciation build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Prononciation(
          id,
          title,
          videoPath,
          description,
          task);
    }
    
    @Override
     public VideoPathStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public DescriptionStep videoPath(String videoPath) {
        Objects.requireNonNull(videoPath);
        this.videoPath = videoPath;
        return this;
    }
    
    @Override
     public TaskStep description(String description) {
        Objects.requireNonNull(description);
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep task(List<PrononcTask> task) {
        Objects.requireNonNull(task);
        this.task = task;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String videoPath, String description, List<PrononcTask> task) {
      super.id(id);
      super.title(title)
        .videoPath(videoPath)
        .description(description)
        .task(task);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder videoPath(String videoPath) {
      return (CopyOfBuilder) super.videoPath(videoPath);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder task(List<PrononcTask> task) {
      return (CopyOfBuilder) super.task(task);
    }
  }
  
}
