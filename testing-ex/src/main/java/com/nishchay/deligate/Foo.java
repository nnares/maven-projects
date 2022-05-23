package com.nishchay.deligate;

public abstract class Foo {

  public void bar1(){
    System.out.println("Base::bar1");
  }
  public void bar2(){
    System.out.println("Base::bar2");
  }

  public void bar3(String name){
    System.out.println("Base::bar3" + name);
  }



  public boolean isTraceEnabled() {
    System.out.println("Base::isTraceEnabled");
    return false;
  }

  public void trace(String msg) {
    System.out.println("Base::trace" + msg);
  }

  public void trace(String format, Object arg) {
    System.out.println("Base::trace" + format + arg);
  }

  public void error(String format, Object... arguments) {
    System.out.println("Base::error" + format + arguments);
  }

  public void debug(String msg) {
    System.out.println("Base::debug" + msg);
  }
}