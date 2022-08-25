package pl.com.mobilelabs.mobilus.model.objects;

import android.support.v7.widget.AppCompatCheckBox;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public class UserUtils {
  public static void addRemoveDeviceId(MobilusModel.User.Builder paramBuilder, Device paramDevice, boolean paramBoolean) {
    long l = paramDevice.getId();
    if (paramBoolean) {
      if (!paramBuilder.getAssignedDevicesIdsList().contains(Long.valueOf(l)))
        paramBuilder.addAssignedDevicesIds(l); 
    } else if (paramBuilder.getAssignedDevicesIdsList().contains(Long.valueOf(l))) {
      ArrayList arrayList = new ArrayList();
      arrayList.addAll(paramBuilder.getAssignedDevicesIdsList());
      arrayList.remove(Long.valueOf(l));
      paramBuilder.clearAssignedDevicesIds();
      paramBuilder.addAllAssignedDevicesIds(arrayList);
    } 
  }
  
  public static void addRemoveGroupId(MobilusModel.User.Builder paramBuilder, Group paramGroup, boolean paramBoolean) {
    Device[] arrayOfDevice = paramGroup.getDevices();
    for (byte b = 0; b < arrayOfDevice.length; b++)
      addRemoveDeviceId(paramBuilder, arrayOfDevice[b], paramBoolean); 
  }
  
  public static void addRemovePlaceId(MobilusModel.User.Builder paramBuilder, Place paramPlace, boolean paramBoolean) {
    Group[] arrayOfGroup = paramPlace.getGroups();
    boolean bool = false;
    byte b;
    for (b = 0; b < arrayOfGroup.length; b++)
      addRemoveGroupId(paramBuilder, arrayOfGroup[b], paramBoolean); 
    Device[] arrayOfDevice = paramPlace.getDevices();
    for (b = bool; b < arrayOfDevice.length; b++)
      addRemoveDeviceId(paramBuilder, arrayOfDevice[b], paramBoolean); 
  }
  
  public static void changeGroupsCheckBoxSelections(Set<Long> paramSet, ArrayList<AppCompatCheckBox> paramArrayList) {
    for (byte b = 0; b < paramArrayList.size(); b++) {
      Group group = (Group)((AppCompatCheckBox)paramArrayList.get(b)).getTag();
      HashSet<Long> hashSet = new HashSet();
      Device[] arrayOfDevice = group.getDevices();
      for (byte b1 = 0; b1 < arrayOfDevice.length; b1++)
        hashSet.add(Long.valueOf(arrayOfDevice[b1].getId())); 
      ((AppCompatCheckBox)paramArrayList.get(b)).setChecked(paramSet.containsAll(hashSet));
    } 
  }
  
  public static void changePlacesCheckBoxSelections(Set<Long> paramSet, ArrayList<AppCompatCheckBox> paramArrayList1, ArrayList<AppCompatCheckBox> paramArrayList2) {
    HashSet<Long> hashSet = new HashSet();
    byte b;
    for (b = 0; b < paramArrayList1.size(); b++) {
      Group group = (Group)((AppCompatCheckBox)paramArrayList1.get(b)).getTag();
      HashSet<Long> hashSet1 = new HashSet();
      Device[] arrayOfDevice = group.getDevices();
      for (byte b1 = 0; b1 < arrayOfDevice.length; b1++)
        hashSet1.add(Long.valueOf(arrayOfDevice[b1].getId())); 
      if (paramSet.containsAll(hashSet1))
        hashSet.add(Long.valueOf(group.getId())); 
    } 
    for (b = 0; b < paramArrayList2.size(); b++) {
      boolean bool1;
      Place place = (Place)((AppCompatCheckBox)paramArrayList2.get(b)).getTag();
      Group[] arrayOfGroup = place.getGroups();
      byte b1 = 0;
      while (true) {
        if (b1 < arrayOfGroup.length) {
          if (hashSet.contains(Long.valueOf(arrayOfGroup[b1].getId()))) {
            boolean bool = true;
            break;
          } 
          b1++;
          continue;
        } 
        bool1 = false;
        break;
      } 
      boolean bool2 = bool1;
      if (!bool1) {
        HashSet<Long> hashSet1 = new HashSet();
        Device[] arrayOfDevice = place.getDevices();
        for (b1 = 0; b1 < arrayOfDevice.length; b1++)
          hashSet1.add(Long.valueOf(arrayOfDevice[b1].getId())); 
        bool2 = bool1;
        if (paramSet.containsAll(hashSet1))
          bool2 = true; 
      } 
      ((AppCompatCheckBox)paramArrayList2.get(b)).setChecked(bool2);
    } 
  }
  
  public static String firstAvailaibleNewUsername(ArrayList<MobilusModel.User> paramArrayList) {
    ArrayList<String> arrayList = new ArrayList();
    for (byte b = 0; b < paramArrayList.size(); b++) {
      if (((MobilusModel.User)paramArrayList.get(b)).getLogin().startsWith("new_user_"))
        arrayList.add(((MobilusModel.User)paramArrayList.get(b)).getLogin()); 
    } 
    return String.format("%s%s", new Object[] { "new_user_", Integer.valueOf(arrayList.size() + 1) });
  }
  
  public static long firstAvailaibleUserId(ArrayList<MobilusModel.User> paramArrayList) {
    // Byte code:
    //   0: new java/util/HashSet
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: iconst_0
    //   9: istore_2
    //   10: iload_2
    //   11: aload_0
    //   12: invokevirtual size : ()I
    //   15: if_icmpge -> 43
    //   18: aload_1
    //   19: aload_0
    //   20: iload_2
    //   21: invokevirtual get : (I)Ljava/lang/Object;
    //   24: checkcast pl/com/mobilelabs/mobilus/model/communication/MobilusModel$User
    //   27: invokevirtual getId : ()J
    //   30: invokestatic valueOf : (J)Ljava/lang/Long;
    //   33: invokevirtual add : (Ljava/lang/Object;)Z
    //   36: pop
    //   37: iinc #2, 1
    //   40: goto -> 10
    //   43: lconst_1
    //   44: lstore_3
    //   45: lload_3
    //   46: aload_1
    //   47: invokevirtual size : ()I
    //   50: i2l
    //   51: lcmp
    //   52: ifgt -> 80
    //   55: aload_1
    //   56: new java/lang/Long
    //   59: dup
    //   60: lload_3
    //   61: invokespecial <init> : (J)V
    //   64: invokevirtual contains : (Ljava/lang/Object;)Z
    //   67: ifne -> 73
    //   70: goto -> 82
    //   73: lload_3
    //   74: lconst_1
    //   75: ladd
    //   76: lstore_3
    //   77: goto -> 45
    //   80: lconst_0
    //   81: lstore_3
    //   82: lload_3
    //   83: lstore #5
    //   85: lload_3
    //   86: lconst_0
    //   87: lcmp
    //   88: ifne -> 100
    //   91: aload_1
    //   92: invokevirtual size : ()I
    //   95: iconst_1
    //   96: iadd
    //   97: i2l
    //   98: lstore #5
    //   100: lload #5
    //   102: lreturn
  }
  
  public static boolean isUsernameAvailaible(ArrayList<MobilusModel.User> paramArrayList, String paramString) {
    ArrayList<String> arrayList = new ArrayList();
    for (byte b = 0; b < paramArrayList.size(); b++)
      arrayList.add(((MobilusModel.User)paramArrayList.get(b)).getLogin()); 
    return arrayList.contains(paramString) ^ true;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\UserUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */