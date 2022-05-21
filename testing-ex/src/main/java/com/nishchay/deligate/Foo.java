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

}