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

/** This is an auto generated class representing the UsersVocabulary type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UsersVocabularies", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class UsersVocabulary implements Model {
  public static final QueryField ID = field("UsersVocabulary", "id");
  public static final QueryField WORDS = field("UsersVocabulary", "words");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) List<String> words;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public List<String> getWords() {
      return words;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private UsersVocabulary(String id, List<String> words) {
    this.id = id;
    this.words = words;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UsersVocabulary usersVocabulary = (UsersVocabulary) obj;
      return ObjectsCompat.equals(getId(), usersVocabulary.getId()) &&
              ObjectsCompat.equals(getWords(), usersVocabulary.getWords()) &&
              ObjectsCompat.equals(getCreatedAt(), usersVocabulary.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), usersVocabulary.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getWords())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UsersVocabulary {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("words=" + String.valueOf(getWords()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static WordsStep builder() {
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
  public static UsersVocabulary justId(String id) {
    return new UsersVocabulary(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      words);
  }
  public interface WordsStep {
    BuildStep words(List<String> words);
  }
  

  public interface BuildStep {
    UsersVocabulary build();
    BuildStep id(String id);
  }
  

  public static class Builder implements WordsStep, BuildStep {
    private String id;
    private List<String> words;
    @Override
     public UsersVocabulary build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UsersVocabulary(
          id,
          words);
    }
    
    @Override
     public BuildStep words(List<String> words) {
        Objects.requireNonNull(words);
        this.words = words;
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
    private CopyOfBuilder(String id, List<String> words) {
      super.id(id);
      super.words(words);
    }
    
    @Override
     public CopyOfBuilder words(List<String> words) {
      return (CopyOfBuilder) super.words(words);
    }
  }
  
}
