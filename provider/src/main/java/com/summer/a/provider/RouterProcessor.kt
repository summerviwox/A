package com.summer.a.provider

import com.alibaba.android.arouter.facade.annotation.Route
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.MirroredTypeException
import javax.tools.FileObject
import javax.tools.StandardLocation

@AutoService(Processor::class)
class RouterProcessor: AbstractProcessor() {

    var filer:Filer? = null

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        filer = processingEnv?.filer
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        var set = mutableSetOf(Route::class.java.canonicalName)
        return set
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }


    override fun process(mutableSet: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        var list:MutableList<MutableMap<String,String>> = mutableListOf()
        mutableSet?.forEach {
                set->
            var element = roundEnvironment?.getElementsAnnotatedWith(set)
            element?.forEach {
                if(it.kind!= ElementKind.CLASS){
                    return@forEach
                }
                var route:Route = it.getAnnotation(Route::class.java)
                var value: String? = null
                try {
                    value = route.path.toString()
                } catch (e: MirroredTypeException) {
                    //Attempt to access Class object for TypeMirror 获取不到providerAnno.value就trycatch这样处理
                    value = e.typeMirror.toString()
                }
                var typeElement = it as TypeElement
                value?.apply {
                    list.add(mutableMapOf(value to typeElement.qualifiedName.toString()))
                }
            }
        }
        generatorClass(list)
        return true
    }

    fun generatorClass(list:MutableList<MutableMap<String,String>>){

        var file: FileObject? =null
        try {
            //createSourceFile 会创建ProviderManager.kt.java 用createResource
            file = filer?.createResource(StandardLocation.SOURCE_OUTPUT,"","com.summer.a.router.RouterProvider.kt")
            var path = file?.toUri()?.path
            var writer = file?.openWriter()
            println("RouterProvider")
            writer?.apply {
                writer.write("package com.summer.a.router\n")
                writer.write("import kotlin.reflect.KClass\n")
                writer.write("class TestA {\n")
                writer.write("    companion object{\n")
                writer.write("var mutableMap = mutableMapOf<String, KClass<*>>()\n")
                writer.write("init {\n")
                list.forEach {
                    println("##### ${it.keys.first()} ${it.values.first()}")
                    writer.write("mutableMap.put(\"${it.keys.first()}\",${it.values.first()}::class)\n")
                }

                writer.write("}\n")
                writer.write("fun getClass(path:String):KClass<*>?{\n")
                writer.write("return TestA.mutableMap[path]\n")
                writer.write("}\n")
                writer.write("}\n")
                writer.write("}\n")
                writer.flush()
                writer.close()
            }
        } catch (e: Exception) {
            //javax.annotation.processing.FilerException: Attempt to recreate a file for type 重复创建trycatch处理
            e
        }
    }
//    fun generatorClass(list:MutableList<MutableMap<String,String>>){
//
//        var file: FileObject? =null
//        try {
//            //createSourceFile 会创建ProviderManager.kt.java 用createResource
//            file = filer?.createResource(StandardLocation.SOURCE_OUTPUT,"","com.summer.a.router.RouterPath.kt")
//            var path = file?.toUri()?.path
//            var writer = file?.openWriter()
//            println("generatorClass")
//            writer?.apply {
//                writer.write("package com.summer.a.router\n")
//                writer.write("class RouterPath {\n")
//                writer.write("companion object{\n")
//                list.forEach {
//                    println("##### ${it.keys.first()} ${it.values.first()}")
//                    writer.write("val ${it.keys.first().replace("/","_")} =\"${it.keys.first()}\"\n")
//                }
//                writer.write("}\n")
//                writer.write("}\n")
//                writer.flush()
//                writer.close()
//            }
//        } catch (e: Exception) {
//            //javax.annotation.processing.FilerException: Attempt to recreate a file for type 重复创建trycatch处理
//            e
//        }
//    }
}