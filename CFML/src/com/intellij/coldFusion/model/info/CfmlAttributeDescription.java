package com.intellij.coldFusion.model.info;

import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * @author vnikolaenko
 */
public class CfmlAttributeDescription implements Comparable<CfmlAttributeDescription> {
  private Pattern myNamePattern;
  // private String myName;
  private int myType;
  private boolean myRequired;
  private String myDescription;
  private String myCompletionExample = null;
  private String[] myValues = null;

  public CfmlAttributeDescription(String name, int type, boolean required, String description) {
    myNamePattern = Pattern.compile(name);
    myType = type;
    myRequired = required;
    myDescription = description;
  }

  public CfmlAttributeDescription(String name, int type, boolean required, String description, String completionExample) {
    this(name, type, required, description);
    myCompletionExample = completionExample;
  }

  public void addValue(String value) {
    if (myValues == null) {
      myValues = ArrayUtil.EMPTY_STRING_ARRAY;
    }
    myValues = ArrayUtil.append(myValues, value);
  }

  @Nullable
  public String[] getValues() {
    return myValues;
  }

  public String getName() {
    return myNamePattern.matcher(myNamePattern.pattern()).matches() ? myNamePattern.pattern() : myCompletionExample;
  }

  public String getDescription() {
    return myDescription;
  }

  public boolean acceptName(String name) {
    return myNamePattern.matcher(name).matches();
  }

  public int getType() {
    return myType;
  }

  public boolean isRequired() {
    return myRequired;
  }

  public int compareTo(CfmlAttributeDescription o) {
    return myNamePattern.pattern().compareTo(o.myNamePattern.pattern());
  }

  @Override
  public String toString() {
    return "" +
           myNamePattern.pattern() +
           "</div>" +
           "" +
           getDescription() +
           "</div>" +
           "" +
           getType() +
           "</div>" +
           "" +
           isRequired() +
           "</div>";
  }
}
