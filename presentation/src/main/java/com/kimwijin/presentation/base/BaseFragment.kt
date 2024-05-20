package com.kimwijin.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * 모든 Fragment의 기본 클래스로 사용되는 추상 클래스 입니다.
 * 이 클래스는 데이터 바인딩을 포함한 기본적인 Fragment 설정을 제공하여,
 * 파생되는 모든 Fragment에서 중복 코드를 줄이고, 공통의 기능을 통합 관리할 수 있도록 합니다.
 *
 * BaseFragment는 ViewDataBinding을 활용해 XML 레이아웃과 연결된 데이터를 자동으로 관리합니다.
 * 이를 통해 UI 컴포넌트의 초기화 및 이벤트 바인딩을 보다 쉽게 처리할 수 있습니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        return binding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        loadData()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initView()

    abstract fun loadData()
}