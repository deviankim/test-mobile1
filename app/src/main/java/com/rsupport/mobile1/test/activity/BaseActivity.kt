package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.rsupport.mobile1.test.activity.viewmodel.BaseViewModel
import com.toy.project.ctudy.view.LoadingDialog

/**
 * Base Activity
 * DataBinding, ViewModel 정의
 */
abstract class BaseActivity<DataBinding : ViewDataBinding, model : BaseViewModel> :
    AppCompatActivity(), LifecycleObserver {

    private var _binding: DataBinding? = null
    val mViewBinding get() = _binding

    abstract val layoutId: Int
    abstract val variable: Int
    abstract val viewModel: model

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<DataBinding>(this, layoutId).apply {
            setVariable(variable, viewModel);
            lifecycleOwner = this@BaseActivity
            _binding = this;
        }

        // 로딩 타입에 따라 로딩 Dialog 노출 여부 결정
        viewModel.startLoadingDialogState.observe(this@BaseActivity, {
            if (it == LoadingDialogType.SHOW) {
                showLoadingDialog()
            } else {
                dismissLoadingDialog()
            }
        })
    }

    /**
     * Loading Dialog Show
     */
    protected fun showLoadingDialog() {
        if (isFinishing) {
            return
        }

        loadingDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        } ?: run {
            loadingDialog = LoadingDialog(this).apply {
                show()
            }
        }
    }

    /**
     * Loading Dialog Dismiss
     */
    protected fun dismissLoadingDialog() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        loadingDialog = null
    }

    override fun onDestroy() {
        super.onDestroy()

        // 액티비티 종료 시 옵저버 리소스를 모두 해제시켜준다.
        viewModel.run {
            viewModel.clearDisposable()
        }
    }
}


enum class LoadingDialogType {
    SHOW,
    DISMISS
}