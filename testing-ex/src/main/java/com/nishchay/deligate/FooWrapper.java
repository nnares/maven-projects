package com.nishchay.deligate;

public class FooWrapper extends Foo {

    private final Foo delegate;

    private final String prefix;

    protected FooWrapper(Foo delegate, String prefix) {
        this.delegate = delegate;
        this.prefix = prefix;
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

    @Override
    public boolean isTraceEnabled() {
        return delegate.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        delegate.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        delegate.trace(format, arg);
    }

    public void error(String format, Object... arguments) {
        delegate.error(format, arguments);
    }

    public void debug(String msg) {
        delegate.debug(prefix + msg);
    }

    public void debug(String format, Object arg) {
        delegate.debug(format, arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        delegate.debug(format, arg1, arg2);
    }

    public void debug(String format, Object... arguments) {
        delegate.debug(format, arguments);
    }

    public void debug(String msg, Throwable t) {
        delegate.debug(prefix + msg, t);
    }

}