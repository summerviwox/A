import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.ApplicationPlugin

/**
 * 自定义插件
 */
class DeleteSDKSOPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.afterEvaluate {
            //只有主工程才运用
            //hasPlugin而不是contains
            ApplicationPlugin
            if(!project.plugins.hasPlugin("com.android.application")){
                return
            }

            def android = project.extensions.findByName("android")
            def variant = android.applicationVariants.find { it.buildType.name == android.buildTypes.getByName("release").name }
            println(variant.buildType.name)
            //def variant = android.applicationVariants.find { it.buildType.name == android.buildTypes.getByName("release").name }

            println("DeleteSDKSOPlugin ################################")

            Task delete = project.tasks.register("DeleteSDKSOTask",DeleteSDKSOTask.class).get()
            //mergeDebugNativeLibs mergeReleaseNativeLibs  gradle Task：收集项目所有的 native 库
            //合并所有依赖的 native 库
            Task merge = project.tasks.findByName("mergeDebugNativeLibs")
            //stripDebugDebugSymbols stripReleaseDebugSymbols
            //从 Native 库中移除 Debug 符号
            //so文件md5码不相同问题
            Task strip = project.tasks.findByName("stripDebugDebugSymbols")
            if (merge != null && strip!=null) {
                //delete.mustRunAfter(merge)
                merge.finalizedBy(delete)
                //delete.dependsOn(merge)
                //移除这个代码会报错 不移除so文件没有删掉
                //Caused by: java.nio.file.NoSuchFileException: E:\A\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a\libijkffmpeg.so
                //at com.android.build.gradle.internal.tasks.StripDebugSymbolsRunnable.run(StripDebugSymbolsTask.kt:251)
                strip.dependsOn(delete)
            }
        }

    }
}