package com.example.mylibrary.refresh;

import android.support.annotation.IntDef;

import static com.example.mylibrary.refresh.LoadingType.LOAD_FAIL;
import static com.example.mylibrary.refresh.LoadingType.LOAD_ING;
import static com.example.mylibrary.refresh.LoadingType.LOAD_NONE;
import static com.example.mylibrary.refresh.LoadingType.LOAD_START;
import static com.example.mylibrary.refresh.LoadingType.LOAD_SUCCESS;


/**
 * Created by PengFeifei on 17-7-17.
 */
@IntDef({LOAD_START, LOAD_ING, LOAD_FAIL, LOAD_SUCCESS,LOAD_NONE})
public @interface LoadingType {
    int INIT = -1;
    int LOAD_START = 0;
    int LOAD_ING = 1;
    int LOAD_FAIL = 2;
    int LOAD_SUCCESS = 3;
    int LOAD_NONE = 4;
}
