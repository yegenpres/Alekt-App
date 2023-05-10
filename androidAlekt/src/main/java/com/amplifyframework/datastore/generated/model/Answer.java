package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the Answer type in your schema. */
public final class Answer {
  private final String title;
  private final String soundPath;
  public String getTitle() {
      return title;
  }
  
  public String getSoundPath() {
      return soundPath;
  }
  
  private Answer(String title, String soundPath) {
    this.title = title;
    this.soundPath = soundPath;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Answer answer = (Answer) obj;
      return ObjectsCompat.equals(getTitle(), answer.getTitle()) &&
              ObjectsCompat.equals(getSoundPath(), answer.getSoundPath());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getTitle())
      .append(getSoundPath())
      .toString()
      .hashCode();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(title,
      soundPath);
  }
  public interface TitleStep {
    SoundPathStep title(String title);
  }
  

  public interface SoundPathStep {
    BuildStep soundPath(String soundPath);
  }
  

  public interface BuildStep {
    Answer build();
  }
  

  public static class Builder implements TitleStep, SoundPathStep, BuildStep {
    private String title;
    private String soundPath;
    @Override
     public Answer build() {
        
        return new Answer(
          title,
          soundPath);
    }
    
    @Override
     public SoundPathStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep soundPath(String soundPath) {
        Objects.requireNonNull(soundPath);
        this.soundPath = soundPath;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String title, String soundPath) {
      super.title(title)
        .soundPath(soundPath);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder soundPath(String soundPath) {
      return (CopyOfBuilder) super.soundPath(soundPath);
    }
  }
  
}
