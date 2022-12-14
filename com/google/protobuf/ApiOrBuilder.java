package com.google.protobuf;

import java.util.List;

public interface ApiOrBuilder extends MessageOrBuilder {
  Method getMethods(int paramInt);
  
  int getMethodsCount();
  
  List<Method> getMethodsList();
  
  MethodOrBuilder getMethodsOrBuilder(int paramInt);
  
  List<? extends MethodOrBuilder> getMethodsOrBuilderList();
  
  Mixin getMixins(int paramInt);
  
  int getMixinsCount();
  
  List<Mixin> getMixinsList();
  
  MixinOrBuilder getMixinsOrBuilder(int paramInt);
  
  List<? extends MixinOrBuilder> getMixinsOrBuilderList();
  
  String getName();
  
  ByteString getNameBytes();
  
  Option getOptions(int paramInt);
  
  int getOptionsCount();
  
  List<Option> getOptionsList();
  
  OptionOrBuilder getOptionsOrBuilder(int paramInt);
  
  List<? extends OptionOrBuilder> getOptionsOrBuilderList();
  
  SourceContext getSourceContext();
  
  SourceContextOrBuilder getSourceContextOrBuilder();
  
  Syntax getSyntax();
  
  int getSyntaxValue();
  
  String getVersion();
  
  ByteString getVersionBytes();
  
  boolean hasSourceContext();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ApiOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */