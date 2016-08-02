// Generated code from Butter Knife. Do not modify!
package com.example.romulo.studioelegancy.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.example.romulo.studioelegancy.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493000, "field '_signupLink'");
    target._signupLink = finder.castView(view, 2131493000, "field '_signupLink'");
  }

  @Override public void unbind(T target) {
    target._signupLink = null;
  }
}
