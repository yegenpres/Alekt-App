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

/** This is an auto generated class representing the ReadingTask type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ReadingTasks", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class ReadingTask implements Model {
  public static final QueryField ID = field("ReadingTask", "id");
  public static final QueryField TITLE = field("ReadingTask", "title");
  public static final QueryField SUB_TITLE = field("ReadingTask", "subTitle");
  public static final QueryField TEXT = field("ReadingTask", "text");
  public static final QueryField SOUND_PATH = field("ReadingTask", "soundPath");
  public static final QueryField LEVEL = field("ReadingTask", "level");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String subTitle;
  private final @ModelField(targetType="String", isRequired = true) String text;
  private final @ModelField(targetType="String", isRequired = true) String soundPath;
  private final @ModelField(targetType="Level", isRequired = true) Level level;
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
  
  public String getSubTitle() {
      return subTitle;
  }
  
  public String getText() {
      return text;
  }
  
  public String getSoundPath() {
      return soundPath;
  }
  
  public Level getLevel() {
      return level;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ReadingTask(String id, String title, String subTitle, String text, String soundPath, Level level) {
    this.id = id;
    this.title = title;
    this.subTitle = subTitle;
    this.text = text;
    this.soundPath = soundPath;
    this.level = level;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ReadingTask readingTask = (ReadingTask) obj;
      return ObjectsCompat.equals(getId(), readingTask.getId()) &&
              ObjectsCompat.equals(getTitle(), readingTask.getTitle()) &&
              ObjectsCompat.equals(getSubTitle(), readingTask.getSubTitle()) &&
              ObjectsCompat.equals(getText(), readingTask.getText()) &&
              ObjectsCompat.equals(getSoundPath(), readingTask.getSoundPath()) &&
              ObjectsCompat.equals(getLevel(), readingTask.getLevel()) &&
              ObjectsCompat.equals(getCreatedAt(), readingTask.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), readingTask.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getSubTitle())
      .append(getText())
      .append(getSoundPath())
      .append(getLevel())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ReadingTask {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("subTitle=" + String.valueOf(getSubTitle()) + ", ")
      .append("text=" + String.valueOf(getText()) + ", ")
      .append("soundPath=" + String.valueOf(getSoundPath()) + ", ")
      .append("level=" + String.valueOf(getLevel()) + ", ")
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
  public static ReadingTask justId(String id) {
    return new ReadingTask(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      subTitle,
      text,
      soundPath,
      level);
  }
  public interface TitleStep {
    SubTitleStep title(String title);
  }
  

  public interface SubTitleStep {
    TextStep subTitle(String subTitle);
  }
  

  public interface TextStep {
    SoundPathStep text(String text);
  }
  

  public interface SoundPathStep {
    LevelStep soundPath(String soundPath);
  }
  

  public interface LevelStep {
    BuildStep level(Level level);
  }
  

  public interface BuildStep {
    ReadingTask build();
    BuildStep id(String id);
  }
  

  public static class Builder implements TitleStep, SubTitleStep, TextStep, SoundPathStep, LevelStep, BuildStep {
    private String id;
    private String title;
    private String subTitle;
    private String text;
    private String soundPath;
    private Level level;
    @Override
     public ReadingTask build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ReadingTask(
          id,
          title,
          subTitle,
          text,
          soundPath,
          level);
    }
    
    @Override
     public SubTitleStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public TextStep subTitle(String subTitle) {
        Objects.requireNonNull(subTitle);
        this.subTitle = subTitle;
        return this;
    }
    
    @Override
     public SoundPathStep text(String text) {
        Objects.requireNonNull(text);
        this.text = text;
        return this;
    }
    
    @Override
     public LevelStep soundPath(String soundPath) {
        Objects.requireNonNull(soundPath);
        this.soundPath = soundPath;
        return this;
    }
    
    @Override
     public BuildStep level(Level level) {
        Objects.requireNonNull(level);
        this.level = level;
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
    private CopyOfBuilder(String id, String title, String subTitle, String text, String soundPath, Level level) {
      super.id(id);
      super.title(title)
        .subTitle(subTitle)
        .text(text)
        .soundPath(soundPath)
        .level(level);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder subTitle(String subTitle) {
      return (CopyOfBuilder) super.subTitle(subTitle);
    }
    
    @Override
     public CopyOfBuilder text(String text) {
      return (CopyOfBuilder) super.text(text);
    }
    
    @Override
     public CopyOfBuilder soundPath(String soundPath) {
      return (CopyOfBuilder) super.soundPath(soundPath);
    }
    
    @Override
     public CopyOfBuilder level(Level level) {
      return (CopyOfBuilder) super.level(level);
    }
  }
  
}
