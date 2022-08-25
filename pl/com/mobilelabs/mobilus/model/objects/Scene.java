package pl.com.mobilelabs.mobilus.model.objects;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public class Scene extends BaseObject implements Comparable<Scene> {
  private int astral;
  
  private MobilusModel.SceneAstralSchedule[] astralSchedules;
  
  private List<Date> dateList;
  
  private ArrayList<Integer> daysOfWeek;
  
  private boolean enabled;
  
  private MobilusModel.SceneEvent[] events;
  
  private int hour;
  
  private int interval;
  
  private boolean isAstralValue;
  
  private Location location;
  
  private int minute;
  
  private ArrayList<MobilusModel.SceneEvent> tempSceneEvents;
  
  private MobilusModel.SceneWeekSchedule[] weekSchedules;
  
  private Scene(long paramLong, String paramString, int paramInt, boolean paramBoolean, MobilusModel.SceneEvent[] paramArrayOfSceneEvent, MobilusModel.SceneAstralSchedule[] paramArrayOfSceneAstralSchedule, MobilusModel.SceneWeekSchedule[] paramArrayOfSceneWeekSchedule, Location paramLocation) {
    super(paramLong, paramString, paramInt);
    this.location = paramLocation;
    this.enabled = paramBoolean;
    this.events = paramArrayOfSceneEvent;
    this.astralSchedules = paramArrayOfSceneAstralSchedule;
    this.weekSchedules = paramArrayOfSceneWeekSchedule;
    this.dateList = new LinkedList<>();
    this.tempSceneEvents = null;
    this.daysOfWeek = null;
    this.astral = -1;
    this.interval = 0;
    this.hour = -1;
    this.minute = -1;
    this.isAstralValue = false;
    refreshDates();
  }
  
  public static Scene createFrom(MobilusModel.Scene paramScene, Location paramLocation) {
    boolean bool;
    if (!paramScene.hasId() || !paramScene.hasName() || !paramScene.hasIcon())
      return null; 
    if (paramScene.hasEnabled()) {
      bool = paramScene.getEnabled();
    } else {
      bool = false;
    } 
    MobilusModel.SceneEvent[] arrayOfSceneEvent = new MobilusModel.SceneEvent[0];
    if (paramScene.getSceneEventsCount() > 0)
      arrayOfSceneEvent = (MobilusModel.SceneEvent[])paramScene.getSceneEventsList().toArray((Object[])new MobilusModel.SceneEvent[paramScene.getSceneEventsList().size()]); 
    MobilusModel.SceneAstralSchedule[] arrayOfSceneAstralSchedule = new MobilusModel.SceneAstralSchedule[0];
    if (paramScene.getSceneAstralSchedulesCount() > 0)
      arrayOfSceneAstralSchedule = (MobilusModel.SceneAstralSchedule[])paramScene.getSceneAstralSchedulesList().toArray((Object[])new MobilusModel.SceneAstralSchedule[paramScene.getSceneAstralSchedulesList().size()]); 
    MobilusModel.SceneWeekSchedule[] arrayOfSceneWeekSchedule = new MobilusModel.SceneWeekSchedule[0];
    if (paramScene.getSceneWeekSchedulesCount() > 0)
      arrayOfSceneWeekSchedule = (MobilusModel.SceneWeekSchedule[])paramScene.getSceneWeekSchedulesList().toArray((Object[])new MobilusModel.SceneWeekSchedule[paramScene.getSceneWeekSchedulesList().size()]); 
    return new Scene(paramScene.getId(), paramScene.getName(), paramScene.getIcon(), bool, arrayOfSceneEvent, arrayOfSceneAstralSchedule, arrayOfSceneWeekSchedule, paramLocation);
  }
  
  private void extractSchedule() {
    if (this.daysOfWeek == null) {
      this.daysOfWeek = new ArrayList<>();
      if (this.dateList != null)
        for (Date date1 : this.dateList) {
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date1);
          this.daysOfWeek.add(Integer.valueOf(calendar.get(7) - 1));
        }  
      Date date = getNextRun();
      if (date != null) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
      } else {
        Calendar calendar = Calendar.getInstance();
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
      } 
      if (this.isAstralValue) {
        this.astral = this.astralSchedules[0].getAstral();
        this.interval = this.astralSchedules[0].getInterval();
      } else {
        this.astral = -1;
        this.interval = 0;
      } 
    } 
  }
  
  private MobilusModel.SceneEvent modifySceneEvent(MobilusModel.SceneEvent paramSceneEvent, String paramString) {
    MobilusModel.SceneEvent.Builder builder = MobilusModel.SceneEvent.newBuilder();
    builder.setId(paramSceneEvent.getId());
    builder.setDeviceId(paramSceneEvent.getDeviceId());
    builder.setEventNumber(paramSceneEvent.getEventNumber());
    builder.setValue(paramString);
    return builder.build();
  }
  
  public boolean changeEventValue(Context paramContext, long paramLong, DeviceType paramDeviceType, int paramInt) {
    if (this.tempSceneEvents == null) {
      this.tempSceneEvents = new ArrayList<>();
      this.tempSceneEvents.addAll(Arrays.asList(this.events));
    } 
    for (MobilusModel.SceneEvent sceneEvent : this.tempSceneEvents) {
      if (sceneEvent.getDeviceId() == paramLong) {
        if (isActionCapable(paramDeviceType)) {
          ArrayList<String> arrayList = valuesForType(paramContext, paramDeviceType);
          if (paramInt >= 0 && paramInt < arrayList.size()) {
            MobilusModel.SceneEvent sceneEvent1 = modifySceneEvent(sceneEvent, arrayList.get(paramInt));
            this.tempSceneEvents.set(this.tempSceneEvents.indexOf(sceneEvent), sceneEvent1);
            return true;
          } 
        } 
        break;
      } 
    } 
    return false;
  }
  
  public void clearTempEvents() {
    if (this.tempSceneEvents != null) {
      this.tempSceneEvents.clear();
      this.tempSceneEvents = null;
    } 
  }
  
  public int compareTo(@NonNull Scene paramScene) {
    int i = getName().compareTo(paramScene.getName());
    if (i != 0)
      return i; 
    i = getId() cmp paramScene.getId();
    return (i < 0) ? -1 : ((i > 0) ? 1 : 0);
  }
  
  public ArrayList<MobilusModel.SceneEvent> exportSceneEvents() {
    if (this.tempSceneEvents == null) {
      this.tempSceneEvents = new ArrayList<>();
      this.tempSceneEvents.addAll(Arrays.asList(this.events));
    } 
    return this.tempSceneEvents;
  }
  
  public ArrayList<MobilusModel.SceneAstralSchedule> extractAstralSchedules() {
    extractSchedule();
    ArrayList<MobilusModel.SceneAstralSchedule> arrayList = new ArrayList();
    for (Integer integer : this.daysOfWeek) {
      if (this.astral > 0) {
        MobilusModel.SceneAstralSchedule.Builder builder = MobilusModel.SceneAstralSchedule.newBuilder();
        builder.setId(this.id);
        builder.setWeekDay(integer.intValue());
        builder.setAstral(this.astral);
        builder.setInterval(this.interval);
        arrayList.add(builder.build());
      } 
    } 
    return arrayList;
  }
  
  public ArrayList<MobilusModel.SceneWeekSchedule> extractWeekSchedules() {
    extractSchedule();
    ArrayList<MobilusModel.SceneWeekSchedule> arrayList = new ArrayList();
    for (Integer integer : this.daysOfWeek) {
      Calendar calendar1 = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      calendar1.set(7, integer.intValue() + 1);
      calendar1.set(11, this.hour);
      calendar1.set(12, this.minute);
      calendar2.setTime(calendar1.getTime());
      int i = calendar2.get(7);
      int j = calendar2.get(11);
      int k = calendar2.get(12);
      MobilusModel.SceneWeekSchedule.Builder builder = MobilusModel.SceneWeekSchedule.newBuilder();
      builder.setId(this.id);
      builder.setWeekDay(i - 1);
      builder.setHour(j * 100 + k);
      arrayList.add(builder.build());
    } 
    return arrayList;
  }
  
  public void forgetSchedule() {
    if (this.daysOfWeek != null) {
      this.daysOfWeek.clear();
      this.daysOfWeek = null;
      this.hour = -1;
      this.minute = -1;
      this.astral = -1;
      this.interval = 0;
    } 
  }
  
  public void generateAstralSchedules() {
    this.weekSchedules = new MobilusModel.SceneWeekSchedule[0];
    this.astralSchedules = extractAstralSchedules().<MobilusModel.SceneAstralSchedule>toArray(this.astralSchedules);
  }
  
  public void generateWeekSchedules() {
    this.astralSchedules = new MobilusModel.SceneAstralSchedule[0];
    this.weekSchedules = extractWeekSchedules().<MobilusModel.SceneWeekSchedule>toArray(this.weekSchedules);
  }
  
  public int getAstral() {
    extractSchedule();
    return this.astral;
  }
  
  public MobilusModel.SceneAstralSchedule[] getAstralSchedules() {
    return this.astralSchedules;
  }
  
  public boolean getDay(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial extractSchedule : ()V
    //   4: aload_0
    //   5: getfield daysOfWeek : Ljava/util/ArrayList;
    //   8: invokevirtual iterator : ()Ljava/util/Iterator;
    //   11: astore_2
    //   12: aload_2
    //   13: invokeinterface hasNext : ()Z
    //   18: ifeq -> 42
    //   21: aload_2
    //   22: invokeinterface next : ()Ljava/lang/Object;
    //   27: checkcast java/lang/Integer
    //   30: invokevirtual intValue : ()I
    //   33: iload_1
    //   34: if_icmpne -> 12
    //   37: iconst_1
    //   38: istore_3
    //   39: goto -> 44
    //   42: iconst_0
    //   43: istore_3
    //   44: iload_3
    //   45: ireturn
  }
  
  public MobilusModel.SceneEvent[] getEvents() {
    return this.events;
  }
  
  public int getHour() {
    extractSchedule();
    return this.hour;
  }
  
  public int getInterval() {
    extractSchedule();
    return this.interval;
  }
  
  public int getMinute() {
    extractSchedule();
    return this.minute;
  }
  
  @RequiresApi(api = 24)
  public Date getNextRun() {
    this.dateList.sort(new Comparator<Date>() {
          public int compare(Date param1Date1, Date param1Date2) {
            return param1Date1.compareTo(param1Date2);
          }
        });
    return (this.dateList.size() > 0) ? this.dateList.get(0) : null;
  }
  
  public MobilusModel.SceneWeekSchedule[] getWeekSchedules() {
    return this.weekSchedules;
  }
  
  public boolean hasDevice(long paramLong) {
    boolean bool2;
    if (this.tempSceneEvents == null) {
      this.tempSceneEvents = new ArrayList<>();
      this.tempSceneEvents.addAll(Arrays.asList(this.events));
    } 
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < this.tempSceneEvents.size()) {
        if (((MobilusModel.SceneEvent)this.tempSceneEvents.get(b)).getDeviceId() == paramLong) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    return bool2;
  }
  
  public boolean isActionCapable(DeviceType paramDeviceType) {
    boolean bool;
    if (paramDeviceType != DeviceType.CGR) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isAstral() {
    return this.isAstralValue;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void refreshDates() {
    this.dateList.clear();
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.getTime();
    int i = calendar.get(1);
    int j = calendar.get(3);
    MobilusModel.SceneWeekSchedule[] arrayOfSceneWeekSchedule = this.weekSchedules;
    byte b = 0;
    if (arrayOfSceneWeekSchedule != null)
      for (byte b1 = 0; b1 < this.weekSchedules.length; b1++) {
        this.isAstralValue = false;
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar1.set(1, i);
        calendar1.set(3, j);
        int k = this.weekSchedules[b1].getWeekDay();
        int m = this.weekSchedules[b1].getHour() / 100;
        int n = this.weekSchedules[b1].getHour();
        calendar1.set(7, k + 1);
        calendar1.set(11, m % 24);
        calendar1.set(12, n % 100 % 60);
        calendar1.set(13, 0);
        if (calendar1.before(calendar))
          calendar1.add(3, 1); 
        this.dateList.add(calendar1.getTime());
      }  
    if (this.astralSchedules != null) {
      SunriseSunsetCalculator sunriseSunsetCalculator = new SunriseSunsetCalculator(new Location(this.location.getLatitude(), this.location.getLongitude()), TimeZone.getTimeZone("UTC"));
      for (byte b1 = b; b1 < this.astralSchedules.length; b1++) {
        Calendar calendar1;
        this.isAstralValue = true;
        MobilusModel.SceneAstralSchedule sceneAstralSchedule = this.astralSchedules[b1];
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar2.set(1, i);
        calendar2.set(3, j);
        calendar2.set(7, sceneAstralSchedule.getWeekDay() + 1);
        arrayOfSceneWeekSchedule = null;
        if (sceneAstralSchedule.getAstral() == 1) {
          calendar1 = sunriseSunsetCalculator.getOfficialSunriseCalendarForDate(calendar2);
        } else if (sceneAstralSchedule.getAstral() == 2) {
          calendar1 = sunriseSunsetCalculator.getOfficialSunsetCalendarForDate(calendar2);
        } 
        calendar1.add(12, sceneAstralSchedule.getInterval());
        Calendar calendar3 = calendar1;
        if (calendar1.before(calendar)) {
          calendar2.add(3, 1);
          if (sceneAstralSchedule.getAstral() == 1) {
            calendar1 = sunriseSunsetCalculator.getOfficialSunriseCalendarForDate(calendar2);
          } else if (sceneAstralSchedule.getAstral() == 2) {
            calendar1 = sunriseSunsetCalculator.getOfficialSunsetCalendarForDate(calendar2);
          } 
          calendar1.add(12, sceneAstralSchedule.getInterval());
          calendar3 = calendar1;
        } 
        this.dateList.add(calendar3.getTime());
      } 
    } 
  }
  
  public void saveTempEvents() {
    if (this.tempSceneEvents != null)
      this.events = this.tempSceneEvents.<MobilusModel.SceneEvent>toArray(this.events); 
  }
  
  public void selectDay(int paramInt, boolean paramBoolean) {
    extractSchedule();
    boolean bool = false;
    byte b = 0;
    while (true) {
      if (b < this.daysOfWeek.size()) {
        if (((Integer)this.daysOfWeek.get(b)).intValue() == paramInt) {
          bool = true;
          break;
        } 
        b++;
        continue;
      } 
      b = -1;
      break;
    } 
    if (paramBoolean && !bool)
      this.daysOfWeek.add(Integer.valueOf(paramInt)); 
    if (!paramBoolean && bool && b != -1)
      this.daysOfWeek.remove(b); 
  }
  
  public void setHourMinute(int paramInt1, int paramInt2) {
    this.hour = paramInt1;
    this.minute = paramInt2;
  }
  
  public void setLocation(Location paramLocation) {
    this.location = paramLocation;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setSunriseWithOffset(int paramInt) {
    this.astral = 1;
    this.interval = paramInt;
  }
  
  public void setSunsetWithOffset(int paramInt) {
    this.astral = 2;
    this.interval = paramInt;
  }
  
  public void updateTempEvents(Context paramContext, List<Device> paramList) {
    if (this.tempSceneEvents == null) {
      this.tempSceneEvents = new ArrayList<>();
      this.tempSceneEvents.addAll(Arrays.asList(this.events));
    } 
    Iterator<MobilusModel.SceneEvent> iterator = this.tempSceneEvents.iterator();
    while (true) {
      boolean bool = iterator.hasNext();
      boolean bool1 = true;
      if (bool) {
        MobilusModel.SceneEvent sceneEvent = iterator.next();
        Iterator<Device> iterator1 = paramList.iterator();
        while (true) {
          if (iterator1.hasNext()) {
            if (((Device)iterator1.next()).getId() == sceneEvent.getDeviceId())
              break; 
            continue;
          } 
          bool1 = false;
          break;
        } 
        if (!bool1)
          this.tempSceneEvents.remove(sceneEvent); 
        continue;
      } 
      for (Device device : paramList) {
        Iterator<MobilusModel.SceneEvent> iterator1 = this.tempSceneEvents.iterator();
        while (true) {
          if (iterator1.hasNext()) {
            if (((MobilusModel.SceneEvent)iterator1.next()).getDeviceId() == device.getId()) {
              bool1 = true;
              break;
            } 
            continue;
          } 
          bool1 = false;
          break;
        } 
        if (!bool1 && isActionCapable(device.getType())) {
          MobilusModel.SceneEvent.Builder builder = MobilusModel.SceneEvent.newBuilder();
          builder.setId(getId());
          builder.setEventNumber(6);
          builder.setDeviceId(device.getId());
          builder.setValue(valuesForType(paramContext, device.getType()).get(0));
          this.tempSceneEvents.add(builder.build());
        } 
      } 
      return;
    } 
  }
  
  public ArrayList<String> valuesForType(Context paramContext, DeviceType paramDeviceType) {
    ArrayList<String> arrayList = new ArrayList();
    if (paramDeviceType == DeviceType.ON_OFF || paramDeviceType == DeviceType.ON_OFF_POTENTIAL_FREE) {
      arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903044)));
      return arrayList;
    } 
    if (paramDeviceType == DeviceType.COSMO || paramDeviceType == DeviceType.CMR) {
      arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903041)));
      return arrayList;
    } 
    if (paramDeviceType == DeviceType.SENSO) {
      arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903041)));
      arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903042)));
    } else {
      if (paramDeviceType == DeviceType.CZR || paramDeviceType == DeviceType.MZR) {
        arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903041)));
        arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903043)));
        return arrayList;
      } 
      if (paramDeviceType == DeviceType.SENSO_Z) {
        arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903041)));
        arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903042)));
        arrayList.addAll(Arrays.asList(paramContext.getResources().getStringArray(2130903043)));
      } 
    } 
    return arrayList;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\Scene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */