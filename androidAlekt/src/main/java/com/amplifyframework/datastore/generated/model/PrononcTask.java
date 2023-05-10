package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the PrononcTask type in your schema. */
public final class PrononcTask {
  private final String question;
  private final Answer rightAnswer;
  private final List<Answer> answers;
  public String getQuestion() {
      return question;
  }
  
  public Answer getRightAnswer() {
      return rightAnswer;
  }
  
  public List<Answer> getAnswers() {
      return answers;
  }
  
  private PrononcTask(String question, Answer rightAnswer, List<Answer> answers) {
    this.question = question;
    this.rightAnswer = rightAnswer;
    this.answers = answers;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      PrononcTask prononcTask = (PrononcTask) obj;
      return ObjectsCompat.equals(getQuestion(), prononcTask.getQuestion()) &&
              ObjectsCompat.equals(getRightAnswer(), prononcTask.getRightAnswer()) &&
              ObjectsCompat.equals(getAnswers(), prononcTask.getAnswers());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getQuestion())
      .append(getRightAnswer())
      .append(getAnswers())
      .toString()
      .hashCode();
  }
  
  public static QuestionStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(question,
      rightAnswer,
      answers);
  }
  public interface QuestionStep {
    RightAnswerStep question(String question);
  }
  

  public interface RightAnswerStep {
    AnswersStep rightAnswer(Answer rightAnswer);
  }
  

  public interface AnswersStep {
    BuildStep answers(List<Answer> answers);
  }
  

  public interface BuildStep {
    PrononcTask build();
  }
  

  public static class Builder implements QuestionStep, RightAnswerStep, AnswersStep, BuildStep {
    private String question;
    private Answer rightAnswer;
    private List<Answer> answers;
    @Override
     public PrononcTask build() {
        
        return new PrononcTask(
          question,
          rightAnswer,
          answers);
    }
    
    @Override
     public RightAnswerStep question(String question) {
        Objects.requireNonNull(question);
        this.question = question;
        return this;
    }
    
    @Override
     public AnswersStep rightAnswer(Answer rightAnswer) {
        Objects.requireNonNull(rightAnswer);
        this.rightAnswer = rightAnswer;
        return this;
    }
    
    @Override
     public BuildStep answers(List<Answer> answers) {
        Objects.requireNonNull(answers);
        this.answers = answers;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String question, Answer rightAnswer, List<Answer> answers) {
      super.question(question)
        .rightAnswer(rightAnswer)
        .answers(answers);
    }
    
    @Override
     public CopyOfBuilder question(String question) {
      return (CopyOfBuilder) super.question(question);
    }
    
    @Override
     public CopyOfBuilder rightAnswer(Answer rightAnswer) {
      return (CopyOfBuilder) super.rightAnswer(rightAnswer);
    }
    
    @Override
     public CopyOfBuilder answers(List<Answer> answers) {
      return (CopyOfBuilder) super.answers(answers);
    }
  }
  
}
