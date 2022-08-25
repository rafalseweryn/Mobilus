package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RepeatedFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
  private List<SingleFieldBuilder<MType, BType, IType>> builders;
  
  private BuilderExternalList<MType, BType, IType> externalBuilderList;
  
  private MessageExternalList<MType, BType, IType> externalMessageList;
  
  private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
  
  private boolean isClean;
  
  private boolean isMessagesListMutable;
  
  private List<MType> messages;
  
  private GeneratedMessage.BuilderParent parent;
  
  public RepeatedFieldBuilder(List<MType> paramList, boolean paramBoolean1, GeneratedMessage.BuilderParent paramBuilderParent, boolean paramBoolean2) {
    this.messages = paramList;
    this.isMessagesListMutable = paramBoolean1;
    this.parent = paramBuilderParent;
    this.isClean = paramBoolean2;
  }
  
  private void ensureBuilders() {
    if (this.builders == null) {
      this.builders = new ArrayList<>(this.messages.size());
      for (byte b = 0; b < this.messages.size(); b++)
        this.builders.add(null); 
    } 
  }
  
  private void ensureMutableMessageList() {
    if (!this.isMessagesListMutable) {
      this.messages = new ArrayList<>(this.messages);
      this.isMessagesListMutable = true;
    } 
  }
  
  private MType getMessage(int paramInt, boolean paramBoolean) {
    if (this.builders == null)
      return this.messages.get(paramInt); 
    SingleFieldBuilder singleFieldBuilder = this.builders.get(paramInt);
    if (singleFieldBuilder == null)
      return this.messages.get(paramInt); 
    if (paramBoolean) {
      singleFieldBuilder = (SingleFieldBuilder)singleFieldBuilder.build();
    } else {
      singleFieldBuilder = (SingleFieldBuilder)singleFieldBuilder.getMessage();
    } 
    return (MType)singleFieldBuilder;
  }
  
  private void incrementModCounts() {
    if (this.externalMessageList != null)
      this.externalMessageList.incrementModCount(); 
    if (this.externalBuilderList != null)
      this.externalBuilderList.incrementModCount(); 
    if (this.externalMessageOrBuilderList != null)
      this.externalMessageOrBuilderList.incrementModCount(); 
  }
  
  private void onChanged() {
    if (this.isClean && this.parent != null) {
      this.parent.markDirty();
      this.isClean = false;
    } 
  }
  
  public RepeatedFieldBuilder<MType, BType, IType> addAllMessages(Iterable<? extends MType> paramIterable) {
    Iterator<? extends MType> iterator2 = paramIterable.iterator();
    while (iterator2.hasNext())
      Internal.checkNotNull((GeneratedMessage)iterator2.next()); 
    int i = -1;
    if (paramIterable instanceof Collection) {
      Collection collection = (Collection)paramIterable;
      if (collection.size() == 0)
        return this; 
      i = collection.size();
    } 
    ensureMutableMessageList();
    if (i >= 0 && this.messages instanceof ArrayList)
      ((ArrayList)this.messages).ensureCapacity(this.messages.size() + i); 
    Iterator<? extends MType> iterator1 = paramIterable.iterator();
    while (iterator1.hasNext())
      addMessage(iterator1.next()); 
    onChanged();
    incrementModCounts();
    return this;
  }
  
  public BType addBuilder(int paramInt, MType paramMType) {
    ensureMutableMessageList();
    ensureBuilders();
    SingleFieldBuilder<MType, GeneratedMessage.Builder, MessageOrBuilder> singleFieldBuilder = new SingleFieldBuilder<>(paramMType, this, this.isClean);
    this.messages.add(paramInt, null);
    this.builders.add(paramInt, singleFieldBuilder);
    onChanged();
    incrementModCounts();
    return (BType)singleFieldBuilder.getBuilder();
  }
  
  public BType addBuilder(MType paramMType) {
    ensureMutableMessageList();
    ensureBuilders();
    SingleFieldBuilder<MType, GeneratedMessage.Builder, MessageOrBuilder> singleFieldBuilder = new SingleFieldBuilder<>(paramMType, this, this.isClean);
    this.messages.add(null);
    this.builders.add(singleFieldBuilder);
    onChanged();
    incrementModCounts();
    return (BType)singleFieldBuilder.getBuilder();
  }
  
  public RepeatedFieldBuilder<MType, BType, IType> addMessage(int paramInt, MType paramMType) {
    Internal.checkNotNull(paramMType);
    ensureMutableMessageList();
    this.messages.add(paramInt, paramMType);
    if (this.builders != null)
      this.builders.add(paramInt, null); 
    onChanged();
    incrementModCounts();
    return this;
  }
  
  public RepeatedFieldBuilder<MType, BType, IType> addMessage(MType paramMType) {
    Internal.checkNotNull(paramMType);
    ensureMutableMessageList();
    this.messages.add(paramMType);
    if (this.builders != null)
      this.builders.add(null); 
    onChanged();
    incrementModCounts();
    return this;
  }
  
  public List<MType> build() {
    this.isClean = true;
    if (!this.isMessagesListMutable && this.builders == null)
      return this.messages; 
    if (!this.isMessagesListMutable) {
      byte b1 = 0;
      while (true) {
        if (b1 < this.messages.size()) {
          Message message = (Message)this.messages.get(b1);
          SingleFieldBuilder singleFieldBuilder = this.builders.get(b1);
          if (singleFieldBuilder != null && singleFieldBuilder.build() != message) {
            b1 = 0;
            break;
          } 
          b1++;
          continue;
        } 
        b1 = 1;
        break;
      } 
      if (b1 != 0)
        return this.messages; 
    } 
    ensureMutableMessageList();
    for (byte b = 0; b < this.messages.size(); b++)
      this.messages.set(b, getMessage(b, true)); 
    this.messages = Collections.unmodifiableList(this.messages);
    this.isMessagesListMutable = false;
    return this.messages;
  }
  
  public void clear() {
    this.messages = Collections.emptyList();
    this.isMessagesListMutable = false;
    if (this.builders != null) {
      for (SingleFieldBuilder<MType, BType, IType> singleFieldBuilder : this.builders) {
        if (singleFieldBuilder != null)
          singleFieldBuilder.dispose(); 
      } 
      this.builders = null;
    } 
    onChanged();
    incrementModCounts();
  }
  
  public void dispose() {
    this.parent = null;
  }
  
  public BType getBuilder(int paramInt) {
    ensureBuilders();
    SingleFieldBuilder<GeneratedMessage, GeneratedMessage.Builder, MessageOrBuilder> singleFieldBuilder1 = this.builders.get(paramInt);
    SingleFieldBuilder<GeneratedMessage, GeneratedMessage.Builder, MessageOrBuilder> singleFieldBuilder2 = singleFieldBuilder1;
    if (singleFieldBuilder1 == null) {
      singleFieldBuilder2 = new SingleFieldBuilder<>((GeneratedMessage)this.messages.get(paramInt), this, this.isClean);
      this.builders.set(paramInt, singleFieldBuilder2);
    } 
    return (BType)singleFieldBuilder2.getBuilder();
  }
  
  public List<BType> getBuilderList() {
    if (this.externalBuilderList == null)
      this.externalBuilderList = new BuilderExternalList<>(this); 
    return this.externalBuilderList;
  }
  
  public int getCount() {
    return this.messages.size();
  }
  
  public MType getMessage(int paramInt) {
    return getMessage(paramInt, false);
  }
  
  public List<MType> getMessageList() {
    if (this.externalMessageList == null)
      this.externalMessageList = new MessageExternalList<>(this); 
    return this.externalMessageList;
  }
  
  public IType getMessageOrBuilder(int paramInt) {
    if (this.builders == null)
      return (IType)this.messages.get(paramInt); 
    SingleFieldBuilder singleFieldBuilder = this.builders.get(paramInt);
    return (IType)((singleFieldBuilder == null) ? (MessageOrBuilder)this.messages.get(paramInt) : singleFieldBuilder.getMessageOrBuilder());
  }
  
  public List<IType> getMessageOrBuilderList() {
    if (this.externalMessageOrBuilderList == null)
      this.externalMessageOrBuilderList = new MessageOrBuilderExternalList<>(this); 
    return this.externalMessageOrBuilderList;
  }
  
  public boolean isEmpty() {
    return this.messages.isEmpty();
  }
  
  public void markDirty() {
    onChanged();
  }
  
  public void remove(int paramInt) {
    ensureMutableMessageList();
    this.messages.remove(paramInt);
    if (this.builders != null) {
      SingleFieldBuilder singleFieldBuilder = this.builders.remove(paramInt);
      if (singleFieldBuilder != null)
        singleFieldBuilder.dispose(); 
    } 
    onChanged();
    incrementModCounts();
  }
  
  public RepeatedFieldBuilder<MType, BType, IType> setMessage(int paramInt, MType paramMType) {
    Internal.checkNotNull(paramMType);
    ensureMutableMessageList();
    this.messages.set(paramInt, paramMType);
    if (this.builders != null) {
      SingleFieldBuilder singleFieldBuilder = this.builders.set(paramInt, null);
      if (singleFieldBuilder != null)
        singleFieldBuilder.dispose(); 
    } 
    onChanged();
    incrementModCounts();
    return this;
  }
  
  private static class BuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<BType> implements List<BType> {
    RepeatedFieldBuilder<MType, BType, IType> builder;
    
    BuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> param1RepeatedFieldBuilder) {
      this.builder = param1RepeatedFieldBuilder;
    }
    
    public BType get(int param1Int) {
      return this.builder.getBuilder(param1Int);
    }
    
    void incrementModCount() {
      this.modCount++;
    }
    
    public int size() {
      return this.builder.getCount();
    }
  }
  
  private static class MessageExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<MType> implements List<MType> {
    RepeatedFieldBuilder<MType, BType, IType> builder;
    
    MessageExternalList(RepeatedFieldBuilder<MType, BType, IType> param1RepeatedFieldBuilder) {
      this.builder = param1RepeatedFieldBuilder;
    }
    
    public MType get(int param1Int) {
      return this.builder.getMessage(param1Int);
    }
    
    void incrementModCount() {
      this.modCount++;
    }
    
    public int size() {
      return this.builder.getCount();
    }
  }
  
  private static class MessageOrBuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<IType> implements List<IType> {
    RepeatedFieldBuilder<MType, BType, IType> builder;
    
    MessageOrBuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> param1RepeatedFieldBuilder) {
      this.builder = param1RepeatedFieldBuilder;
    }
    
    public IType get(int param1Int) {
      return this.builder.getMessageOrBuilder(param1Int);
    }
    
    void incrementModCount() {
      this.modCount++;
    }
    
    public int size() {
      return this.builder.getCount();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\RepeatedFieldBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */