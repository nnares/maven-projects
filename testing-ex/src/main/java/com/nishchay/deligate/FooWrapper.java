package com.nishchay.deligate;

public class FooWrapper extends Foo {

    private final Foo delegate;

    protected FooWrapper(Foo delegate) {
        this.delegate = delegate;
    }

    @Override
    public void bar1() {
        delegate.bar1();
    }

    @Override
    public void bar2() {
        delegate.bar2();
    }

    @Override
    public void bar3(String name) {
        delegate.bar3(name);
    }
}