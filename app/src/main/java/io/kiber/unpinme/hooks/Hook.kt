package io.kiber.unpinme.hooks

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import io.kiber.unpinme.logError

abstract class Hook(private var lpparam: LoadPackageParam) {
    abstract fun hook()

    fun findAndHookMethod(className: String, methodName: String, vararg parameterTypesAndCallback: Any) {
        var parameterTypes = parameterTypesAndCallback.asList()
        var callback: XC_MethodHook? = null
        if (parameterTypes.last() is XC_MethodHook) {
            callback = parameterTypes.last() as XC_MethodHook
            parameterTypes = parameterTypes.slice(IntRange(0, parameterTypes.size - 2))
        }
        try {
            val clazz = XposedHelpers.findClass(className, lpparam.classLoader)
            if (parameterTypes.isEmpty()) {
                var found = false
                clazz.declaredMethods.forEach {
                    if (it.name.equals(methodName)) {
                        found = true
                        XposedBridge.hookMethod(it, callback)
                    }
                }
                if (!found) {
                    val fullMethodName = clazz.name + '#' + methodName + getParametersString(parameterTypes)
                    throw NoSuchMethodError(fullMethodName)
                }
            } else {
                XposedHelpers.findAndHookMethod(
                    className,
                    lpparam.classLoader,
                    methodName,
                    *parameterTypesAndCallback
                )
            }
        } catch (e: Throwable) {
            onError(e, className, methodName, parameterTypes)
        }
    }

    private fun onError(
        e: Throwable,
        className: String,
        methodName: String,
        vararg parameterTypes: Any
    ) {
        var methodSignature = "$className.$methodName("
        val parameters = ArrayList<String>()
        parameterTypes.forEach {
            val name: String = if (it is Class<*>) {
                it.name
            } else {
                it.javaClass.name
            }
            parameters.add(name)
        }
        methodSignature += "${parameters.joinToString(", ")})"
        logError("Cannot hook", "\"$methodSignature\":", e)
    }

    private fun getParametersString(clazzes: List<Any>): String {
        val sb = StringBuilder("(")
        var first = true
        for (clazz in clazzes) {
            if (first) first = false else sb.append(",")
            if (clazz is Class<*>) {
                sb.append(clazz.canonicalName)
            } else {
                sb.append(clazz)
            }
        }
        sb.append(")")
        return sb.toString()
    }
}