package org.eclipse.paho.android.service;

enum Status {
  ERROR, NO_RESULT, OK;
  
  static {
    ERROR = new Status("ERROR", 1);
    NO_RESULT = new Status("NO_RESULT", 2);
    $VALUES = new Status[] { OK, ERROR, NO_RESULT };
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\Status.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */