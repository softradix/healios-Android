package com.softradix.healios.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * Abstract Activity which binds [ViewBinding] [VB]
 */

abstract class BaseActivity< VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBinding()
    }

    /**
     * It returns [VB] which is assigned to [mViewBinding] and used in [onCreate]
     */
    abstract fun getViewBinding(): VB
}