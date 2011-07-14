package com.intellij.flex.uiDesigner.plugins.test {
import flash.display.BitmapData;
import flash.display.ColorCorrection;

import org.hamcrest.Matcher;
import org.hamcrest.assertThat;
import org.hamcrest.core.allOf;
import org.hamcrest.core.isA;
import org.hamcrest.core.not;
import org.hamcrest.object.equalTo;
import org.hamcrest.object.strictlyEqualTo;

public class MxmlTest extends BaseTestCase {
  public function MxmlTest() {
    //noinspection ConstantIfStatementJS
    if (false) {
      SparkApplication();
      SparkComponentsDependOnMx();
      ViewNavigatorApplication();
      SparkComponents();
      MxComponents();
      Embed();
      UntypedProperty();
      ClassProperty();
      ItemRendererAndMixDefaultExplicitContent();
      WindowedApplication();
      PropertyAsTagWithArrayType();
      RichTextAndCollapseWhitespace();
      MixedTextAndSubTags();
      InlineArrayAsAttributeValue();
      InvalidColorName();
      RuntimeError();
      CannotFindDefaultProperty();
      AbstractClass();
      MouseSelectionTest();
      ColorEquals0();
      ChildrenTypeCheck();
      CustomComponent();
    }
  }
  
  public function SparkComponents():void {
    assertThat(app, {document: app});
  }

  public function SparkComponentsDependOnMx():void {
    assertThat(app, {document: app});
  }

  public function MxComponents():void {
    assertThat(app, {document: app});
  }

  public function SparkApplication():void {
    var m:Object = {colorCorrection: ColorCorrection.DEFAULT};
    assertThat(app, m);
    app.colorCorrection = ColorCorrection.ON;
    assertThat(app, m);
  }

  public function ViewNavigatorApplication():void {

  }

  public function Embed():void {
    var bitmapData:BitmapData = app.getElementAt(0).getElementAt(0).source;
    var m:Matcher = allOf(equalTo(bitmapData), {transparent: false, width: 240, height: 180});
    assertThat(app, [[{source: m}, {source: m}], {source: {transparent: true, width: 240, height: 180}}, {source: {transparent: true, width: 500, height: 367}}, {}]);
  }
  
  public function UntypedProperty():void {
    assertThat(app, {left: allOf(isA(int), strictlyEqualTo(0)), right: strictlyEqualTo("c22:12"), top: strictlyEqualTo(0.4), bottom: allOf(isA(int), strictlyEqualTo(-30))});
  }
  
  public function ClassProperty():void {
    assertThat(app, {skinClass: getClass("spark.skins.spark.ApplicationSkin")});
  }
  
  public function ItemRendererAndMixDefaultExplicitContent():void {

  }
  
  public function WindowedApplication():void {

  }
  
  public function PropertyAsTagWithArrayType():void {
    assertThat(app.transitions, [{fromState: "*"}, {fromState: "A"}]);
  }
  
  public function RichTextAndCollapseWhitespace():void {
    assertThat(app, [
      {text: "\n      This is paragraph 1.&\n      This is paragraph \"2\".\n    "},
      {text: "dfds        fadfs      f              "},
      {text: "TextArea 1. TextArea 2. TextArea 3."},
      {text: " TextArea as Default Property 1. TextArea as Default Property 2. TextArea as Default Property 3. "},
      {text: " This is paragraph 1. This is paragraph 2. This is paragraph 3. \nThis is paragraph 2."},
      {text: "BIG NEW"},
      {text: "a      https://bugs.adobe.com/jira/browse/SDK-3983           "}
    ]);
    
    assertThat(app.getElementAt(5).textFlow.getElementByID("span1").fontSize, strictlyEqualTo(16));
  }
  
  public function MixedTextAndSubTags():void {
    assertThat(app, [
      {text: "AAA in UUU "},
      {text: "UUU in AAA "},
      {text: "in UUU "},
      {blendMode: "difference", text: "\n\n    UUU\n    "},
      {text: "TRAMUU&"},
      {text: strictlyEqualTo("12"), buttonMode: strictlyEqualTo(true)},
      {text: strictlyEqualTo("\n    34\n    "), buttonMode: strictlyEqualTo(true)},
      {text: strictlyEqualTo("\n    34\n  "), buttonMode: strictlyEqualTo(true)}
    ]);
  }
  
  public function InlineArrayAsAttributeValue():void {
   // http://youtrack.jetbrains.net/issue/IDEA-64721 
  }
  
  public function InvalidColorName():void {
    assertThat(app, [{color: 0}]);
  }

  public function RuntimeError():void {
    app.validateNow(); // force update for predictable asserts (updateDisplayList with expected error logs before our tests passed result)
    assertThat(app, [{text: 1}, {} , {text: 2}]);
  }

  [Test(nullableDocument)]
  public function RuntimeErrorInMxmlRead():void {
    if (documentManager.document != null) {
      assertThat(documentManager.document.file.name, not("RuntimeErrorInMxmlRead.mxml"));
    }
  }

  public function CannotFindDefaultProperty():void {
    assertThat(app, [{fill: null}, {text: 2}]);
  }

  public function AbstractClass():void {
    assertThat(app, [{label: "before invalid control"}, {label: "after invalid control"}]);
  }

  public function MouseSelectionTest():void {
  }

  public function ColorEquals0():void {
    assertThat(app, [{color: 0}]);
  }

  public function ChildrenTypeCheck():void {
    assertThat(app, [[], [], []]);
  }

  public function CustomComponent():void {
    assertThat(app, []);
  }
}
}