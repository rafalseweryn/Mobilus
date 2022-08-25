package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RepeatedFieldBuilderV3<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> implements AbstractMessage.BuilderParent {
  private List<SingleFieldBuilderV3<MType, BType, IType>> builders;
  
  private BuilderExternalList<MType, BType, IType> externalBuilderList;
  
  private MessageExternalList<MType, BType, IType> externalMessageList;
  
  private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
  
  private boolean isClean;
  
  private boolean isMessagesListMutable;
  
  private List<MType> messages;
  
  private AbstractMessage.BuilderParent parent;
  
  public RepeatedFieldBuilderV3(List<MType> paramList, boolean paramBoolean1, AbstractMessage.BuilderParent paramBuilderParent, boolean paramBoolean2) {
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
    SingleFieldBuilderV3 singleFieldBuilderV3 = this.builders.get(paramInt);
    if (singleFieldBuilderV3 == null)
      return this.messages.get(paramInt); 
    if (paramBoolean) {
      singleFieldBuilderV3 = (SingleFieldBuilderV3)singleFieldBuilderV3.build();
    } else {
      singleFieldBuilderV3 = (SingleFieldBuilderV3)singleFieldBuilderV3.getMessage();
    } 
    return (MType)singleFieldBuilderV3;
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
  
  public RepeatedFieldBuilderV3<MType, BType, IType> addAllMessages(Iterable<? extends MType> paramIterable) {
    Iterator<? extends MType> iterator2 = paramIterable.iterator();
    while (iterator2.hasNext())
      Internal.checkNotNull((AbstractMessage)iterator2.next()); 
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
    SingleFieldBuilderV3<MType, AbstractMessage.Builder, MessageOrBuilder> singleFieldBuilderV3 = new SingleFieldBuilderV3<>(paramMType, this, this.isClean);
    this.messages.add(paramInt, null);
    this.builders.add(paramInt, singleFieldBuilderV3);
    onChanged();
    incrementModCounts();
    return (BType)singleFieldBuilderV3.getBuilder();
  }
  
  public BType addBuilder(MType paramMType) {
    ensureMutableMessageList();
    ensureBuilders();
    SingleFieldBuilderV3<MType, AbstractMessage.Builder, MessageOrBuilder> singleFieldBuilderV3 = new SingleFieldBuilderV3<>(paramMType, this, this.isClean);
    this.messages.add(null);
    this.builders.add(singleFieldBuilderV3);
    onChanged();
    incrementModCounts();
    return (BType)singleFieldBuilderV3.getBuilder();
  }
  
  public RepeatedFieldBuilderV3<MType, BType, IType> addMessage(int paramInt, MType paramMType) {
    Internal.checkNotNull(paramMType);
    ensureMutableMessageList();
    this.messages.add(paramInt, paramMType);
    if (this.builders != null)
      this.builders.add(paramInt, null); 
    onChanged();
    incrementModCounts();
    return this;
  }
  
  public RepeatedFieldBuilderV3<MType, BType, IType> addMessage(MType paramMType) {
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
          SingleFieldBuilderV3 singleFieldBuilderV3 = this.builders.get(b1);
          if (singleFieldBuilderV3 != null && singleFieldBuilderV3.build() != message) {
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
      for (SingleFieldBuilderV3<MType, BType, IType> singleFieldBuilderV3 : this.builders) {
        if (singleFieldBuilderV3 != null)
          singleFieldBuilderV3.dispose(); 
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
    SingleFieldBuilderV3<AbstractMessage, AbstractMessage.Builder, MessageOrBuilder> singleFieldBuilderV31 = this.builders.get(paramInt);
    SingleFieldBuilderV3<AbstractMessage, AbstractMessage.Builder, MessageOrBuilder> singleFieldBuilderV32 = singleFieldBuilderV31;
    if (singleFieldBuilderV31 == null) {
      singleFieldBuilderV32 = new SingleFieldBuilderV3<>((AbstractMessage)this.messages.get(paramInt), this, this.isClean);
      this.builders.set(paramInt, singleFieldBuilderV32);
    } 
    return (BType)singleFieldBuilderV32.getBuilder();
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
    SingleFieldBuilderV3 singleFieldBuilderV3 = this.builders.get(paramInt);
    return (IType)((singleFieldBuilderV3 == null) ? (MessageOrBuilder)this.messages.get(paramInt) : singleFieldBuilderV3.getMessageOrBuilder());
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
      SingleFieldBuilderV3 singleFieldBuilderV3 = this.builders.remove(paramInt);
      if (singleFieldBuilderV3 != null)
        singleFieldBuilderV3.dispose(); 
    } 
    onChanged();
    incrementModCounts();
  }
  
  public RepeatedFieldBuilderV3<MType, BType, IType> setMessage(int paramInt, MType paramMType) {
    Internal.checkNotNull(paramMType);
    ensureMutableMessageList();
    this.messages.set(paramInt, paramMType);
    if (this.builders != null) {
      SingleFieldBuilderV3 singleFieldBuilderV3 = this.builders.set(paramInt, null);
      if (singleFieldBuilderV3 != null)
        singleFieldBuilderV3.dispose(); 
    } 
    onChanged();
    incrementModCounts();
    return this;
  }
  
  private static class BuilderExternalList<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<BType> implements List<BType> {
    RepeatedFieldBuilderV3<MType, BType, IType> builder;
    
    BuilderExternalList(RepeatedFieldBuilderV3<MType, BType, IType> param1RepeatedFieldBuilderV3) {
      this.builder = param1RepeatedFieldBuilderV3;
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
  
  private static class MessageExternalList<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<MType> implements List<MType> {
    RepeatedFieldBuilderV3<MType, BType, IType> builder;
    
    MessageExternalList(RepeatedFieldBuilderV3<MType, BType, IType> param1RepeatedFieldBuilderV3) {
      this.builder = param1RepeatedFieldBuilderV3;
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
  
  private static class MessageOrBuilderExternalList<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<IType> implements List<IType> {
    RepeatedFieldBuilderV3<MType, BType, IType> builder;
    
    MessageOrBuilderExternalList(RepeatedFieldBuilderV3<MType, BType, IType> param1RepeatedFieldBuilderV3) {
      this.builder = param1RepeatedFieldBuilderV3;
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\RepeatedFieldBuilderV3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */