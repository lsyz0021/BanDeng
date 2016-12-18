package com.bandeng.bandeng.persenter;

import com.bandeng.bandeng.view.activity.IView;
import com.bandeng.bandeng.view.activity.RegisterActivity;

/**
 * Created by Lilu on 2016/12/11.
 */

public class RegisterPersenter {
    private RegisterActivity registerActivity;

    public RegisterPersenter(IView view) {
        this.registerActivity = (RegisterActivity) view;
    }


}
