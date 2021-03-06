/*
 * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.common.adapter.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.common.adapter.RcvAdapterWrapper;

/**
 * Created on 16/9/22.下午3:54.
 *
 * @author bobomee.
 * @description
 */

public class LayoutUtil {

  /**
   * 设置头和底部的跨列
   */
  public static void setSpanSizeLookup(final RecyclerView.Adapter adapter,
      final GridLayoutManager layoutManager) {
    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        final int type = adapter.getItemViewType(position);
        if (type == RcvAdapterWrapper.TYPE_HEADER || type == RcvAdapterWrapper.TYPE_FOOTER) {
          // 如果是头部和底部，那么就横跨
          return layoutManager.getSpanCount();
        } else {
          // 如果是普通的，那么就保持原样
          //return layoutManager.getSpanSizeLookup().getSpanSize(position - adapter.getHeaderCount());
          return 1;
        }
      }
    });
  }

  public static void setFullSpan(View view, RecyclerView.LayoutManager layoutManager) {
    final int itemHeight = view.getLayoutParams() != null ? view.getLayoutParams().height
        : ViewGroup.LayoutParams.WRAP_CONTENT;

    if (layoutManager instanceof StaggeredGridLayoutManager) {
      StaggeredGridLayoutManager.LayoutParams layoutParams =
          new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
              itemHeight);
      layoutParams.setFullSpan(true);
      view.setLayoutParams(layoutParams);
    } else if (layoutManager instanceof GridLayoutManager) {
      view.setLayoutParams(
          new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight));
    }
  }
}
