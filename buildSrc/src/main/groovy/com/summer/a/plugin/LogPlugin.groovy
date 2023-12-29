package com.summer.a.plugin

import com.summer.a.util.LogUtil
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义插件
 */
class LogPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {

        target.extensions.add("LogUtil", LogUtil)
    }
}