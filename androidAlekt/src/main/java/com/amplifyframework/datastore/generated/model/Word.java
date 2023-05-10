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

/** This is an auto generated class representing the Word type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Words", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Word implements Model {
  public static final QueryField ID = field("Word", "id");
  public static final QueryField TRANSCRIPTION = field("Word", "transcription");
  public static final QueryField SOUND_PATH = field("Word", "soundPath");
  public static final QueryField TRANSLATE_ENG = field("Word", "translateEng");
  public static final QueryField TRANSLATE_RU = field("Word", "translateRu");
  public static final QueryField WORD_DK = field("Word", "wordDK");
  public static final QueryField LEVEL = field("Word", "level");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String transcription;
  private final @ModelField(targetType="String") String soundPath;
  private final @ModelField(targetType="String", isRequired = true) String translateEng;
  private final @ModelField(targetType="String", isRequired = true) String translateRu;
  private final @ModelField(targetType="String", isRequired = true) String wordDK;
  private final @ModelField(targetType="Level", isRequired = true) Level level;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTranscription() {
      return transcription;
  }
  
  public String getSoundPath() {
      return soundPath;
  }
  
  public String getTranslateEng() {
      return translateEng;
  }
  
  public String getTranslateRu() {
      return translateRu;
  }
  
  public String getWordDk() {
      return wordDK;
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
  
  private Word(String id, String transcription, String soundPath, String translateEng, String translateRu, String wordDK, Level level) {
    this.id = id;
    this.transcription = transcription;
    this.soundPath = soundPath;
    this.translateEng = translateEng;
    this.translateRu = translateRu;
    this.wordDK = wordDK;
    this.level = level;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Word word = (Word) obj;
      return ObjectsCompat.equals(getId(), word.getId()) &&
              ObjectsCompat.equals(getTranscription(), word.getTranscription()) &&
              ObjectsCompat.equals(getSoundPath(), word.getSoundPath()) &&
              ObjectsCompat.equals(getTranslateEng(), word.getTranslateEng()) &&
              ObjectsCompat.equals(getTranslateRu(), word.getTranslateRu()) &&
              ObjectsCompat.equals(getWordDk(), word.getWordDk()) &&
              ObjectsCompat.equals(getLevel(), word.getLevel()) &&
              ObjectsCompat.equals(getCreatedAt(), word.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), word.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTranscription())
      .append(getSoundPath())
      .append(getTranslateEng())
      .append(getTranslateRu())
      .append(getWordDk())
      .append(getLevel())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Word {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("transcription=" + String.valueOf(getTranscription()) + ", ")
      .append("soundPath=" + String.valueOf(getSoundPath()) + ", ")
      .append("translateEng=" + String.valueOf(getTranslateEng()) + ", ")
      .append("translateRu=" + String.valueOf(getTranslateRu()) + ", ")
      .append("wordDK=" + String.valueOf(getWordDk()) + ", ")
      .append("level=" + String.valueOf(getLevel()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TranslateEngStep builder() {
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
  public static Word justId(String id) {
    return new Word(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      transcription,
      soundPath,
      translateEng,
      translateRu,
      wordDK,
      level);
  }
  public interface TranslateEngStep {
    TranslateRuStep translateEng(String translateEng);
  }
  

  public interface TranslateRuStep {
    WordDkStep translateRu(String translateRu);
  }
  

  public interface WordDkStep {
    LevelStep wordDk(String wordDk);
  }
  

  public interface LevelStep {
    BuildStep level(Level level);
  }
  

  public interface BuildStep {
    Word build();
    BuildStep id(String id);
    BuildStep transcription(String transcription);
    BuildStep soundPath(String soundPath);
  }
  

  public static class Builder implements TranslateEngStep, TranslateRuStep, WordDkStep, LevelStep, BuildStep {
    private String id;
    private String translateEng;
    private String translateRu;
    private String wordDK;
    private Level level;
    private String transcription;
    private String soundPath;
    @Override
     public Word build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Word(
          id,
          transcription,
          soundPath,
          translateEng,
          translateRu,
          wordDK,
          level);
    }
    
    @Override
     public TranslateRuStep translateEng(String translateEng) {
        Objects.requireNonNull(translateEng);
        this.translateEng = translateEng;
        return this;
    }
    
    @Override
     public WordDkStep translateRu(String translateRu) {
        Objects.requireNonNull(translateRu);
        this.translateRu = translateRu;
        return this;
    }
    
    @Override
     public LevelStep wordDk(String wordDk) {
        Objects.requireNonNull(wordDk);
        this.wordDK = wordDk;
        return this;
    }
    
    @Override
     public BuildStep level(Level level) {
        Objects.requireNonNull(level);
        this.level = level;
        return this;
    }
    
    @Override
     public BuildStep transcription(String transcription) {
        this.transcription = transcription;
        return this;
    }
    
    @Override
     public BuildStep soundPath(String soundPath) {
        this.soundPath = soundPath;
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
    private CopyOfBuilder(String id, String transcription, String soundPath, String translateEng, String translateRu, String wordDk, Level level) {
      super.id(id);
      super.translateEng(translateEng)
        .translateRu(translateRu)
        .wordDk(wordDk)
        .level(level)
        .transcription(transcription)
        .soundPath(soundPath);
    }
    
    @Override
     public CopyOfBuilder translateEng(String translateEng) {
      return (CopyOfBuilder) super.translateEng(translateEng);
    }
    
    @Override
     public CopyOfBuilder translateRu(String translateRu) {
      return (CopyOfBuilder) super.translateRu(translateRu);
    }
    
    @Override
     public CopyOfBuilder wordDk(String wordDk) {
      return (CopyOfBuilder) super.wordDk(wordDk);
    }
    
    @Override
     public CopyOfBuilder level(Level level) {
      return (CopyOfBuilder) super.level(level);
    }
    
    @Override
     public CopyOfBuilder transcription(String transcription) {
      return (CopyOfBuilder) super.transcription(transcription);
    }
    
    @Override
     public CopyOfBuilder soundPath(String soundPath) {
      return (CopyOfBuilder) super.soundPath(soundPath);
    }
  }
  
}
