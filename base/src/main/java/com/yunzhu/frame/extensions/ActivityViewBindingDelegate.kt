package com.yunzhu.frame.extensions

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 * to use,just call :
 *  private val binding by viewBinding(ActivitySecondBinding::inflate)
 * */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val invoke = bindingInflater.invoke(layoutInflater)
        setContentView(invoke.root) //可选
        invoke
    }

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding:ActivitySecondBinding by viewBinding()
 * with your binding class and access it as you normally would.
 */
inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding() = ActivityViewBindingDelegate(
    T::class.java,
    this
)

class ActivityViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    private val activity: AppCompatActivity
) : ReadOnlyProperty<AppCompatActivity, T> {
    private val clearBindingHandler by lazy(LazyThreadSafetyMode.NONE) { Handler(Looper.getMainLooper()) }
    private var binding: T? = null

    private val bindMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

    init {
        activity.lifecycleScope.launch {
            activity.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    Log.e("LifeCycle_Tag", "onDestroy")
                    clearBindingHandler.post { binding = null }
                }
            })
        }
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (!thisRef.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${thisRef.lifecycle.currentState}!")
        }

        @Suppress("UNCHECKED_CAST")
        binding = bindMethod.invoke(null, thisRef.layoutInflater) as T
        thisRef.setContentView(binding?.root)
        return binding!!
    }

}


