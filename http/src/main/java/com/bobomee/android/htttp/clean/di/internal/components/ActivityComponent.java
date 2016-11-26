/*
 * Copyright (c) 2016. BoBoMEe(wbwjx115@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bobomee.android.htttp.clean.di.internal.components;

import android.app.Activity;
import android.content.Context;
import com.bobomee.android.htttp.clean.di.Dagger2Activity;
import com.bobomee.android.htttp.clean.di.Dagger2Application;
import com.bobomee.android.htttp.clean.di.core.ActivityContext;
import com.bobomee.android.htttp.clean.di.core.PerActivity;
import com.bobomee.android.htttp.clean.di.internal.modules.ActivityModule;
import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();

  @ActivityContext Context activityContext();

  void inject(Dagger2Activity activity);

  class Init {
    private Init() {
    }

    public static <T extends Dagger2Activity> ActivityComponent initialize(Dagger2Activity activity) {
      return DaggerActivityComponent.builder()
          .applicationComponent(Dagger2Application.get(activity).getApplicationComponent())
          .activityModule(new ActivityModule(activity))
          .build();
    }
  }
}
