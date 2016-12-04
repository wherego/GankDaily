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

package com.bobomee.android.myapplication.mvp.presenter;

import com.bobomee.android.common.mvp.MvpBasePresenter;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.myapplication.mapper.ReposDataMapper;
import com.bobomee.android.myapplication.model.GankCategoryModel;
import com.bobomee.android.myapplication.mvp.view.ReposListView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class CategoryListPresenter extends MvpBasePresenter<ReposListView> {

    private final Category mGetRepos;
    private final ReposDataMapper mUserModelDataMapper;

    @Inject public CategoryListPresenter(Category getRepos,
                              ReposDataMapper userModelDataMapper) {
        mGetRepos = getRepos;
        mUserModelDataMapper = userModelDataMapper;
    }

    @Override
    public void detachView(boolean retainInstance) {
        mGetRepos.unsubscribe();
        super.detachView(retainInstance);
    }

    /**
     * Initializes the presenter by start retrieving the user
     */
    @Override
    public void initialize() {
        getUserList();
    }

    private void processUserList(GankCategory reposEntity) {
        final List<GankCategoryModel> reposModels = mUserModelDataMapper.transform(reposEntity);
        getView().userList(reposModels);
    }

    private void getUserList() {
        mGetRepos.setParam(DomainConstants.福利, DomainConstants.PAGE_SIZE,
            DomainConstants.FIRST_PAGE);
        mGetRepos.execute(new UserSubscriber());
    }

    private class UserSubscriber extends DefaultSubscriber<GankCategory> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override public void onNext(GankCategory reposEntity) {
            processUserList(reposEntity);
        }
    }
}