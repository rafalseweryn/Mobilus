package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CollectionUtils {
  public static <K, V> Map<K, V> inOrderMapOf() {
    return mapOf();
  }
  
  public static <K, V> Map<K, V> inOrderMapOf(K paramK, V paramV) {
    return mapOf(paramK, paramV);
  }
  
  public static <K, V> Map<K, V> inOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2) {
    Map<?, ?> map = zzg(2, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> inOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3) {
    Map<?, ?> map = zzg(3, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> inOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4) {
    Map<?, ?> map = zzg(4, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> inOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5) {
    Map<?, ?> map = zzg(5, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    map.put(paramK5, paramV5);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> inOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5, K paramK6, V paramV6) {
    Map<?, ?> map = zzg(6, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    map.put(paramK5, paramV5);
    map.put(paramK6, paramV6);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> inOrderMapOfKeyValueArrays(K[] paramArrayOfK, V[] paramArrayOfV) {
    zza(paramArrayOfK, paramArrayOfV);
    int i = paramArrayOfK.length;
    switch (i) {
      default:
        return Collections.unmodifiableMap(zzb(i, false, paramArrayOfK, paramArrayOfV));
      case 1:
        return inOrderMapOf(paramArrayOfK[0], paramArrayOfV[0]);
      case 0:
        break;
    } 
    return inOrderMapOf();
  }
  
  public static <T> Set<T> inOrderSetOf() {
    return setOf();
  }
  
  public static <T> Set<T> inOrderSetOf(T paramT) {
    return setOf(paramT);
  }
  
  public static <T> Set<T> inOrderSetOf(T paramT1, T paramT2) {
    Set<?> set = zze(2, false);
    set.add(paramT1);
    set.add(paramT2);
    return Collections.unmodifiableSet((Set)set);
  }
  
  public static <T> Set<T> inOrderSetOf(T paramT1, T paramT2, T paramT3) {
    Set<?> set = zze(3, false);
    set.add(paramT1);
    set.add(paramT2);
    set.add(paramT3);
    return Collections.unmodifiableSet((Set)set);
  }
  
  public static <T> Set<T> inOrderSetOf(T paramT1, T paramT2, T paramT3, T paramT4) {
    Set<?> set = zze(4, false);
    set.add(paramT1);
    set.add(paramT2);
    set.add(paramT3);
    set.add(paramT4);
    return Collections.unmodifiableSet((Set)set);
  }
  
  public static <T> Set<T> inOrderSetOf(T... paramVarArgs) {
    switch (paramVarArgs.length) {
      default:
        return Collections.unmodifiableSet(zzb(paramVarArgs.length, false, paramVarArgs));
      case 4:
        return inOrderSetOf(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2], paramVarArgs[3]);
      case 3:
        return inOrderSetOf(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2]);
      case 2:
        return inOrderSetOf(paramVarArgs[0], paramVarArgs[1]);
      case 1:
        return inOrderSetOf(paramVarArgs[0]);
      case 0:
        break;
    } 
    return inOrderSetOf();
  }
  
  public static boolean isEmpty(@Nullable Collection<?> paramCollection) {
    return (paramCollection == null) ? true : paramCollection.isEmpty();
  }
  
  public static boolean isEmpty(@Nullable Map<?, ?> paramMap) {
    return (paramMap == null) ? true : paramMap.isEmpty();
  }
  
  @Deprecated
  public static <T> List<T> listOf() {
    return Collections.emptyList();
  }
  
  @Deprecated
  public static <T> List<T> listOf(T paramT) {
    return Collections.singletonList(paramT);
  }
  
  @Deprecated
  public static <T> List<T> listOf(T... paramVarArgs) {
    switch (paramVarArgs.length) {
      default:
        return Collections.unmodifiableList(Arrays.asList(paramVarArgs));
      case 1:
        return listOf(paramVarArgs[0]);
      case 0:
        break;
    } 
    return listOf();
  }
  
  public static <K, V> Map<K, V> mapOf() {
    return Collections.emptyMap();
  }
  
  public static <K, V> Map<K, V> mapOf(K paramK, V paramV) {
    return Collections.singletonMap(paramK, paramV);
  }
  
  public static <K, V> Map<K, V> mapOf(K paramK1, V paramV1, K paramK2, V paramV2) {
    Map<?, ?> map = zzf(2, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> mapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3) {
    Map<?, ?> map = zzf(3, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> mapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4) {
    Map<?, ?> map = zzf(4, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> mapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5) {
    Map<?, ?> map = zzf(5, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    map.put(paramK5, paramV5);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> mapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5, K paramK6, V paramV6) {
    Map<?, ?> map = zzf(6, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    map.put(paramK5, paramV5);
    map.put(paramK6, paramV6);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> mapOfKeyValueArrays(K[] paramArrayOfK, V[] paramArrayOfV) {
    zza(paramArrayOfK, paramArrayOfV);
    switch (paramArrayOfK.length) {
      default:
        return Collections.unmodifiableMap(zza(paramArrayOfK.length, false, paramArrayOfK, paramArrayOfV));
      case 1:
        return mapOf(paramArrayOfK[0], paramArrayOfV[0]);
      case 0:
        break;
    } 
    return mapOf();
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOf() {
    return new LinkedHashMap<>();
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOf(K paramK, V paramV) {
    return mutableInOrderMapOfWithSize(1, paramK, paramV);
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2) {
    return mutableInOrderMapOfWithSize(2, paramK1, paramV1, paramK2, paramV2);
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3) {
    return mutableInOrderMapOfWithSize(3, paramK1, paramV1, paramK2, paramV2, paramK3, paramV3);
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOfKeyValueArrays(K[] paramArrayOfK, V[] paramArrayOfV) {
    zza(paramArrayOfK, paramArrayOfV);
    int i = paramArrayOfK.length;
    return (i == 0) ? mutableInOrderMapOf() : zzb(i, true, paramArrayOfK, paramArrayOfV);
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOfKeyValueArraysWithSize(int paramInt, K[] paramArrayOfK, V[] paramArrayOfV) {
    zza(paramArrayOfK, paramArrayOfV);
    int i = Math.max(paramInt, paramArrayOfK.length);
    return (i == 0) ? mutableInOrderMapOf() : ((paramArrayOfK.length == 0) ? mutableInOrderMapOfWithSize(i) : zzb(paramInt, true, paramArrayOfK, paramArrayOfV));
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOfWithSize(int paramInt) {
    return (paramInt == 0) ? mutableInOrderMapOf() : zzg(paramInt, true);
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOfWithSize(int paramInt, K paramK, V paramV) {
    Map<?, ?> map = zzg(Math.max(paramInt, 1), true);
    map.put(paramK, paramV);
    return (Map)map;
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOfWithSize(int paramInt, K paramK1, V paramV1, K paramK2, V paramV2) {
    Map<?, ?> map = zzg(Math.max(paramInt, 2), true);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    return (Map)map;
  }
  
  public static <K, V> Map<K, V> mutableInOrderMapOfWithSize(int paramInt, K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3) {
    Map<?, ?> map = zzg(Math.max(paramInt, 3), true);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    return (Map)map;
  }
  
  public static <T> Set<T> mutableInOrderSetOf() {
    return new LinkedHashSet<>();
  }
  
  public static <T> Set<T> mutableInOrderSetOf(T paramT) {
    return mutableInOrderSetOfWithSize(1, paramT);
  }
  
  public static <T> Set<T> mutableInOrderSetOf(T paramT1, T paramT2) {
    return mutableInOrderSetOfWithSize(2, paramT1, paramT2);
  }
  
  public static <T> Set<T> mutableInOrderSetOf(T... paramVarArgs) {
    return (paramVarArgs.length == 0) ? mutableInOrderSetOf() : zzb(paramVarArgs.length, true, paramVarArgs);
  }
  
  public static <T> Set<T> mutableInOrderSetOfWithSize(int paramInt) {
    return (paramInt == 0) ? mutableInOrderSetOf() : zze(paramInt, true);
  }
  
  public static <T> Set<T> mutableInOrderSetOfWithSize(int paramInt, T paramT) {
    Set<?> set = zze(Math.max(paramInt, 1), true);
    set.add(paramT);
    return (Set)set;
  }
  
  public static <T> Set<T> mutableInOrderSetOfWithSize(int paramInt, T paramT1, T paramT2) {
    Set<?> set = zze(Math.max(paramInt, 2), true);
    set.add(paramT1);
    set.add(paramT2);
    return (Set)set;
  }
  
  public static <T> Set<T> mutableInOrderSetOfWithSize(int paramInt, T... paramVarArgs) {
    int i = Math.max(paramInt, paramVarArgs.length);
    return (i == 0) ? mutableSetOf() : ((paramVarArgs.length == 0) ? mutableInOrderSetOfWithSize(paramInt) : zzb(i, true, paramVarArgs));
  }
  
  public static <T> List<T> mutableListOf() {
    return new ArrayList<>();
  }
  
  public static <T> List<T> mutableListOf(T paramT) {
    return mutableListOfWithSize(1, paramT);
  }
  
  public static <T> List<T> mutableListOf(T paramT1, T paramT2) {
    return mutableListOfWithSize(2, paramT1, paramT2);
  }
  
  public static <T> List<T> mutableListOf(T... paramVarArgs) {
    return (paramVarArgs.length == 0) ? mutableListOf() : new ArrayList<>(Arrays.asList(paramVarArgs));
  }
  
  public static <T> List<T> mutableListOfWithSize(int paramInt) {
    return (paramInt == 0) ? mutableListOf() : zzc(paramInt, true);
  }
  
  public static <T> List<T> mutableListOfWithSize(int paramInt, T paramT) {
    List<?> list = zzc(Math.max(paramInt, 1), true);
    list.add(paramT);
    return (List)list;
  }
  
  public static <T> List<T> mutableListOfWithSize(int paramInt, T paramT1, T paramT2) {
    List<?> list = zzc(Math.max(paramInt, 2), true);
    list.add(paramT1);
    list.add(paramT2);
    return (List)list;
  }
  
  public static <T> List<T> mutableListOfWithSize(int paramInt, T... paramVarArgs) {
    int i = Math.max(paramInt, paramVarArgs.length);
    if (i == 0)
      return mutableListOf(); 
    if (paramVarArgs.length == 0)
      return mutableListOfWithSize(paramInt); 
    if (paramVarArgs.length == i)
      return new ArrayList<>(Arrays.asList(paramVarArgs)); 
    List<?> list = zzc(i, true);
    list.addAll(Arrays.asList((Object[])paramVarArgs));
    return (List)list;
  }
  
  public static <K, V> Map<K, V> mutableMapOf() {
    return (Map<K, V>)new ArrayMap();
  }
  
  public static <K, V> Map<K, V> mutableMapOf(K paramK, V paramV) {
    return mutableMapOfWithSize(1, paramK, paramV);
  }
  
  public static <K, V> Map<K, V> mutableMapOf(K paramK1, V paramV1, K paramK2, V paramV2) {
    return mutableMapOfWithSize(2, paramK1, paramV1, paramK2, paramV2);
  }
  
  public static <K, V> Map<K, V> mutableMapOf(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3) {
    return mutableMapOfWithSize(3, paramK1, paramV1, paramK2, paramV2, paramK3, paramV3);
  }
  
  public static <K, V> Map<K, V> mutableMapOfKeyValueArrays(K[] paramArrayOfK, V[] paramArrayOfV) {
    zza(paramArrayOfK, paramArrayOfV);
    int i = paramArrayOfK.length;
    return (i == 0) ? mutableMapOf() : zza(i, true, paramArrayOfK, paramArrayOfV);
  }
  
  public static <K, V> Map<K, V> mutableMapOfKeyValueArraysWithSize(int paramInt, K[] paramArrayOfK, V[] paramArrayOfV) {
    zza(paramArrayOfK, paramArrayOfV);
    int i = Math.max(paramInt, paramArrayOfK.length);
    return (i == 0) ? mutableMapOf() : ((paramArrayOfK.length == 0) ? mutableMapOfWithSize(paramInt) : zza(i, true, paramArrayOfK, paramArrayOfV));
  }
  
  public static <K, V> Map<K, V> mutableMapOfWithSize(int paramInt) {
    return (paramInt == 0) ? mutableMapOf() : zzf(paramInt, true);
  }
  
  public static <K, V> Map<K, V> mutableMapOfWithSize(int paramInt, K paramK, V paramV) {
    Map<?, ?> map = zzf(Math.max(paramInt, 1), true);
    map.put(paramK, paramV);
    return (Map)map;
  }
  
  public static <K, V> Map<K, V> mutableMapOfWithSize(int paramInt, K paramK1, V paramV1, K paramK2, V paramV2) {
    Map<?, ?> map = zzf(Math.max(paramInt, 2), true);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    return (Map)map;
  }
  
  public static <K, V> Map<K, V> mutableMapOfWithSize(int paramInt, K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3) {
    Map<?, ?> map = zzf(Math.max(paramInt, 3), true);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    return (Map)map;
  }
  
  public static <T> Set<T> mutableSetOf() {
    return (Set<T>)new ArraySet();
  }
  
  public static <T> Set<T> mutableSetOf(T paramT) {
    return mutableSetOfWithSize(1, paramT);
  }
  
  public static <T> Set<T> mutableSetOf(T paramT1, T paramT2) {
    return mutableSetOfWithSize(2, paramT1, paramT2);
  }
  
  public static <T> Set<T> mutableSetOf(T... paramVarArgs) {
    return (paramVarArgs.length == 0) ? mutableSetOf() : zza(paramVarArgs.length, true, paramVarArgs);
  }
  
  public static <T> Set<T> mutableSetOfWithSize(int paramInt) {
    return (paramInt == 0) ? mutableSetOf() : zzd(paramInt, true);
  }
  
  public static <T> Set<T> mutableSetOfWithSize(int paramInt, T paramT) {
    Set<?> set = zzd(Math.max(paramInt, 1), true);
    set.add(paramT);
    return (Set)set;
  }
  
  public static <T> Set<T> mutableSetOfWithSize(int paramInt, T paramT1, T paramT2) {
    Set<?> set = zzd(Math.max(paramInt, 2), true);
    set.add(paramT1);
    set.add(paramT2);
    return (Set)set;
  }
  
  public static <T> Set<T> mutableSetOfWithSize(int paramInt, T... paramVarArgs) {
    int i = Math.max(paramInt, paramVarArgs.length);
    return (i == 0) ? mutableSetOf() : ((paramVarArgs.length == 0) ? mutableSetOfWithSize(paramInt) : zza(i, true, paramVarArgs));
  }
  
  @Deprecated
  public static <T> Set<T> setOf() {
    return Collections.emptySet();
  }
  
  @Deprecated
  public static <T> Set<T> setOf(T paramT) {
    return Collections.singleton(paramT);
  }
  
  @Deprecated
  public static <T> Set<T> setOf(T paramT1, T paramT2) {
    Set<?> set = zzd(2, false);
    set.add(paramT1);
    set.add(paramT2);
    return Collections.unmodifiableSet((Set)set);
  }
  
  @Deprecated
  public static <T> Set<T> setOf(T paramT1, T paramT2, T paramT3) {
    Set<?> set = zzd(3, false);
    set.add(paramT1);
    set.add(paramT2);
    set.add(paramT3);
    return Collections.unmodifiableSet((Set)set);
  }
  
  @Deprecated
  public static <T> Set<T> setOf(T paramT1, T paramT2, T paramT3, T paramT4) {
    Set<?> set = zzd(4, false);
    set.add(paramT1);
    set.add(paramT2);
    set.add(paramT3);
    set.add(paramT4);
    return Collections.unmodifiableSet((Set)set);
  }
  
  @Deprecated
  public static <T> Set<T> setOf(T... paramVarArgs) {
    switch (paramVarArgs.length) {
      default:
        return Collections.unmodifiableSet(zza(paramVarArgs.length, false, paramVarArgs));
      case 4:
        return setOf(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2], paramVarArgs[3]);
      case 3:
        return setOf(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2]);
      case 2:
        return setOf(paramVarArgs[0], paramVarArgs[1]);
      case 1:
        return setOf(paramVarArgs[0]);
      case 0:
        break;
    } 
    return setOf();
  }
  
  private static <K, V> Map<K, V> zza(int paramInt, boolean paramBoolean, K[] paramArrayOfK, V[] paramArrayOfV) {
    Map<?, ?> map = zzf(paramInt, paramBoolean);
    zza(map, (Object[])paramArrayOfK, (Object[])paramArrayOfV);
    return (Map)map;
  }
  
  private static <T> Set<T> zza(int paramInt, boolean paramBoolean, T[] paramArrayOfT) {
    Set<?> set = zzd(paramInt, paramBoolean);
    Collections.addAll(set, (Object[])paramArrayOfT);
    return (Set)set;
  }
  
  private static <K, V> void zza(Map<K, V> paramMap, K[] paramArrayOfK, V[] paramArrayOfV) {
    for (byte b = 0; b < paramArrayOfK.length; b++)
      paramMap.put(paramArrayOfK[b], paramArrayOfV[b]); 
  }
  
  private static <K, V> void zza(K[] paramArrayOfK, V[] paramArrayOfV) {
    if (paramArrayOfK.length != paramArrayOfV.length) {
      int i = paramArrayOfK.length;
      int j = paramArrayOfV.length;
      StringBuilder stringBuilder = new StringBuilder(66);
      stringBuilder.append("Key and values array lengths not equal: ");
      stringBuilder.append(i);
      stringBuilder.append(" != ");
      stringBuilder.append(j);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  private static <K, V> Map<K, V> zzb(int paramInt, boolean paramBoolean, K[] paramArrayOfK, V[] paramArrayOfV) {
    Map<?, ?> map = zzg(paramInt, paramBoolean);
    zza(map, (Object[])paramArrayOfK, (Object[])paramArrayOfV);
    return (Map)map;
  }
  
  private static <T> Set<T> zzb(int paramInt, boolean paramBoolean, T[] paramArrayOfT) {
    Set<?> set = zze(paramInt, paramBoolean);
    Collections.addAll(set, (Object[])paramArrayOfT);
    return (Set)set;
  }
  
  private static <T> List<T> zzc(int paramInt, boolean paramBoolean) {
    return new ArrayList<>(paramInt);
  }
  
  private static <T> Set<T> zzd(int paramInt, boolean paramBoolean) {
    float f;
    char c;
    if (paramBoolean) {
      f = 0.75F;
    } else {
      f = 1.0F;
    } 
    if (paramBoolean) {
      c = '';
    } else {
      c = 'Ā';
    } 
    return (Set<T>)((paramInt <= c) ? new ArraySet(paramInt) : new HashSet<>(paramInt, f));
  }
  
  private static <T> Set<T> zze(int paramInt, boolean paramBoolean) {
    float f;
    if (paramBoolean) {
      f = 0.75F;
    } else {
      f = 1.0F;
    } 
    return new LinkedHashSet<>(paramInt, f);
  }
  
  private static <K, V> Map<K, V> zzf(int paramInt, boolean paramBoolean) {
    float f;
    char c;
    if (paramBoolean) {
      f = 0.75F;
    } else {
      f = 1.0F;
    } 
    if (paramBoolean) {
      c = '';
    } else {
      c = 'Ā';
    } 
    return (Map<K, V>)((paramInt <= c) ? new ArrayMap(paramInt) : new HashMap<>(paramInt, f));
  }
  
  private static <K, V> Map<K, V> zzg(int paramInt, boolean paramBoolean) {
    float f;
    if (paramBoolean) {
      f = 0.75F;
    } else {
      f = 1.0F;
    } 
    return new LinkedHashMap<>(paramInt, f);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\CollectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */